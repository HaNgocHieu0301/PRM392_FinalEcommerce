package Repository;

import android.app.Application;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import DAOs.IProductDAO;
import DAOs.ProductRoomDatabase;
import models.Category;
import models.Product;
import models.ProductTmp;

public class ProductRepository {
    ProductRoomDatabase productRoomDatabase;
    IProductDAO productDAO;
    private List<Product> products;
    public ProductRepository(Application application) {
        productRoomDatabase = ProductRoomDatabase.getDatabase(application);
        productDAO = productRoomDatabase.productDAO();
        products = productDAO.getAll();
    }

    public void insertProducts(Product... products) {
        for (int i = 0; i < products.length; i++) {
            long id = productRoomDatabase.productDAO().insert(products)[i];
        //     Product p = products[i];
        //    p.productId = 0;
        //    FirebaseFirestore.getInstance().collection("products").add(p);
        }
    }
    public long insertProduct(Product product) {
        return productRoomDatabase.productDAO().insert(product)[0];
    }
    public List<Product> getAllProducts() {
        return products;
    }
    
    public List<Product> getAllShowedProducts(){
        List<Product> filteredList = new ArrayList<>();
        for (Product obj : products) {
            if (obj.isShow) {
                filteredList.add(obj);
            }
        }
        return filteredList;
    }

    public List<Product> getAllProductsByCategoryName(Application application, String name) {
        CategoryRepository categoryRepository = new CategoryRepository(application);
        List<Category> categories = categoryRepository.getAllCategories();
        List<Product> res = new ArrayList<>();
        int categoryId = -1;
        for (Category category : categories) {
            if (category.getCategoryName().equals(name)) {
                categoryId = category.getCategoryId();
            }
        }
        if (categoryId != -1) {
            for (Product p : products) {
                if (p.categoryId == categoryId) {
                    res.add(p);
                }
            }
        }
        return res;
    }

    public Product getProductById(int id){
        return productDAO.getProductById(id);
    }

    public void UpdateProduct(Product p){
        productDAO.update(p);
    }

    public void InsertDataFromFirebaseToSqlite(Application application, DataInsertionByFirebaseCallback callback) {
        Task<QuerySnapshot> task = FirebaseFirestore.getInstance()
                .collection("products")
                .get();
        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                productDAO.removeAll();
                List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : lst) {
                    ProductTmp pTmp = d.toObject(ProductTmp.class);
                    Product p = new Product();
                    p.productName = pTmp.productName;
                    p.price = pTmp.price;
                    p.discount = pTmp.discount;
                    p.unitsInStock = pTmp.unitsInStock;
                    p.description = pTmp.description;
                    p.image = pTmp.image;
                    productDAO.insert(p);
                }
                if (callback != null) {
                    callback.onDataInserted();
                }
            }
        });
    }
}

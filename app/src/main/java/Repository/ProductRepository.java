package Repository;

import android.app.Application;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import DAOs.IProductDAO;
import DAOs.ProductRoomDatabase;
import models.Category;
import models.Product;

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
        for (Product p : products) {
            String uniqueId = "";
            do{
                UUID uuid = UUID.randomUUID();
                uniqueId = uuid.toString();
                p.productId = uniqueId;
            }while(!isProductPrimaryKeyValid(uniqueId));
            FirebaseFirestore.getInstance().collection("products").add(p);
        }
        productRoomDatabase.productDAO().insert(products);
    }

    private boolean isProductPrimaryKeyValid(String uniqueId) {
        for (Product p: products) {
            if(p.productId == uniqueId){
                return false;
            }
        }
        return true;
    }

    public List<Product> getAllProducts() {
        return products;
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

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    public void InsertDataFromFirebaseToSqlite(Application application) {
        Task<QuerySnapshot> task = FirebaseFirestore.getInstance()
                .collection("products")
                .get();
        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                productDAO.removeAll();
                List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : lst) {
                    Product p = d.toObject(Product.class);
                    productDAO.insert(p);
                }
            }
        });
    }
}

package Repository;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import DAOs.IProductDAO;
import DAOs.IUserDAO;
import DAOs.ProductRoomDatabase;
import DAOs.UserRoomDatabase;
import models.Category;
import models.Product;
import models.User;

public class ProductRepository {
    ProductRoomDatabase productRoomDatabase;
    IProductDAO productDAO;
    private List<Product> products;
    public ProductRepository(Application application){
        productRoomDatabase = ProductRoomDatabase.getDatabase(application);
        productDAO = productRoomDatabase.productDAO();
        products = productDAO.getAll();
    }

    public void insertProducts(Product...products){
        productRoomDatabase.productDAO().insert(products);
    }
    public List<Product> getAllProducts(){
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

    public Product getProductById(int id){
        return productDAO.getProductById(id);
    }
}

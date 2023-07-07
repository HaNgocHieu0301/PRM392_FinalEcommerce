package Repository;

import android.app.Application;

import java.util.List;

import DAOs.IProductDAO;
import DAOs.IUserDAO;
import DAOs.ProductRoomDatabase;
import DAOs.UserRoomDatabase;
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

    public Product getProductById(int id){
        return productDAO.getProductById(id);
    }
}

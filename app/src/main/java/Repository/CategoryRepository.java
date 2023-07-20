package Repository;

import android.app.Application;

import java.util.List;

import DAOs.CategoryRoomDatabase;
import DAOs.ICategoryDAO;
import DAOs.IProductDAO;
import DAOs.ProductRoomDatabase;
import models.Category;
import models.Product;

public class CategoryRepository {
    CategoryRoomDatabase categoryRoomDatabase;
    ICategoryDAO categoryDAO;
    private List<Category> categories;

    public CategoryRepository(Application application){
        categoryRoomDatabase = CategoryRoomDatabase.getDatabase(application);
        categoryDAO = categoryRoomDatabase.categoryDAO();
        categories = categoryDAO.getAll();
    }

    public void insertCategories(Category...categories){
        categoryRoomDatabase.categoryDAO().insert(categories);
    }

    public List<Category> getAllCategories(){
        return categories;
    }
}

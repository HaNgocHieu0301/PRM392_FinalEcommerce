package Repository;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import DAOs.IWishDAO;
import DAOs.ProductRoomDatabase;
import DAOs.WishRoomDatabase;
import models.Product;
import models.Wish;

public class WishRepository {
    WishRoomDatabase wishRoomDatabase;
    IWishDAO wishDAO;
    Application application;
    public WishRepository(Application application){
        wishRoomDatabase = WishRoomDatabase.getDatabase(application);
        wishDAO = wishRoomDatabase.wishDAO();
        this.application = application;
    }
    public List<Wish> getWishList(int userId) {
        List<Wish> list = wishDAO.getWishList(userId);
        List<Wish> newList = new ArrayList<>();
        ProductRepository repo = new ProductRepository(application);
        for(Wish wish : list)
        {
            Product p = repo.getProductById(wish.productId);
            if(p.isShow)
                newList.add(wish);
        }
        return newList;
    }
    public void insertItem(Wish wish){
        wishDAO.insert(wish);
    }
    public void updateItem(Wish wish){
        wishDAO.update(wish);
    }
    public void deleteItem(Wish wish){
        wishDAO.delete(wish);
    }
    public Wish getWishByUserIdAndProductId(int userId, int productId){
        return wishDAO.getWishByUserIdAndProductId(userId, productId);
    }

    public Wish getWishById(int id) {
        return wishDAO.getWishById(id);
    }
}

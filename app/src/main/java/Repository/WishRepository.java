package Repository;

import android.app.Application;

import java.util.List;

import DAOs.IWishDAO;
import DAOs.WishRoomDatabase;
import models.Wish;

public class WishRepository {
    WishRoomDatabase wishRoomDatabase;
    IWishDAO wishDAO;

    public WishRepository(Application application){
        wishRoomDatabase = WishRoomDatabase.getDatabase(application);
        wishDAO = wishRoomDatabase.wishDAO();
    }
    public List<Wish> getWishList(int userId) { return wishDAO.getWishList(userId);}
    public void insertItem(Wish wish){
        wishDAO.insert(wish);
    }
    public void updateItem(Wish wish){
        wishDAO.update(wish);
    }
    public void deleteItem(Wish wish){
        wishDAO.delete(wish);
    }
}

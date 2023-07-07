package Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import DAOs.IUserDAO;
import DAOs.UserRoomDatabase;
import models.Product;
import models.User;

public class UserRepository {
    UserRoomDatabase userRoomDatabase;
    IUserDAO userDAO;
    private List<User> users;

    public UserRepository(Application application){
        userRoomDatabase = UserRoomDatabase.getDatabase(application);
        userDAO = userRoomDatabase.userDAO();
        users = userDAO.getAll();
    }

    public void insertUser(User...users){
        for(int i = 0; i < users.length; i++){
            long id = userRoomDatabase.userDAO().insert(users)[i];
            User u = users[i];
            u.setUserId((int)id);
            FirebaseFirestore.getInstance()
                    .collection("products")
                    .add(u);
        }
        userRoomDatabase.userDAO().insert(users);
    }
    public List<User> getAllUser(){
        return users;
    }
}

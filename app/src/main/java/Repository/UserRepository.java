package Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import DAOs.IUserDAO;
import DAOs.UserRoomDatabase;
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
        userRoomDatabase.userDAO().insert(users);
    }
    public List<User> getAllUser(){
        return users;
    }
}

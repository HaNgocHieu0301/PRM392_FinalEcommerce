package Repository;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import DAOs.IUserDAO;
import DAOs.UserRoomDatabase;
import models.User;

public class UserRepository {
    UserRoomDatabase userRoomDatabase;
    IUserDAO userDAO;
    private List<User> users;

    public UserRepository(Application application) {
        userRoomDatabase = UserRoomDatabase.getDatabase(application);
        userDAO = userRoomDatabase.userDAO();
        users = userDAO.getAll();
    }

    public void insertUser(User... users) {
        for (int i = 0; i < users.length; i++) {
            long id = userRoomDatabase.userDAO().insert(users)[i];
            User u = users[i];
            u.userId = 0;
            FirebaseFirestore.getInstance().collection("users").add(u);
        }
        //long idxx = userRoomDatabase.userDAO().insert(users)[0];
    }

    public User getUserByUsername(String uname) {
        return userRoomDatabase.userDAO().getUserByUsername(uname);
    }

    public List<User> getAllUser() {
        return users;
    }

    public void InsertDataFromFirebaseToSqlite(Application application) {

        FirebaseFirestore.getInstance().collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                userDAO.removeAll();
                List<DocumentSnapshot> documentList = task.getResult().getDocuments();
                for (DocumentSnapshot document : documentList) {
                    User p = document.toObject(User.class);
                    userDAO.insert(p);
                }
            }
        });
    }

    public User getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }
}

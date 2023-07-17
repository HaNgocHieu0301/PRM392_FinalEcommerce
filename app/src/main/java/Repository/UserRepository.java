package Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.UUID;

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
        for (User u : users) {
            String uniqueId = "";
            do{
                UUID uuid = UUID.randomUUID();
                uniqueId = uuid.toString();
                u.userId = uniqueId;
            }while(!isUserPrimaryKeyValid(uniqueId));
            FirebaseFirestore.getInstance().collection("users").add(u);
        }
        userRoomDatabase.userDAO().insert(users);
    }

    private boolean isUserPrimaryKeyValid(String uniqueId) {
        for (User p: users) {
            if(p.userId == uniqueId){
                return false;
            }
        }
        return true;
    }

    public List<User> getAllUser(){
        return users;
    }
    public void InsertDataFromFirebaseToSqlite(Application application ) {
        Task<QuerySnapshot> task = FirebaseFirestore.getInstance()
                .collection("users")
                .get();
        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                userDAO.removeAll();
                List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : lst) {
                    User p = d.toObject(User.class);
                    userDAO.insert(p);
                }
            }
        });
    }

}

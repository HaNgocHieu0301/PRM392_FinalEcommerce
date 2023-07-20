package Repository;

import android.app.Application;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import DAOs.CategoryRoomDatabase;
import DAOs.ICategoryDAO;
import models.Category;

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
        for (int i = 0; i < categories.length; i++) {
            long id = categoryRoomDatabase.categoryDAO().insert(categories)[i];
            Category p = categories[i];
            p.setCategoryId(0);
            FirebaseFirestore.getInstance().collection("categories").add(p);
        }
    }

    public List<Category> getAllCategories(){
        return categories;
    }

    public void InsertDataFromFirebaseToSqlite(Application application, DataInsertionByFirebaseCallback callback) {
        Task<QuerySnapshot> task = FirebaseFirestore.getInstance()
                .collection("categories")
                .get();
        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                categoryDAO.removeAll();
                List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : lst) {
                    Category p = d.toObject(Category.class);
                    categoryDAO.insert(p);
                }
                // Gọi callback khi quá trình chèn hoàn thành
                if (callback != null) {
                    callback.onDataInserted();
                }
            }
        });
    }
}

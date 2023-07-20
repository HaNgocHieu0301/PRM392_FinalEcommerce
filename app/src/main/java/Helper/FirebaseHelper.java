package Helper;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import models.Product;

public class FirebaseHelper {
    public static void InsertDataFromFirebaseToSqlite(){
        List<Product> products = new ArrayList<>();
        FirebaseFirestore.getInstance()
                .collection("product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<DocumentSnapshot> documentList = task.getResult().getDocuments();

                            for(DocumentSnapshot document: documentList){
                                Product p = document.toObject(Product.class);
                                products.add(p);
                            }
                        }else{

                        }
                    }
                });
    }
}

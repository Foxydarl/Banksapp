package com.example.banksapp.Models;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class FirestoreHelper {
    private FirebaseFirestore db;
    private static final String USERS_COLLECTION = "users";

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    // Метод для получения списка пользователей
    public void getUsers(final FirestoreCallback<List<User>> callback) {
        CollectionReference usersCollection = db.collection(USERS_COLLECTION);
        usersCollection.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<User> userList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                userList.add(user);
                            }
                            callback.onSuccess(userList);
                        } else {
                            Log.w("FirestoreHelper", "Error getting users.", task.getException());
                            callback.onFailure(task.getException());
                        }
                    }
                });
    }

    // Интерфейс для обратного вызова
    public interface FirestoreCallback<T> {
        void onSuccess(T result);
        void onFailure(Exception e);
    }
}

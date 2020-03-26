package com.example.recyclerviewdemo3.storage;

import androidx.annotation.NonNull;

import com.example.recyclerviewdemo3.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NoteStorage {

    public static List<String> list = new ArrayList<>();
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static MainActivity mainActivity;
    private static DocumentSnapshot lastVisible;
    private static int LIMIT = 5;
    private final static String path = "notes";


    public static void init(MainActivity activity) {
        mainActivity = activity;
        getInitialNotes();
    }

    private static void getInitialNotes() {
        list.clear(); // Because this is asking for the "fresh" list.
        addOneTimeQuery(db.collection(path)
        .orderBy("head")
        .limit(LIMIT));
    }

    private static void addOneTimeQuery (Query query) {
        // Will only fetch data ONCE.
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (DocumentSnapshot snap: task.getResult()) {
                        list.add(snap.get("head").toString());
                    }
                    // Notify the adapter.
                    mainActivity.notifyAdapter();
                    // Get last Documents we went through.
                    if(task.getResult().size() > 0) {
                        lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);
                    }
                    if(task.getResult().size() < LIMIT) {
                        mainActivity.setIsLastItemReached(true);
                    }
                }
            }
        });
    }

}

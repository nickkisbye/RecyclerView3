package com.example.recyclerviewdemo3.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewdemo3.R;
import com.example.recyclerviewdemo3.storage.NoteStorage;
import com.example.recyclerviewdemo3.view.MyViewHolder;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private NoteStorage noteStorage;
    private static final int WITH_IMAGE = 1;
    private static final int TEXT_ONLY = 2;

    public MyAdapter(NoteStorage noteStorage) {
        this.noteStorage = noteStorage;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll = null;
        if (viewType == TEXT_ONLY) {
            ll = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.mylayout, parent, false);
        } else if (viewType == WITH_IMAGE ) {
            ll = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.mylayout, parent, false);
        }
        return new MyViewHolder(ll); // Pass the LinearLayout as constructor arg. to MyViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // holder.setData("Dummy data" + position); // Start with dummy data.
        holder.setData(noteStorage.list.get(position));
    }

    @Override
    public int getItemCount() {
        //return 5; // Start with 5 dummy data rows.
        return noteStorage.list.size();
    }

    @Override // Allow us to specify what type of content is at a given position
    public int getItemViewType(int position) {
        // Note note = storage.get(position)
        // note.hasImage
        if (position % 2 == 0) {
            return TEXT_ONLY;
        } else {
            return WITH_IMAGE;
        }
    }

}

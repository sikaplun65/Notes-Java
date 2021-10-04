package com.example.projectthree;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectthree.domain.NoteEntity;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<NoteEntity> list = new ArrayList<>();
    private onItemClickListener clickListener = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<NoteEntity> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(parent,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        holder.bind(getItem(position));
    }

    private NoteEntity getItem(int position){
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(onItemClickListener listener ){
        clickListener = listener;
    }

    interface onItemClickListener{
        void OnItemClick(NoteEntity item);
    }
}

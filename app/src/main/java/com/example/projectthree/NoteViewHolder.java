package com.example.projectthree;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectthree.domain.NoteEntity;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private final TextView dateTextView = itemView.findViewById(R.id.date_text_view);
    private final TextView titleTextView = itemView.findViewById(R.id.title_text_view);
    private final TextView detailTextView = itemView.findViewById(R.id.detail_text_view);
    private NoteEntity note;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public NoteViewHolder(@NonNull ViewGroup parent, NoteAdapter.onItemClickListener clickListener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
        itemView.setOnClickListener(v -> clickListener.OnItemClick(note));
    }

    public void bind(NoteEntity note) {
        this.note = note;
        dateTextView.setText(note.getDate());
        titleTextView.setText(note.getTitle());
        detailTextView.setText(note.getDetail());
    }
}

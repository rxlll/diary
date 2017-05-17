package com.example.liza.superdiary.ui.list.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liza.superdiary.R;
import com.example.liza.superdiary.database.models.Note;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

public class RecyclerNotesAdapter extends RecyclerView.Adapter<RecyclerNotesAdapter.RecyclerViewHolder> {
    private List<Note> notes;
    private OnNoteClickListener onNoteClickListener;
    private OnDeleteClickListener onDeleteClickListener;

    public RecyclerNotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    public RecyclerNotesAdapter setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
        return this;
    }

    public RecyclerNotesAdapter setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
        return this;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerNotesAdapter.RecyclerViewHolder holder, int position) {
        holder.textViewPreview.setText(notes.get(position).getText());
        holder.itemView.setOnClickListener(view ->
                onNoteClickListener.onNoteClick(notes.get(position)));
        holder.imageViewDelete.setOnClickListener(view ->
                onDeleteClickListener.onDeleteClick(notes.get(position), position));
    }

    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Note note, int position);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDelete;
        TextView textViewPreview;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            imageViewDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
            textViewPreview = (TextView) itemView.findViewById(R.id.textViewPreview);
        }
    }
}

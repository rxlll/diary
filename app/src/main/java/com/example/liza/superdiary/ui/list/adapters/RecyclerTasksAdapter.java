package com.example.liza.superdiary.ui.list.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liza.superdiary.R;
import com.example.liza.superdiary.database.models.Task;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

public class RecyclerTasksAdapter extends RecyclerView.Adapter<RecyclerTasksAdapter.RecyclerViewHolder> {
    private List<Task> tasks;
    private OnTaskClickListener onTaskClickListener;
    private OnDeleteClickListener onDeleteClickListener;

    public RecyclerTasksAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public RecyclerTasksAdapter setOnTaskClickListener(OnTaskClickListener onTaskClickListener) {
        this.onTaskClickListener = onTaskClickListener;
        return this;
    }

    public RecyclerTasksAdapter setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
        return this;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerTasksAdapter.RecyclerViewHolder holder, int position) {
        holder.textViewPreview.setText(tasks.get(position).getText());
        holder.itemView.setOnClickListener(view ->
                onTaskClickListener.onTaskClick(tasks.get(position)));
        holder.imageViewDelete.setOnClickListener(view ->
                onDeleteClickListener.onDeleteClick(tasks.get(position), position));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public interface OnTaskClickListener {
        void onTaskClick(Task note);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Task note, int position);
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

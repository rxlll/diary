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

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.RecyclerViewHolder> {
    private List<Task> tasks;
    private OnTaskClickListener onTaskClickListener;
    private OnDeleteClickListener onDeleteClickListener;

    public TasksRecyclerAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TasksRecyclerAdapter setOnTaskClickListener(OnTaskClickListener onTaskClickListener) {
        this.onTaskClickListener = onTaskClickListener;
        return this;
    }

    public TasksRecyclerAdapter setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
        return this;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(TasksRecyclerAdapter.RecyclerViewHolder holder, int position) {
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

    public void addTask(Task task) {
        tasks.add(0, task);
        notifyItemInserted(0);
    }

    public void deleteFromRecycler(Task task, int position) {
        tasks.remove(task);
        notifyItemRemoved(position);
    }

    public interface OnTaskClickListener {
        void onTaskClick(Task note);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Task task, int position);
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

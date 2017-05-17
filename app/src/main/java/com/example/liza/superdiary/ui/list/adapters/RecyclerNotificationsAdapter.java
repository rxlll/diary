package com.example.liza.superdiary.ui.list.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liza.superdiary.R;
import com.example.liza.superdiary.database.models.Notification;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

public class RecyclerNotificationsAdapter extends RecyclerView.Adapter<RecyclerNotificationsAdapter.RecyclerViewHolder> {
    private List<Notification> notifications;
    private OnNotificationClickListener onNotificationClickListener;
    private OnDeleteClickListener onDeleteClickListener;

    public RecyclerNotificationsAdapter(List<Notification> Notifications) {
        this.notifications = Notifications;
    }

    public RecyclerNotificationsAdapter setOnNotificationClickListener(OnNotificationClickListener onNotificationClickListener) {
        this.onNotificationClickListener = onNotificationClickListener;
        return this;
    }

    public RecyclerNotificationsAdapter setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
        return this;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerNotificationsAdapter.RecyclerViewHolder holder, int position) {
        holder.textViewPreview.setText(notifications.get(position).getText());
        holder.textViewTime.setText(notifications.get(position).getTime());
        holder.itemView.setOnClickListener(view ->
                onNotificationClickListener.onNotificationClick(notifications.get(position)));
        holder.imageViewDelete.setOnClickListener(view ->
                onDeleteClickListener.onDeleteClick(notifications.get(position), position));
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public interface OnNotificationClickListener {
        void onNotificationClick(Notification Notification);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Notification Notification, int position);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDelete;
        TextView textViewPreview;
        TextView textViewTime;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            imageViewDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
            textViewPreview = (TextView) itemView.findViewById(R.id.textViewPreview);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
        }
    }
}

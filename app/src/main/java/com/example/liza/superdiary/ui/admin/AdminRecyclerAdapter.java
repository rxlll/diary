package com.example.liza.superdiary.ui.admin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liza.superdiary.R;
import com.example.liza.superdiary.database.models.User;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

public class AdminRecyclerAdapter extends RecyclerView.Adapter<AdminRecyclerAdapter.RecyclerViewHolder> {
    private List<User> users;
    private OnConfirmClickListener onConfirmClickListener;

    public AdminRecyclerAdapter(List<User> users) {
        this.users = users;
    }

    public AdminRecyclerAdapter setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
        return this;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(AdminRecyclerAdapter.RecyclerViewHolder holder, int position) {
        holder.textViewLogin.setText(users.get(position).getLogin());
        holder.imageViewConfirm.setImageResource(users.get(position).getIsConfirmed() ?
                R.drawable.confirm_active : R.drawable.confirm);
        holder.imageViewConfirm.setOnClickListener(view ->
                onConfirmClickListener.onConfirmClick(users.get(position), position));
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public interface OnConfirmClickListener {
        void onConfirmClick(User user, int position);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewConfirm;
        TextView textViewLogin;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            imageViewConfirm = (ImageView) itemView.findViewById(R.id.imageViewConfirm);
            textViewLogin = (TextView) itemView.findViewById(R.id.textViewLogin);
        }
    }
}

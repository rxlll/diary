package com.example.liza.superdiary.ui.recycler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;
import com.example.liza.superdiary.ui.main.MoxyController;
import com.example.liza.superdiary.ui.user.BundleBuilder;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

public class RecyclerController extends MoxyController implements RecyclerView {

    @InjectPresenter
    public RecyclerPresenter recyclerPresenter;

    public static final String KEY_TYPE = "UserController.type";
    public static final int NOTES = 0;
    public static final int NOTIFICATIONS = 1;
    public static final int TASKS = 2;


    public RecyclerController(int type) {
        this(new BundleBuilder(new Bundle())
                .putInt(KEY_TYPE, type)
                .build());
    }

    public RecyclerController(Bundle args) {
        super(args);
    }


    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_recycler, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        recyclerPresenter.onType(getArgs().getInt(KEY_TYPE));
    }

    @Override
    public void showRecyclerNotes(List<Note> notes) {

    }

    @Override
    public void showRecyclerNotifications(List<Notification> notifications) {

    }

    @Override
    public void showRecyclerTasks(List<Task> tasks) {

    }
}

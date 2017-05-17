package com.example.liza.superdiary.ui.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;
import com.example.liza.superdiary.ui.details.DetailsController;
import com.example.liza.superdiary.ui.list.adapters.RecyclerNotesAdapter;
import com.example.liza.superdiary.ui.main.BundleBuilder;
import com.example.liza.superdiary.ui.main.MoxyController;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

public class ListController extends MoxyController implements ListView {

    @InjectPresenter
    public ListPresenter listPresenter;

    public static final String KEY_TYPE = "UserController.type";
    public static final int NOTES = 0;
    public static final int NOTIFICATIONS = 1;
    public static final int TASKS = 2;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;

    public ListController(int type) {
        this(new BundleBuilder(new Bundle())
                .putInt(KEY_TYPE, type)
                .build());
    }

    public ListController(Bundle args) {
        super(args);
    }


    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_recycler, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        int type = getArgs().getInt(KEY_TYPE);
        listPresenter.onType(type);
        view.findViewById(R.id.fab).setOnClickListener(view1 ->
                getRouter().pushController(RouterTransaction.with(new DetailsController(type))
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler())));
    }

    @Override
    public void showRecyclerNotes(List<Note> notes) {
        recyclerAdapter = new RecyclerNotesAdapter(notes);
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setHasFixedSize(true);
        ((RecyclerNotesAdapter)recyclerAdapter).setOnNoteClickListener(note ->
                getRouter().pushController(RouterTransaction.with(new DetailsController(note))
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler())));
        ((RecyclerNotesAdapter) recyclerAdapter).setOnDeleteClickListener((note, position) -> {
            ((RecyclerNotesAdapter) recyclerAdapter).getNotes().remove(note);
            recyclerAdapter.notifyDataSetChanged();
            listPresenter.deleteFromDatabase(note);
        });

    }


    @Override
    public void showRecyclerNotifications(List<Notification> notifications) {

    }

    @Override
    public void showRecyclerTasks(List<Task> tasks) {

    }
}

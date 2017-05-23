package com.example.liza.superdiary.ui.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;
import com.example.liza.superdiary.ui.list.ListController;
import com.example.liza.superdiary.ui.main.BundleBuilder;
import com.example.liza.superdiary.ui.main.MoxyController;

import static com.example.liza.superdiary.ui.list.ListController.KEY_TYPE;
import static com.example.liza.superdiary.ui.list.ListController.LIST_CONTROLLER;
import static com.example.liza.superdiary.ui.list.ListController.NOTES;
import static com.example.liza.superdiary.ui.list.ListController.NOTIFICATIONS;
import static com.example.liza.superdiary.ui.list.ListController.TASKS;

/**
 * Created by User on 17.05.2017.
 */

public class DetailsController extends MoxyController implements DetailsView {

    @InjectPresenter
    public DetailsPresenter detailsPresenter;

    public static final String KEY_OBJECT = "DetailsController.object";

    public DetailsController(int type) {
        this(new BundleBuilder(new Bundle())
                .putInt(KEY_TYPE, type)
                .build());
    }

    public DetailsController(Note note) {
        this(new BundleBuilder(new Bundle())
                .putParcelable(KEY_OBJECT, note)
                .build());
    }

    public DetailsController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_details, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        View buttonSave = view.findViewById(R.id.buttonSave);
        EditText editTextDetailed = (EditText) view.findViewById(R.id.editTextDetailed);
        EditText editTextTime = (EditText) view.findViewById(R.id.editTextTime);
        switch (getArgs().getInt(KEY_TYPE)) {
            case NOTES:
                buttonSave.setOnClickListener(v -> detailsPresenter.saveNote(
                        editTextDetailed.getText().toString()
                ));
                break;
            case TASKS:
                buttonSave.setOnClickListener(v -> detailsPresenter.saveTask(
                        editTextDetailed.getText().toString()
                ));
                break;
            case NOTIFICATIONS:
                buttonSave.setOnClickListener(v -> detailsPresenter.saveNotification(
                        editTextDetailed.getText().toString(),
                        editTextTime.getText().toString()
                ));
                view.findViewById(R.id.editTextTime).setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void showListWithNotification(Notification notification) {
        ((ListController) getRouter()
                .getControllerWithTag(LIST_CONTROLLER))
                .showAddedNotification(notification);
        handleBack();
    }

    @Override
    public void showListWithNote(Note note) {
        ((ListController) getRouter()
                .getControllerWithTag(LIST_CONTROLLER))
                .showAddedNote(note);
        handleBack();
    }

    @Override
    public void showListWithTask(Task task) {
        ((ListController) getRouter()
                .getControllerWithTag(LIST_CONTROLLER))
                .showAddedTask(task);
        handleBack();
    }
}

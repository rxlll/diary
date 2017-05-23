package com.example.liza.superdiary.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

@SuppressWarnings("ConstantConditions")
public class DetailsController extends MoxyController implements DetailsView {

    @InjectPresenter
    DetailsPresenter detailsPresenter;

    private static final String KEY_OBJECT = "DetailsController.object";
    private EditText editTextDetailed;
    public DetailsController(int type) {
        this(new BundleBuilder(new Bundle())
                .putInt(KEY_TYPE, type)
                .build());
    }

    public DetailsController(Bundle args) {
        super(args);
    }

    public DetailsController(Note note) {
        this(new BundleBuilder(new Bundle())
                .putParcelable(KEY_OBJECT, note)
                .build());
    }

    public DetailsController(Notification notification) {
        this(new BundleBuilder(new Bundle())
                .putParcelable(KEY_OBJECT, notification)
                .build());
    }

    public DetailsController(Task task) {
        this(new BundleBuilder(new Bundle())
                .putParcelable(KEY_OBJECT, task)
                .build());
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_details, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        View buttonSave = view.findViewById(R.id.buttonSave);
        editTextDetailed = (EditText) view.findViewById(R.id.editTextDetailed);
        EditText editTextTime = (EditText) view.findViewById(R.id.editTextTime);

        Parcelable parcelable = getArgs().getParcelable(KEY_OBJECT);
        if (parcelable instanceof Note) {
            editTextDetailed.setText(((Note)parcelable).getText());
            buttonSave.setOnClickListener(v -> detailsPresenter.updateNote(
                    (Note)parcelable,
                    editTextDetailed.getText().toString()
            ));
        }
        if (parcelable instanceof Notification) {
            editTextDetailed.setText(((Notification)parcelable).getText());
            editTextTime.setText(((Notification)parcelable).getTime());
            buttonSave.setOnClickListener(v -> detailsPresenter.updateNotification(
                    (Notification)parcelable,
                    editTextDetailed.getText().toString(),
                    editTextTime.getText().toString()
            ));
        }
        if (parcelable instanceof Task) {
            editTextDetailed.setText(((Task)parcelable).getText());
            buttonSave.setOnClickListener(v -> detailsPresenter.updateTask(
                    (Task)parcelable,
                    editTextDetailed.getText().toString()
            ));
        }

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
                view.findViewById(R.id.editTextTime).setVisibility(View.VISIBLE);
                buttonSave.setOnClickListener(v -> detailsPresenter.saveNotification(
                        editTextDetailed.getText().toString(),
                        editTextTime.getText().toString()
                ));
                break;
        }
    }

    @Override
    public void showListController() {
        ((ListController) getRouter()
                .getControllerWithTag(LIST_CONTROLLER))
                .showUpdated();
        getRouter().popController(this);
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        hideKeyboard();
    }

    private void hideKeyboard() {
        editTextDetailed.setInputType(0);
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextDetailed.getWindowToken(), 0);
    }
}

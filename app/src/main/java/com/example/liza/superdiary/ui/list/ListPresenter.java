package com.example.liza.superdiary.ui.list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.preferences.PreferencesRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.liza.superdiary.ui.list.ListController.NOTES;
import static com.example.liza.superdiary.ui.list.ListController.NOTIFICATIONS;
import static com.example.liza.superdiary.ui.list.ListController.TASKS;

/**
 * Created by User on 17.05.2017.
 */

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {

    @Inject
    DatabaseRepo databaseRepo;

    @Inject
    PreferencesRepo preferencesRepo;

    public ListPresenter() {
        App.appComponent.inject(this);
    }

    public void onType(int type) {
        preferencesRepo.getCurrentLogin()
                .flatMap(s -> databaseRepo.getUser(s))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    switch (type) {
                        case NOTES:
                            getViewState().showRecyclerNotes(user.getNotes());
                            break;
                        case NOTIFICATIONS:
                            getViewState().showRecyclerNotifications(user.getNotifications());
                            break;
                        case TASKS:
                            getViewState().showRecyclerTasks(user.getTasks());
                            break;
                    }
                });

    }

    public void deleteFromDatabase(Note note) {

    }
}

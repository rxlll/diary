package com.example.liza.superdiary.ui.list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;
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

    void onType(int type) {
        preferencesRepo.getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
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

    void deleteFromDatabase(Note note, int position) {
        preferencesRepo.getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.deleteNote(user, note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showDeleted(position));
    }

    void deleteFromDatabase(Notification notification, int position) {
        preferencesRepo.getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.deleteNotification(user, notification))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showDeleted(position));
    }

    void deleteFromDatabase(Task task, int position) {
        preferencesRepo.getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.deleteTask(user, task))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showDeleted(position));
    }
}

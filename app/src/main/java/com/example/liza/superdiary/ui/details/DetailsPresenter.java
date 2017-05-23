package com.example.liza.superdiary.ui.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;
import com.example.liza.superdiary.preferences.PreferencesRepo;

import javax.inject.Inject;

/**
 * Created by User on 17.05.2017.
 */

@InjectViewState
public class DetailsPresenter extends MvpPresenter<DetailsView> {

    @Inject
    DatabaseRepo databaseRepo;

    @Inject
    PreferencesRepo preferencesRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.appComponent.inject(this);
    }

    void saveNotification(String text, String date) {
        preferencesRepo
                .getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.addNotification(user, new Notification(text, date)))
                .doOnComplete(() -> getViewState().showListController())
                .subscribe();
    }

    void saveNote(String text) {
        preferencesRepo
                .getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.addNote(user, new Note(text)))
                .doOnComplete(() -> getViewState().showListController())
                .subscribe();
    }

    void saveTask(String text) {
        preferencesRepo
                .getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.addTask(user, new Task(text)))
                .doOnComplete(() -> getViewState().showListController())
                .subscribe();
    }

    public void updateNote(Note note, String text) {
        note.setText(text);
        preferencesRepo
                .getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.updateNote(user, note))
                .doOnComplete(() -> getViewState().showListController())
                .subscribe();
    }

    public void updateNotification(Notification notification, String text, String time) {
        notification.setText(text);
        notification.setTime(time);
        preferencesRepo
                .getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.updateNotification(user, notification))
                .doOnComplete(() -> getViewState().showListController())
                .subscribe();
    }

    public void updateTask(Task task, String text) {
        task.setText(text);
        preferencesRepo
                .getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.updateTask(user, task))
                .doOnComplete(() -> getViewState().showListController())
                .subscribe();
    }
}

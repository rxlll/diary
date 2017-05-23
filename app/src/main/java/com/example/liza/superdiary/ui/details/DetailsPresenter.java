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

    public void saveNotification(String text, String date) {
        preferencesRepo
                .getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.addNotification(user, new Notification(text, date)))
                .doOnComplete(() -> getViewState().showListWithNotification(new Notification(text, date)))
                .subscribe();
    }

    public void saveNote(String text) {
        preferencesRepo
                .getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.addNote(user, new Note(text)))
                .doOnComplete(() -> getViewState().showListWithNote(new Note(text)))
                .subscribe();
    }

    public void saveTask(String text) {
        preferencesRepo
                .getCurrentLogin()
                .flatMap(login -> databaseRepo.getUser(login))
                .flatMapCompletable(user -> databaseRepo.addTask(user, new Task(text)))
                .doOnComplete(() -> getViewState().showListWithTask(new Task(text)))
                .subscribe();
    }
}

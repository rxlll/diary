package com.example.liza.superdiary.ui.recycler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.database.models.User;
import com.example.liza.superdiary.preferences.PreferencesRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.liza.superdiary.ui.recycler.RecyclerController.NOTES;
import static com.example.liza.superdiary.ui.recycler.RecyclerController.NOTIFICATIONS;
import static com.example.liza.superdiary.ui.recycler.RecyclerController.TASKS;

/**
 * Created by User on 17.05.2017.
 */

@InjectViewState
public class RecyclerPresenter extends MvpPresenter<RecyclerView> {

    @Inject
    DatabaseRepo databaseRepo;

    @Inject
    PreferencesRepo preferencesRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
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
}

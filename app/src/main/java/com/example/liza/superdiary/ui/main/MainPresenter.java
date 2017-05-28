package com.example.liza.superdiary.ui.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.database.models.User;
import com.example.liza.superdiary.preferences.PreferencesRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 15.05.2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public static final String ADMIN = "admin";
    public static final String EMPTY = "";

    @Inject
    PreferencesRepo preferencesRepo;

    @Inject
    DatabaseRepo databaseRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.appComponent.inject(this);
        preferencesRepo.getCurrentLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLaunch);
        putAdminIfNeeded();
    }

    private void putAdminIfNeeded() {
        databaseRepo.addUser(new User("admin", "admin", "", "", "", "", ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, Throwable::printStackTrace);
    }

    private void onLaunch(String currentLogin) {
        switch (currentLogin) {
            case ADMIN:
                getViewState().showAdminController();
                break;
            case EMPTY:
                getViewState().showStartController();
                break;
            default:
                getViewState().showUserController();
        }
    }
}

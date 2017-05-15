package com.example.liza.superdiary.ui.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
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

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.appComponent.inject(this);
        preferencesRepo.getCurrentLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((currentLogin) -> onLogin(currentLogin));
    }

    private void onLogin(String currentLogin) {
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

package com.example.liza.superdiary.ui.user;

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
public class UserPresenter extends MvpPresenter<UserView> {

    @Inject
    PreferencesRepo preferencesRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.appComponent.inject(this);
    }

    public void logout() {
        preferencesRepo.clear()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().showStartController());
    }
}

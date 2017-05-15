package com.example.liza.superdiary.ui.user;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;

import javax.inject.Inject;

/**
 * Created by User on 15.05.2017.
 */

@InjectViewState
public class UserPresenter extends MvpPresenter<UserView> {

    @Inject
    DatabaseRepo databaseRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.appComponent.inject(this);
    }
}

package com.example.liza.superdiary.ui.admin;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;

import javax.inject.Inject;

/**
 * Created by User on 15.05.2017.
 */

@InjectViewState
public class AdminPresenter extends MvpPresenter<AdminView> {

    @Inject
    DatabaseRepo databaseRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.appComponent.inject(this);
    }
}

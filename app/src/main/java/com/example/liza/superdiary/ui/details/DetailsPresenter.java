package com.example.liza.superdiary.ui.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;

import javax.inject.Inject;

/**
 * Created by User on 17.05.2017.
 */

@InjectViewState
public class DetailsPresenter extends MvpPresenter<DetailsView> {

    @Inject
    DatabaseRepo databaseRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.appComponent.inject(this);
    }
}

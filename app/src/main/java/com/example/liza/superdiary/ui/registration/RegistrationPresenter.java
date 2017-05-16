package com.example.liza.superdiary.ui.registration;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.database.models.User;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 15.05.2017.
 */

@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {

    @Inject
    DatabaseRepo databaseRepo;

    public RegistrationPresenter() {
        App.appComponent.inject(this);
    }

    void register(String login, String password, String name, String patronymic,
                  String lastname, String email, String birthday) {
        databaseRepo.addUser(new User(login, password, name, lastname, patronymic, email, birthday))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> getViewState().showToast("Ожидайте подтверждения администрации."))
                .doOnError(throwable -> getViewState().showToast(throwable.getMessage()))
                .onErrorComplete()
                .subscribe(() -> getViewState().showStartController());

    }
}

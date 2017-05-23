package com.example.liza.superdiary.ui.login;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.preferences.PreferencesRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 15.05.2017.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Inject
    DatabaseRepo databaseRepo;

    @Inject
    PreferencesRepo preferencesRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.appComponent.inject(this);
    }

    public void login(String login, String password) {
        databaseRepo.contains(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(user -> {
                    if (user.getLogin() == null || user.getLogin().isEmpty()) {
                        getViewState().showToast("Логин с таким паролем не найден.");
                        return false;
                    }
                    if (user.getIsConfirmed() == null || !user.getIsConfirmed()) {
                        getViewState().showToast("Ваш аккаунт еще не подтвержден.");
                        return false;
                    }
                    return true;
                })
                .doOnEvent((user, throwable) -> preferencesRepo.putCurrentLogin(login).subscribe())
                .subscribe(user -> getViewState().showAuthorizedController(login));
    }
}

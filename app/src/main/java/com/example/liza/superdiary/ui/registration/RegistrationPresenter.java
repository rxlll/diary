package com.example.liza.superdiary.ui.registration;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by User on 15.05.2017.
 */

@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {

    void onButtonRegisterClick() {
        getViewState().showUserController();
    }
}

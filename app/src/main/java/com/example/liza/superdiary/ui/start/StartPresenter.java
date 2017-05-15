package com.example.liza.superdiary.ui.start;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by User on 15.05.2017.
 */

@InjectViewState
public class StartPresenter extends MvpPresenter<StartView> {

    void onButtonLoginClick() {
        getViewState().showLoginController();
    }

    void onButtonRegistrationClick() {
        getViewState().showRegistrationController();
    }
}

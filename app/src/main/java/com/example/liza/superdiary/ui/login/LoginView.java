package com.example.liza.superdiary.ui.login;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by User on 15.05.2017.
 */

@StateStrategyType(SkipStrategy.class)
public interface LoginView extends MvpView {
    void showUserController();


    void showToast(String s);
}

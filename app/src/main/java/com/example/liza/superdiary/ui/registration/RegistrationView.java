package com.example.liza.superdiary.ui.registration;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by User on 15.05.2017.
 */

@StateStrategyType(SkipStrategy.class)
public interface RegistrationView extends MvpView {
    void showUserController();
}

package com.example.liza.superdiary.ui.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by User on 15.05.2017.
 */

@StateStrategyType(SkipStrategy.class)
public interface MainView extends MvpView {
    void showStartController();

    void showUserController();

    void showAdminController();
}

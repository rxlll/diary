package com.example.liza.superdiary.ui.admin;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.liza.superdiary.database.models.User;

import java.util.List;

/**
 * Created by User on 15.05.2017.
 */

@StateStrategyType(SkipStrategy.class)
public interface AdminView extends MvpView {
    void showStartController();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showRecycler(List<User> users);

    void showConfirmed();
}

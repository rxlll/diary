package com.example.liza.superdiary.ui.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by User on 17.05.2017.
 */

@StateStrategyType(SkipStrategy.class)
public interface DetailsView extends MvpView {
    void showListController();
}

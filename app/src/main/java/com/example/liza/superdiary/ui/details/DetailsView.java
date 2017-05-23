package com.example.liza.superdiary.ui.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;

/**
 * Created by User on 17.05.2017.
 */

@StateStrategyType(SkipStrategy.class)
public interface DetailsView extends MvpView {
    void showListWithNotification(Notification notification);

    void showListWithNote(Note note);

    void showListWithTask(Task task);
}

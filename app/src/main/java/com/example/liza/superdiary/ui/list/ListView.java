package com.example.liza.superdiary.ui.list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

@StateStrategyType(SkipStrategy.class)
public interface ListView extends MvpView {
    void showRecyclerNotes(List<Note> notes);

    void showRecyclerNotifications(List<Notification> notifications);

    void showRecyclerTasks(List<Task> tasks);

    void showUpdated();

    void showDeleted(int position);
}

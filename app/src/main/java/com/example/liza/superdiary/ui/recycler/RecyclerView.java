package com.example.liza.superdiary.ui.recycler;

import com.arellomobile.mvp.MvpView;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

public interface RecyclerView extends MvpView {
    void showRecyclerNotes(List<Note> notes);

    void showRecyclerNotifications(List<Notification> notifications);

    void showRecyclerTasks(List<Task> tasks);
}

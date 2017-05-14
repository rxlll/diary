package com.example.liza.superdiary.database;

import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;
import com.example.liza.superdiary.database.models.User;

/**
 * Created by User on 14.05.2017.
 */

public interface DatabaseRepo {
    void addUser(User user);

    void deleteUser(User user);

    void setConfirmed(User user);

    void addNote(User user, Note note);

    void deleteNote(User user, Note note);

    void addNotification(User user, Notification notification);

    void deleteNotification(User user, Notification notification);

    void addTask(User user, Task task);

    void deleteTask(User user, Task task);
}

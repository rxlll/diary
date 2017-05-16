package com.example.liza.superdiary.database;

import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.Task;
import com.example.liza.superdiary.database.models.User;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by User on 14.05.2017.
 */

public interface DatabaseRepo {
    Completable addUser(User user);

    Single<User> getUser(String login);

    Single<Boolean> contains(String login, String password);

    Completable deleteUser(User user);

    Completable setConfirmed(User user);

    Completable addNote(User user, Note note);

    Completable addNotification(User user, Notification notification);

    Completable addTask(User user, Task task);

    Completable deleteNote(User user, Note note);

    Completable deleteNotification(User user, Notification notification);

    Completable deleteTask(User user, Task task);


}

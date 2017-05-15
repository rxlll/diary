package com.example.liza.superdiary.database;

import com.example.liza.superdiary.App;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.NoteDao;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.NotificationDao;
import com.example.liza.superdiary.database.models.Task;
import com.example.liza.superdiary.database.models.TaskDao;
import com.example.liza.superdiary.database.models.User;
import com.example.liza.superdiary.database.models.UserDao;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by User on 14.05.2017.
 */

public class DatabaseRepoImpl implements DatabaseRepo {

    @Inject
    NoteDao noteDao;

    @Inject
    NotificationDao notificationDao;

    @Inject
    TaskDao taskDao;

    @Inject
    UserDao userDao;

    public DatabaseRepoImpl() {
        App.appComponent.inject(this);
    }

    public Completable addUser(User user) {
        return Completable.fromAction(() -> userDao.insert(user));
    }

    @Override
    public Single<Boolean> contains(User user) {
        return Single.fromCallable(() -> userDao.hasKey(user));
    }

    @Override
    public Completable deleteUser(User user) {
        return Completable.fromAction(() -> userDao.delete(user));
    }

    @Override
    public Completable setConfirmed(User user) {
        return Completable.fromAction(() -> {
            user.setIsConfirmed(true);
            userDao.update(user);
        });
    }

    @Override
    public Completable addNote(User user, Note note) {
        return Completable.fromAction(() -> {
            user.getNotes().add(note);
            userDao.update(user);
        });
    }

    @Override
    public Completable deleteNote(User user, Note note) {
        return Completable.fromAction(() -> {
            user.getNotes().remove(note);
            userDao.update(user);
        });
    }

    @Override
    public Completable addNotification(User user, Notification notification) {
        return Completable.fromAction(() -> {
            user.getNotifications().add(notification);
            userDao.update(user);
        });
    }

    @Override
    public Completable deleteNotification(User user, Notification notification) {
        return Completable.fromAction(() -> {
            user.getNotifications().remove(notification);
            userDao.update(user);
        });
    }

    @Override
    public Completable addTask(User user, Task task) {
        return Completable.fromAction(() -> {
            user.getTasks().add(task);
            userDao.update(user);
        });
    }

    @Override
    public Completable deleteTask(User user, Task task) {
        return Completable.fromAction(() -> {
            user.getTasks().remove(task);
            userDao.update(user);
        });
    }
}

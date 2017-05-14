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

    public void addUser(User user) {
        Completable.fromAction(() -> userDao.insert(user));
    }

    @Override
    public void deleteUser(User user) {
        Completable.fromAction(() -> userDao.delete(user));
    }

    @Override
    public void setConfirmed(User user) {
        Completable.fromAction(() -> {
            user.setIsConfirmed(true);
            userDao.update(user);
        });
    }

    @Override
    public void addNote(User user, Note note) {
        Completable.fromAction(() -> {
            user.getNotes().add(note);
            userDao.update(user);
        });
    }

    @Override
    public void deleteNote(User user, Note note) {
        Completable.fromAction(() -> {
            user.getNotes().remove(note);
            userDao.update(user);
        });
    }

    @Override
    public void addNotification(User user, Notification notification) {
        Completable.fromAction(() -> {
            user.getNotifications().add(notification);
            userDao.update(user);
        });
    }

    @Override
    public void deleteNotification(User user, Notification notification) {
        Completable.fromAction(() -> {
            user.getNotifications().remove(notification);
            userDao.update(user);
        });
    }

    @Override
    public void addTask(User user, Task task) {
        Completable.fromAction(() -> {
            user.getTasks().add(task);
            userDao.update(user);
        });
    }

    @Override
    public void deleteTask(User user, Task task) {
        Completable.fromAction(() -> {
            user.getTasks().remove(task);
            userDao.update(user);
        });
    }
}

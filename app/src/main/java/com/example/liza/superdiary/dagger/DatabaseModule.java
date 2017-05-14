package com.example.liza.superdiary.dagger;

import android.support.annotation.NonNull;

import com.example.liza.superdiary.database.models.DaoSession;
import com.example.liza.superdiary.database.models.NoteDao;
import com.example.liza.superdiary.database.models.NotificationDao;
import com.example.liza.superdiary.database.models.TaskDao;
import com.example.liza.superdiary.database.models.UserDao;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 14.05.2017.
 */

@Module
public class DatabaseModule {
    private final DaoSession daoSession;

    @Inject
    public DatabaseModule(@NonNull final DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Provides
    @Singleton
    public NoteDao provideNoteDao() {
        return daoSession.getNoteDao();
    }

    @Provides
    @Singleton
    public NotificationDao provideNotificationDao() {
        return daoSession.getNotificationDao();
    }

    @Provides
    @Singleton
    public TaskDao provideTaskDao() {
        return daoSession.getTaskDao();
    }

    @Provides
    @Singleton
    public UserDao provideUserDao() {
        return daoSession.getUserDao();
    }
}

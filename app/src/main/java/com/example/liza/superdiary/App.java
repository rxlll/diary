package com.example.liza.superdiary;

import android.app.Application;

import com.example.liza.superdiary.dagger.AppComponent;
import com.example.liza.superdiary.dagger.DaggerAppComponent;
import com.example.liza.superdiary.dagger.DatabaseModule;
import com.example.liza.superdiary.dagger.PreferencesModule;
import com.example.liza.superdiary.database.models.DaoMaster;
import com.example.liza.superdiary.database.models.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by User on 14.05.2017.
 */

public class App extends Application {
    private static final String DATABASE_NAME = "app-database";
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .databaseModule(new DatabaseModule(getDaoSession()))
                .preferencesModule(new PreferencesModule(this))
                .build();
    }

    private DaoSession getDaoSession() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, DATABASE_NAME);
        Database database = devOpenHelper.getWritableDb();
        return new DaoMaster(database).newSession();
    }
}

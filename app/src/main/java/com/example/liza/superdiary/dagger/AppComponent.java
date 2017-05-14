package com.example.liza.superdiary.dagger;

import com.example.liza.superdiary.database.DatabaseRepoImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by User on 14.05.2017.
 */

@Component(modules = {DatabaseModule.class})
@Singleton
public interface AppComponent {
    void inject(DatabaseRepoImpl pagerPresenter);
}

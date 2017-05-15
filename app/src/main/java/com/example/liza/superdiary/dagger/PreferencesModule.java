
package com.example.liza.superdiary.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.liza.superdiary.preferences.PreferencesRepo;
import com.example.liza.superdiary.preferences.PreferencesRepoImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 14.05.2017.
 */

@Module
public class PreferencesModule {
    private Context context;

    public PreferencesModule(@NonNull Context context) {
        this.context = context;
    }

    @Provides
    @NonNull
    @Singleton
    public PreferencesRepo providePreferencesRepo() {
        return new PreferencesRepoImpl(context);
    }
}

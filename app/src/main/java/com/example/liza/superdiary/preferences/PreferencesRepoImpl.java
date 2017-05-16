package com.example.liza.superdiary.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by User on 15.05.2017.
 */

public class PreferencesRepoImpl implements PreferencesRepo {

    private static final String PREFERENCES_NAME = "app-preferences";
    private static final String KEY_CURRENT_LOGIN = "key-login";
    private SharedPreferences preferences;

    public PreferencesRepoImpl(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
    }

    @Override
    public Completable putCurrentLogin(String login) {
        return Completable.fromAction(() ->
                preferences.edit().putString(KEY_CURRENT_LOGIN, login).apply());
    }

    @Override
    public Single<String> getCurrentLogin() {
        return Single.fromCallable(() -> preferences.getString(KEY_CURRENT_LOGIN, ""));
    }

    @Override
    public Completable clear() {
        return Completable.fromAction(() -> preferences.edit().clear().apply());

    }
}

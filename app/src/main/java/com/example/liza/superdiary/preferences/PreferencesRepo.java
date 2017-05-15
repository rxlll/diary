package com.example.liza.superdiary.preferences;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by User on 15.05.2017.
 */

public interface PreferencesRepo {
    Completable putCurrentLogin(String login);
    Single<String> getCurrentLogin();
}

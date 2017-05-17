package com.example.liza.superdiary.dagger;

import com.example.liza.superdiary.database.DatabaseRepoImpl;
import com.example.liza.superdiary.ui.admin.AdminPresenter;
import com.example.liza.superdiary.ui.details.DetailsPresenter;
import com.example.liza.superdiary.ui.login.LoginPresenter;
import com.example.liza.superdiary.ui.main.MainPresenter;
import com.example.liza.superdiary.ui.list.ListPresenter;
import com.example.liza.superdiary.ui.registration.RegistrationPresenter;
import com.example.liza.superdiary.ui.user.UserPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by User on 14.05.2017.
 */

@Component(modules = {DatabaseModule.class, PreferencesModule.class})
@Singleton
public interface AppComponent {
    void inject(DatabaseRepoImpl pagerPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(UserPresenter userPresenter);

    void inject(MainPresenter mainPresenter);

    void inject(AdminPresenter adminPresenter);

    void inject(RegistrationPresenter registrationPresenter);

    void inject(ListPresenter listPresenter);

    void inject(DetailsPresenter detailsPresenter);
}

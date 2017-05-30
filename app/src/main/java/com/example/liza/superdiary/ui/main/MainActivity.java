package com.example.liza.superdiary.ui.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.ui.admin.AdminController;
import com.example.liza.superdiary.ui.login.LoginController;
import com.example.liza.superdiary.ui.registration.RegistrationController;
import com.example.liza.superdiary.ui.start.StartController;
import com.example.liza.superdiary.ui.user.UserController;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    public MainPresenter mainPresenter;

    public static final int ANIM_LENGTH = 150;
    public Router router;
    private View container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        router = Conductor.attachRouter(this, (ViewGroup) container, savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    @Override
    public void showStartController() {
        router.setRoot(RouterTransaction.with(new StartController())
                .tag("start")
                .pushChangeHandler(new FadeChangeHandler(ANIM_LENGTH))
                .popChangeHandler(new FadeChangeHandler(ANIM_LENGTH)));
    }

    @Override
    public void showUserController() {
        router.setRoot(RouterTransaction.with(new UserController())
                .pushChangeHandler(new FadeChangeHandler(ANIM_LENGTH))
                .popChangeHandler(new FadeChangeHandler(ANIM_LENGTH)));
    }

    @Override
    public void showAdminController() {
        router.setRoot(RouterTransaction.with(new AdminController())
                .tag("admin")
                .pushChangeHandler(new FadeChangeHandler(ANIM_LENGTH))
                .popChangeHandler(new FadeChangeHandler(ANIM_LENGTH)));
    }

    public String getVisibleControllerName() {
        if (router.getControllerWithTag("user") != null) {
            return ((UserController) router.getControllerWithTag("user")).getClass().getSimpleName();
        }
        if (router.getControllerWithTag("login") != null) {
            return ((LoginController) router.getControllerWithTag("login")).getClass().getSimpleName();
        }
        if (router.getControllerWithTag("admin") != null) {
            return ((AdminController) router.getControllerWithTag("admin")).getClass().getSimpleName();
        }
        if (router.getControllerWithTag("register") != null) {
            return ((RegistrationController) router.getControllerWithTag("register")).getClass().getSimpleName();
        }
        if (router.getControllerWithTag("start") != null) {
            return ((StartController) router.getControllerWithTag("start")).getClass().getSimpleName();
        }
        return null;
    }
}

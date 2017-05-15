package com.example.liza.superdiary.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.ui.admin.AdminController;
import com.example.liza.superdiary.ui.start.StartController;
import com.example.liza.superdiary.ui.user.UserController;

public class MainActivity extends AppCompatActivity implements MainView {

    private Router router;
    private View container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        router = Conductor.attachRouter(this, (ViewGroup) container, savedInstanceState);

    }

    @Override
    public void showStartController() {
        router.setRoot(RouterTransaction.with(new StartController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
    }

    @Override
    public void showUserController() {
        router.setRoot(RouterTransaction.with(new UserController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
    }

    @Override
    public void showAdminController() {
        router.setRoot(RouterTransaction.with(new AdminController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
    }
}

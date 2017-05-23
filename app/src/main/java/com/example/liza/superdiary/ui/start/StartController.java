package com.example.liza.superdiary.ui.start;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.ui.login.LoginController;
import com.example.liza.superdiary.ui.main.MoxyController;
import com.example.liza.superdiary.ui.registration.RegistrationController;

import static com.example.liza.superdiary.ui.main.MainActivity.ANIM_LENGTH;

/**
 * Created by User on 15.05.2017.
 */

public class StartController extends MoxyController implements StartView {

    @InjectPresenter
    public StartPresenter startPresenter;

    private Button buttonLogin;
    private Button buttonRegistration;

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_start, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        buttonLogin = (Button) view.findViewById(R.id.buttonLogin);
        buttonRegistration = (Button) view.findViewById(R.id.buttonRegistration);
        buttonLogin.setOnClickListener(v -> startPresenter.onButtonLoginClick());
        buttonRegistration.setOnClickListener(v -> startPresenter.onButtonRegistrationClick());
    }

    @Override
    public void showLoginController() {
        getRouter().pushController(RouterTransaction.with(new LoginController())
                .pushChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH))
                .popChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH)));
    }

    @Override
    public void showRegistrationController() {
        getRouter().pushController(RouterTransaction.with(new RegistrationController())
                .pushChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH))
                .popChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH)));
    }
}

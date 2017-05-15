package com.example.liza.superdiary.ui.login;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.ui.main.MoxyController;
import com.example.liza.superdiary.ui.user.UserController;

/**
 * Created by User on 15.05.2017.
 */

public class LoginController extends MoxyController implements LoginView {

    @InjectPresenter
    public LoginPresenter loginPresenter;

    private Button buttonLogin;
    private EditText editTextLogin;
    private EditText editTextPassword;

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_login, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        buttonLogin = (Button) view.findViewById(R.id.buttonLogin);
        editTextLogin = (EditText) view.findViewById(R.id.editTextLogin);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        buttonLogin.setOnClickListener(view1 -> loginPresenter.onButtonLoginClick(
                editTextLogin.getText().toString(), editTextPassword.getText().toString()));
    }

    @Override
    public void showUserController() {
        getRouter().pushController(RouterTransaction.with(new UserController())
                .pushChangeHandler(new VerticalChangeHandler())
                .popChangeHandler(new VerticalChangeHandler()));
    }
}

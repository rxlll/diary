package com.example.liza.superdiary.ui.login;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.ui.admin.AdminController;
import com.example.liza.superdiary.ui.main.MainPresenter;
import com.example.liza.superdiary.ui.main.MoxyController;
import com.example.liza.superdiary.ui.user.UserController;

import static com.example.liza.superdiary.ui.main.MainActivity.ANIM_LENGTH;

/**
 * Created by User on 15.05.2017.
 */

public class LoginController extends MoxyController implements LoginView {

    @InjectPresenter
    public LoginPresenter loginPresenter;

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_login, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        view.findViewById(R.id.buttonLogin).setOnClickListener(view1 -> loginPresenter.login(
                ((EditText) view.findViewById(R.id.editTextLogin)).getText().toString(),
                ((EditText) view.findViewById(R.id.editTextPassword)).getText().toString()));
    }

    @Override
    public void showAuthorizedController(String login) {
        if (login.equals(MainPresenter.ADMIN))
            getRouter().setRoot(RouterTransaction.with(new AdminController())
                    .pushChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH))
                    .popChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH)));
        else
            getRouter().setRoot(RouterTransaction.with(new UserController())
                    .pushChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH))
                    .popChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH)));

    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}

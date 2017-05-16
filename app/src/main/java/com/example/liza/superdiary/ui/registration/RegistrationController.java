package com.example.liza.superdiary.ui.registration;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.ui.main.MoxyController;

/**
 * Created by User on 15.05.2017.
 */

public class RegistrationController extends MoxyController implements RegistrationView {

    @InjectPresenter
    public RegistrationPresenter registrationPresenter;

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_registration, container, false);

    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        view.findViewById(R.id.buttonBack).setOnClickListener(view1 -> showStartController());
        view.findViewById(R.id.buttonRegister).setOnClickListener(view1 ->
                registrationPresenter.register(
                        ((EditText) view.findViewById(R.id.editTextLogin)).getText().toString(),
                        ((EditText) view.findViewById(R.id.editTextPassword)).getText().toString(),
                        ((EditText) view.findViewById(R.id.editTextName)).getText().toString(),
                        ((EditText) view.findViewById(R.id.editTextPatronymic)).getText().toString(),
                        ((EditText) view.findViewById(R.id.editTextSurname)).getText().toString(),
                        ((EditText) view.findViewById(R.id.editTextEmail)).getText().toString(),
                        ((EditText) view.findViewById(R.id.editTextBirthday)).getText().toString()));
    }

    @Override
    public void showStartController() {
        getRouter().popCurrentController();
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}

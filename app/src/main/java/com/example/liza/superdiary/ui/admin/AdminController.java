package com.example.liza.superdiary.ui.admin;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.liza.superdiary.ui.main.MoxyController;

/**
 * Created by User on 15.05.2017.
 */

public class AdminController extends MoxyController implements AdminView {

    @InjectPresenter
    public AdminPresenter adminPresenter;

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

    }
}

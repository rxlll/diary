package com.example.liza.superdiary.ui.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.ui.main.BundleBuilder;
import com.example.liza.superdiary.ui.main.MoxyController;

import static com.example.liza.superdiary.ui.list.ListController.KEY_TYPE;

/**
 * Created by User on 17.05.2017.
 */

public class DetailsController extends MoxyController implements DetailsView {

    public static final String KEY_OBJECT = "DetailsController.object";
    @InjectPresenter
    public DetailsPresenter detailsPresenter;

    public DetailsController(int type) {
        this(new BundleBuilder(new Bundle())
                .putInt(KEY_TYPE, type)
                .build());
    }

    public DetailsController(Note note) {
        this(new BundleBuilder(new Bundle())
                .putParcelable(KEY_OBJECT, note)
                .build());
    }

    public DetailsController(Bundle args) {
        super(args);
    }



    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_details, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
    }
}

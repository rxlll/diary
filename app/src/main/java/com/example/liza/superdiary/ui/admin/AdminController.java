package com.example.liza.superdiary.ui.admin;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.database.models.User;
import com.example.liza.superdiary.ui.main.MoxyController;
import com.example.liza.superdiary.ui.start.StartController;

import java.util.List;

import static com.example.liza.superdiary.ui.main.MainActivity.ANIM_LENGTH;

/**
 * Created by User on 15.05.2017.
 */

public class AdminController extends MoxyController implements AdminView {

    @InjectPresenter
    public AdminPresenter adminPresenter;

    private RecyclerView recyclerView;
    private AdminRecyclerAdapter recyclerAdapter;

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_admin, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        view.findViewById(R.id.buttonLogout).setOnClickListener(v -> adminPresenter.logout());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    @Override
    public void showRecycler(List<User> users) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new AdminRecyclerAdapter(users);
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter.setOnConfirmClickListener((user, position) ->
                adminPresenter.confirm(user));
    }

    @Override
    public void showStartController() {
        getRouter().setRoot(RouterTransaction.with(new StartController())
                .tag("start")
                .pushChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH))
                .popChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH)));
    }

    @Override
    public void showConfirmed() {
        recyclerAdapter.notifyDataSetChanged();
    }
}

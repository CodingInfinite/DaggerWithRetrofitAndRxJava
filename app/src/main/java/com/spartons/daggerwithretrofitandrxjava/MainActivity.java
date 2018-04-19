package com.spartons.daggerwithretrofitandrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.spartons.daggerwithretrofitandrxjava.adapter.CarCategoryAdapter;
import com.spartons.daggerwithretrofitandrxjava.backend.ServiceUtil;
import com.spartons.daggerwithretrofitandrxjava.components.DaggerServiceApplicationComponent;
import com.spartons.daggerwithretrofitandrxjava.modules.ApplicationContextModule;
import com.spartons.daggerwithretrofitandrxjava.response.CarCategoryResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    Picasso picasso;
    @Inject
    ServiceUtil serviceUtil;

    private RecyclerView mainRecyclerView;
    private CarCategoryAdapter carCategoryAdapter;
    private List<CarCategoryResponse.CarCategory> carCategories = new ArrayList<>();
    private ProgressBar mainProgressBar;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerServiceApplicationComponent.builder()
                .applicationContextModule(new ApplicationContextModule(this))
                .build()
                .inject(this);
        initViews();
        setRecyclerViewProperties();
        disposable = serviceUtil.getCarCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carCategoryResponse -> {
                    if (carCategoryResponse.getCarCategories() != null && carCategoryResponse.getCarCategories().size() > 0) {
                        this.carCategories.addAll(carCategoryResponse.getCarCategories());
                        carCategoryAdapter.notifyDataSetChanged();
                    } else
                        Toast.makeText(this, "No data found!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    if (mainProgressBar.getVisibility() == View.VISIBLE)
                        mainProgressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Internet not connect", Toast.LENGTH_SHORT).show();
                }, () -> {
                    if (mainProgressBar.getVisibility() == View.VISIBLE)
                        mainProgressBar.setVisibility(View.GONE);
                });
    }

    private void setRecyclerViewProperties() {
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setHasFixedSize(true);
        carCategoryAdapter = new CarCategoryAdapter(carCategories, this);
        mainRecyclerView.setAdapter(carCategoryAdapter);
    }

    private void initViews() {
        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        mainProgressBar = findViewById(R.id.mainProgressBar);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
                if (mainProgressBar != null && mainProgressBar.getVisibility() == View.VISIBLE)
                    mainProgressBar.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        carCategoryAdapter = null;
        carCategories.clear();
        carCategories = null;
        serviceUtil = null;
        picasso = null;
        disposable = null;
    }
}

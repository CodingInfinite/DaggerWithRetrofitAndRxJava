package com.spartons.daggerwithretrofitandrxjava.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.spartons.daggerwithretrofitandrxjava.R;
import com.spartons.daggerwithretrofitandrxjava.response.CarCategoryResponse;
import com.spartons.daggerwithretrofitandrxjava.viewHolders.CarCategoryViewHolder;

import java.util.List;

public class CarCategoryAdapter extends RecyclerView.Adapter<CarCategoryViewHolder> {

    private List<CarCategoryResponse.CarCategory> carCategories;
    private LayoutInflater layoutInflater;

    public CarCategoryAdapter(List<CarCategoryResponse.CarCategory> carCategories, final Context context) {
        this.carCategories = carCategories;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CarCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarCategoryViewHolder(layoutInflater.inflate(R.layout.list_item_single_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarCategoryViewHolder holder, int position) {
        CarCategoryResponse.CarCategory carCategory = carCategories.get(position);
        holder.getBase_fare().setText(String.valueOf("Rs ").concat(carCategory.getBaseFare()));
        holder.getCar_category().setText(carCategory.getName());
        holder.getRate_per_km().setText(String.valueOf("Rs ").concat(carCategory.getRatePerKm()));
        holder.getRate_per_min().setText(String.valueOf("Rs ").concat(carCategory.getRatePerMin()));
    }

    @Override
    public int getItemCount() {
        return carCategories.size();
    }
}

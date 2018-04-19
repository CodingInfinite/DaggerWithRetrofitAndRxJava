package com.spartons.daggerwithretrofitandrxjava.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.spartons.daggerwithretrofitandrxjava.R;

/**
 * Created by Ahsen Saeed on 04/09/2018.
 */

public class CarCategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView car_category, rate_per_min, rate_per_km, base_fare;

    public CarCategoryViewHolder(View itemView) {
        super(itemView);
        car_category = itemView.findViewById(R.id.car_category);
        rate_per_min = itemView.findViewById(R.id.rate_per_min);
        rate_per_km = itemView.findViewById(R.id.rate_per_km);
        base_fare = itemView.findViewById(R.id.base_fare);
    }

    public TextView getCar_category() {
        return car_category;
    }

    public TextView getRate_per_min() {
        return rate_per_min;
    }

    public TextView getRate_per_km() {
        return rate_per_km;
    }

    public TextView getBase_fare() {
        return base_fare;
    }
}

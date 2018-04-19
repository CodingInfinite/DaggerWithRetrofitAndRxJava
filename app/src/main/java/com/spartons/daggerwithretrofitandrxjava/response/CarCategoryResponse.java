package com.spartons.daggerwithretrofitandrxjava.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahsen Saeed on 04/09/2018.
 */

public class CarCategoryResponse {

    @SerializedName("car_categories")
    @Expose
    private List<CarCategory> carCategories;

    public List<CarCategory> getCarCategories() {
        return carCategories;
    }

    public class CarCategory {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("rate_per_km")
        @Expose
        private String ratePerKm;
        @SerializedName("rate_per_min")
        @Expose
        private String ratePerMin;
        @SerializedName("base_fare")
        @Expose
        private String baseFare;

        @Override
        public String toString() {
            return "CarCategory{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", ratePerKm=" + ratePerKm +
                    ", ratePerMin='" + ratePerMin + '\'' +
                    ", baseFare='" + baseFare + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRatePerKm() {
            return ratePerKm;
        }

        public String getRatePerMin() {
            return ratePerMin;
        }

        public String getBaseFare() {
            return baseFare;
        }
    }
}

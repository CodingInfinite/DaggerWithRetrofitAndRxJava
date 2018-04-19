package com.spartons.daggerwithretrofitandrxjava.backend;

import com.spartons.daggerwithretrofitandrxjava.response.CarCategoryResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Ahsen Saeed on 12/4/2017.
 */

public interface ServiceUtil {

    @GET("GetCarsCategories.php")
    Observable<CarCategoryResponse> getCarCategories();
}

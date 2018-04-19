package com.spartons.daggerwithretrofitandrxjava.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.spartons.daggerwithretrofitandrxjava.backend.ServiceUtil;
import com.spartons.daggerwithretrofitandrxjava.scopes.CustomApplicationScope;


import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ahsen Saeed on 12/4/2017.
 */

@Module(includes = NetworkModule.class)
public class ServiceUtilModule {

    private static final String BASE_URL = "https://www.frisbeego.com/secure/index.php/";

    @Provides
    @CustomApplicationScope
    ServiceUtil getServiceUtil(Retrofit retrofit) {
        return retrofit.create(ServiceUtil.class);
    }

    @Provides
    @CustomApplicationScope
    Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @CustomApplicationScope
    Retrofit getRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }
}

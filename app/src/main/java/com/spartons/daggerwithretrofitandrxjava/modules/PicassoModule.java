package com.spartons.daggerwithretrofitandrxjava.modules;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;

import com.spartons.daggerwithretrofitandrxjava.qualifier.ApplicationContextQualifier;
import com.spartons.daggerwithretrofitandrxjava.scopes.CustomApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Ahsen Saeed on 12/4/2017.
 */

@Module(includes = NetworkModule.class)
public class PicassoModule {

    @Provides
    @CustomApplicationScope
    Picasso getPicasso(@ApplicationContextQualifier Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @CustomApplicationScope
    OkHttp3Downloader getOkHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}

package com.spartons.daggerwithretrofitandrxjava.modules;

import android.content.Context;


import com.spartons.daggerwithretrofitandrxjava.qualifier.ApplicationContextQualifier;
import com.spartons.daggerwithretrofitandrxjava.scopes.CustomApplicationScope;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by Ahsen Saeed on 12/4/2017.
 */

@Module(includes = ApplicationContextModule.class)
public class NetworkModule {

    @Provides
    @CustomApplicationScope
    HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }

    @Provides
    @CustomApplicationScope
    Cache getCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000);  // 10 MiB cache
    }

    @Provides
    @CustomApplicationScope
    File getFile(@ApplicationContextQualifier Context context) {
        File file = new File(context.getFilesDir(), "cache_dir");
        if (!file.exists())
            file.mkdirs();
        return file;
    }

    @Provides
    @CustomApplicationScope
    OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
                .build();
    }
}

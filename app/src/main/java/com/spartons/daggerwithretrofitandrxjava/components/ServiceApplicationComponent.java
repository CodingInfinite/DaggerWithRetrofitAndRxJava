package com.spartons.daggerwithretrofitandrxjava.components;


import com.spartons.daggerwithretrofitandrxjava.MainActivity;
import com.spartons.daggerwithretrofitandrxjava.backend.ServiceUtil;
import com.spartons.daggerwithretrofitandrxjava.modules.PicassoModule;
import com.spartons.daggerwithretrofitandrxjava.modules.ServiceUtilModule;
import com.spartons.daggerwithretrofitandrxjava.scopes.CustomApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Ahsen Saeed on 12/4/2017.
 */

@CustomApplicationScope
@Component(modules = {ServiceUtilModule.class, PicassoModule.class})
public interface ServiceApplicationComponent {

    Picasso getPicasso();

    ServiceUtil getServiceUtil();

    void inject(MainActivity mainActivity);
}

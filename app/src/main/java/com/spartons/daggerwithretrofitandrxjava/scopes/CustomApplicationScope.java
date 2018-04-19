package com.spartons.daggerwithretrofitandrxjava.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Ahsen Saeed on 12/4/2017.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface CustomApplicationScope {
}

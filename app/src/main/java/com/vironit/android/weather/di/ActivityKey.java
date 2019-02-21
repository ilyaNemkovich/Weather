package com.vironit.android.weather.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.MapKey;
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ActivityKey {
    Class value();
}

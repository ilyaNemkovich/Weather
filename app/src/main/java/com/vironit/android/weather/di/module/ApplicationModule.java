package com.vironit.android.weather.di.module;

import android.app.Application;
import android.content.Context;

import com.vironit.android.weather.di.qualifiers.ApplicationContext;
import com.vironit.android.weather.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    @ApplicationScope
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }
}

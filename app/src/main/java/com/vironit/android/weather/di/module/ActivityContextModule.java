package com.vironit.android.weather.di.module;

import android.content.Context;

import com.vironit.android.weather.di.qualifiers.ActivityContext;
import com.vironit.android.weather.di.scopes.ActivityScope;
import com.vironit.android.weather.ui.activities.main.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityContextModule {

    public Context context;

    public ActivityContextModule(MainActivity mainActivity) {
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }
}

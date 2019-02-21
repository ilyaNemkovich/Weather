package com.vironit.android.weather.ui.activities.main;

import android.content.Context;

import com.vironit.android.weather.adapter.WeatherFragmentPagerAdapter;
import com.vironit.android.weather.di.qualifiers.ActivityContext;
import com.vironit.android.weather.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    MainActivity mainActivity;

    @Provides
    @ActivityScope
    @ActivityContext
    Context provideContext(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    WeatherFragmentPagerAdapter providePagerAdapter(MainActivity mainActivity){
        return new WeatherFragmentPagerAdapter(mainActivity.getSupportFragmentManager());
    }
}

package com.vironit.android.weather.di.module;

import com.vironit.android.weather.di.scopes.ActivityScope;
import com.vironit.android.weather.ui.activities.main.MainActivity;
import com.vironit.android.weather.ui.activities.main.MainActivityModule;
import com.vironit.android.weather.ui.fragments.calendar.CalendarFragment;
import com.vironit.android.weather.ui.fragments.calendar.CalendarFragmentProvider;
import com.vironit.android.weather.ui.fragments.map.MapFragmentProvider;
import com.vironit.android.weather.ui.fragments.weather.WeatherFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivitiesModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            MapFragmentProvider.class,
            WeatherFragmentProvider.class,
            CalendarFragmentProvider.class
    })
    MainActivity contributeMainActivityInjector();
}

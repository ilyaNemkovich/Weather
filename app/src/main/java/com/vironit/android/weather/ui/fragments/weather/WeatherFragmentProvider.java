package com.vironit.android.weather.ui.fragments.weather;

import com.vironit.android.weather.di.scopes.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface WeatherFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector(modules = {
            WeatherFragmentModule.class})
    WeatherFragment bindWeatherFragment();
}

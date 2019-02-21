package com.vironit.android.weather.ui.fragments.map;

import com.vironit.android.weather.di.scopes.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MapFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector(modules = {
            MapFragmentModule.class})
    MapFragment bindMapFragment();
}

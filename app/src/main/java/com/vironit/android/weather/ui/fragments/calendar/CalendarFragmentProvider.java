package com.vironit.android.weather.ui.fragments.calendar;

import com.vironit.android.weather.di.scopes.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface CalendarFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector(modules = {
            CalendarFragmentModule.class})
    CalendarFragment bindCalendarFragment();
}

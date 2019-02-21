package com.vironit.android.weather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vironit.android.weather.ui.fragments.calendar.CalendarFragment;
import com.vironit.android.weather.ui.fragments.map.MapFragment;
import com.vironit.android.weather.ui.fragments.weather.WeatherFragment;

public class WeatherFragmentPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEM = 3;

    public WeatherFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CalendarFragment.getInstance();
            case 1:
                return WeatherFragment.getInstance();
            case 2:
                return MapFragment.getInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEM;
    }
}

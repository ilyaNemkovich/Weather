package com.vironit.android.weather.ui.fragments.calendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.vironit.android.weather.R;
import com.vironit.android.weather.ui.base.BaseFragment;

public class CalendarFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        return view;
    }

    public static CalendarFragment getInstance() {
        CalendarFragment fragment = new CalendarFragment();
        Bundle arg = new Bundle();
        fragment.setArguments(arg);
        return fragment;
    }
}

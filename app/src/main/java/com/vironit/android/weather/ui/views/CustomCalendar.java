package com.vironit.android.weather.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vironit.android.weather.R;
import com.vironit.android.weather.adapter.CalendarAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CustomCalendar extends LinearLayout implements CalendarAdapter.OnDateItemListener {

    TextView firstDate;

    CalendarAdapter calendarAdapter;

    TextView startViewDate;

    // how many days to show, defaults to six weeks, 42 days
    private static final int DAYS_COUNT = 42;

    // default date format
    private static final String DATE_FORMAT = "MMM yyyy";

    // current displayed month
    private Calendar currentDate = Calendar.getInstance();

    GridView mGridView;

    public CustomCalendar(Context context) {
        super(context);
        init(context, null);
    }

    public CustomCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.custom_calendar, this);

        mGridView = findViewById(R.id.grid_view);
        startViewDate = findViewById(R.id.start_tv);

        mGridView.setNumColumns(7);
        updateCalendar();
    }

    private void updateCalendar() {
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) currentDate.clone();

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        // fill cells (42 days calendar as per our business logic)
        while (cells.size() < DAYS_COUNT) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        calendarAdapter = new CalendarAdapter(getContext(), cells, CustomCalendar.this);

        mGridView.setAdapter(calendarAdapter);
    }

    @Override
    public void onDateItemSelected(View view, Integer position) {
        startViewDate.animate()
                .x(view.getX())
                .y(view.getY())
                .setDuration(700)
                .start();
        startViewDate.setText(((TextView) view).getText());
    }

    @Override
    public boolean onDateItemDragged(View view, DragEvent event) {
        return false;
    }

    @Override
    public boolean onDataItemLongClicked(View view) {
        return false;
    }
}

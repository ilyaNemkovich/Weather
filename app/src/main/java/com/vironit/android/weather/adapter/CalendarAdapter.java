package com.vironit.android.weather.adapter;

import android.content.Context;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vironit.android.weather.R;
import com.vironit.android.weather.ui.views.CustomCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarAdapter extends BaseAdapter {

    ArrayList<Date> dates;
    Context context;
    Calendar currentCalendar = Calendar.getInstance();
    LayoutInflater inflater;
    OnDateItemListener onDateItemListener;

    public CalendarAdapter(Context context, ArrayList<Date> dates, CustomCalendar customCalendar) {
        this.dates = dates;
        this.context = context;
        inflater = LayoutInflater.from(context);
        onDateItemListener = customCalendar;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat yearFormat = new SimpleDateFormat("yyyy");

        int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = currentCalendar.get(Calendar.MONTH) + 1;
        int currentYear = currentCalendar.get(Calendar.YEAR);

        int day = Integer.parseInt(dateFormat.format(dates.get(position)));
        int month = Integer.parseInt(monthFormat.format(dates.get(position)));
        int year = Integer.parseInt(yearFormat.format(dates.get(position)));

        TextView tv;
        if (convertView == null) {
            tv = (TextView) inflater.inflate(R.layout.calendar_day, parent, false);
        } else {
            tv = (TextView) convertView;
        }

        if (month != currentMonth || year != currentYear) {
            tv.setTextColor(context.getResources().getColor(R.color.grey_out));
        }

        if (day == currentDay && month == currentMonth) {
            tv.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
        tv.setText(String.valueOf(day));
        tv.setId(position);

        tv.setOnClickListener(v -> onDateItemListener.onDateItemSelected(v, position));
        tv.setOnDragListener((v, event) -> onDateItemListener.onDateItemDragged(v, event));
        tv.setOnLongClickListener(v -> onDateItemListener.onDataItemLongClicked(v));

        return tv;
    }

    private boolean handleEvent(View v, DragEvent event) {

        return true;
    }

    public void updateData(ArrayList<Date> cells) {
        dates.clear();
        dates.addAll(cells);
        notifyDataSetChanged();
    }

    public interface OnDateItemListener {
        void onDateItemSelected(View view, Integer position);
        boolean onDateItemDragged(View view, DragEvent event);
        boolean onDataItemLongClicked(View view);
    }
}

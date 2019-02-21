package com.vironit.android.weather.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vironit.android.weather.R;
import com.vironit.android.weather.dto.openWeather.ForecastResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    private ArrayList<ForecastResponse> mDataSet;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mCountryTv;
        TextView mCityTv;
        TextView mTempTv;
        TextView mHumidityTv;
        TextView mWindTv;
        TextView mIconTv;
        TextView mDataTv;
        TextView mTimeTv;

        View mItemView;

        MyViewHolder(View v) {
            super(v);
            mCountryTv = v.findViewById(R.id.tv_country);
            mCityTv = v.findViewById(R.id.tv_city);
            mTempTv = v.findViewById(R.id.tv_temp);
            mIconTv = v.findViewById(R.id.tv_icon_forecast);
            mHumidityTv = v.findViewById(R.id.tv_humidity);
            mWindTv = v.findViewById(R.id.tv_wind);
            mDataTv = v.findViewById(R.id.tv_data);
            mTimeTv = v.findViewById(R.id.tv_time);
        }

        void onBind(final ForecastResponse data) {
            int temp = (int) Math.round(data.getMain().getTemp() - 273);
            mCountryTv.setText(data.getSys().getCountry());
            mCityTv.setText(data.getName() + " " + temp + "°C");
            mIconTv.setText(setTextIcon(
                    data.getDt(),
                    data.getSys().getSunrise(),
                    data.getSys().getSunset(),
                    data.getWeather().get(0).getId(),
                    mIconTv));
            mHumidityTv.setText("Humidity: " + data.getMain().getHumidity().toString() + "%");
            mWindTv.setText("Wind: " + data.getWind().getSpeed() + " m/s");
            mDataTv.setText(dataFormat(new Date(data.getDt() * 1000)));
            mTimeTv.setText(timeFormat(new Date(data.getDt() * 1000)));
        }
    }

    private String setTextIcon(Long dt, Integer sunrise, Integer sunset, Integer id, View view) {
        String iconIdSting = "wi_owm_";
        if (dt >= sunrise && dt <= sunset) {
            iconIdSting = iconIdSting + "day_";
        } else {
            iconIdSting = iconIdSting + "night_";
        }
        iconIdSting = iconIdSting + id;
        int identifier = view.getContext()
                .getResources()
                .getIdentifier(iconIdSting, "string", view
                        .getContext()
                        .getPackageName());

        return view.getContext().getString(identifier);
    }

    private String dataFormat(Date mill) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormatter.format(mill);
    }

    private String timeFormat(Date mill) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");
        return dateFormatter.format(mill);
    }

    public WeatherAdapter() {
        mDataSet = new ArrayList<>();
    }

    public void cleanList() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    public void addForecast(ForecastResponse forecastResponse) {
        mDataSet.add(forecastResponse);
        notifyItemInserted(mDataSet.size() - 1);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        if (mDataSet != null)
            return mDataSet.size();
        else return 0;
    }
}

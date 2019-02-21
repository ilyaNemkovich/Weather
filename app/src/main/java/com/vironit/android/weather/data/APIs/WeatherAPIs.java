package com.vironit.android.weather.data.APIs;

import com.vironit.android.weather.dto.openWeather.ForecastResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPIs {
    @GET("/data/2.5/weather")
    Single<ForecastResponse> getWeatherByCity(
            @Query("q") String city,
            @Query("appid") String apiKey);
}

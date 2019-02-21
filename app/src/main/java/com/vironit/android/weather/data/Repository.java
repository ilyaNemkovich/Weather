package com.vironit.android.weather.data;

import com.vironit.android.weather.data.APIs.BelrosstrahAPIs;
import com.vironit.android.weather.data.APIs.WeatherAPIs;
import com.vironit.android.weather.dto.belrosstrah.BelrosstrahResponse;
import com.vironit.android.weather.dto.openWeather.ForecastResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class Repository {

    @Inject
    WeatherAPIs weatherAPIs;

    @Inject
    BelrosstrahAPIs belrosstrahAPIs;

    @Inject
    public Repository() {
    }

    public Single<BelrosstrahResponse> getBelrosstrahOfices() {
        return belrosstrahAPIs.getBelrosstrahOffices("http://ais.brs.by:38516/ords/mobile_user/v1_3/GetOfficesInfo/");
    }

    public Single<ForecastResponse> getForecast(String city) {
        return weatherAPIs.getWeatherByCity(city, "d53e98c38d58484f69c04b31b2fd9b57");
    }
}
package com.vironit.android.weather.ui.fragments.weather;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.vironit.android.weather.dto.openWeather.ForecastResponse;

public interface IWeatherView extends MvpView {
    void addItemToList(ForecastResponse forecastResponse);

    void clearItemList();

    void showProgressBar();

    void clearProgressBar();

    void showEmptyView();

    void showNetworkError();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showNoFoundAnimation();
}

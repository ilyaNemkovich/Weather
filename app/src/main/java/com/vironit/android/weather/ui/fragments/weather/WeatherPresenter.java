package com.vironit.android.weather.ui.fragments.weather;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.vironit.android.weather.MyApplication;
import com.vironit.android.weather.data.Repository;
import com.vironit.android.weather.dto.openWeather.ForecastResponse;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class WeatherPresenter extends MvpPresenter<IWeatherView> {

    @Inject
    public WeatherPresenter() {
        MyApplication.getApplicationComponent().provideApi(this);
    }

    @Inject
    Repository repository;

    public void loadForecast(String city) {
        getViewState().showProgressBar();
        repository.getForecast(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ForecastResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ForecastResponse forecastResponse) {
                        getViewState().addItemToList(forecastResponse);
                        getViewState().clearProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            if (((HttpException) e).code() == 404) {
                                getViewState().clearProgressBar();
                                getViewState().showNoFoundAnimation();
                                return;
                            }
                        } catch (ClassCastException cce) {
                            Log.d("TAG", "onError: ClassCastException");
                        }
                        getViewState().clearProgressBar();
                        getViewState().showNetworkError();
                    }
                });
    }
}

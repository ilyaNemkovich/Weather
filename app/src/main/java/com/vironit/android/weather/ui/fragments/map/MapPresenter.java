package com.vironit.android.weather.ui.fragments.map;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vironit.android.weather.MyApplication;
import com.vironit.android.weather.data.Repository;
import com.vironit.android.weather.dto.belrosstrah.BelrosstrahResponse;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MapPresenter extends MvpPresenter<IMapView> {

    @Inject
    Repository repository;

    public MapPresenter() {
        MyApplication.getApplicationComponent().provideApi(this);
    }

    void fetchBelrosstrahDetails() {
        repository.getBelrosstrahOfices()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BelrosstrahResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(BelrosstrahResponse belrosstrahResponse) {
                        getViewState().setUpClusterer(belrosstrahResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}


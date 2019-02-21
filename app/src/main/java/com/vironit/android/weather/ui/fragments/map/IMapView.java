package com.vironit.android.weather.ui.fragments.map;

import com.arellomobile.mvp.MvpView;
import com.vironit.android.weather.dto.belrosstrah.BelrosstrahResponse;

interface IMapView extends MvpView {
    void setUpClusterer(BelrosstrahResponse belrosstrahResponse);
}

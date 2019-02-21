package com.vironit.android.weather.data.APIs;

import com.vironit.android.weather.dto.belrosstrah.BelrosstrahResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BelrosstrahAPIs {
    @GET
    Single<BelrosstrahResponse> getBelrosstrahOffices(
            @Url String url
    );
}

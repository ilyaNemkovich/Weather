package com.vironit.android.weather.ui.fragments.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.vironit.android.weather.R;
import com.vironit.android.weather.dto.belrosstrah.BelrosstrahResponse;
import com.vironit.android.weather.ui.base.BaseFragment;
import com.vironit.android.weather.ui.maps.ClusterIconRendered;
import com.vironit.android.weather.ui.maps.OfficeClusterItem;

import java.util.Objects;

public class MapFragment extends BaseFragment implements OnMapReadyCallback, IMapView {
    private MapView mapView;
    private GoogleMap gmap;

    private ClusterManager<OfficeClusterItem> mClusterManager;

    @InjectPresenter
    MapPresenter mapPresenter;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void setUpClusterer(BelrosstrahResponse belrosstrahResponse) {
        mClusterManager = new ClusterManager<>(Objects.requireNonNull(getContext()), gmap);

        gmap.setOnCameraIdleListener(mClusterManager);
        gmap.setOnMarkerClickListener(mClusterManager);
        for (int i = 0; i < belrosstrahResponse.getItems().size(); i++) {
            double latitude = belrosstrahResponse.getItems().get(i).getLatitude();
            double longitude = belrosstrahResponse.getItems().get(i).getLongitude();
            int id = belrosstrahResponse.getItems().get(i).getId();
            String title = belrosstrahResponse.getItems().get(i).getCity();
            OfficeClusterItem offsetItem = new OfficeClusterItem(latitude, longitude, "id " + id, title);
            mClusterManager.addItem(offsetItem);
        }
        mClusterManager.setRenderer(new ClusterIconRendered(getContext(), gmap, mClusterManager));
        mClusterManager.cluster();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();
        Bundle arg = new Bundle();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        LatLng by = new LatLng(53.9, 27.5);
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(by, 5.9f));
        mapPresenter.fetchBelrosstrahDetails();
    }
}

package com.vironit.android.weather.ui.fragments.weather;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.android.weather.R;
import com.vironit.android.weather.adapter.WeatherAdapter;
import com.vironit.android.weather.dto.openWeather.ForecastResponse;
import com.vironit.android.weather.ui.base.BaseFragment;

import java.util.Objects;

public class WeatherFragment extends BaseFragment implements IWeatherView {

    private SearchView mSearchView;
    private ImageView sunImage;
    private TextView mStartTextView;
    private RecyclerView mRecyclerView;
    private PopupWindow popup;

    private Animation startSearchAnim;
    private Animation sunAnim;
    private Animation sunStartAnim;
    private ObjectAnimator animBounce;

    private WeatherAdapter mAdapter;

    @InjectPresenter
    WeatherPresenter mWeatherPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        initViews(view);
        startAnim();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mWeatherPresenter.loadForecast(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;
    }

    private void initViews(View view) {
        /*init views*/
        mRecyclerView = view.findViewById(R.id.recycle_view);
        mSearchView = view.findViewById(R.id.search_view_main);
        sunImage = view.findViewById(R.id.imageView);
        mStartTextView = view.findViewById(R.id.tv_start);

        /*set up popup*/
        popup = new PopupWindow();
        View layout = View.inflate(getContext(), R.layout.popup_layout, null);
        popup.setContentView(layout);
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setOutsideTouchable(true);
        popup.setFocusable(false);

        /*init and set up view's animation*/
        startSearchAnim = AnimationUtils.loadAnimation(getContext(), R.anim.start_search_anim);
        sunAnim = AnimationUtils.loadAnimation(getContext(), R.anim.sun_image_anim);
        sunStartAnim = AnimationUtils.loadAnimation(getContext(), R.anim.sun_image_start_anim);
        animBounce = ObjectAnimator.ofFloat(mSearchView, View.TRANSLATION_X, -20, 20f, 0f);
        animBounce.setInterpolator(new BounceInterpolator());
        animBounce.setDuration(800);

        /*remove searchView underline*/
        View searchPlate = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        searchPlate.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.MULTIPLY);

        /*set up recyclerView*/
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WeatherAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.do_nothing_item:
                clearItemList();
        }
        return super.onOptionsItemSelected(item);
    }

    public static WeatherFragment getInstance() {
        WeatherFragment fragment = new WeatherFragment();
        Bundle arg = new Bundle();
        fragment.setArguments(arg);
        return fragment;
    }

    private void startAnim() {
        mSearchView.startAnimation(startSearchAnim);
        showEmptyView();
    }

    @Override
    public void addItemToList(ForecastResponse forecastResponse) {
        mAdapter.addForecast(forecastResponse);
        mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
        mSearchView.clearFocus();
    }

    @Override
    public void clearItemList() {
        if (mAdapter.getItemCount() != 0) {
            mAdapter.cleanList();
            showEmptyView();
        }
    }

    @Override
    public void showProgressBar() {
        sunImage.setVisibility(View.VISIBLE);
        mStartTextView.setVisibility(View.INVISIBLE);
        sunImage.startAnimation(sunAnim);
    }

    @Override
    public void clearProgressBar() {
        sunImage.clearAnimation();
        sunImage.setVisibility(View.GONE);
        mStartTextView.setVisibility(View.GONE);
        if (mAdapter.getItemCount() == 0) {
            showEmptyView();
        }
    }

    @Override
    public void showEmptyView() {
        sunImage.setVisibility(View.VISIBLE);
        mStartTextView.setVisibility(View.VISIBLE);
        sunImage.startAnimation(sunStartAnim);
    }

    @Override
    public void showNetworkError() {
        popup.showAsDropDown(mSearchView, mSearchView.getWidth() / 4, 0);
    }

    @Override
    public void showNoFoundAnimation() {
        animBounce.start();
    }
}

package com.vironit.android.weather.ui.activities.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.vironit.android.weather.R;
import com.vironit.android.weather.adapter.WeatherFragmentPagerAdapter;
import com.vironit.android.weather.ui.base.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Inject
    WeatherFragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        if (savedInstanceState == null) {
            viewPager.setCurrentItem(1);
        }
    }

    private void init() {
        viewPager = findViewById(R.id.view_pager_main);
        tabLayout = findViewById(R.id.tab_layout);
        toolbar = findViewById(R.id.toolbar_top);

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        setToolbarNames();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    private void setToolbarNames() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            switch (i) {
                case 0:
                    tv.setText(getResources().getString(R.string.calendar));
                    break;
                case 1:
                    tv.setText(getResources().getString(R.string.weather));
                    break;
                case 2:
                    tv.setText(getResources().getString(R.string.map));
                    break;
            }
            tabLayout.getTabAt(i).setCustomView(tv);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}

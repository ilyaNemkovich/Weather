package com.vironit.android.weather.di.component;

import android.app.Application;
import android.content.Context;

import com.vironit.android.weather.MyApplication;
import com.vironit.android.weather.di.module.ActivitiesModule;
import com.vironit.android.weather.di.module.ApplicationModule;
import com.vironit.android.weather.di.module.RetrofitModule;
import com.vironit.android.weather.di.qualifiers.ApplicationContext;
import com.vironit.android.weather.di.scopes.ApplicationScope;
import com.vironit.android.weather.data.APIs.WeatherAPIs;
import com.vironit.android.weather.ui.fragments.map.MapPresenter;
import com.vironit.android.weather.ui.fragments.weather.WeatherPresenter;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@ApplicationScope
@Component(modules = {
        ApplicationModule.class,
        AndroidInjectionModule.class,
        ActivitiesModule.class,
        RetrofitModule.class})
public interface ApplicationComponent {
    WeatherAPIs getApiInterface();

    void provideApi(WeatherPresenter weatherPresenter);
    void provideApi(MapPresenter weatherPresenter);

    @ApplicationContext
    Context provideContext();

    void injectApplication(MyApplication myApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance Builder applicationModule(Application application);
        ApplicationComponent build();
    }
}

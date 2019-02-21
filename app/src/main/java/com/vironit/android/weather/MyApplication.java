package com.vironit.android.weather;

import android.app.Activity;
import android.app.Application;

import com.vironit.android.weather.di.component.ApplicationComponent;
import com.vironit.android.weather.di.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(this)
                .build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}

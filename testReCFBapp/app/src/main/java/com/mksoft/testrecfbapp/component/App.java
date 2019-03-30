package com.mksoft.testrecfbapp.component;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.mksoft.testrecfbapp.DI.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


/**
 * Created by Philippe on 02/03/2018.
 */

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
        context = getApplicationContext();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    // ---

    private void initDagger(){
        DaggerAppComponent.builder().application(this).build().inject(this);
    }
}

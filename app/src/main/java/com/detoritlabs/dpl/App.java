package com.detoritlabs.dpl;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by andrewgiang on 7/12/14.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}

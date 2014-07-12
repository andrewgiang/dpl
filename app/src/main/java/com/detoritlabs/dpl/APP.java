package com.detoritlabs.dpl;

import android.app.Application;

import com.google.gson.Gson;

/**
 * Created by andrewgiang on 7/11/14.
 */
public class App extends Application {

    private static Gson gson;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Gson getGsonInstance(){
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }
}

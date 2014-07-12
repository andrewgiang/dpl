package com.detoritlabs.dpl;

import android.app.Application;

import com.detoritlabs.dpl.api.OpenLibraryApi;
import com.detoritlabs.dpl.api.model.Book;
import com.detoritlabs.dpl.api.model.BookResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.danlew.android.joda.JodaTimeAndroid;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by andrewgiang on 7/12/14.
 */
public class App extends Application {

    private RestAdapter mRestAdapter;
    private OpenLibraryApi mOpenLibApi;

    public static Application application;
    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    private int ISBN;
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        Gson gson = new GsonBuilder().registerTypeAdapter(BookResponse.class, new Book.BookResponseDeserializer()).create();
        mRestAdapter = new RestAdapter.Builder().setEndpoint(OpenLibraryApi.BASE_URL).setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("jscmd", "data");
                request.addQueryParam("format", "json");
            }
        }).setConverter(new GsonConverter(gson)).setLogLevel(RestAdapter.LogLevel.FULL).build();
        mOpenLibApi = mRestAdapter.create(OpenLibraryApi.class);

        application = this;

    }


    public OpenLibraryApi getOpenLibApi() {
        return mOpenLibApi;
    }
}

package com.detoritlabs.dpl.api;

import com.detoritlabs.dpl.api.model.BookResponse;
import com.detoritlabs.dpl.model.RssItem;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by andrewgiang on 7/12/14.
 */
public interface OpenLibraryApi {

    public static final String BASE_URL = "https://openlibrary.org/api";
//    /books?bibkeys=ISBN:0385342454&format=json

    @GET("/books?format=json&jscmd=data")
    public void getBook(@Query("bibkeys") String key, Callback<BookResponse> callback);


}

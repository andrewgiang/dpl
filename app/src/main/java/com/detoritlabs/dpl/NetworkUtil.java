package com.detoritlabs.dpl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;

import com.detoritlabs.dpl.model.Channel;
import com.google.gson.Gson;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;


/**
 * Created by andrewgiang on 7/11/14.
 * "http://www.detroit.lib.mi.us/news/rss.xml"
 */
public class NetworkUtil {
    private static Gson gson;

    public static void fetchRss(Context context, String url, FutureCallback<String> callback){
        Ion.with(context).load(url).
                asString().
                setCallback(callback);
    }

    public static Channel getChannel(String s) throws JSONException {
        JSONObject jsonObject = XML.toJSONObject(s);
        String jsonString = jsonObject.getJSONObject("rss").getJSONObject("channel").toString();
        Gson gson = getGsonInstance();
        return gson.fromJson(jsonString, Channel.class);
    }

    public static Gson getGsonInstance(){
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public static final char NBSP = (char) 160;
    public static final char SPACE = (char) 32;
    public static final char IMG = (char) 65532;

    public static CharSequence stripHtml(String s) {
        return Html.fromHtml(s).toString().replace('\n', SPACE)
                .replace(NBSP, SPACE).replace(IMG, SPACE).trim();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

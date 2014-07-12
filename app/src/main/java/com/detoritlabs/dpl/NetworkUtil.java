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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


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

    public static String resizeImage(String s) {
        // I don't give two half fucks how bad of a way this is to resize images YALL CANT CONTROL ME
        return s.replace("width=\"", "width=\"100%\" data-null1=\"").replace("height=\"","data-null2=\"");
    }

    public static boolean hasReadMore(String s) {
        Document doc = Jsoup.parse(s);
        Elements elements = doc.select("*");
        for (Element element : elements) {
            if(element.ownText().equals( "read more" ) && element.tagName().equals("a")){
                return true;
            }
        }
        return false;
    }

    public static String getReadMoreLink(String s){
        Document doc = Jsoup.parse(s);
        Elements elements = doc.select("*");
        for (Element element : elements) {
            if(element.ownText().equals( "read more" ) && element.tagName().equals("a")){
                return element.attr("href");
            }
        }
        return "";
    }

    public static String fixBranch(String s){
        Document doc = Jsoup.parse(s);
        Elements elements = doc.select("*");
        for (Element element : elements) {
            if(element.attr("class").equals("title node-title")){
                element.attr("style", "display: inline-block; font-size: 1em;");
            }
        }
        return doc.toString();

    }
    public static String removeReadMore(String s) {
        return s.replace("read more", "");
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
         return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}

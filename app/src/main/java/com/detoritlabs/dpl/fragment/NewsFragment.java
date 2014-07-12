package com.detoritlabs.dpl.fragment;



import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.detoritlabs.dpl.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class NewsFragment extends Fragment {


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsFragment.
     */
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public NewsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        JSONObject jsonObj = null;
                        URL url = new URL("http://www.detroit.lib.mi.us/news/rss.xml");
                        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                        String str;
                        String text = "";
                        while ((str = in.readLine()) != null) {
                            text += str + "\n";
                        }
                        in.close();
                        try {
                            jsonObj = XML.toJSONObject(text);

                            //System.out.println(jsonObj.toString());

                        } catch (JSONException e) {
                            Log.e("JSON exception", e.getMessage());
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }


}

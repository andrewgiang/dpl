package com.detoritlabs.dpl.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.detoritlabs.dpl.NetworkUtil;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.adapter.LocationAdapter;
import com.detoritlabs.dpl.model.Location;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by andrewgiang on 7/12/14.
 */
public class LocationFragment extends Fragment {

    @InjectView(R.id.list)
    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_locations, container, false);
        ButterKnife.inject(this, root);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open("locations.json");
            Reader reader = new InputStreamReader(is, "UTF-8");
            Type listType = new TypeToken<List<Location>>() {}.getType();
            List<Location> locations = NetworkUtil.getGsonInstance().fromJson(reader, listType);
            listView.setAdapter(new LocationAdapter(getActivity(), locations));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}

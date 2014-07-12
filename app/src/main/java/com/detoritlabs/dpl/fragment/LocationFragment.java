package com.detoritlabs.dpl.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.detoritlabs.dpl.NetworkUtil;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.activity.MainActivity;
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
public class LocationFragment extends Fragment implements TextWatcher {

    @InjectView(R.id.list)
    ListView listView;
    @InjectView(R.id.input_search)
    EditText searchEditText;


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

            searchEditText.addTextChangedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        LocationAdapter adapter = (LocationAdapter) listView.getAdapter();
        adapter.getFilter().filter(charSequence);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}

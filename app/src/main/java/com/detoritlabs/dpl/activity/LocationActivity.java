package com.detoritlabs.dpl.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.fragment.LocationFragment;

/**
 * Created by andrewgiang on 7/12/14.
 */
public class LocationActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        replace(new LocationFragment());
    }

    public void replace(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}

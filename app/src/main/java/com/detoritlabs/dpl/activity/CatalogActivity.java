package com.detoritlabs.dpl.activity;

import android.app.Activity;
import android.os.Bundle;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.fragment.CatalogFragment;

public class CatalogActivity extends Activity {

    public static final String KEY_TYPE = "KEY_TYPE";
    public static final String KEY_URL = "KEY_URL";
    public static final int TYPE_SEARCH = 1;
    public static final int TYPE_HOME = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        if(getIntent() != null){
            int type = getIntent().getIntExtra(KEY_TYPE, TYPE_HOME);
            String url = getIntent().getStringExtra(KEY_URL);
            CatalogFragment fragment = CatalogFragment.newInstance(type, url);
            getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

        }
    }


}

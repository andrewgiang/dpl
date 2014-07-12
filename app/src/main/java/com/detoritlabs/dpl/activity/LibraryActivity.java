package com.detoritlabs.dpl.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.fragment.LibraryFragment;


public class LibraryActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        replace(new LibraryFragment());
    }


    public void replace(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.libraryContainer, fragment).commit();
    }
}

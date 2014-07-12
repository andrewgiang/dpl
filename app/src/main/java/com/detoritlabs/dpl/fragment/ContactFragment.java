package com.detoritlabs.dpl.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.activity.LocationActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactFragment extends Fragment {


    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
    public ContactFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.inject(this, root);
        return root;
    }


    @OnClick(R.id.locationButton)
    public void onLibraryButtonClick(){
        Intent intent = new Intent(getActivity(), LocationActivity.class);
        startActivity(intent);
    }

}

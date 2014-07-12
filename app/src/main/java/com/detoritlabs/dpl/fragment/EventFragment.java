package com.detoritlabs.dpl.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.detoritlabs.dpl.EventAdapter;
import com.detoritlabs.dpl.FakeModelGenerator;
import com.detoritlabs.dpl.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment {
    @InjectView(R.id.list)
    ListView mListView;

    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.inject(this, root);
        //TODO get real events
        mListView.setAdapter(new EventAdapter(getActivity(), FakeModelGenerator.getEvents()));

        return root;
    }


}

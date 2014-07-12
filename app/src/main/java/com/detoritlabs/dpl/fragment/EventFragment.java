package com.detoritlabs.dpl.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.detoritlabs.dpl.EventAdapter;
import com.detoritlabs.dpl.NetworkUtil;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.Channel;
import com.koushikdutta.async.future.FutureCallback;

import org.json.JSONException;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment {
    private static final String TAG = EventFragment.class.getName();
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
        NetworkUtil.fetchRss(getActivity(), "http://www.detroit.lib.mi.us/events/rss.xml", new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String s) {
                try {
                    final Channel channel = NetworkUtil.getChannel(s);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mListView.setAdapter(new EventAdapter(getActivity(), channel.getItem()));
                        }
                    });
                } catch (JSONException e1) {
                    Log.e(TAG, e.toString());
                }
            }
        });

        return root;
    }




}

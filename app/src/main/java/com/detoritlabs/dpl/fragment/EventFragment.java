package com.detoritlabs.dpl.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.detoritlabs.dpl.activity.EventDetailActivity;
import com.detoritlabs.dpl.adapter.EventAdapter;
import com.detoritlabs.dpl.adapter.NewsAdapter;
import com.detoritlabs.dpl.NetworkUtil;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.Channel;
import com.detoritlabs.dpl.model.RssItem;
import com.koushikdutta.async.future.FutureCallback;

import org.json.JSONException;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment implements AdapterView.OnItemClickListener {
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

        mListView.setOnItemClickListener(this);
        return root;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        EventAdapter adapter = (EventAdapter) mListView.getAdapter();
        RssItem item = adapter.getItem(i);
        Intent intent = new Intent(getActivity(), EventDetailActivity.class);
        intent.putExtra(EventDetailActivity.RSS_ITEM, item);
        startActivity(intent);


    }
}

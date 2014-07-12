package com.detoritlabs.dpl.fragment;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.detoritlabs.dpl.adapter.NewsAdapter;
import com.detoritlabs.dpl.NetworkUtil;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.Channel;
import com.koushikdutta.async.future.FutureCallback;

import org.json.JSONException;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class NewsFragment extends Fragment {
    @InjectView(R.id.list)
    ListView mListView;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public NewsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.inject(this, root);
        NetworkUtil.fetchRss(getActivity(), "http://www.detroit.lib.mi.us/news/rss.xml", new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String s) {
                if (e == null) {
                    try {
                        final Channel channel = NetworkUtil.getChannel(s);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListView.setAdapter(new NewsAdapter(getActivity(), channel.getItem()));

                            }
                        });
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        return root;
    }


}

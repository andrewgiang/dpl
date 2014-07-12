package com.detoritlabs.dpl.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.detoritlabs.dpl.activity.RssDetailActivity;
import com.detoritlabs.dpl.adapter.NewsAdapter;
import com.detoritlabs.dpl.NetworkUtil;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.Channel;
import com.detoritlabs.dpl.model.RssItem;
import com.koushikdutta.async.future.FutureCallback;

import org.json.JSONException;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener {
    @InjectView(R.id.list)
    ListView mListView;
    @InjectView(R.id.progress)
    ProgressBar mProgressBar;
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
        View root = inflater.inflate(R.layout.fragment_news_feed, container, false);
        ButterKnife.inject(this, root);
        mListView.setOnItemClickListener(this);
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
                                mProgressBar.setVisibility(View.GONE);
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


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        NewsAdapter adapter = (NewsAdapter) mListView.getAdapter();
        RssItem item = adapter.getItem(i);
        Intent intent = new Intent(getActivity(), RssDetailActivity.class);
        intent.putExtra(RssDetailActivity.RSS_ITEM, item);
        startActivity(intent);
    }
}

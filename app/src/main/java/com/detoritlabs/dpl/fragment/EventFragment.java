package com.detoritlabs.dpl.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.detoritlabs.dpl.activity.RssDetailActivity;
import com.detoritlabs.dpl.adapter.EventAdapter;
import com.detoritlabs.dpl.NetworkUtil;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.Channel;
import com.detoritlabs.dpl.model.RssItem;
import com.koushikdutta.async.future.FutureCallback;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.GregorianCalendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = EventFragment.class.getName();
    @InjectView(R.id.list)
    ListView mListView;
    @InjectView(R.id.progress)
    ProgressBar mProgressBar;
    @InjectView(R.id.internet_connection_error)
    RelativeLayout errorView;



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
        View root = inflater.inflate(R.layout.fragment_news_feed, container, false);
        ButterKnife.inject(this, root);
        NetworkUtil.fetchRss(getActivity(), "http://www.detroit.lib.mi.us/events/rss.xml", new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String s) {
                        try {
                            Channel channel1 = NetworkUtil.getChannel(s);
                            String oldDate = "";
                            for (int x = 0; x < channel1.getItem().size(); x += 2) {
                                String newDate;
                                Document parse = Jsoup.parse(channel1.getItem().get(x).getDescription());
                                Elements elementsByClass = parse.getElementsByClass("date-display-single");
                                Element dateElement = elementsByClass.get(0);
                                String dateText = dateElement.text();
                                String[] split = dateText.split("-", 2);
                                newDate = split[0];
                                if (!newDate.equals(oldDate)) {
                                    RssItem item = new RssItem(channel1.getItem().get(x).getTitle(), channel1.getItem().get(x).getLink(), channel1.getItem().get(x).getDescription());
                                    item.setIsOnlyDate(true);
                                    item.setPubDate(channel1.getItem().get(x).getPubDate());
                                    oldDate = newDate;
                                    channel1.getItem().add(x, item);
                                }
                            }
                            final Channel channel = channel1;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mListView.setAdapter(new EventAdapter(getActivity(), channel.getItem()));
                                    mProgressBar.setVisibility(View.GONE);
                                }
                            });
                        } catch (JSONException e1) {
                            Log.e(TAG, e.toString());
                        }
                    }
                });

        mListView.setOnItemClickListener(this);
        mListView.setItemsCanFocus(true);
        return root;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        EventAdapter adapter = (EventAdapter) mListView.getAdapter();
        RssItem item = adapter.getItem(i);
        Intent intent = new Intent(getActivity(), RssDetailActivity.class);
        intent.putExtra(RssDetailActivity.RSS_ITEM, item);
        startActivity(intent);
    }
}

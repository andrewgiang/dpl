package com.detoritlabs.dpl.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.RssItem;
import com.laurencedawson.activetextview.ActiveTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RssDetailActivity extends Activity {

    public static final String RSS_ITEM = "RSS_ITEM";
    RssItem mEventItem;

    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.desc)
    ActiveTextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_detail);
        ButterKnife.inject(this);
        if(getIntent() != null){
            mEventItem = getIntent().getParcelableExtra(RSS_ITEM);
            mTitle.setText(mEventItem.getTitle());
            mDescription.setText(Html.fromHtml(mEventItem.getDescription(), new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String s) {
                    return getResources().getDrawable(android.R.drawable.screen_background_dark_transparent);
                }
            }, null));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.detoritlabs.dpl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.model.RssItem;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EventDetailActivity extends Activity {

    public static final String RSS_ITEM = "RSS_ITEM";
    RssItem mEventItem;

    @InjectView(R.id.title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.inject(this);
        if(getIntent() != null){
            mEventItem = getIntent().getParcelableExtra(RSS_ITEM);
            mTitle.setText(mEventItem.getTitle());
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

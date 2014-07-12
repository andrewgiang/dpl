package com.detoritlabs.dpl.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.detoritlabs.dpl.NetworkUtil;
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
    WebView mDescription;

    Boolean showReadMore = true;

    private String readMoreLink;


    private void openLink(String link) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_detail);
        ButterKnife.inject(this);
        if(getIntent() != null){
            mEventItem = getIntent().getParcelableExtra(RSS_ITEM);
            mTitle.setText(mEventItem.getTitle());
            mDescription.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            String html = "<style type=\"text/css\">" +
                    "body{color: #8d8d8d;}" +
                    ".node{display: inline-block;}" +
                    ".node-inner{display: inline-block;}" +
                    "h2{display: inline-block; margin: 0; padding: 0;}" +
                    "a{color: #2C8E08}" +
                    ".field-field-map{display: none;}" +
                    "fieldset{border-top: 1px solid #8d8d8d; border-bottom: 1px solid #8d8d8d; border-left: 0; border-right: 0; background-color: #e8e8e8 }" +
                    "</style><body>" +
                    NetworkUtil.resizeImage(mEventItem.getDescription()) +
                    "</body>";
            if(NetworkUtil.hasReadMore(html)){
                readMoreLink = NetworkUtil.getReadMoreLink(html);
                html = NetworkUtil.removeReadMore(html);
            }else{
                showReadMore = false;
            }
            html = NetworkUtil.fixBranch(html);
            mDescription.loadData( html, "text/html", "utf-8");
            mDescription.setBackgroundColor(0x00000000);
            mDescription.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rss_detail_activity_actions, menu);
        MenuItem item = menu.findItem(R.id.action_open);
        item.setVisible(showReadMore);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_open) {
            openLink(readMoreLink);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

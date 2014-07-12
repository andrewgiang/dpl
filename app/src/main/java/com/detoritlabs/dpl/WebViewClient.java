package com.detoritlabs.dpl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;

import com.detoritlabs.dpl.fragment.CatalogFragment;

/**
 * Created by andrewgiang on 7/11/14.
 */
public class WebViewClient extends android.webkit.WebViewClient{
    private final Context mContext;

    public WebViewClient(Context context) {
        mContext = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.contains(CatalogFragment.CATALOG_URL)){
            return false;
        }
        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mContext.startActivity(intent);
        return true;
    }
}

package com.detoritlabs.dpl.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.WebViewClient;
import com.detoritlabs.dpl.activity.CatalogActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class CatalogFragment extends Fragment {
    @InjectView(R.id.webview)
    WebView mWebView;

    public static String HOME_URL = "http://dplopac.detroitpubliclibrary.org/";

    public static String SEARCH_URL = "http://dplopac.detroitpubliclibrary.org/uhtbin/cgisirsi/x/0/0/57/5?user_id=webserver&searchdata1=";

    private int type;
    private String url;

    public static CatalogFragment newInstance(int type, String url) {
        CatalogFragment fragment = new CatalogFragment();
        Bundle args = new Bundle();
        args.putInt(CatalogActivity.KEY_TYPE, type);
        args.putString(CatalogActivity.KEY_URL, url);
        fragment.setArguments(args);

        return fragment;
    }

    public CatalogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            type = getArguments().getInt(CatalogActivity.KEY_TYPE);
            url = getArguments().getString(CatalogActivity.KEY_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dplcheckout, container, false);
        ButterKnife.inject(this, root);
        if(type == CatalogActivity.TYPE_SEARCH){
            startWebView(url);
        }else{
            startWebView(HOME_URL);
        }
        return root;
    }


    public void startWebView(String url){
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(getActivity()));
        mWebView.loadUrl(url);

    }

}

package com.detoritlabs.dpl.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.WebViewClient;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class CatalogFragment extends Fragment {
    @InjectView(R.id.webview)
    WebView mWebView;

    public static String CATALOG_URL = "http://dplopac.detroitpubliclibrary.org/";

    public static CatalogFragment newInstance() {
        CatalogFragment fragment = new CatalogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CatalogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dplcheckout, container, false);
        ButterKnife.inject(this, root);
        startWebView();
        return root;
    }


    public void startWebView(){
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(getActivity()));
        mWebView.loadUrl(CATALOG_URL);

    }

}

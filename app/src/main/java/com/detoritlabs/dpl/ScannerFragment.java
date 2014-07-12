package com.detoritlabs.dpl;

import android.app.Application;
import android.app.Fragment;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.detoritlabs.dpl.activity.BookDetailActivity;
import com.detoritlabs.dpl.activity.CatalogActivity;
import com.detoritlabs.dpl.api.model.BookResponse;
import com.detoritlabs.dpl.fragment.CatalogFragment;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ScannerFragment extends Fragment implements
        ZXingScannerView.ResultHandler, Callback<BookResponse> {
    private static final String FLASH_STATE = "FLASH_STATE";
    private static final String AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE";
    private static final String SELECTED_FORMATS = "SELECTED_FORMATS";
    private static final String TAG = ScannerFragment.class.getName();
    private ZXingScannerView mScannerView;
    private boolean mFlash;
    private boolean mAutoFocus;
    private ArrayList<Integer> mSelectedIndices;
    private String isbn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mScannerView = new ZXingScannerView(getActivity());
        if(state != null) {
            mFlash = state.getBoolean(FLASH_STATE, false);
            mAutoFocus = state.getBoolean(AUTO_FOCUS_STATE, true);
            mSelectedIndices = state.getIntegerArrayList(SELECTED_FORMATS);
        } else {
            mFlash = false;
            mAutoFocus = true;
            mSelectedIndices = null;
        }
        setupFormats();
        return mScannerView;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setHasOptionsMenu(true);
    }



    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        mScannerView.setFlash(mFlash);
        mScannerView.setAutoFocus(mAutoFocus);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FLASH_STATE, mFlash);
        outState.putBoolean(AUTO_FOCUS_STATE, mAutoFocus);
        outState.putIntegerArrayList(SELECTED_FORMATS, mSelectedIndices);
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        switch (rawResult.getBarcodeFormat()){
            case EAN_13:
                App application = (App) getActivity().getApplication();
                isbn = rawResult.getText();
                application.getOpenLibApi().getBook("ISBN:"+ isbn, this);
                break;
            default:
                Toast.makeText(getActivity(), "Unable to scan", Toast.LENGTH_SHORT).show();
                mScannerView.startCamera();
                mScannerView.setFlash(mFlash);
                mScannerView.setAutoFocus(mAutoFocus);
                break;
        }
    }







    public void setupFormats() {
        List<BarcodeFormat> formats = new ArrayList<BarcodeFormat>();

        formats.add(BarcodeFormat.EAN_13);
        if(mScannerView != null) {
            mScannerView.setFormats(formats);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();

    }

    @Override
    public void success(BookResponse bookResponse, Response response) {
//        Toast.makeText(getActivity(),"s", Toast.LENGTH_LONG).show();
        if(bookResponse.getBook() == null) {
            Toast.makeText(getActivity(), "Unable to parse book info from isbn launching catalog", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), CatalogActivity.class);
            i.putExtra(CatalogActivity.KEY_URL, CatalogFragment.SEARCH_URL+isbn);
            i.putExtra(CatalogActivity.KEY_TYPE, CatalogActivity.TYPE_SEARCH);
            startActivity(i);
        }else{
            Intent i = new Intent(getActivity(), BookDetailActivity.class);
            i.putExtra("BOOK", bookResponse.getBook());
            i.putExtra("ISBN", isbn);
            startActivity(i);
        }
    }

    @Override
    public void failure(RetrofitError error) {
//        Toast.makeText(getActivity(),"f", Toast.LENGTH_LONG).show();

    }
}
package com.detoritlabs.dpl.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.astuetz.PagerSlidingTabStrip;
import com.detoritlabs.dpl.App;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.ScannerFragment;
import com.detoritlabs.dpl.Section;
import com.detoritlabs.dpl.api.OpenLibraryApi;
import com.detoritlabs.dpl.api.model.BookResponse;
import com.detoritlabs.dpl.fragment.CatalogFragment;
import com.detoritlabs.dpl.fragment.ContactFragment;
import com.detoritlabs.dpl.fragment.EventFragment;
import com.detoritlabs.dpl.fragment.NewsFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.pager)
    ViewPager mViewPager;

    @InjectView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mViewPager.setAdapter(new MainFragmentPagerAdapter(getFragmentManager()));
        tabs.setViewPager(mViewPager);
        tabs.setOnPageChangeListener(this);
//;
//        OpenLibraryApi openLibraryApi = ((App)getApplication()).getOpenLibApi();
//        openLibraryApi.getBook("ISBN:0385472579", new Callback<BookResponse>() {
//            @Override
//            public void success(BookResponse bookResponse, Response response) {
//                Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Toast.makeText(MainActivity.this, "FAIL", Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        setTitle(Section.values()[i].getTitle());
    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public static class MainFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider{

        public MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (Section.values()[position]) {
                case Events:
                    return EventFragment.newInstance();
                case News:
                    return NewsFragment.newInstance();
                case More:
                    return ContactFragment.newInstance();
                case Scanner:
                    return new ScannerFragment();

                default: return NewsFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return Section.values().length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Section.values()[position].name();
        }

        @Override
        public int getPageIconResId(int i) {
            return Section.values()[i].getIcon();
        }
    }
}

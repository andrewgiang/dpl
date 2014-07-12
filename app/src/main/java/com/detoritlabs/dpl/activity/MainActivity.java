package com.detoritlabs.dpl.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;


import com.astuetz.PagerSlidingTabStrip;
import com.detoritlabs.dpl.R;
import com.detoritlabs.dpl.Section;
import com.detoritlabs.dpl.fragment.CatalogFragment;
import com.detoritlabs.dpl.fragment.ContactFragment;
import com.detoritlabs.dpl.fragment.EventFragment;
import com.detoritlabs.dpl.fragment.NewsFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity {

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



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public static class MainFragmentPagerAdapter extends FragmentPagerAdapter{

        public MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (Section.values()[position]) {
                case EVENTS:
                    return EventFragment.newInstance();
                case NEWS:
                    return NewsFragment.newInstance();
                case MORE:
                    return ContactFragment.newInstance();
                case CATALOG:
                    return CatalogFragment.newInstance();

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
    }
}

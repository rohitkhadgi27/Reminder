package com.rohitkhadgi27.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.rohitkhadgi27.myapplication.tabs.SlidingTabLayout;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolBar;
    private SlidingTabLayout mtabs;
    ViewPager viewPager;
    TextView v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fm));

        toolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);
        
        mtabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mtabs.setDistributeEvenly(true);
        mtabs.setCustomTabView(R.layout.custom_tab_view, R.id.tab_text);
        mtabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        mtabs.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        mtabs.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case R.id.action_settings:
                return true;
            case R.id.action_add_work:
                Intent addWork = new Intent(MainActivity.this, AddWorkActivity.class);
                startActivity(addWork);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class MyAdapter extends FragmentPagerAdapter {

        String[] tabs;

        public MyAdapter(FragmentManager fm) {

            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);

        }

        @Override
        public Fragment getItem(int i) {

            Fragment fragment = null;
            switch (i) {
                case 0:
                    fragment = new RemainingFragment();
                    return fragment;

                case 1:
                    fragment = new CompletedFragment();
                    return fragment;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}


























/*public class MainActivity extends FragmentActivity implements RemainingFragment.Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RemainingFragment frag = new RemainingFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.add(R.id.container, frag).commit();
    }

    @Override
    public void respond(String data) {

        Bundle bundle = new Bundle();
        bundle.putString("finalData", data);
        //Log.d("check", "asdf=" + data);
        CompletedFragment frag = new CompletedFragment();
        frag.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.container, frag).commit();


    }
}*/
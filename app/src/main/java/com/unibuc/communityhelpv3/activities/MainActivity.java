package com.unibuc.communityhelpv3.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.unibuc.communityhelpv3.MyApplication;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.fragments.FavoritePeopleFragment;
import com.unibuc.communityhelpv3.fragments.TasksFragment;
import com.unibuc.communityhelpv3.fragments.OtherTasksFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    private TabLayout tabLayout;

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, String.valueOf(getFragmentManager().getBackStackEntryCount()));

        if(getFragmentManager().getBackStackEntryCount() == 0) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkAuth()) {
            checkIfLocationExists();
        }
        initLayout();
    }

    private void checkIfLocationExists() {

        if(!((MyApplication) getApplication()).getPrefManager().isLocationInserted()) {
            Intent intent = new Intent(this, PlacePickersActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private boolean checkAuth() {
        if(((MyApplication) getApplication()).getPrefManager().getFacebookToken() == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return false;
        } else {
            Log.e(TAG, "null Token");
        }
        return true;
    }

    private void initLayout() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.layout_action_bar);

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, AddTaskActivity.class));
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /*
        MenuItem item = menu.findItem(R.id.notification_button_action_bar);
        MenuItemCompat.setActionView(item, R.layout.layout_action_bar);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);

        TextView tv = (TextView) notifCount.findViewById(R.id.actionbar_notification_badge);
        tv.setText("12");
        */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.user_profile){
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        }
        else if (id == R.id.action_logout) {
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if(id == R.id.notification_button_action_bar) {
            Log.d(TAG, "notification button clicked");
            startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavoritePeopleFragment(), "Friends");
        adapter.addFragment(new OtherTasksFragment(), "Tasks");
        adapter.addFragment(new TasksFragment(), "Your tasks");
        viewPager.setAdapter(adapter);




    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final FragmentManager fragmentManager;


        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragmentManager = fm;
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
                return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

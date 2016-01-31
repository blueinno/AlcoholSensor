package com.blueino.android.unist;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.blueino.android.unist.adapter.PagerAdapter;
import com.blueino.android.unist.bluetooth.BlueToothActivity;
import com.blueino.android.unist.component.NonViewPager;
import com.blueino.android.unist.fragment.NavigationDrawerFragment;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends BlueToothActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private DrawerLayout mDrawerLayout;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private NonViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void createChidren() {
        super.createChidren();
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mViewPager = (NonViewPager) findViewById(R.id.viewPager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
    }

    protected void setProperties() {
        super.setProperties();

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout, mToolbar);
        mViewPager.setAdapter(mPagerAdapter);
    }

    //  =======================================================================================

    @Override
    protected void update(byte[] data) {
        float f = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        String temp = String.format("%.1f", f);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Log.e("rrobbie", "update : " + temp + " / " + dateFormat.format(calendar.getTime()));
    }

    @Override
    protected void updateUI() {

    }

    //  =========================================================================================

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if( mViewPager != null ) {
            mViewPager.setCurrentItem(position);
        }
    }

    //  =========================================================================================

}

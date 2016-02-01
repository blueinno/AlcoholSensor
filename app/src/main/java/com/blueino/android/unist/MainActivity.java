package com.blueino.android.unist;

import android.bluetooth.BluetoothDevice;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.blueino.android.unist.adapter.PagerAdapter;
import com.blueino.android.unist.bluetooth.BlueToothActivity;
import com.blueino.android.unist.component.NonViewPager;
import com.blueino.android.unist.fragment.DeviceListFragment;
import com.blueino.android.unist.fragment.DeviceFragment;
import com.blueino.android.unist.fragment.GraphFragment;
import com.blueino.android.unist.fragment.NavigationDrawerFragment;
import com.blueino.android.unist.fragment.SettingsFragment;
import com.blueino.android.unist.fragment.TerminalFragment;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends BlueToothActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private DrawerLayout mDrawerLayout;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private NonViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    private DeviceListFragment deviceListFragment;
    private DeviceFragment deviceFragment;
    private GraphFragment graphFragment;
    private SettingsFragment settingsFragment;
    private TerminalFragment terminalFragment;

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
    }

    protected void setProperties() {
        super.setProperties();

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout, mToolbar);

        deviceListFragment = new DeviceListFragment();
        deviceFragment = new DeviceFragment();
        graphFragment = new GraphFragment();
        settingsFragment = new SettingsFragment();
        terminalFragment = new TerminalFragment();

        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add( deviceFragment );
        fragments.add( graphFragment );
        fragments.add( settingsFragment );
        fragments.add( terminalFragment );
        fragments.add( deviceListFragment );

        mPagerAdapter = new PagerAdapter(this, getSupportFragmentManager(), fragments);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(mPagerAdapter);
    }

    //  =======================================================================================

    public void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position);
    }

    //  =======================================================================================

    @Override
    protected void update(byte[] data) {
        float f = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        String temp = String.format("%.1f", f);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Log.e("rrobbie", "update : " + temp + " / " + dateFormat.format(calendar.getTime()));

        terminalFragment.update(data);
        graphFragment.update(data);
    }

    @Override
    protected void updateUI() {
    }

    @Override
    protected void updateScan(BluetoothDevice device) {
        super.updateScan(device);

        deviceListFragment.setUp(device);
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

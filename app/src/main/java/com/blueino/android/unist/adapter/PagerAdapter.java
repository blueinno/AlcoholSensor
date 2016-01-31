package com.blueino.android.unist.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.blueino.android.unist.fragment.DevicesFragment;

import com.blueino.android.unist.fragment.GraphFragment;
import com.blueino.android.unist.fragment.SettingsFragment;
import com.blueino.android.unist.fragment.TerminalFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if( position == 0 ) return DevicesFragment.newInstance("1,", "2");
        if( position == 1 ) return SettingsFragment.newInstance("1,", "2");
        if( position == 2 ) return GraphFragment.newInstance("1,", "2");
        return TerminalFragment.newInstance("1,", "2");
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Devices";
    }
}

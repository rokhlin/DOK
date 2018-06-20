package com.selfapps.dok.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.selfapps.dok.view.fragment.PersonsFragment;
import com.selfapps.dok.view.fragment.PlacesFragment;
import com.selfapps.dok.view.fragment.RoutesFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class MainActivityPagerAdapter extends FragmentPagerAdapter {

    public MainActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PlacesFragment();
            case 1:
                return new PersonsFragment();
            case 2:
                return new RoutesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
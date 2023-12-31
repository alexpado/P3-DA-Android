package com.openclassrooms.entrevoisins.ui.neighbour.list;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.enums.NeighbourListMode;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (NeighbourListMode.fromPage(position)) {
            case ALL:
                return NeighbourFragment.newInstance();
            case FAVORITES:
                return FavoriteNeighbourFragment.newInstance();
            default:
                throw new IllegalStateException("Unknown mode");
        }
    }

    /**
     * @return the number of pages
     */
    @Override
    public int getCount() {

        return 2;
    }

}
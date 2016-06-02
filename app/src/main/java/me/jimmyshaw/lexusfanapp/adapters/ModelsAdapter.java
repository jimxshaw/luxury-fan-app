package me.jimmyshaw.lexusfanapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

// An adapter holds data and inflates data. The data to be used with this adapter comes from
// ModelsActivity. They comprise of a list of fragments and a list of tab titles. The adapter
// inflates the passed in data so they can be used with our viewpager items.
public class ModelsAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private List<String> mTabTitleList;

    public ModelsAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tabTitleList) {
        super(fm);
        mFragmentList = fragmentList;
        mTabTitleList = tabTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        // Return a particular fragment that'll be inflated based on the current position of the
        // viewpager item.
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        // This method should always return the number of items in the viewpager. That number is
        // determined by how many fragments are in our fragment list.
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitleList.get(position);
    }
}

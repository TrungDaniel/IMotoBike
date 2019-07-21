package com.example.imotobike.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class HomePagerAdapter extends FragmentPagerAdapter {
    Context context;
    ArrayList<Fragment> data;

    public HomePagerAdapter(FragmentManager fm, Context context, ArrayList<Fragment> data) {
        super(fm);
        this.context = context;
        this.data = data;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Tất cả";
            case 1:
                return "Thường xuyên";
        }
        return super.getPageTitle(position);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}

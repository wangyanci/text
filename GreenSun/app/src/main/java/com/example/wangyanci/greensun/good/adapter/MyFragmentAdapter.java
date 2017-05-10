package com.example.wangyanci.greensun.good.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wangyanci on 2017/5/1.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> list;

    public MyFragmentAdapter(FragmentManager fragmentManager, List<Fragment> list){
        super(fragmentManager);
        this.list=list ;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}

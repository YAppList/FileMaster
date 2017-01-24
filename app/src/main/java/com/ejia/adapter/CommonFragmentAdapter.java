package com.ejia.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/******************************************
 * 类描述： Fragment适配器 类名称：CommonFragmentAdapter
 *
 * @version: 1.0
 * @author: gl
 * @time: 2016-7-21 下午10:32:31
 ******************************************/
public class CommonFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments ;
    private List<String> mTitles;

    public List<Fragment> getFragments() {
        return mFragments;
    }

    public void setFragments(List<Fragment> mFragments) {
        this.mFragments = mFragments;
    }

    public List<String> getTitles() {
        return mTitles;
    }

    public void setTitles(List<String> mTitles) {
        this.mTitles = mTitles;
    }
    public CommonFragmentAdapter(FragmentManager fm, int viewPagerId) {
        super(fm);
        initFragmentManager(fm,viewPagerId);
    }
    public CommonFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles, int viewPagerId) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
//        initFragmentManager(fm,viewPagerId);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    private void initFragmentManager(FragmentManager fm, int viewPagerId) {
        if(mFragments==null||mFragments.isEmpty()){
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            String name = makeFragmentName(viewPagerId, i);
            Fragment fragment = fm.findFragmentByTag(name);
            if (fragment != null) {
                ft.detach(fragment);
                ft.remove(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }
    /**
     * 获取viewPage里面的Fragment类名
     * @param viewId
     * @param id
     * @return
     */
    private String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
    @Override
    public Fragment getItem(int position) {
        if(mFragments==null||mFragments.isEmpty()){
            return null;
        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if(mFragments==null||mFragments.isEmpty()){
            return 0;
        }
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(mTitles==null||mTitles.isEmpty()){
            return "";
        }
        return mTitles.get(position);
    }
}

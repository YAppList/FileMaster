package com.ejia.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.ejia.adapter.CommonFragmentAdapter;
import com.example.yangzhongyu.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzhongyu on 2017/1/22.
 */
public class EjiaMainActivity extends FragmentActivity {

    private CommonFragmentAdapter mCommonFragmentAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private SignFragment mSignFragment = new SignFragment();//我要签单
    private SelfFrament mSelfFragment = new SelfFrament();//个人中心

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejia_main);

        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_title);

        mViewPager.setOffscreenPageLimit(2);
        setupViewPager();
    }

    private void setupViewPager() {
        List<String>  titleList = new ArrayList<>();
        List<Fragment> tabFragment = new ArrayList<>();

        titleList.add("我要装修");
        tabFragment.add(mSignFragment);

        titleList.add("个人中心");
        tabFragment.add(mSelfFragment);

        mCommonFragmentAdapter = new CommonFragmentAdapter(getSupportFragmentManager(),tabFragment,titleList,mViewPager.getId());
        mViewPager.setAdapter(mCommonFragmentAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }
}

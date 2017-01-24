package com.rupeng.view;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangzhongyu.myapplication.R;

import java.util.ArrayList;


public class FileMasterTabActivity extends FragmentActivity {
    private ViewPager mPager;
    public ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    public MyFragmentPagerAdapter mFragmentPagerAdapter;

    private ImageView image;
    private TextView view1,view2,view3;
    private int currIndex;
    private int imageWith;
    private int offset;
    FileCategoryFragment firstFragment = FileCategoryFragment.newInstance("");
    FileExplorerFragment secondFragment = FileExplorerFragment.newInstance("");
    NetworkFileFragment thirdFragment = NetworkFileFragment.newInstance("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filemaster);
        getWindow().setBackgroundDrawable(null);
        initTextView();
        initImage();
        initViewPager();

    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
        if(firstFragment.isVisible()){
            firstFragment.showCategoryView();
        }
    }

    /**
     * 初始化图片位移像素
     */
    private void initImage(){
        //image为指示表
        image = (ImageView) findViewById(R.id.indicator);
        image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageWith = image.getWidth();

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int sceenW = dm.widthPixels;
                offset = (sceenW / 3 - imageWith)/2;

                //imageivew 设置平移，使下划线平移到初始位置（平移一个offset）
                Matrix matrix = new Matrix();
                matrix.postTranslate(offset,0);
                image.setScaleType(ImageView.ScaleType.MATRIX);//必不可少
                 //一定要用src
                image.setImageMatrix(matrix);
              //   image.setTranslationX(offset);

            }
        });
    }

    private  void initViewPager(){
        mPager = (ViewPager) findViewById(R.id.viewpager);
        firstFragment = FileCategoryFragment.newInstance("");
        secondFragment = FileExplorerFragment.newInstance("");
        thirdFragment = NetworkFileFragment.newInstance("");

        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        mPager.setAdapter(mFragmentPagerAdapter);
        mPager.setCurrentItem(0);

        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    private void initTextView() {
        view1 = (TextView) findViewById(R.id.guid1);
        view2 = (TextView) findViewById(R.id.guid2);
        view3 = (TextView) findViewById(R.id.guid3);;

        view1.setOnClickListener(new TxListener(0));
        view2.setOnClickListener(new TxListener(1));
        view3.setOnClickListener(new TxListener(2));

    }

    @Override
    protected void onResume() {

        super.onResume();

    }

    private class TxListener implements View.OnClickListener {
        private int index=0;
        public TxListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View view) {
            mPager.setCurrentItem(index);
        }
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;
        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
            super(fm);
            this.list = fragmentList;

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            //return false ;//不显示
           // return true;//显示后瞬间消失
            return  super.isViewFromObject(view,o);///正常显示
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

             int one = offset*2 + imageWith;//两个相邻页面的偏移量

            Animation animation = new TranslateAnimation(currIndex*one,i*one,0,0);
            currIndex = i;
            animation.setFillAfter(true);
            animation.setDuration(200);
            //指示标移动
            image.setAnimation(animation);
         //   image.setTranslationX(0);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}

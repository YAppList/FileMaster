package com.example.yangzhongyu.myapplication;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;
//import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by yangzhongyu on 2016/8/24.
 */

public class MyApplication extends Application {
    public  RefWatcher refWatcher = null;
    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        CrashReport.initCrashReport(getApplicationContext(), "900053101", false);
    }
}

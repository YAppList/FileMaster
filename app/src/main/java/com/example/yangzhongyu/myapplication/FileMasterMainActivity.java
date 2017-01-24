package com.example.yangzhongyu.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;

import java.lang.ref.WeakReference;

/**
 * 此示例用于演示Activity泄漏
 */
public class FileMasterMainActivity extends Activity {

   byte[] mBigData = new byte[64*1024*1024];//64M

    EditText text ;

  /* static Handler mHandler = new Handler(){
        //在Handler这个静态内部类里面持有了外部类Actvity的引用
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            text.setText("oom");
        }
    };*/
    //
    static class MyHandler extends Handler{
        Context context;
        WeakReference<FileMasterMainActivity> weakReference = null;
        public  MyHandler(FileMasterMainActivity c){
         ///   context = c;
         //   c -> weakReference ->context;
            weakReference = new WeakReference<FileMasterMainActivity>(c);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(weakReference.get() != null){
                ((FileMasterMainActivity)weakReference.get()).text.setText("oom");
            }
        }
    }


    //Activity->handler->Message-->Messagequeue
    MyHandler innerHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        innerHandler.postDelayed(new Runnable(){
            @Override
            public void run() {

            }
        },100000);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //第一种解决方法
       // mHandler.removeCallbacksAndMessages(null);
        Object obj = new Object();
        ((MyApplication)getApplication()).refWatcher.watch(obj);

    }
}

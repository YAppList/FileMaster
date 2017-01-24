package com.rupeng.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
public class CardView extends View {

    interface  CallBack{
        void onSwipDone(int percent);
    }
    private  CallBack mCallback;
    public  void setCallback(CallBack c){
        mCallback = c;
    }
    int percent;
    public CardView(Context context)
    {
        this(context, null);
    }

    public CardView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CardView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(40);
    }

    private Path mPath = new Path();
    private Paint mPaint = new Paint();
    private float mLastX;
    private float mLastY;
    Canvas mCanvas = null;
    Bitmap mBitmap = null;
    volatile  boolean isDone = false;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String text = "谢谢品尝";

        Paint p = new Paint();
        Rect r = new Rect();
        p.setColor(Color.WHITE);
        p.setTextSize(80);
        p.getTextBounds(text,0,text.length(),r);
        int x = getWidth()/2 - r.width()/2;
        int y = getHeight()/2 + r.height()/2;
        canvas.drawText(text, x, y, p);

        if(!isDone){
            drawPath();
            canvas.drawBitmap(mBitmap,0,0,null);
        }else{
            if(mCallback !=null){
                mCallback.onSwipDone(percent);
            }
        }


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();


  //      int width = MeasureSpec.getSize(widthMeasureSpec);
   //     int height = MeasureSpec.getSize(heightMeasureSpec);

    //    int width = 1080;
    //    int height = 1500;



        mBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.GRAY);//离屏缓冲区

    }

    private void drawPath() {

         mPaint.setXfermode(new PorterDuffXfermode((PorterDuff.Mode.DST_OUT)));
         mCanvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                mPath.moveTo(mLastX,mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                mPath.lineTo(x,y);
                invalidate();//requestLayout

                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                new Thread(new CaculateBitmapRunnable()).start();
                break;

        }
        return  true;
    }


    private class CaculateBitmapRunnable implements Runnable {
        int[] pixes;
        int allPixesCount;
        int wipePixesCount;

        @Override
        public void run() {
            int w = getWidth();
            int h = getHeight();
            pixes = new int[w*h];
            mBitmap.getPixels(pixes,0,w,0,0,w,h);

            allPixesCount = w * h;
            for(int row = 0;row < h;row++){
                for(int cloum =0;cloum < w;cloum++){
                    int index = row * w + cloum;
                    if(pixes[index] == 0){
                        wipePixesCount ++;
                        percent = wipePixesCount * 100 / allPixesCount;
                    }
                }
            }
            if(percent > 20){
                //要清除残留的遮盖了
                isDone = true;
                postInvalidate();

            }

        }
    }
}

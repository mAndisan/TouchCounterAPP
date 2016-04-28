package com.touchcounterapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageCircle;
    private Bitmap bitmapCircle;
    private int mTouchTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageCircle = (ImageView) findViewById(R.id.counter_image);

        Bitmap bitMap = getResIcon(getResources(), R.mipmap.circle_yellow);
        bitmapCircle = generatorContactCountIcon(bitMap, 0);
        imageCircle.setImageBitmap(bitmapCircle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);

        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            mTouchTimes ++;
            Bitmap bitMap = getResIcon(getResources(), R.mipmap.circle_yellow);
            bitmapCircle = generatorContactCountIcon(bitMap, mTouchTimes);
            imageCircle.setImageBitmap(bitmapCircle);
        }

        return true;
    }

    /**
     * 根据id获取一个图片
     *
     * @param
     * @param resId
     * @return
     */
    private Bitmap getResIcon(Resources res, int resId) {
        Drawable icon;
        icon = res.getDrawable(resId);
        if (icon instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) icon;
            return bd.getBitmap();
        } else {
            return null;
        }
    }

    /**
     * 在给定的图片的右上角加上数字。数量用红色表示
     *
     * @param icon
     *            给定的图片
     * @return 带数字的图片
     */
    private Bitmap generatorContactCountIcon(Bitmap icon, int contacyCount) {
        // 初始化画布
        int iconSize = (int) getResources().getDimension(R.dimen.circle_yellow_size);
        Bitmap contactIcon = Bitmap.createBitmap(iconSize, iconSize,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(contactIcon);

// 拷贝图片
        Paint iconPaint = new Paint();
        iconPaint.setDither(true);// 防抖动
        iconPaint.setFilterBitmap(true);// 用来对Bitmap进行滤波处理，这样，当你选择Drawable时，会有抗锯齿的效果
        Rect src = new Rect(0, 0, icon.getWidth(), icon.getHeight());
        Rect dst = new Rect(0, 0, iconSize, iconSize);
        canvas.drawBitmap(icon, src, dst, iconPaint);

// 模拟数字
        if(contacyCount >= 100){
            Paint countPaint = new Paint(Paint.ANTI_ALIAS_FLAG
                    | Paint.DEV_KERN_TEXT_FLAG);
// 字体颜色
            countPaint.setColor(Color.BLACK);
// 字体大小
            countPaint.setTextSize(260f);
// 加粗
            countPaint.setTypeface(Typeface.DEFAULT_BOLD);
// 数字的显示位置
            canvas.drawText(String.valueOf(contacyCount), iconSize / 2 - 220, iconSize / 2 + 100,
                    countPaint);
        }else if (contacyCount >= 10) {
// 启用抗锯齿和使用设备的文本字距
            Paint countPaint = new Paint(Paint.ANTI_ALIAS_FLAG
                    | Paint.DEV_KERN_TEXT_FLAG);
// 字体颜色
            countPaint.setColor(Color.BLACK);
// 字体大小
            countPaint.setTextSize(260f);
// 加粗
            countPaint.setTypeface(Typeface.DEFAULT_BOLD);
// 数字的显示位置
            canvas.drawText(String.valueOf(contacyCount), iconSize / 2 - 150, iconSize / 2 + 100,
                    countPaint);
        } else {
// 启用抗锯齿和使用设备的文本字距
            Paint countPaint = new Paint(Paint.ANTI_ALIAS_FLAG
                    | Paint.DEV_KERN_TEXT_FLAG);
            countPaint.setColor(Color.BLACK);
            countPaint.setTextSize(300f);
            countPaint.setTypeface(Typeface.DEFAULT_BOLD);
// 数字的显示位置
            canvas.drawText(String.valueOf(contacyCount), iconSize / 2 - 85, iconSize / 2 + 110,
                    countPaint);
        }
        return contactIcon;
    }
}

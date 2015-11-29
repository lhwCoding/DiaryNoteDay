package com.dnd.diarynoteday.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import com.dnd.diarynoteday.app.BaseApplication;


/**
 * Created by hongwu on 2015/11/29.
 */
public class UIUtils {

public static Context getContext() {
    return BaseApplication.getContext();
}

public static Handler getHandler() {
    return BaseApplication.getMainThreadHandler();
}

public static Looper getMainThreadLooper() {
    return BaseApplication.getMainThreadLooper();
}

public static int getMainThreadId() {
    return BaseApplication.getMainThreadId();
}

public static Thread getMainThread() {
    return BaseApplication.getMainThread();
}

/**
 * 从资源文件中获取对象
 *
 * @return
 */
public static Resources getResources() {
    return getContext().getResources();
}

public static String getString(int id) {
    return getResources().getString(id);
}

public static String[] getStringArray(int id) {
    return getResources().getStringArray(id);
}

public static Drawable getDrawable(int id) {
    return getResources().getDrawable(id);
}

/**
 * dip To px
 *
 * @param dp
 * @return
 */
public static int dip2px(int dp) {
    // dp和px的转换关系
    float density = getResources().getDisplayMetrics().density;
    // 2*1.5+0.5 2*0.75 = 1.5+0.5
    return (int) (dp * density + 0.5);
}

/**
 * px To dip
 *
 * @param px
 * @return
 */
public static int px2dip(int px) {
    float density = getResources().getDisplayMetrics().density;
    return (int) (px / density + 0.5);
}

/**
 * 判断当前的线程是否在主线程中
 *
 * @return
 */
public static boolean isRunInMainThread() {
    return android.os.Process.myTid() == getMainThreadId();
}

/**
 * UI展示，保证处理UI的操作都在主线程中
 *
 * @param runnable
 */
public static void runInMainThread(Runnable runnable) {
    if (isRunInMainThread()) {
        // 直接跑在主线程中，直接调用任务的run方法，处理UI
        runnable.run();
    } else {
        // 获取handler，调用post方法，将任务传递进去执行
        getHandler().post(runnable);
    }
}

/**
 * 吐司
 *
 * @param context
 * @param msg
 */
public static void showToast(final Activity context, final String msg) {
    //吐司开关
    boolean flag = true;
    if (flag) {
        if ("main".equals(Thread.currentThread().getName())) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


/**
 * 针对不同的按钮状态显示不同的颜色，颜色选择器
 *
 * @param mTabTextColorResId
 * @return
 */
public static ColorStateList getColorStateList(int mTabTextColorResId) {
    return getResources().getColorStateList(mTabTextColorResId);
}


/**
 * view 转换 bitmap
 */
public static Bitmap getViewBitmap(View addViewContent) {
    addViewContent.setDrawingCacheEnabled(true);
    addViewContent.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
    addViewContent.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
    addViewContent.layout(0, 0,
            addViewContent.getMeasuredWidth(),
            addViewContent.getMeasuredHeight());
    addViewContent.buildDrawingCache();
    Bitmap cacheBitmap = addViewContent.getDrawingCache();
    Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
    return bitmap;
}


public static Bitmap getLocationViewBitmap(View addViewContent) {
    addViewContent.setDrawingCacheEnabled(true);
    addViewContent.measure(
            View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.EXACTLY));
    addViewContent.layout(0, 0,
            addViewContent.getMeasuredWidth(),
            addViewContent.getMeasuredHeight());

    addViewContent.buildDrawingCache();
    Bitmap cacheBitmap = addViewContent.getDrawingCache();
    Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
    return bitmap;
}


/**
 * 把图片变成圆角
 *
 * @param bitmap 转化成圆角的图片
 * @param pixels 圆角的数值 数值越大 圆角越大 20首页圆角 180 商城
 * @return
 */
public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(output);
    final int color = 0xff424242;
    final Paint paint = new Paint();
    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    final RectF rectF = new RectF(rect);
    final float roundPx = pixels;
    paint.setAntiAlias(true);
    canvas.drawARGB(0, 0, 0, 0);
    paint.setColor(color);
    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(bitmap, rect, rect, paint);
    return output;
}

/**
 * byte 转换成 bitmap
 *
 * @param b
 * @return
 */
public static Bitmap Bytes2Bitmap(byte[] b) {
    if (b.length != 0) {
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
    return null;
}

/**
 * Bitmap转换成Drawable
 *
 * @param bitmap
 * @return
 */
public static Drawable bitmap2Drawable(Bitmap bitmap) {
    BitmapDrawable bd = new BitmapDrawable(bitmap);
    Drawable d = (Drawable) bd;
    return d;
}

/**
 * 通过空格截取字符串
 *
 * @param value
 * @return
 */
public static String[] splitString(String value) {
    if (!TextUtils.isEmpty(value))
        return value.split(" ");
    return null;
}

private static long lastClickTime;

//防止重复点击。
public static boolean isFastDoubleClick() {
    long time = System.currentTimeMillis();
    long timeD = time - lastClickTime;
    if (0 < timeD && timeD < 800) {
        return true;
    }
    lastClickTime = time;
    return false;
}

/**
 * 获取屏幕的宽度
 *
 * @return
 */
public static int getScreenWidth() {
    DisplayMetrics dm = getResources().getDisplayMetrics();
    return dm.widthPixels;
}

/**
 * 把图片按照屏幕宽度缩放，使图片宽度缩放到屏幕宽度
 *
 * @param bm
 * @return
 */
public static Bitmap setScaleImage(Bitmap bm) {
    // 得到当前屏幕得到屏幕宽，按宽与图片的比例进行缩放，目的是使图片宽占满屏幕
    int srcWidth = getScreenWidth();
    // 缩放比例
    float scale = (float) srcWidth / bm.getWidth();
    // 缩放动作
    Matrix matrix = new Matrix();
    matrix.postScale(scale, scale);
    // 重构图片
    // 1.原始bitmap；2.3.第一个像素的x/y坐标；4.5.图片的原始宽高；
    // 6.matrix矩阵对象，包含了缩放比；7.如果真的需要过滤，仅适用于如果矩阵包含的不仅仅是转化。
    Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
            bm.getHeight(), matrix, true);
    return bitmap;
}
}
package com.dnd.diarynoteday.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.xutils.x;


/**
 * Created by hongwu on 2015/11/29.
 */
public class BaseApplication extends Application {

    //上下文环境
    private static Context context;
    //主线程ID
    private static int mainThreadId;
    //主线程对象
    private static Thread mainThread;
    //轮寻器
    private static Looper mainThreadLooper;
    //Handler
    private static Handler mainThreadHandler;
    public static Context getContext() {
        return context;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static Looper getMainThreadLooper() {
        return mainThreadLooper;
    }

    public static Handler getMainThreadHandler() {
        return mainThreadHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //获取主线程id
        mainThreadId = android.os.Process.myTid();
        //获取主线程对象
        mainThread = Thread.currentThread();
        //获取主线程轮寻器
        mainThreadLooper = getMainLooper();
        //获取handler对象
        mainThreadHandler = new Handler();
        x.Ext.init(this);
        x.Ext.setDebug(true);//是否输出日志

    }


}

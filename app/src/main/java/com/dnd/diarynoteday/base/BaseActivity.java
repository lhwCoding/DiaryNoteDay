package com.dnd.diarynoteday.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.dnd.diarynoteday.app.BaseApplication;
import com.dnd.diarynoteday.manager.MyActivityManager;

import org.xutils.x;

/**
 *
 */
public abstract class BaseActivity extends Activity {
    protected BaseApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyActivityManager.addActivity(this);
        x.view().inject(this);
        app = (BaseApplication) getApplication();
        init();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyActivityManager.removeActivity(this);
    }


    /**
     * 第一步要进行的初始化操作
     */
    protected abstract void init();

    /**
     * 初始化数据
     */
    protected abstract void initData();




}

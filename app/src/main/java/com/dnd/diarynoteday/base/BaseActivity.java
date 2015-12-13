package com.dnd.diarynoteday.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.app.BaseApplication;
import com.dnd.diarynoteday.manager.MyActivityManager;
import com.dnd.diarynoteday.manager.SystemBarTintManager;

import org.xutils.x;

/**
 *
 */
public abstract class BaseActivity extends Activity {
    protected BaseApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager.addActivity(this);

      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.drawable.loading_bg);//通知栏所需颜色
        }*/
        x.view().inject(this);
        app = (BaseApplication) getApplication();
        init();
        initData();
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
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

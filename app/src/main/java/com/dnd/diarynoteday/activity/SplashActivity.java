package com.dnd.diarynoteday.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.base.BaseActivity;
import com.dnd.diarynoteday.constant.Constant;

import org.xutils.view.annotation.ContentView;

/**
 * Created by hongwu on 2015/12/13.
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    private LinearLayout launch;
    private Animation fadeIn;
    private Animation fadeInScale;

    @Override
    protected void init() {
        launch = (LinearLayout) findViewById(R.id.llt_splash);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.splash_fade_in);
        fadeIn.setDuration(500);
        fadeIn.setFillAfter(true);
        fadeInScale = AnimationUtils.loadAnimation(this, R.anim.splash_fade_in_scale);
        fadeInScale.setDuration(3000);
        fadeInScale.setFillAfter(true);
        launch.startAnimation(fadeIn);
        setListener();
    }

    @Override
    protected void initData() {

    }


    private void setListener() {
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                launch.setAnimation(fadeInScale);
            }
        });
        fadeInScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                //动画完成后跳转到主界面
                Intent intent = null;
                if (sp.getBoolean(Constant.isFirstIn, true)) {
                    intent=new Intent(SplashActivity.this, GuideActivity.class);
                }else{
                    intent=new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }



    //屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

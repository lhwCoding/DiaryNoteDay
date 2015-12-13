package com.dnd.diarynoteday.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.base.BaseActivity;
import com.dnd.diarynoteday.constant.Constant;
import com.dnd.diarynoteday.utils.UIUtils;

import org.xutils.view.annotation.ContentView;


/**
 * Created by Administrator on 2015/4/13.
 * 帮助界面
 */
@ContentView(R.layout.activity_guide)
public class GuideActivity extends BaseActivity implements GestureDetector.OnGestureListener {
    private ViewFlipper flipper;
    private GestureDetector detector;
    private int position = 0; //定义变量记录位置
private Button bt_help;


    @Override
    protected void init() {

        detector = new GestureDetector((GestureDetector.OnGestureListener) this);
        flipper = new ViewFlipper(this);
        for (int i = 0; i < 4; i++) {
            flipper.addView(addTextView(i));
        }
        setContentView(flipper);


    }

    @Override
    protected void initData() {
      //  bt_help.setText("虾逛 "+ SystemUtils.getVersionName());
    }


    private View addTextView(int i) {
        View output = View.inflate(UIUtils.getContext(), R.layout.activity_guide, null);
        bt_help = (Button) output.findViewById(R.id.bt_help);
        Drawable dabg;
        switch (i) {
            case 0:
                dabg = this.getResources().getDrawable(R.drawable.guide1);
                output.setBackgroundDrawable(dabg);

                break;
            case 1:
                dabg = this.getResources().getDrawable(R.drawable.guide2);
                output.setBackgroundDrawable(dabg);

                break;
            case 2:
                dabg = this.getResources().getDrawable(R.drawable.guide3);
                output.setBackgroundDrawable(dabg);

                break;
            case 3:
                dabg = this.getResources().getDrawable(R.drawable.guide4);
                output.setBackgroundDrawable(dabg);

                break;
        }

        return output;
    }

    private void goHome(Class clazz) {
        Intent intent = new Intent(GuideActivity.this, clazz);
        startActivity(intent);
        finish(); // 退出当前界面
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.detector.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getY() - e2.getY() > 200) {//向上滑动
            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_up_in));
            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_up_out));
            position++;
            if (position == 3) {
                bt_help.setVisibility(View.VISIBLE);
                bt_help.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                        SharedPreferences.Editor et=sp.edit();
                        if (sp.getBoolean(Constant.isFirstIn, true)) {
                            goHome(LoginActivity.class);
                            et.putBoolean(Constant.isFirstIn, false);
                            et.commit();
                        }else{
                            finish();
                        }

                    }
                });
            }
            if (position > 3) {
                position = 3;

                return false;
            }
            this.flipper.showNext();
            return true;
        } else if (e1.getY() - e2.getY() < -200) {//向下滑动
            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_down_in));
            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_down_out));
            position--;
            if (position < 0) {
                position = 0;
                return false;
            }
            this.flipper.showPrevious();
            return true;
        }

        return false;
    }



    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }


}

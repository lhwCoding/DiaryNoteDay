package com.dnd.diarynoteday.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.base.BaseActivity;
import com.dnd.diarynoteday.db.DB_PASSWORD;
import com.dnd.diarynoteday.utils.UIUtils;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(R.layout.activity_main)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.edit_denglu)
    private EditText edit_password;
    @ViewInject(R.id.btn_denglu)
    private Button btn_denglu;
    @ViewInject(R.id.iv_loading_bg)
    private ImageView iv_loading_bg;

    private Animation animation;
    private DB_PASSWORD db;

    private int index = 0;


    @Override
    protected void init() {

        ImageOptions imageOptions = new ImageOptions.Builder()
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                // 默认自动适应大小
                .setSize(getWindowManager().getDefaultDisplay().getWidth(),getWindowManager().getDefaultDisplay().getHeight())
                .setIgnoreGif(true)
                .setImageScaleType(ImageView.ScaleType.CENTER).build();
         x.image().bind(iv_loading_bg,"assets://bear_04.gif",imageOptions);
        animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.anim_click_info);


        btn_denglu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (edit_password.getText().toString().equals("")) {
                            TranslateAnimation alphaAnimation2 = new TranslateAnimation(0f, 20f, 0, 0);
                            alphaAnimation2.setDuration(50);
                            alphaAnimation2.setRepeatCount(7);
                            alphaAnimation2.setRepeatMode(Animation.REVERSE);
                            edit_password.setAnimation(alphaAnimation2);
                            alphaAnimation2.start();
                            UIUtils.showToast(LoginActivity.this, "密码不正确");

                        } else {
                            db = new DB_PASSWORD(getApplicationContext());
                            String s = db.selectall();
                            System.out.println("021333" + s);
                            if (s.equals("")) {
                                if (edit_password.getText().toString().equals("123")) {
                                    startActivity(new Intent(LoginActivity.this, MainTabActivity.class));
                                    LoginActivity.this.finish();
                                } else {
                                    UIUtils.showToast(LoginActivity.this, "密码不正确");
                                }
                            } else {
                                if (edit_password.getText().toString().equals(s)) {
                                    startActivity(new Intent(LoginActivity.this, MainTabActivity.class));
                                    LoginActivity.this.finish();
                                } else {
                                    UIUtils.showToast(LoginActivity.this, "密码不正确");
                                }
                            }
                        }

                    }
                });

            }
        });
    }

    @Override
    protected void initData() {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            exit_app();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void exit_app() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("提示").setMessage("想要退出？").setPositiveButton("是的",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        LoginActivity.this.finish();
                    }
                }).setNegativeButton("不了",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        AlertDialog dialog_dialog = dialog.create();
        dialog_dialog.show();

    }
}

package com.dnd.diarynoteday.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.base.BaseActivity;
import com.dnd.diarynoteday.db.Conmon;
import com.dnd.diarynoteday.db.DBHelpe;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@ContentView(R.layout.writedialy_activity)
public class WriteDiaryActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.edit_writedialy)
    private EditText edittext;
    @ViewInject(R.id.edit_year)
    private EditText edit_year;
    @ViewInject(R.id.edit_days)
    private EditText edit_days;
    @ViewInject(R.id.edit_winder)
    private EditText edit_winder;


    private DBHelpe db;
    private String mWay;


    @Override
    protected void init() {

        IntView();
    }

    @Override
    protected void initData() {

    }

    private void IntView() {
        db = new DBHelpe(WriteDiaryActivity.this);


    }


    @Event(value = {R.id.btn_join, R.id.btn_clean, R.id.edit_year, R.id.edit_days,}, type = View.OnClickListener.class)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_join:

                String s = edittext.getText().toString();
                String data = edit_year.getText().toString();
                String days = edit_days.getText().toString();
                String winder = edit_winder.getText().toString();

                if (data.equals("")) {
                    TranslateAnimation alphaAnimation2 = new TranslateAnimation(0f, 20f, 0, 0);
                    alphaAnimation2.setDuration(50);
                    alphaAnimation2.setRepeatCount(7);
                    alphaAnimation2.setRepeatMode(Animation.REVERSE);
                    edit_year.setAnimation(alphaAnimation2);
                    alphaAnimation2.start();
                    edit_year.requestFocus();
                } else {
                    if (days.equals("")) {
                        TranslateAnimation alphaAnimation2 = new TranslateAnimation(0f, 20f, 0, 0);
                        alphaAnimation2.setDuration(50);
                        alphaAnimation2.setRepeatCount(7);
                        alphaAnimation2.setRepeatMode(Animation.REVERSE);
                        edit_days.setAnimation(alphaAnimation2);
                        alphaAnimation2.start();
                        edit_days.requestFocus();
                    } else {
                        if (winder.equals("")) {
                            TranslateAnimation alphaAnimation2 = new TranslateAnimation(0f, 20f, 0, 0);
                            alphaAnimation2.setDuration(50);
                            alphaAnimation2.setRepeatCount(7);
                            alphaAnimation2.setRepeatMode(Animation.REVERSE);
                            edit_winder.setAnimation(alphaAnimation2);
                            alphaAnimation2.start();
                            edit_winder.requestFocus();
                        } else

                        {
                            if (!s.equals("")) {
                                ContentValues values = new ContentValues();
                                values.put("content", s);
                                values.put("data", data);
                                values.put("days", days);
                                values.put("winder", winder);
                                db.insert(values);
                                Conmon.bln_content = true;
                                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                                edittext.setText("");
                            } else {
                                Toast.makeText(getApplicationContext(), "宝贝你什么也没有写哟！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                break;
            case R.id.btn_clean:
                edittext.setText("");


                break;
            case R.id.edit_year:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit_year.getWindowToken(), 0);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
                String time = df.format(new Date());
                edit_year.setText(time);
                break;
            case R.id.edit_days:
                InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm2.hideSoftInputFromWindow(edit_days.getWindowToken(), 0);
                Calendar c = Calendar.getInstance();
                mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
                if ("1".equals(mWay)) {
                    mWay = "天";
                } else if ("2".equals(mWay)) {
                    mWay = "一";
                } else if ("3".equals(mWay)) {
                    mWay = "二";
                } else if ("4".equals(mWay)) {
                    mWay = "三";
                } else if ("5".equals(mWay)) {
                    mWay = "四";
                } else if ("6".equals(mWay)) {
                    mWay = "五";
                } else if ("7".equals(mWay)) {
                    mWay = "六";
                }
                String day = "星期" + mWay;
                edit_days.setText(day);
                break;
            default:
                break;
        }

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

        dialog.setTitle("提示").setMessage("确定要退出？").setPositiveButton("是的",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        System.exit(0);
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

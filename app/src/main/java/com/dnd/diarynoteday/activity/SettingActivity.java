package com.dnd.diarynoteday.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.base.BaseActivity;
import com.dnd.diarynoteday.db.DB_PASSWORD;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.setting_activity)
public class SettingActivity extends BaseActivity {
    @ViewInject(R.id.edit_setting)
    private EditText edit_setting;
    @ViewInject(R.id.btn_setting)
    private Button btn_setting;
    
    
    private DB_PASSWORD db;

    @Override
    protected void init() {
        btn_setting.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                String s = edit_setting.getText().toString().trim();
                db = new DB_PASSWORD(getApplicationContext());

                if (!s.equals("") && db.selectall().equals("")) {
                    ContentValues values = new ContentValues();
                    values.put("password", s);
                    db.insert(values);
                    contral();
                    Toast.makeText(getApplicationContext(), "改密码成功！", Toast.LENGTH_LONG).show();
                } else if (!s.equals("") && !db.selectall().equals("")) {
                    db.update(s);
                    contral();
                    Toast.makeText(getApplicationContext(), "改密码成功！", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "亲，修改密码不能为空!~", Toast.LENGTH_SHORT).show();
                }

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

    private void contral() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_setting.getWindowToken(), 0);
    }
}

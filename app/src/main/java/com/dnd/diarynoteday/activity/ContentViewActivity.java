package com.dnd.diarynoteday.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.base.BaseActivity;
import com.dnd.diarynoteday.db.Conmon;
import com.dnd.diarynoteday.db.DBHelpe;
import com.dnd.diarynoteday.db.modle.Mode;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.contentview_activity)
public class ContentViewActivity extends BaseActivity {
    @ViewInject(R.id.tv_year)
    private TextView tv_year;
    @ViewInject(R.id.tv_days)
    private TextView tv_days;
    @ViewInject(R.id.tv_winder)
    private TextView tv_winder;

    @ViewInject(R.id.tv_content)
    private TextView tv_content;
    @ViewInject(R.id.btn_edit)
    private TextView btn_edit;
    @ViewInject(R.id.btn_delete)
    private TextView btn_delete;


    @ViewInject(R.id.btn_back)
    private Button btn_return;

    private Animation animation;

    private DBHelpe DB;
    private String id;


    @Override
    protected void init() {
        intview();
    }

    @Override
    protected void initData() {

    }

    private void intview() {
        animation = AnimationUtils.loadAnimation(ContentViewActivity.this, R.anim.anim_click_info);

        String content = null, data = null, days = null, winder = null;

        DB = new DBHelpe(ContentViewActivity.this);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        id = bd.getString("id");
        ArrayList<Mode> list = DB.fetchValue(id);
        for (Mode m : list) {
            content = m.getCONTENT();
            data = m.getDATA();
            days = m.getDAYS();
            winder = m.getWINDER();

        }
        tv_year.setText(data);
        tv_days.setText(days);
        tv_content.setText(content);
        tv_winder.setText("天气：" + winder);


    }

    @Event(value = {R.id.btn_edit, R.id.btn_delete, R.id.btn_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_edit:
                Intent intent = new Intent(ContentViewActivity.this, EditDiaryActivity.class);
                Bundle bd = new Bundle();
                bd.putString("content", tv_content.getText().toString());
                bd.putString("id", id);
                intent.putExtra("edit_diary_activity", bd);
                startActivity(intent);
                ContentViewActivity.this.finish();
                break;
            case R.id.btn_delete:
                dialog();
                break;
            case R.id.btn_back:
                ContentViewActivity.this.finish();
                break;
        }
    }

    public void dialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("提示").setMessage("真要删除吗？").setPositiveButton("是的",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        DB.del(Integer.valueOf(id));
                        ArrayList<Mode> list = DB.fetchValue();
                        DB.clean();
                        ContentValues values = new ContentValues();
                        for (Mode mode : list) {
                            values.put("content", mode.getCONTENT());
                            values.put("data", mode.getDATA());
                            values.put("days", mode.getDAYS());
                            values.put("winder", mode.getWINDER());
                            DB.insert(values);
                        }
                        Conmon.bln_content = true;
                        Toast.makeText(ContentViewActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        ContentViewActivity.this.finish();

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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}

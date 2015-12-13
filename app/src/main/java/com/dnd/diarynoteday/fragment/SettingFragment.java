package com.dnd.diarynoteday.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.base.BaseFragment;
import com.dnd.diarynoteday.db.DB_PASSWORD;
import com.dnd.diarynoteday.utils.UIUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by hongwu on 2015/12/7.
 */
@ContentView(R.layout.setting_fragment)
public class SettingFragment extends BaseFragment {
    @ViewInject(R.id.edit_setting)
    private EditText edit_setting;
    @ViewInject(R.id.btn_setting)
    private Button btn_setting;
    private DB_PASSWORD db;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String s = edit_setting.getText().toString().trim();
                db = new DB_PASSWORD(UIUtils.getContext());

                if (!s.equals("") && db.selectall().equals("")) {
                    ContentValues values = new ContentValues();
                    values.put("password", s);
                    db.insert(values);
                    contral();
                    Toast.makeText(UIUtils.getContext(), "改密码成功！", Toast.LENGTH_LONG).show();
                } else if (!s.equals("") && !db.selectall().equals("")) {
                    db.update(s);
                    contral();
                    Toast.makeText(UIUtils.getContext(), "改密码成功！", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UIUtils.getContext(), "亲，修改密码不能为空!~", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void contral() {
        InputMethodManager imm = (InputMethodManager) UIUtils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_setting.getWindowToken(), 0);
    }


}

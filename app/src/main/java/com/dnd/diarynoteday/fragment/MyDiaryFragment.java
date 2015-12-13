package com.dnd.diarynoteday.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.activity.ContentViewActivity;
import com.dnd.diarynoteday.base.BaseFragment;
import com.dnd.diarynoteday.db.Conmon;
import com.dnd.diarynoteday.db.DBHelpe;
import com.dnd.diarynoteday.utils.UIUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by hongwu on 2015/12/7.
 */
@ContentView(R.layout.mydialy_activity)
public class MyDiaryFragment extends BaseFragment {
    @ViewInject(R.id.listview)
    private ListView listview;
    @ViewInject(R.id.tv_mytitle)
    private TextView tv_madialy;

    private String[] from = {"content", "data", "days"};
    private int[] to = {R.id.tv, R.id.tv_year, R.id.tv_days};
    private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private DBHelpe db;
    private int index = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    if (index % 4 == 1) {
                        tv_madialy.setTextColor(Color.YELLOW);
                    }
                    if (index % 4 == 2) {
                        tv_madialy.setTextColor(Color.GREEN);
                    }
                    if (index % 4 == 3) {
                        tv_madialy.setTextColor(Color.RED);
                    }
                    if (index % 4 == 0) {
                        tv_madialy.setTextColor(Color.WHITE);
                    }

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intview();
        String s = db.selectall();
        if (!s.equals("")) {
            cursor = db.query();
            adapter = new SimpleCursorAdapter(UIUtils.getContext(), R.layout.list, cursor, from, to);
            listview.setAdapter(adapter);
            listview.setDivider(null);

        }

        Thread thread = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        Message m = new Message();
                        index++;
                        m.what = 2;
                        handler.sendMessage(m);
                    }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        };
        thread.start();

    }


    @Override
    public void onResume() {
        if (Conmon.bln_content == true) {
            cursor = db.query();
            adapter = new SimpleCursorAdapter(UIUtils.getContext(), R.layout.list, cursor, from, to);
            listview.setAdapter(adapter);
            listview.setDivider(null);
            Conmon.bln_content = false;

        }
        super.onResume();
    }


    private void intview() {
        db = new DBHelpe(UIUtils.getContext());

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int n, long m) {
                n = n + 1;
                Intent intent = new Intent(UIUtils.getContext(), ContentViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", n + "");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

}

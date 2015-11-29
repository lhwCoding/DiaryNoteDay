package com.dnd.diarynoteday.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.dnd.diarynoteday.R;

/**
 * Created by hongwu on 2015/11/29.
 */
public class TabDiaryActivity extends ActivityGroup {
    public static TabHost tab_host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        tab_host = (TabHost) findViewById(R.id.tab_host);
        tab_host.setup(this.getLocalActivityManager());

        TabHost.TabSpec ts1 = tab_host.newTabSpec("TAB_WEATHER");
        ts1.setIndicator("我的日记");
        ts1.setContent(new Intent(this, MyDiaryActivity.class));
        tab_host.addTab(ts1);
        tab_host.getTabWidget().getChildTabViewAt(0).setBackgroundResource(R.drawable.read);


        TabHost.TabSpec ts2 = tab_host.newTabSpec("TAB_MAIL");
        ts2.setIndicator("写日记");
        ts2.setContent(new Intent(this, WriteDiaryActivity.class));
        tab_host.addTab(ts2);
        tab_host.getTabWidget().getChildTabViewAt(1).setBackgroundResource(R.drawable.write);

        TabHost.TabSpec ts3 = tab_host.newTabSpec("TAB_JUMP");
        ts3.setIndicator("设置");
        ts3.setContent(new Intent(this, SettingActivity.class));
        tab_host.addTab(ts3);
        tab_host.getTabWidget().getChildTabViewAt(2).setBackgroundResource(R.drawable.setting);

        tab_host.setCurrentTab(0);

    }
}

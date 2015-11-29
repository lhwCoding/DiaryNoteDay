package com.dnd.diarynoteday.activity;

import com.dnd.diarynoteday.R;

import java.util.ArrayList;
import java.util.List;



public class CallbyeTabActivity extends MyTabActivity {
	public CallbyeTabActivity() {
		super(R.layout.tab_activity, R.drawable.tabbackground);

	}

	@Override
	public List<MyTab> getMyTabList() {
		List<MyTab> myTabList = new ArrayList<MyTab>();
		myTabList.add(new MyTab(R.drawable.read, "我的日记",
				MyDiaryActivity.class));
		myTabList.add(new MyTab(R.drawable.write, "写日记",
				WriteDiaryActivity.class));
		myTabList.add(new MyTab(R.drawable.setting, "设置",
				SettingActivity.class));
	

		return myTabList;
	}
	
}

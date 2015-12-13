package com.dnd.diarynoteday.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.base.BaseActivity;
import com.dnd.diarynoteday.db.Conmon;
import com.dnd.diarynoteday.db.DBHelpe;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.mydialy_activity)
public class MyDiaryActivity extends BaseActivity {
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

	@Override
	protected void init() {
		intview();
		String s = db.selectall();
		if (!s.equals("")) {
			cursor = db.query();
			adapter = new SimpleCursorAdapter(this, R.layout.list, cursor, from, to);
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
						MyDiaryActivity.this.handler.sendMessage(m);
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		};
		thread.start();
	}

	@Override
	protected void initData() {

	}




	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 2 :
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
				default :
					break;
			}
		}
	};
	@Override
	protected void onResume() {
		if (Conmon.bln_content == true) {
			cursor = db.query();
			adapter = new SimpleCursorAdapter(this, R.layout.list, cursor, from, to);
			listview.setAdapter(adapter);
			listview.setDivider(null);
			Conmon.bln_content = false;

		}

		super.onResume();
	}


	private void intview() {
		db = new DBHelpe(MyDiaryActivity.this);
		tv_madialy = (TextView) findViewById(R.id.tv_mytitle);
		listview = (ListView) findViewById(R.id.listview);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int n, long m) {
				// TODO Auto-generated method stub
				n = n + 1;
				Intent intent = new Intent(MyDiaryActivity.this, ContentViewActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("id", n + "");
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});
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

		dialog.setTitle("提示").setMessage("确定要退出？").setPositiveButton("是的", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
			}
		}).setNegativeButton("不了", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		AlertDialog dialog_dialog = dialog.create();
		dialog_dialog.show();

	}
}

package com.dnd.diarynoteday.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.db.DB_PASSWORD;


public class SettingActivity extends Activity {
	private EditText edit_shezhi;
	private Button btn_shezhi;
    private DB_PASSWORD db;
    private TextView tv_laogong2;
    private Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_activity);
		intview();
	}
	private void intview() {
		animation=AnimationUtils.loadAnimation(SettingActivity.this, R.anim.anim_click_info);
		edit_shezhi = (EditText) findViewById(R.id.edit_shezhi);
		btn_shezhi = (Button) findViewById(R.id.btn_shezhi);
		tv_laogong2=(TextView)findViewById(R.id.tv_laogong2);
		btn_shezhi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animation);
				animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						String s = edit_shezhi.getText().toString().trim();
						db = new DB_PASSWORD(getApplicationContext());

						if (!s.equals("") && db.selectall().equals("")) {
							ContentValues values = new ContentValues();
							values.put("password", s);
							db.insert(values);
							contral();
							Toast.makeText(getApplicationContext(), "改密码成功！", Toast.LENGTH_LONG).show();
							//tv_laogong2.setVisibility(View.VISIBLE);
						} else if (!s.equals("") && !db.selectall().equals("")) {
							db.update(s);
							contral();
							//tv_laogong2.setVisibility(View.VISIBLE);
							Toast.makeText(getApplicationContext(), "改密码成功！", Toast.LENGTH_LONG).show();
						}
						else
						{Toast.makeText(getApplicationContext(), "亲，修改密码不能为空!~", Toast.LENGTH_SHORT).show();}// TODO Auto-generated method stub
						
					}
				});
				
				

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
	public void exit_app()
	  {  
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
private void contral()
	{InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	imm.hideSoftInputFromWindow(edit_shezhi.getWindowToken(), 0);}
}

package com.dnd.diarynoteday.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dnd.diarynoteday.R;
import com.dnd.diarynoteday.db.Conmon;
import com.dnd.diarynoteday.db.DBHelpe;

public class EditDiaryActivity extends Activity implements View.OnClickListener {
	private Button btn_return, btn_queding, btn_quxiao;
	private EditText edit_content;
	private String str_content,str_id;
	private Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_diary_activity);
		intview();
	}
	private void intview() {
		btn_return = (Button) findViewById(R.id.btn_bianjifanhui);
		btn_queding = (Button) findViewById(R.id.btn_bianjiqueding);
		btn_quxiao = (Button) findViewById(R.id.btn_bianjiquxiao);
		edit_content = (EditText) findViewById(R.id.edit_content);
		btn_return.setOnClickListener(this);
		btn_queding.setOnClickListener(this);
		btn_quxiao.setOnClickListener(this);
		Intent intent=getIntent();
		Bundle bd=intent.getBundleExtra("edit_diary_activity");
		str_content=bd.getString("content");
		str_id=bd.getString("id");
		Log.d("xxxxxxxxx", str_content+str_id);
		edit_content.setText(str_content);
		animation=AnimationUtils.loadAnimation(EditDiaryActivity.this, R.anim.anim_click_info);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_bianjifanhui :
				btn_return.startAnimation(animation);

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
						// TODO Auto-generated method stub
						EditDiaryActivity.this.finish();	
					}
				});
			
				break;
			case R.id.btn_bianjiqueding :
				btn_queding.startAnimation(animation);
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
						// TODO Auto-generated method stub
						DBHelpe db=new DBHelpe(EditDiaryActivity.this);
						db.update(str_id, edit_content.getText().toString());
						Toast.makeText(EditDiaryActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
						Conmon.bln_content=true;
						EditDiaryActivity.this.finish();
					}
				});
				
				break;
			case R.id.btn_bianjiquxiao :
				btn_quxiao.startAnimation(animation);
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
						// TODO Auto-generated method stub
						EditDiaryActivity.this.finish();	
					}
				});
				
				break;

			default :
				break;
		}// TODO Auto-generated method stub

	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}// TODO Auto-generated method stub
		return false;
	}
}

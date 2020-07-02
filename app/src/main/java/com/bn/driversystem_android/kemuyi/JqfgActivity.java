package com.bn.driversystem_android.kemuyi;

import com.bn.driversystem_android.R;
import com.bn.wenzimiaoshu.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class JqfgActivity extends Activity{//技巧法规
	private  Button button01,button02,button03,button04,button05,button06,button07,button08,button09;
	private ImageView fanhui_miji;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.keyimiji_button);
				
				initView();			
	}

	private void initView() {
		button01=(Button)findViewById(R.id.keyimiji_b1);
		button02=(Button)findViewById(R.id.keyimiji_b2);
		button03=(Button)findViewById(R.id.keyimiji_b3);
		button04=(Button)findViewById(R.id.keyimiji_b4);
		button05=(Button)findViewById(R.id.keyimiji_b5);
		button06=(Button)findViewById(R.id.keyimiji_b6);
		button07=(Button)findViewById(R.id.keyimiji_b7);
		button08=(Button)findViewById(R.id.keyimiji_b8);
		button09=(Button)findViewById(R.id.keyimiji_b9);
		fanhui_miji=(ImageView)findViewById(R.id.fanhui_miji);
		
		OnClickListener clickListener = new OnClickListener();
		
		button01.setOnClickListener(clickListener);
		button02.setOnClickListener(clickListener);
		button03.setOnClickListener(clickListener);
		button04.setOnClickListener(clickListener);
		button05.setOnClickListener(clickListener);
		button06.setOnClickListener(clickListener);
		button07.setOnClickListener(clickListener);
		button08.setOnClickListener(clickListener);
		button09.setOnClickListener(clickListener);
		fanhui_miji.setOnClickListener(clickListener);
	}
	private class OnClickListener implements View.OnClickListener{

			@Override
			public void onClick(View v) {
				int mBtnid = v.getId();
				switch(mBtnid){			
				case R.id.keyimiji_b1:
					startActivity(new Intent().setClass(JqfgActivity.this, miji_1Activity.class));
					break;
				case R.id.keyimiji_b2:
					startActivity(new Intent().setClass(JqfgActivity.this, miji_2Activity.class));
					break;
				case R.id.keyimiji_b3:
					startActivity(new Intent().setClass(JqfgActivity.this, miji_3Activity.class));
					break;
				case R.id.keyimiji_b4:
					startActivity(new Intent().setClass(JqfgActivity.this, miji_4Activity.class));
					break;
				case R.id.keyimiji_b5:
					startActivity(new Intent().setClass(JqfgActivity.this, miji_5Activity.class));
					break;
				case R.id.keyimiji_b6:
					startActivity(new Intent().setClass(JqfgActivity.this, miji_6Activity.class));
					break;
				case R.id.keyimiji_b7:
					startActivity(new Intent().setClass(JqfgActivity.this, miji_7Activity.class));
					break;
				case R.id.keyimiji_b8:
					startActivity(new Intent().setClass(JqfgActivity.this, miji_8Activity.class));
					break;
				case R.id.keyimiji_b9:
					startActivity(new Intent().setClass(JqfgActivity.this, miji_9Activity.class));
					break;
				case R.id.fanhui_miji:
					finish();
					break;
				}
			}
		
	}
//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event) {
//	if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//		//startActivity(new Intent().setClass(JqfgActivity.this, JqfgActivity.class));
//		View v=getWindow().getDecorView();
//		int vi=v.getId();
//		if(vi==R.layout.keyimiji_button)
//		{
//			setContentView(R.layout.main_kemuyi);
//		}
//		else
//		{
//			setContentView(R.layout.keyimiji_button);
//		}
//		return true;
//	}
//	return super.dispatchKeyEvent(event);
//	}
}
package com.bn.wenzimiaoshu;

import com.bn.driversystem_android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class KeMuSanMiJi_DiaoTou extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_three_miji_diaotoujiqiao);
		Button subject_two_kaogui_title;
		subject_two_kaogui_title=(Button)findViewById(R.id.button_subject_two_kaogui_title);
		subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent=new Intent();
//				intent.setClass(KeMuErKaoGui.this, MainActivity.class);
//				startActivityForResult(intent,0);.
				//相当于手机的返回按钮
				KeMuSanMiJi_DiaoTou.this.finish();
			}
			
		}
		);
	}
}

package com.bn.driversystem_android.kemuer;

import com.bn.driversystem_android.R;
import com.bn.wenzimiaoshu.*;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;



public class KeMuErMiJi extends Activity{

	
	Button subjectTwoKaoQianZhunBei,subjectTwoDaoChe,subjectTwoPoDao,
	subjectTwoCeFang,subjectTwoQuXian,subjectTwoZhiXian;
@Override
protected void onCreate(Bundle savedInstanceState)
{
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	super.onCreate(savedInstanceState);
	setContentView(R.layout.subject_two_miji);
	System.out.print("fadsfdasfdsafadsfasd");
	subjectTwoKaoQianZhunBei=(Button)findViewById(R.id.buttonsubject_two_kaoqianzhunbei);
	subjectTwoDaoChe=(Button)findViewById(R.id.buttonsubject_two_daocheruku);
	subjectTwoPoDao=(Button)findViewById(R.id.buttonsubject_two_podaotingcheheqibu);
	subjectTwoCeFang=(Button)findViewById(R.id.buttonsubject_two_cefangtingche);
	subjectTwoQuXian=(Button)findViewById(R.id.buttonsubject_two_quxianxingshi);
	subjectTwoZhiXian=(Button)findViewById(R.id.buttonsubject_two_zhijiaozhuanwan);
	
	
	Button subject_two_kaogui_title;
	subject_two_kaogui_title=(Button)findViewById(R.id.button_subject_two_kaogui_title);
	subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			Intent intent=new Intent();
//			intent.setClass(KeMuErKaoGui.this, MainActivity.class);
//			startActivityForResult(intent,0);.
			//相当于手机的返回按钮
			KeMuErMiJi.this.finish();
		}
		
	}
	);
	subjectTwoKaoQianZhunBei.setOnClickListener(new View.OnClickListener()
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//System.out.println("fdfffffffffffffff");
			Intent intent=new Intent();
			intent.setClass(KeMuErMiJi.this,KeMuErMiJi_KaoQianZhunBei.class);
			startActivity(intent);
		}
		
	}
	);
	subjectTwoDaoChe.setOnClickListener(new View.OnClickListener()
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//System.out.println("fdfffffffffffffff");
			Intent intent=new Intent();
			intent.setClass(KeMuErMiJi.this,KeMuErMiJi_DaoChe.class);
			startActivity(intent);
		}
		
	}
	);
	subjectTwoPoDao.setOnClickListener(new View.OnClickListener()
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intent=new Intent();
			intent.setClass(KeMuErMiJi.this,KeMuErMiJi_PoDao.class);
			startActivity(intent);
		}
	});
	subjectTwoQuXian.setOnClickListener(new View.OnClickListener()
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//System.out.println("fdfffffffffffffff");
			Intent intent=new Intent();
			intent.setClass(KeMuErMiJi.this,KeMuErMiJi_QuXian.class);
			startActivity(intent);
		}
		
	}
	);
	subjectTwoCeFang.setOnClickListener(new View.OnClickListener()
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//System.out.println("fdfffffffffffffff");
			Intent intent=new Intent();
			intent.setClass(KeMuErMiJi.this,KeMuErMiJi_CeFang.class);
			startActivity(intent);
		}
		
	}
	);
	subjectTwoZhiXian.setOnClickListener(new View.OnClickListener()
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//System.out.println("fdfffffffffffffff");
			Intent intent=new Intent();
			intent.setClass(KeMuErMiJi.this,KeMuErMiJi_ZhiXian.class);
			startActivity(intent);
		}
		
	}
	);
	
}


}

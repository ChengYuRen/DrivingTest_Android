package com.bn.driversystem_android.kemuer;

import com.bn.driversystem_android.R;
import com.bn.wenzimiaoshu.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class KeMuSanMiJi extends Activity{
	
	Button subject_koufen,subject_lukaokoujue,subject_qibu,subject_jiaocha,subject_diaotou,
			subject_yekao,subject_wuzhao,subject_lukaomiji;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_three_miji);
		subject_koufen=(Button)findViewById(R.id.buttonsubject_three_koufen);
		subject_lukaokoujue=(Button)findViewById(R.id.buttonsubject_three_lukaokoujue);
		subject_qibu=(Button)findViewById(R.id.buttonsubject_three_qibu);
		subject_jiaocha=(Button)findViewById(R.id.buttonsubject_three_jiaocha);
		subject_diaotou=(Button)findViewById(R.id.buttonsubject_three_diaotou);
		subject_yekao=(Button)findViewById(R.id.buttonsubject_three_yekao);
		subject_wuzhao=(Button)findViewById(R.id.buttonsubject_three_wuzhao);
		subject_lukaomiji=(Button)findViewById(R.id.buttonsubject_three_lukaomiji);
		
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
				KeMuSanMiJi.this.finish();
			}
			
		}
		);
		
		subject_koufen.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//System.out.println("fdfffffffffffffff");
				Intent intent=new Intent();
				intent.setClass(KeMuSanMiJi.this,KeMuSanMiJi_KouFen.class);
				startActivity(intent);
			}
			
		}
		);
		
		subject_lukaokoujue.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//System.out.println("fdfffffffffffffff");
				Intent intent=new Intent();
				intent.setClass(KeMuSanMiJi.this,KeMuSanMiJi_LuKaoKouJue.class);
				startActivity(intent);
			}
			
		}
		);
		
		subject_qibu.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//System.out.println("fdfffffffffffffff");
				Intent intent=new Intent();
				intent.setClass(KeMuSanMiJi.this,KeMuSanMiJi_QiBu.class);
				startActivity(intent);
			}
			
		}
		);
		
		subject_jiaocha.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//System.out.println("fdfffffffffffffff");
				Intent intent=new Intent();
				intent.setClass(KeMuSanMiJi.this,KeMuSanMiJi_JiaoCha.class);
				startActivity(intent);
			}
			
		}
		);
		
		subject_diaotou.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//System.out.println("fdfffffffffffffff");
				Intent intent=new Intent();
				intent.setClass(KeMuSanMiJi.this,KeMuSanMiJi_DiaoTou.class);
				startActivity(intent);
			}
			
		}
		);
		
		subject_yekao.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//System.out.println("fdfffffffffffffff");
				Intent intent=new Intent();
				intent.setClass(KeMuSanMiJi.this,KeMuSanMiJi_YeKao.class);
				startActivity(intent);
			}
			
		}
		);
		
		subject_wuzhao.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//System.out.println("fdfffffffffffffff");
				Intent intent=new Intent();
				intent.setClass(KeMuSanMiJi.this,KeMuSanMiJi_WuZhao.class);
				startActivity(intent);
			}
			
		}
		);
		
		subject_lukaomiji.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//System.out.println("fdfffffffffffffff");
				Intent intent=new Intent();
				intent.setClass(KeMuSanMiJi.this,KeMuSanMiJi_LuKaoMiJi.class);
				startActivity(intent);
			}
			
		}
		);
	}
}

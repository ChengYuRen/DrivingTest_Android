package com.bn.driversystem_android;


import com.bn.driversystem_android.R;
import com.bn.driversystem_android.kemuyi.*;
import com.bn.shuju_tongji.ShuJu_TongJi_Main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class KeMuyiActivity extends Activity{
	private TextView keyikaogui,jiaotongbiaozhi,jiqiaofagui;
	 private ImageView kemuyi_wodeshoucang,kemuyi_shujutongji,kemuyi_wodecuoti,kemuyi_kaoshijilu,
	 kemuyi_shunxu,kemuyi_zhangjie,kemuyi_fenlei,kemuyi_suiji,kemuyi_moni;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.main_kemuyi);
				
				initView();
	}
	private void initView() 
	{
		keyikaogui = (TextView) findViewById(R.id.keyikaogui);
		jiaotongbiaozhi = (TextView) findViewById(R.id.jiaotongbiaozhi);
		jiqiaofagui = (TextView) findViewById(R.id.jiqiaofagui);
		
		kemuyi_wodeshoucang=(ImageView) findViewById(R.id.kemuyi_wodeshoucang);
		kemuyi_shujutongji=(ImageView) findViewById(R.id.kemuyi_shujutongji);
		kemuyi_wodecuoti=(ImageView) findViewById(R.id.kemuyi_wodecuoti);
		kemuyi_kaoshijilu=(ImageView) findViewById(R.id.kemuyi_kaoshijilu);
		
		kemuyi_shunxu=(ImageView) findViewById(R.id.kemuyi_shunxu);
		kemuyi_zhangjie=(ImageView) findViewById(R.id.kemuyi_zhangjie);
		kemuyi_fenlei=(ImageView) findViewById(R.id.kemuyi_fenlei);
		kemuyi_suiji=(ImageView) findViewById(R.id.kemuyi_suiji);
		kemuyi_moni=(ImageView) findViewById(R.id.kemuyi_moni);
		
		MyBtnOnClick mytouchlistener = new MyBtnOnClick();
		keyikaogui.setOnClickListener(mytouchlistener);
		jiaotongbiaozhi.setOnClickListener(mytouchlistener);
		jiqiaofagui.setOnClickListener(mytouchlistener);
		
		kemuyi_wodeshoucang.setOnClickListener(mytouchlistener);
		kemuyi_shujutongji.setOnClickListener(mytouchlistener);
		kemuyi_wodecuoti.setOnClickListener(mytouchlistener);
		kemuyi_kaoshijilu.setOnClickListener(mytouchlistener);
		
		kemuyi_shunxu.setOnClickListener(mytouchlistener);
		kemuyi_zhangjie.setOnClickListener(mytouchlistener);
		kemuyi_fenlei.setOnClickListener(mytouchlistener);
		kemuyi_suiji.setOnClickListener(mytouchlistener);
		kemuyi_moni.setOnClickListener(mytouchlistener);
		
	}
	
		
	private class MyBtnOnClick implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			int mBtnid = v.getId();
			
			switch(mBtnid){			
			case R.id.keyikaogui:
				startActivity(new Intent().setClass(KeMuyiActivity.this, KykgActivity.class));
				break;
			case R.id.jiaotongbiaozhi:
				startActivity(new Intent().setClass(KeMuyiActivity.this, JtbzActivity.class));
				break;
			case R.id.jiqiaofagui:
				startActivity(new Intent().setClass(KeMuyiActivity.this, JqfgActivity.class));
				break;
			case R.id.kemuyi_wodeshoucang:
				MainActivity.LXmoshi=4;//我的收藏模式
				startActivity(new Intent().setClass(KeMuyiActivity.this, shoucangActivity.class));
				break;
			case R.id.kemuyi_shujutongji:
				MainActivity.DianJiKeMu="科目一";
				startActivity(new Intent().setClass(KeMuyiActivity.this, ShuJu_TongJi_Main.class));
				break;	
			case R.id.kemuyi_wodecuoti:
				MainActivity.LXmoshi=5;//我的错题模式
				startActivity(new Intent().setClass(KeMuyiActivity.this, cuotiActivity.class));
				break;
			case R.id.kemuyi_kaoshijilu:
				MainActivity.DianJiKeMu="科目一";
				MainActivity.kaoshijilu=1;
				startActivity(new Intent().setClass(KeMuyiActivity.this,kaoshijiluActivity.class));
				break;
			case R.id.kemuyi_shunxu:
				MainActivity.LXmoshi=0;//为顺序练习
				MainActivity.DianJiKeMu="科目一";
				startActivity(new Intent().setClass(KeMuyiActivity.this, shunxuActivity.class));
				break;
			case R.id.kemuyi_zhangjie:
				MainActivity.LXmoshi=1;//为章节练习
				MainActivity.DianJiKeMu="科目一";
				startActivity(new Intent().setClass(KeMuyiActivity.this,zhangjieXUANZE.class));
				break;
			case R.id.kemuyi_fenlei:
				MainActivity.LXmoshi=2;//为分类练习
				MainActivity.DianJiKeMu="科目一";
				startActivity(new Intent().setClass(KeMuyiActivity.this, fenleiActivity.class));
				break;
			case R.id.kemuyi_suiji:
				MainActivity.LXmoshi=3;//为随机练习
				MainActivity.DianJiKeMu="科目一";
				startActivity(new Intent().setClass(KeMuyiActivity.this, shunxuActivity.class));
				break;
			case R.id.kemuyi_moni:
				MainActivity.DianJiKeMu="科目一";
				MainActivity.kaoshijilu=0;
				startActivity(new Intent().setClass(KeMuyiActivity.this, kaoshiBHYduo.class));
				break;
			}
			
		}
	
	}
}
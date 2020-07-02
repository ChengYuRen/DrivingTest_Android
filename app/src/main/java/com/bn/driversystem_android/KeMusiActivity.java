package com.bn.driversystem_android;


import com.bn.driversystem_android.R;
import com.bn.driversystem_android.kemusi.*;
import com.bn.driversystem_android.kemuyi.*;
import com.bn.shuju_tongji.ShuJu_TongJi_Main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class KeMusiActivity extends Activity{
	private TextView kesikaogui,jiaotongbiaozhi2,kesimiji;
	private ImageView kemusi_wodeshoucang,kemusi_shujutongji,kemusi_wodecuoti,kemusi_kaoshijilu,
	kemusi_shunxu,kemusi_zhangjie,kemusi_fenlei,kemusi_suiji,kemusi_moni;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.main_kemusi);
				
				initView();
	}
	private void initView() 
	{
		kesikaogui = (TextView) findViewById(R.id.kesikaogui);
		jiaotongbiaozhi2 = (TextView) findViewById(R.id.jiaotongbiaozhi2);
		kesimiji = (TextView) findViewById(R.id.kesimiji);
		
		kemusi_wodeshoucang=(ImageView) findViewById(R.id.kemusi_wodeshoucang);
		kemusi_shujutongji=(ImageView) findViewById(R.id.kemusi_shujutongji);
		kemusi_wodecuoti=(ImageView) findViewById(R.id.kemusi_wodecuoti);
		kemusi_kaoshijilu=(ImageView) findViewById(R.id.kemusi_kaoshijilu);
		
		kemusi_shunxu=(ImageView) findViewById(R.id.kemusi_shunxu);
		kemusi_zhangjie=(ImageView) findViewById(R.id.kemusi_zhangjie);
		kemusi_fenlei=(ImageView) findViewById(R.id.kemusi_fenlei);
		kemusi_suiji=(ImageView) findViewById(R.id.kemusi_suiji);
		kemusi_moni=(ImageView) findViewById(R.id.kemusi_moni);
		
		MyBtnOnClick mytouchlistener = new MyBtnOnClick();
		kesikaogui.setOnClickListener(mytouchlistener);
		jiaotongbiaozhi2.setOnClickListener(mytouchlistener);
		kesimiji.setOnClickListener(mytouchlistener);
		
		kemusi_wodeshoucang.setOnClickListener(mytouchlistener);
		kemusi_shujutongji.setOnClickListener(mytouchlistener);
		kemusi_wodecuoti.setOnClickListener(mytouchlistener);
		kemusi_kaoshijilu.setOnClickListener(mytouchlistener);
		
		kemusi_shunxu.setOnClickListener(mytouchlistener);
		kemusi_zhangjie.setOnClickListener(mytouchlistener);
		kemusi_fenlei.setOnClickListener(mytouchlistener);
		kemusi_suiji.setOnClickListener(mytouchlistener);
		kemusi_moni.setOnClickListener(mytouchlistener);
		
	}
	
		
	private class MyBtnOnClick implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			int mBtnid = v.getId();
			
			switch(mBtnid){			
			case R.id.kesikaogui:
				startActivity(new Intent().setClass(KeMusiActivity.this, KskgActivity.class));
				break;
			case R.id.jiaotongbiaozhi2:
				startActivity(new Intent().setClass(KeMusiActivity.this, JtbzActivity.class));
				break;
			case R.id.kesimiji:
				startActivity(new Intent().setClass(KeMusiActivity.this, KsmjActivity.class));
				break;
			case R.id.kemusi_wodeshoucang:
				MainActivity.LXmoshi=4;//我的收藏模式
				startActivity(new Intent().setClass(KeMusiActivity.this, shoucangActivity.class));
				break;
			case R.id.kemusi_shujutongji:
				MainActivity.DianJiKeMu="科目四";
				startActivity(new Intent().setClass(KeMusiActivity.this, ShuJu_TongJi_Main.class));
				break;	
			case R.id.kemusi_wodecuoti:
				MainActivity.LXmoshi=5;//我的错题模式
				startActivity(new Intent().setClass(KeMusiActivity.this, cuotiActivity.class));
				break;
			case R.id.kemusi_kaoshijilu:
				MainActivity.DianJiKeMu="科目四";
				MainActivity.kaoshijilu=2;
				startActivity(new Intent().setClass(KeMusiActivity.this,kaoshijiluActivity.class));
				break;
			case R.id.kemusi_shunxu:
				MainActivity.LXmoshi=0;//为顺序练习
				MainActivity.DianJiKeMu="科目四";
				startActivity(new Intent().setClass(KeMusiActivity.this, shunxuActivity.class));
				break;
			case R.id.kemusi_zhangjie:
				MainActivity.LXmoshi=1;//为章节练习
				MainActivity.DianJiKeMu="科目四";
				startActivity(new Intent().setClass(KeMusiActivity.this,zhangjieXUANZE.class));
				break;
			case R.id.kemusi_fenlei:
				MainActivity.LXmoshi=2;//为分类练习
				MainActivity.DianJiKeMu="科目四";
				startActivity(new Intent().setClass(KeMusiActivity.this, fenleiActivity.class));
				break;
			case R.id.kemusi_suiji:
				MainActivity.LXmoshi=3;//为随机练习
				MainActivity.DianJiKeMu="科目四";
				startActivity(new Intent().setClass(KeMusiActivity.this, shunxuActivity.class));
				break;
			case R.id.kemusi_moni:
				MainActivity.DianJiKeMu="科目四";
				MainActivity.kaoshijilu=0;
				startActivity(new Intent().setClass(KeMusiActivity.this, kaoshiActivity.class));
				break;	
			}
			
		}
	
	}
}
package com.bn.driversystem_android.kemuyi;

import com.bn.driversystem_android.R;
import com.bn.util.DBUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings("deprecation")
public class zhangjieXUANZE extends Activity{//我的错题
	static int zhangjieBZ;  //无 0   标志题1  手势题 2  灯光题  3
	String zhangjiemingcheng[]=new String[4];
	private ListView listview_zhangjie;
	private LinearLayout linear_qingkongSC;
	//对listview  的图片进行设置   现在写成一样的图片   一会记得改
	int zhangjieimage[]={R.drawable.wu,R.drawable.biao,R.drawable.shou,R.drawable.deng};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.zhangjie);
				//返回按钮
				 ImageView subject_two_kaogui_title;
				subject_two_kaogui_title=(ImageView)findViewById(R.id.back);
				subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
				{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//相当于手机的返回按钮
						zhangjieXUANZE.this.finish();
					}
					
				}
				);
				listview_zhangjie=(ListView)findViewById(R.id.ListView_zhangjie);
				linear_qingkongSC=(LinearLayout)findViewById(R.id.Linear_qingkongSC);
				linear_qingkongSC.setVisibility(View.GONE);
				zhangjiemingcheng=DBUtil.getzhangjieName();
				//初始化ListView
				BaseAdapter ba=new BaseAdapter()
		        {
					@Override
					public int getCount() {
						return 4;
						}

					@Override
					public Object getItem(int arg0) { return null; }

					@Override
					public long getItemId(int arg0) { return 0; }

					@Override
					public View getView(int arg0, View arg1, ViewGroup arg2) {
						/*
						 * 动态生成每个下拉项对应的View，每个下拉项View由LinearLayout
						 *中包含一个TextView构成
						*/
						
						//初始化LinearLayout
						LinearLayout ll=new LinearLayout(zhangjieXUANZE.this);
						ll.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向	
						ll.setPadding(5,5,5,5);//设置四周留白
						
						//初始化ImageView    //记得更改图片大小  或者上网查设置
						ImageView  ii=new ImageView(zhangjieXUANZE.this);
						ii.setImageDrawable(getResources().getDrawable(zhangjieimage[arg0]));//设置图片
						ii.setScaleType(ImageView.ScaleType.FIT_XY);
						ii.setLayoutParams(new Gallery.LayoutParams(100,98));
						ll.addView(ii);//添加到LinearLayout中
						
						//初始化TextView
						TextView tv=new TextView(zhangjieXUANZE.this);
						tv.setText(zhangjiemingcheng[arg0]);//设置内容
						tv.setTextSize(24);//设置字体大小
						tv.setTextColor(zhangjieXUANZE.this.getResources().getColor(R.color.palevioletred));//设置字体颜色
						tv.setPadding(5,5,5,5);//设置四周留白
					    tv.setGravity(Gravity.LEFT);
						ll.addView(tv);//添加到LinearLayout中				
						return ll;
					}        	
		        };
		        listview_zhangjie.setAdapter(ba);//为ListView设置内容适配器
		        listview_zhangjie.setOnItemClickListener(
		 	           new OnItemClickListener()
		 	           {
		 				@Override
		 				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		 						long arg3) 
		 				{//重写选项被单击事件的处理方法
		 					LinearLayout ll=(LinearLayout)arg1;//获取当前选中选项对应的LinearLayout
		 					TextView tvn=(TextView)ll.getChildAt(1);//获取其中的TextView 
		 					String zjName=tvn.getText().toString();//得到了你选择的选项中的内容
		 					if(zjName.equals("无"))
		 					{
		 						zhangjieBZ=0;
		 						startActivity(new Intent().setClass(zhangjieXUANZE.this, shunxuActivity.class));
		 						
		 					}
		 					else if(zjName.equals("标志题"))
		 					{
		 						zhangjieBZ=1;
		 						startActivity(new Intent().setClass(zhangjieXUANZE.this, shunxuActivity.class));
		 					}
		 					else if(zjName.equals("手势题"))
		 					{
		 						zhangjieBZ=2;
		 						startActivity(new Intent().setClass(zhangjieXUANZE.this, shunxuActivity.class));
		 					}
		 					else if(zjName.equals("灯光题"))
		 					{
		 						zhangjieBZ=3;
		 						startActivity(new Intent().setClass(zhangjieXUANZE.this, shunxuActivity.class));
		 					}
		 				}
		 	           }
		 	        );
				
	}
}
package com.bn.driversystem_android.kemuyi;

import com.bn.driversystem_android.MainActivity;
import com.bn.driversystem_android.R;
import com.bn.util.DBUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class kaoshijiluActivity extends Activity{//我的错题
	private ImageView back;
	private ListView listview_zhangjie;
	private LinearLayout linear_qingkongSC;
	private TextView textview_biaoti_dati,textview_qingkong;
	String ksid[];//存放  所有属于该科目的ks_id
	static String KSID;//存放考试ID  等待传入考试Activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.zhangjie);
				back=(ImageView)findViewById(R.id.back);
				listview_zhangjie=(ListView)findViewById(R.id.ListView_zhangjie);
				linear_qingkongSC=(LinearLayout)findViewById(R.id.Linear_qingkongSC);
				textview_biaoti_dati=(TextView)findViewById(R.id.textview_biaoti_dati);
				textview_qingkong=(TextView)findViewById(R.id.textview_qingkong);
				textview_qingkong.setText("清空所有考试记录");
				textview_biaoti_dati.setText("考试记录");
				ksid=DBUtil.getksidBYkemu(MainActivity.DianJiKeMu);
				back.setOnClickListener(new OnClickListener()
				{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
					
				});
		      //初始化ListView
				final BaseAdapter ba=new BaseAdapter()
		        {
					@Override
					public int getCount() {
						//还有错  记得改
						return kaoshijilucount(MainActivity.DianJiKeMu);
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
						LinearLayout ll=new LinearLayout(kaoshijiluActivity.this);
						ll.setOrientation(LinearLayout.VERTICAL);		//设置朝向	
						ll.setPadding(5,5,5,5);//设置四周留白
						
						String ksID=ksid[arg0];//拿到第arg0个考试ID
						String shitiid[]=DBUtil.getSTidFromKSByks_id(ksID);//拿到所有的试题id
						String shijianANDfenshu[]=DBUtil.getKSJUFENandSJ(ksID, shitiid[0]);
						//初始化Textview  这个是  //  第一次模拟  0分//
						TextView textTH=new TextView(kaoshijiluActivity.this);
						int cishu=arg0+1;
						textTH.setText("这是第"+cishu+"次模拟: "+shijianANDfenshu[1]+"分");//设置内容
						textTH.setTextSize(24);//设置字体大小
						textTH.setTextColor(kaoshijiluActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
						ll.addView(textTH);//添加到LinearLayout中 
						
						//初始化TextView   这里是时间
						TextView textTM=new TextView(kaoshijiluActivity.this);
						textTM.setText(shijianANDfenshu[0]);//设置内容
						textTM.setTextSize(20);//设置字体大小
						textTM.setTextColor(kaoshijiluActivity.this.getResources().getColor(R.color.slateblue));//设置字体颜色
						textTM.setPadding(5,5,5,5);//设置四周留白
						textTM.setGravity(Gravity.LEFT);
						ll.addView(textTM);//添加到LinearLayout中	
						
						//初始化TextView   这是隐藏view  用来写入考试id 
						TextView textksid=new TextView(kaoshijiluActivity.this);
						textksid.setText(ksID);//设置内容
						textksid.setTextSize(20);//设置字体大小
						textksid.setTextColor(kaoshijiluActivity.this.getResources().getColor(R.color.slateblue));//设置字体颜色
						textksid.setPadding(5,5,5,5);//设置四周留白
						textksid.setGravity(Gravity.LEFT);
						textksid.setVisibility(View.GONE);
						ll.addView(textksid);//添加到LinearLayout中	
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
			 					TextView tvn=(TextView)ll.getChildAt(2);//获取其中的TextView 
			 					KSID=tvn.getText().toString();//得到了你选择的选项中的内容  即  考试ID
			 					if(MainActivity.kaoshijilu==1)
			 					{
			 						startActivity(new Intent().setClass(kaoshijiluActivity.this, kaoshiBHYduo.class));//启动  答题界面
			 					}
			 					else if(MainActivity.kaoshijilu==2)
			 					{
			 						startActivity(new Intent().setClass(kaoshijiluActivity.this, kaoshiActivity.class));//启动  界面
			 					}
			 				}
			 	           }
			 	        );
		        //对清空考试记录 按钮    进行监听
		        linear_qingkongSC.setOnClickListener(new View.OnClickListener()
		        {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//先注释   记得把删除解开  现在要用
						DBUtil.deleteAllKSJL();
						Toast.makeText(kaoshijiluActivity.this, "已经清空所有考试记录", Toast.LENGTH_SHORT).show();
						 listview_zhangjie.setAdapter(ba);//为ListView设置内容适配器
					}
		        });
		 
	}
//根据考试ID  得到总共需要几条记录
	public int kaoshijilucount(String kemu)
	{
		String ksdeID[]=DBUtil.getksidBYkemu(kemu);
		int shuliang=0;
		for(int i=0;i<ksdeID.length;i++)
		{
			if(ksdeID[i]!=null)
			{
				shuliang++;//当  内容不为空  则数量+1
			}
		}
		return shuliang;
	}
}
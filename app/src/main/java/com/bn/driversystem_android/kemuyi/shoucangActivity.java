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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings({ "unused", "deprecation" })
public class shoucangActivity extends Activity{//我的错题
	private ListView listview_zhangjie;
	private LinearLayout linear_qingkongSC;
	private TextView textview_biaoti_dati;
	String shitiid[];//这个存放的是  收藏表拿来的试题的ID
	String shitidexinxi[][]=new String [1][5];
	static String SCshitiID=null;
	static int putongmoshi=0;
	//对listview  的图片进行设置   现在写成一样的图片   一会记得改
	int zhangjieimage[]={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
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
						shoucangActivity.this.finish();
					}
					
				}
				);
				
				listview_zhangjie=(ListView)findViewById(R.id.ListView_zhangjie);
				linear_qingkongSC=(LinearLayout)findViewById(R.id.Linear_qingkongSC);
				textview_biaoti_dati=(TextView)findViewById(R.id.textview_biaoti_dati);
				textview_biaoti_dati.setText("我的收藏");
				//初始化ListView
				final BaseAdapter ba=new BaseAdapter()
		        {
					@Override
					public int getCount() {
						return SCzongshu();
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
						LinearLayout ll=new LinearLayout(shoucangActivity.this);
						ll.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向	
						ll.setPadding(5,5,5,5);//设置四周留白
						
						
						shitiid=new String[SCzongshu()];
						shitiid=DBUtil.getSCallID();//得到收藏标的试题ID
		
						//初始化Textview    这个是题号  //刚刚写的  写错了  先用着
						TextView textTH=new TextView(shoucangActivity.this);
						textTH.setText(arg0+1+"");//设置内容
						textTH.setTextSize(24);//设置字体大小
						textTH.setTextColor(shoucangActivity.this.getResources().getColor(R.color.black));//设置字体颜色
						ll.addView(textTH);//添加到LinearLayout中 
						
						
						//初始化TextView   这里写的是题目
						shitidexinxi=fangfa.getshiti(shitiid[arg0]);//得到  该题的试题信息
						TextView textTM=new TextView(shoucangActivity.this);
						textTM.setText(shitidexinxi[0][0]);//设置内容
						textTM.setTextSize(15);//设置字体大小
						textTM.setTextColor(shoucangActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
						textTM.setPadding(5,5,5,5);//设置四周留白
						textTM.setGravity(Gravity.LEFT);
						ll.addView(textTM);//添加到LinearLayout中				
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
		 					String timu=tvn.getText().toString();//得到了你选择的选项中的内容
		 					SCshitiID=DBUtil.getSTidBYtimu(timu);//点击以后 得到点击选项的试题ID
		 					String shitixinxi[][]=fangfa.getshiti(SCshitiID);//根据试题ID 得到试题信息
		 					if(!shitixinxi[0][3].equals("多项选择题"))
		 					{
		 						startActivity(new Intent().setClass(shoucangActivity.this, shunxuActivity.class));//启动  顺序答题界面
		 					}
		 					else 
		 					{
		 						putongmoshi=1;//等于1时   告诉多项activity  现在是别人进入的
		 						startActivity(new Intent().setClass(shoucangActivity.this, duoxiangdatiActivity.class));//启动  顺序答题界面
		 					}
		 					
		 				}
		 	           }
		 	        );
		  //对清空收藏按钮  进行监听
		        linear_qingkongSC.setOnClickListener(new View.OnClickListener()
		        {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DBUtil.deleteAllSC();
						Toast.makeText(shoucangActivity.this, "已经清空所有的收藏", Toast.LENGTH_SHORT).show();
						listview_zhangjie.setAdapter(ba);
					}
		        });
		 
	}
	//得到  收藏表的总数
	   public int SCzongshu()
	   {
		   int Zs=0;
		   try
		   {
			   String zongshu=DBUtil.getSCCount();
			   Zs=Integer.parseInt(zongshu);
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		return Zs;
	   }
}
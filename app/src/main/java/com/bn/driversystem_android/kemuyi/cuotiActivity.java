package com.bn.driversystem_android.kemuyi;
import com.bn.Begin.Login_Begin_PerSon;
import com.bn.driversystem_android.R;
import com.bn.fyq.Constant.Constant;
import com.bn.util.DBUtil;
import com.bn.util.DataUtil;
import com.bn.util.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
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
public class cuotiActivity extends Activity{//我的错题
	private ImageView back;
	private ListView listview_zhangjie;
	private LinearLayout linear_qingkongSC;
	private TextView textview_biaoti_dati,textview_qingkong,textview_tongbu;
	String shitiid[];//这个存放的是  错题表拿来的试题的ID
	String shitidexinxi[][]=new String [1][5];
	static String CUOTIdeid;
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
				textview_qingkong.setText("清空所有的错题");
				textview_biaoti_dati.setText("我的错题");
				textview_tongbu=(TextView)findViewById(R.id.textview_tongbu);
				textview_tongbu.setVisibility(View.VISIBLE);
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
						LinearLayout ll=new LinearLayout(cuotiActivity.this);
						ll.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向	
						ll.setPadding(5,5,5,5);//设置四周留白
						
						
						shitiid=new String[SCzongshu()];
						shitiid=DBUtil.getCUOTIallID();//得到收藏标的试题ID
		
						//初始化Textview    这个是题号  //刚刚写的  写错了  先用着
						TextView textTH=new TextView(cuotiActivity.this);
						textTH.setText(arg0+1+" :");//设置内容
						textTH.setTextSize(24);//设置字体大小
						textTH.setTextColor(cuotiActivity.this.getResources().getColor(R.color.palevioletred));//设置字体颜色
						ll.addView(textTH);//添加到LinearLayout中 
						
						
						//初始化TextView   这里写的是题目
						shitidexinxi=fangfa.getshiti(shitiid[arg0]);//得到  该题的试题信息
						TextView textTM=new TextView(cuotiActivity.this);
						textTM.setText(shitidexinxi[0][0]);//设置内容
						textTM.setTextSize(20);//设置字体大小
						textTM.setTextColor(cuotiActivity.this.getResources().getColor(R.color.slateblue));//设置字体颜色
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
		 					CUOTIdeid=DBUtil.getSTidBYtimu(timu);//点击以后 得到点击选项的试题ID
		 					startActivity(new Intent().setClass(cuotiActivity.this, shunxuActivity.class));//启动  答题界面
		 				}
		 	           }
		 	        );
		  //对清空收藏按钮  进行监听
		        linear_qingkongSC.setOnClickListener(new View.OnClickListener()
		        {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DBUtil.deleteAllCUOTI();
						Toast.makeText(cuotiActivity.this, "已经清空所有的错题", Toast.LENGTH_SHORT).show();
						listview_zhangjie.setAdapter(ba);
					}
		        });
		        //同步按钮 的监听
textview_tongbu.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//Toast.makeText(cuotiActivity.this, "ssss", Toast.LENGTH_SHORT).show();
						if(Constant.LOGIN_OK==0)
						{
							Toast.makeText(cuotiActivity.this, "请登录!", Toast.LENGTH_SHORT).show();
							//走起  登录界面
						}
						else 
						{
							showNormalDia();
						}
					}
				});
		 
	}
	//得到  收藏表的总数
	   public int SCzongshu()
	   {
		   int Zs=0;
		   try
		   {
			   String zongshu=DBUtil.getCUOTICount();
			   Zs=Integer.parseInt(zongshu);
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		return Zs;
	   }
	 //写同步对话框
		private void showNormalDia()  
	    {  
	        //AlertDialog.Builder normalDialog=new AlertDialog.Builder(getApplicationContext());  
	        AlertDialog.Builder normalDia=new AlertDialog.Builder(cuotiActivity.this);  
	        normalDia.setIcon(R.drawable.ic_launcher);  
	        normalDia.setTitle("同步");
	       normalDia.setMessage("选择将你的错题同步至数据库还是下载你的错题?"); 
	        normalDia.setPositiveButton("同步", new DialogInterface.OnClickListener() {  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                // TODO Auto-generated method stub
	            	//Toast.makeText(cuotiActivity.this, "tongbu", Toast.LENGTH_SHORT).show();
	            	if(SCzongshu()==0)
	            	{
	            		Toast.makeText(cuotiActivity.this, "你还没有错题！", Toast.LENGTH_SHORT).show();
	            	}
	            	else
	            	{
	            	SocketClient.ConnectSevert(Constant.GETID_BY_ZHANGHAO+Login_Begin_PerSon.GET_ZHANGHAO);
					String SCid=SocketClient.readinfo;//得到用户的ID
					//试题同步之前  将之前这个用户干掉
					SocketClient.ConnectSevert(Constant.delErrorByu+SCid);
					String shanchujieguo=SocketClient.readinfo;
					SocketClient.ConnectSevert(Constant.getErrorMaxID);
					String STmaxid=SocketClient.readinfo;//得到的错题最大ID
					int maxid=Integer.parseInt(STmaxid);
					String tjshijian=fangfa.huoqushijian();
					shitiid=new String[SCzongshu()];
					shitiid=DBUtil.getCUOTIallID();//得到收藏标的试题ID
					if(shanchujieguo.equals("ok"))
					{
						for(int q=0;q<SCzongshu();q++)
						{
							StringBuilder s=new StringBuilder();
							maxid++;
							String e_id=fangfa.zhuanhuanSTid(maxid);
							s.append(Constant.addError+e_id);//添加错题ID
							s.append(Constant.addError+shitiid[q]);//添加错题的ID
							s.append(Constant.addError+SCid);//添加用户ID
							s.append(Constant.addError+tjshijian);//添加错题的ID
							SocketClient.ConnectSevert(s.toString());
						}
						Toast.makeText(cuotiActivity.this, "同步成功", Toast.LENGTH_SHORT).show();
					}
					else 
					{
						Toast.makeText(cuotiActivity.this, "同步失败", Toast.LENGTH_SHORT).show();
					}
	            	}
	            }  
	        });  
	        normalDia.setNegativeButton("下载", new DialogInterface.OnClickListener() {  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                // TODO Auto-generated method stub  
	            	//判断是否含有
	            	SocketClient.ConnectSevert(Constant.GETID_BY_ZHANGHAO+Login_Begin_PerSon.GET_ZHANGHAO);
					String SCid=SocketClient.readinfo;//得到用户的ID
					SocketClient.ConnectSevert(Constant.getErrorCount+SCid);
					String cuotiCount=SocketClient.readinfo;//得到的错题最大ID
					if(cuotiCount.equals("0"))
					{
						Toast.makeText(cuotiActivity.this, "服务器没有你的错题", Toast.LENGTH_SHORT).show();
					}
					else
					{	
						try
						{
							DataUtil.jiazaierror(SCid);
						}
						catch(Exception e)
						{
							Toast.makeText(cuotiActivity.this, "加载错题失败", Toast.LENGTH_SHORT).show();
						}
						cuotiActivity.this.finish();
						startActivity(new Intent().setClass(cuotiActivity.this, cuotiActivity.class));
					}
	            }  
	        });  
	        normalDia.create().show();  
	    }
}
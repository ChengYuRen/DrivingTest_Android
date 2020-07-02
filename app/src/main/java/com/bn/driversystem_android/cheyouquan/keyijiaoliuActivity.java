package com.bn.driversystem_android.cheyouquan;

import com.bn.Begin.Login_Begin_FromCheYouQuan;
import com.bn.driversystem_android.CheYouQuanActivity;
import com.bn.driversystem_android.R;
import com.bn.fyq.Constant.Constant;
import com.bn.util.DataUtil;
import com.bn.util.SocketClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class keyijiaoliuActivity extends Activity{//我的错题
	public String[][] sdata;
	public String [][] sname;
	static String TieMainID;
	int []ceshitupian={R.drawable.login_head};
	public static int CheYouQuan_KeYi=0;//是否从...界面进入 其他界面同理
	private TextView luntan_textview;
	private ImageView fanhui_lt,imageview_canyutaolun;
	private ScrollView ScrollView_tiemain,ScrollView_huifu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				
				//如果登录标志位为0 则调到登录界面
				if(Constant.LOGIN_OK==0)
				{
					Toast toast=Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT);
					toast.show(); 
					CheYouQuan_KeYi=1;
					//跳转到登录界面
					Intent intent=new Intent();
					intent.setClass(keyijiaoliuActivity.this, Login_Begin_FromCheYouQuan.class);
					startActivity(intent);
					keyijiaoliuActivity.this.finish();
				}
				else
				{
					setContentView(R.layout.luntan);
					ScrollView_tiemain=(ScrollView)findViewById(R.id.ScrollView_tiemian);
					ScrollView_tiemain.setVisibility(View.VISIBLE);
					ScrollView_huifu=(ScrollView)findViewById(R.id.ScrollView_hufu);
					ScrollView_huifu.setVisibility(View.GONE);
					String LunTanXX=CheYouQuanActivity.luntanXX;//确定点击的是哪个类别
					luntan_textview=(TextView)findViewById(R.id.luntan_textview);
					luntan_textview.setText(CheYouQuanActivity.luntanMZ);
					String getreply=null;
					fanhui_lt=(ImageView)findViewById(R.id.fanhui_lt);
					imageview_canyutaolun=(ImageView)findViewById(R.id.imageview_canyutaolun);
					fanhui_lt.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v) {
							
							keyijiaoliuActivity.this.finish();
						}
					}
					);
					//点击打开开贴界面
					imageview_canyutaolun.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							//当前页面处于tiemain  所以是新开一个帖子
							startActivity(new Intent().setClass(keyijiaoliuActivity.this, fabutiezi.class));	
							
						}
					});
					try
					{
						SocketClient.ConnectSevert(Constant.getTiemainbyclass+LunTanXX);
						getreply=SocketClient.readinfo;//得到所有的主贴类别
					}
					catch(Exception e )
					{
						e.printStackTrace();
					}
					String[] s=getreply.split("#");//删除最后的#号  将每条回复写于 数组中
					 sdata=new String [s.length][6];//5是主贴表的属性个数
					
					 for(int i=0;i<s.length;i++)
					 {
						String[] sreply=s[i].split("η");//通过η将数组中的每个数据分割开来
						
						for(int j=0;j<sreply.length;j++)
						 {
							sdata[i][j]=sreply[j];
						 }
					}
					 sname=new String [s.length][1];
					 for(int i=0;i<s.length;i++)//更改用户id 为名称
					 {
						 
						 String suser=sdata[i][2];
						 try
						 {
							  SocketClient.ConnectSevert(Constant.getUserNameById+suser);
							  sname[i][0]=SocketClient.readinfo;//得到所有的数据
						 }
						 catch(Exception e)
						 {
							 e.printStackTrace();
						 }
						
					}
					 //现在sdata中  0为编号 1为科目  2为用户ID  3为题目  4为内容  5为时间
					//初始化ListView
				        ListView lv=(ListView)this.findViewById(R.id.ListView_xuanxiang);
				        //为ListView准备内容适配器
				        BaseAdapter ba=new BaseAdapter()
				        {
							@Override
							public int getCount() {
								return sdata.length;
							}

							@Override
							public Object getItem(int arg0) { return null; }

							@Override
							public long getItemId(int arg0) { return 0; }

							@Override
							public View getView(int arg0, View arg1, ViewGroup arg2) {
								/*
								 * 动态生成每个下拉项对应的View，每个下拉项View由LinearLayout
								 *中包含一个ImageView及一个TextView构成
								*/
								//初始化  最外面的linearlayout
								LinearLayout linear_zong=new LinearLayout(keyijiaoliuActivity.this);
								linear_zong.setOrientation(LinearLayout.VERTICAL);		//设置朝向	
								linear_zong.setPadding(5,5,5,5);//设置四周留白
								//初始化LinearLayout   
								LinearLayout linear_yonghu=new LinearLayout(keyijiaoliuActivity.this);
								linear_yonghu.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向	
								linear_yonghu.setPadding(5,5,5,5);//设置四周留白
								linear_zong.addView(linear_yonghu);
								
								
								
								//初始化一个linearlayout   用来存放用户照片
								LinearLayout linear_image=new LinearLayout(keyijiaoliuActivity.this);
								linear_image.setOrientation(LinearLayout.VERTICAL);		//设置朝向
								linear_yonghu.addView(linear_image);
								//初始化ImageView
								ImageView  imageview_yonghu=new ImageView(keyijiaoliuActivity.this);
								//根据用户ID  从服务器访问图片
								SocketClient.ConnectSevert(Constant.getUsertouxiangByid+sdata[arg0][2]);
								String getreply=SocketClient.readinfo;//得到图片信息
								if(getreply.equals("空"))
								{//如果为空  就用死图片
									imageview_yonghu.setImageDrawable(getResources().getDrawable(ceshitupian[0]));//设置图片
								}
								else 
								{
									Bitmap bm=DataUtil.stringtoBitmap(getreply);
									imageview_yonghu.setImageBitmap(bm);
								}
								imageview_yonghu.setScaleType(ImageView.ScaleType.FIT_XY);
								imageview_yonghu.setLayoutParams(new Gallery.LayoutParams(100,98));
								linear_image.addView(imageview_yonghu);//添加到LinearLayout中
								
								
								
								//初始化一个linearlayout用来存放两个textview
								LinearLayout linear_textview=new LinearLayout(keyijiaoliuActivity.this);
								linear_textview.setOrientation(LinearLayout.VERTICAL);		//设置朝向
								linear_yonghu.addView(linear_textview);
								//初始化TextView
								final TextView textview_nicheng=new TextView(keyijiaoliuActivity.this);
								textview_nicheng.setText(sname[arg0][0]);//设置内容
								textview_nicheng.setTextSize(14);//设置字体大小
								textview_nicheng.setTextColor(Color.GRAY);
								textview_nicheng.setPadding(5,5,5,5);//设置四周留白
								textview_nicheng.setGravity(Gravity.LEFT);
							    linear_textview.addView(textview_nicheng);//添加到LinearLayout中
							    //初始化另一个textview
							    TextView textview_shijian=new TextView(keyijiaoliuActivity.this);
							    textview_shijian.setText(sdata[arg0][5]);//设置内容
							    textview_shijian.setTextSize(10);//设置字体大小
							    textview_shijian.setPadding(5,5,5,5);//设置四周留白
							    textview_shijian.setTextColor(Color.GRAY);
							    textview_shijian.setGravity(Gravity.LEFT);
							    linear_textview.addView(textview_shijian);//添加到LinearLayout中
							    //对上面linear的监听
							    linear_yonghu.setOnClickListener(new View.OnClickListener() {
													
													@Override
													public void onClick(View v) {
														// TODO Auto-generated method stub
														String renming=textview_nicheng.getText().toString();
														Toast.makeText(keyijiaoliuActivity.this, "你点的是:"+renming+",具体activity一会再说", Toast.LENGTH_SHORT).show();
													}
												});
							    
							    
							    //=====================================
							    			//上面添加用户成功  下面添加发帖人标题  以及其内容
							    //=======================================
							  //初始化LinearLayout    写出内容的linearLayout
								final LinearLayout linear_neirong=new LinearLayout(keyijiaoliuActivity.this);
								linear_neirong.setOrientation(LinearLayout.VERTICAL);		//设置朝向	
								linear_neirong.setPadding(5,5,5,5);//设置四周留白
								linear_zong.addView(linear_neirong);
								//初始化TextView
								final TextView textview_biaoti=new TextView(keyijiaoliuActivity.this);
								textview_biaoti.setText(sdata[arg0][3]);//设置内容
							
								textview_biaoti.setTextSize(18);//设置字体大小
								linear_neirong.addView(textview_biaoti);//添加到LinearLayout中
							    //初始化另一个textview
							    TextView textview_neirong=new TextView(keyijiaoliuActivity.this);
							    textview_neirong.setText(sdata[arg0][4]);//设置内容
							    textview_neirong.setTextSize(12);//设置字体大小
							    linear_neirong.addView(textview_neirong);//添加到LinearLayout中
							    linear_neirong.setOnClickListener(new View.OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										//根据点击内容  获取题目
										String dianjiTM=textview_biaoti.getText().toString();
										SocketClient.ConnectSevert(Constant.getTiemainIDByName+dianjiTM);
										TieMainID=SocketClient.readinfo;//根据题目内容  得到所选择的题目ID
										//接下来  应该新建立一个activity   来显示回复内容
										startActivity(new Intent().setClass(keyijiaoliuActivity.this, keyireplyActivity.class));
									}
								});
								return linear_zong;
							}        	
				        };
				        lv.setAdapter(ba);//为ListView设置内容适配器
			}
				}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    
    {
	 	if(keyCode == KeyEvent.KEYCODE_BACK)
	 	{
	 		keyijiaoliuActivity.this.finish();
	 	}
		return false;
	 
    }
}
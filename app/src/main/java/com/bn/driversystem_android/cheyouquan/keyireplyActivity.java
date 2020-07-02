package com.bn.driversystem_android.cheyouquan;
import com.bn.driversystem_android.R;
import com.bn.driversystem_android.kemuyi.fangfa;
import com.bn.fyq.Constant.Constant;
import com.bn.util.DataUtil;
import com.bn.util.SocketClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class keyireplyActivity extends Activity{
	
	public String[][] sdata;
	public String [][] sname;
	String replyadd[]=new String [5];
	StringBuilder sadd=new StringBuilder();
	ImageView fanhui_lt;
	int []ceshitupian={R.drawable.login_head};
	private ListView ListView_xuanxiang;
	private TextView luntan_textview;
	private ScrollView ScrollView_tiemain,ScrollView_huifu;
	private ImageView imageview_fabureply;
	private EditText edittext_fabureply;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.luntan);
				luntan_textview=(TextView)findViewById(R.id.luntan_textview);
				luntan_textview.setText("回复");
				String tiemainid=keyijiaoliuActivity.TieMainID;//if  点击的是科目一
				fanhui_lt=(ImageView)findViewById(R.id.fanhui_lt);
				ScrollView_tiemain=(ScrollView)findViewById(R.id.ScrollView_tiemian);
				ScrollView_tiemain.setVisibility(View.GONE);
				ScrollView_huifu=(ScrollView)findViewById(R.id.ScrollView_hufu);
				ScrollView_huifu.setVisibility(View.VISIBLE);
				edittext_fabureply=(EditText)findViewById(R.id.edittext_fabureply);
				imageview_fabureply=(ImageView)findViewById(R.id.imageview_fabureply);
				ListView_xuanxiang=(ListView)findViewById(R.id.ListView_xuanxiang);
				ListView_xuanxiang.setBackgroundResource(R.drawable.tiezi_bg);
				fanhui_lt.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						keyireplyActivity.this.finish();
					}
				});
				imageview_fabureply.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						SocketClient.ConnectSevert(Constant.getReplyMaxID);
						String String_getid=SocketClient.readinfo;//得到主贴类别最大ID
						int int_getid=Integer.parseInt(String_getid);
						int_getid++;
						replyadd[0]=fangfa.zhuanhuanSTid(int_getid);//现在得到可输入ID
						//Toast.makeText(fabutiezi.this, tiemainadd[0], Toast.LENGTH_SHORT).show();
						replyadd[1]=keyijiaoliuActivity.TieMainID;//得到所属与的主贴表ID
						replyadd[2]="00001";//jidexiugai
						replyadd[3]=edittext_fabureply.getText().toString();
						if(replyadd[3].length()==0)
						{
							Toast.makeText(keyireplyActivity.this, "请输入内容再发布", Toast.LENGTH_SHORT).show();
							return;
						}
						replyadd[4]=fangfa.huoqushijian();
						for(int i=0;i<replyadd.length;i++)
						{
							sadd.append(Constant.addReply+replyadd[i]);
						}
						SocketClient.ConnectSevert(sadd.toString());
						String isok=SocketClient.readinfo;
						if(isok.equals("ok"))
						{
							keyireplyActivity.this.finish();
							startActivity(new Intent().setClass(keyireplyActivity.this, keyireplyActivity.class));	
						}
						else
						{
							Toast.makeText(keyireplyActivity.this, "哦哦  貌似失败了", Toast.LENGTH_SHORT).show();
						}
					}
				});
				String getreply=null;
				try
				{
					SocketClient.ConnectSevert(Constant.GET_REPLYbyid+tiemainid);
					getreply=SocketClient.readinfo;//得到所有的主贴回复
				}
				catch(Exception e )
				{
					e.printStackTrace();
				}
				String[] s=getreply.split("#");//删除最后的#号  将每条回复写于 数组中
				 sdata=new String [s.length][4];//5是主贴表的属性个数
				 for(int i=0;i<s.length;i++)
				 {
					String[] sreply=s[i].split("η");//通过η将数组中的每个数据分割开来
					
					for(int j=0;j<sreply.length;j++)
					 {
						sdata[i][j]=sreply[j];//得到回复表
					 }
				}
				 sname=new String [s.length][1];
				 for(int i=0;i<s.length;i++)//更改用户id 为名称
				 {
					 
					 String suser=sdata[i][1];
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
				//现在sdata中  0为编号 1为yonghu  2为回复  3为时间
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
							LinearLayout linear_zong=new LinearLayout(keyireplyActivity.this);
							linear_zong.setOrientation(LinearLayout.VERTICAL);		//设置朝向	
							linear_zong.setPadding(5,5,5,5);//设置四周留白
							//初始化LinearLayout   
							LinearLayout linear_yonghu=new LinearLayout(keyireplyActivity.this);
							linear_yonghu.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向	
							linear_yonghu.setPadding(5,5,5,5);//设置四周留白
							linear_zong.addView(linear_yonghu);
							
							
							
							//初始化一个linearlayout   用来存放用户照片
							LinearLayout linear_image=new LinearLayout(keyireplyActivity.this);
							linear_image.setOrientation(LinearLayout.VERTICAL);		//设置朝向
							linear_yonghu.addView(linear_image);
							//初始化ImageView
							ImageView  imageview_yonghu=new ImageView(keyireplyActivity.this);
							//根据用户ID  从服务器访问图片
							SocketClient.ConnectSevert(Constant.getUsertouxiangByid+sdata[arg0][1]);
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
							LinearLayout linear_textview=new LinearLayout(keyireplyActivity.this);
							linear_textview.setOrientation(LinearLayout.VERTICAL);		//设置朝向
							linear_yonghu.addView(linear_textview);
							//初始化TextView
							final TextView textview_nicheng=new TextView(keyireplyActivity.this);
							textview_nicheng.setText(sname[arg0][0]);//设置内容
							textview_nicheng.setTextSize(14);//设置字体大小
							textview_nicheng.setTextColor(Color.GRAY);
							textview_nicheng.setPadding(5,5,5,5);//设置四周留白
							textview_nicheng.setGravity(Gravity.LEFT);
						    linear_textview.addView(textview_nicheng);//添加到LinearLayout中
						    //初始化另一个textview
						    TextView textview_shijian=new TextView(keyireplyActivity.this);
						    textview_shijian.setText(sdata[arg0][3]);//设置内容
						    textview_shijian.setTextSize(10);//设置字体大小
						    textview_shijian.setPadding(5,5,5,5);//设置四周留白
						    textview_shijian.setGravity(Gravity.LEFT);
						    linear_textview.addView(textview_shijian);//添加到LinearLayout中
						    //对上面linear的监听
						    linear_yonghu.setOnClickListener(new View.OnClickListener() {
												
												@Override
												public void onClick(View v) {
													// TODO Auto-generated method stub
													String renming=textview_nicheng.getText().toString();
													Toast.makeText(keyireplyActivity.this, "你点的是:"+renming+",具体activity一会再说", Toast.LENGTH_SHORT).show();
												}
											});
						    
						    
						    //=====================================
						    			//上面添加用户成功  下面添加发帖人标题  以及其内容
						    //=======================================
						  //初始化LinearLayout    写出内容的linearLayout
							final LinearLayout linear_neirong=new LinearLayout(keyireplyActivity.this);
							linear_neirong.setOrientation(LinearLayout.VERTICAL);		//设置朝向	
							linear_neirong.setPadding(5,5,5,5);//设置四周留白
							linear_zong.addView(linear_neirong);
		
						    //初始化另一个textview
						    TextView textview_neirong=new TextView(keyireplyActivity.this);
						    textview_neirong.setText(sdata[arg0][2]);//设置内容
						    textview_neirong.setTextSize(14);//设置字体大小
						    linear_neirong.addView(textview_neirong);//添加到LinearLayout中
							return linear_zong;
						}        	
			        };
			        lv.setAdapter(ba);//为ListView设置内容适配器
				
	}
}
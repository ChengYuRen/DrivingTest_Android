package com.bn.driversystem_android;
import com.bn.driversystem_android.R;
import com.bn.driversystem_android.kemuer.KeMuErKaoGui;
import com.bn.driversystem_android.kemuer.KeMuErMiJi;
import com.bn.driversystem_android.kemuer.KeMuSanKaoGui;
import com.bn.driversystem_android.kemuer.KeMuSanMiJi;
import com.bn.fyq.Constant.Constant;
import com.bn.util.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class KeMuerActivity extends Activity{
	
	//科目二点击按钮 点击后出现科目二界面 默认是科目二界面 科目三按钮选择需点击
	private Button subjectTwo;
	private Button subjectThree;

	
	//科目二考规
	private Button subjectTwoKaoGui;
	//科目二秘籍
	private Button subjectTwoMiJi;
	//科目三考规
	private Button subjectThreeKaoGui;
	//科目三秘籍
	private Button subjectThreeMiJi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_kemuer);
		
		//默认在界面二 各个按钮的监听
		subjectTwoKaoGui=(Button)findViewById(R.id.buttonkaoguitwo);
		subjectTwoMiJi=(Button)findViewById(R.id.buttonmijitwo);
		
		//科目二考规监听
		subjectTwoKaoGui.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(KeMuerActivity.this, KeMuErKaoGui.class);
				startActivity(intent);
			}
			
		}
		);
		//科目二秘籍监听
		subjectTwoMiJi.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(KeMuerActivity.this, KeMuErMiJi.class);
				startActivity(intent);
			}
			
		}
		);				
		
		//科目三点击监听

		subjectThree=(Button)findViewById(R.id.buttonsubjectThree);
		subjectThree.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.main_kemusan);
				subjectThreeKaoGui=(Button)findViewById(R.id.buttonkaoguithree);
				subjectThreeMiJi=(Button)findViewById(R.id.buttonmijithree);
				
				//科目三考规监听
				subjectThreeKaoGui.setOnClickListener(new View.OnClickListener()
				{
		
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						System.out.println("fdfffffffffffffff");
						Intent intent=new Intent();
						intent.setClass(KeMuerActivity.this, KeMuSanKaoGui.class);
						startActivity(intent);
					}
					
				}
				);
				//科目三秘籍监听
				subjectThreeMiJi.setOnClickListener(new View.OnClickListener()
				{
		
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent();
						intent.setClass(KeMuerActivity.this, KeMuSanMiJi.class);
						startActivity(intent);
					}
					
				}
				);	

				//=================
				Button button_shangchezhunbei=(Button)findViewById(R.id.button_shangchezhunbei);
				Button buttonshang_yejiandengguang=(Button)findViewById(R.id.buttonshang_yejiandengguang);	
				Button button_renxingdao=(Button)findViewById(R.id.button_renxingdao);
				Button button_qibu=(Button)findViewById(R.id.button_qibu);
				Button button_xuexiao=(Button)findViewById(R.id.button_xuexiao);
				Button button_zhixian=(Button)findViewById(R.id.button_zhixian);
				Button button_gonggongqiche=(Button)findViewById(R.id.button_gonggongqiche);
				Button button_jiajiansu=(Button)findViewById(R.id.button_jiajiansu);
				Button button_huiche=(Button)findViewById(R.id.button_huiche);
				Button button_biangengchedao=(Button)findViewById(R.id.button_biangengchedao);
				Button button_chaoche=(Button)findViewById(R.id.button_chaoche);
				Button button_kaobiantingche=(Button)findViewById(R.id.button_kaobiantingche);
				Button button_yejianxingshi=(Button)findViewById(R.id.button_yejianxingshi);
				Button buttonzhaiLuDiaoTou=(Button)findViewById(R.id.buttonzhaiLuDiaoTou);
				
				buttonzhaiLuDiaoTou.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )
						.setPositiveButton("是" ,new OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"窄路掉头"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);   
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				
				buttonshang_yejiandengguang.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"模拟夜间场景灯光使用"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);   
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_shangchezhunbei.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"上车准备"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_renxingdao.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"通过人行横道"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_qibu.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"起步"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_xuexiao.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"通过学校区域"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_zhixian.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"直线行驶"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_gonggongqiche.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"通过公共汽车站"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_jiajiansu.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"加减档操作"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_huiche.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"会车"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_biangengchedao.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"变更车道"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_chaoche.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"超车"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_kaobiantingche.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"靠边停车"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_yejianxingshi.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"夜间行驶"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				
				goToSubjectTwo();
				
			}	
		});
		
		
		Button buttondaoCheRuKu=(Button)findViewById(R.id.buttondaoCheRuKu);//倒车入库
		Button button_sluxingshi=(Button)findViewById(R.id.button_sluxingshi);//S
		Button buttonpoDaoStopAndBegin=(Button)findViewById(R.id.buttonpoDaoStopAndBegin);//坡道定点停车
		Button button_cefangtingche=(Button)findViewById(R.id.button_cefangtingche);//侧方
		Button button_zhijiaozhuanwan=(Button)findViewById(R.id.button_zhijiaozhuanwan);//直角
		//===========================BEGIN================================	
		//倒车入库
		buttondaoCheRuKu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new  AlertDialog.Builder(KeMuerActivity.this)   
				.setTitle("小驾提醒您：" )  
				.setMessage("此过程需要联网，建议您在wifi下观看" )  
				
				.setPositiveButton("是" ,new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent= new Intent();        
					    intent.setAction("android.intent.action.VIEW");    
					    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"倒车入库"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
						String isok=SocketClient.readinfo;
					    Uri content_url = Uri.parse(isok);  
					    intent.setData(content_url);  
					    startActivity(intent);
					}
					
				})  
				.setNegativeButton("否" , null)  
				.show();  
			}
		});
		button_sluxingshi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new  AlertDialog.Builder(KeMuerActivity.this)   
				.setTitle("小驾提醒您：" )  
				.setMessage("此过程需要联网，建议您在wifi下观看" )  
				
				.setPositiveButton("是" ,new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent= new Intent();        
					    intent.setAction("android.intent.action.VIEW");    
					    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"S路行驶"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
						String isok=SocketClient.readinfo;
					    Uri content_url = Uri.parse(isok);
					    intent.setData(content_url);  
					    startActivity(intent);
					}
					
				})  
				.setNegativeButton("否" , null)  
				.show();  
			}
		});
		buttonpoDaoStopAndBegin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new  AlertDialog.Builder(KeMuerActivity.this)   
				.setTitle("小驾提醒您：" )  
				.setMessage("此过程需要联网，建议您在wifi下观看" )  
				
				.setPositiveButton("是" ,new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent= new Intent();        
					    intent.setAction("android.intent.action.VIEW");    
					    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"坡道顶点停车和起步"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
						String isok=SocketClient.readinfo;
					    Uri content_url = Uri.parse(isok);
					    intent.setData(content_url);  
					    startActivity(intent);
					}
					
				})  
				.setNegativeButton("否" , null)  
				.show();  
			}
		});
		
		button_cefangtingche.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new  AlertDialog.Builder(KeMuerActivity.this)   
				.setTitle("小驾提醒您：" )  
				.setMessage("此过程需要联网，建议您在wifi下观看" )  
				
				.setPositiveButton("是" ,new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent= new Intent();        
					    intent.setAction("android.intent.action.VIEW");    
					    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"侧方停车"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
						String isok=SocketClient.readinfo;
					    Uri content_url = Uri.parse(isok);
					    intent.setData(content_url);  
					    startActivity(intent);
					}
					
				})  
				.setNegativeButton("否" , null)  
				.show();  
			}
		});
		button_zhijiaozhuanwan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new  AlertDialog.Builder(KeMuerActivity.this)   
				.setTitle("小驾提醒您：" )  
				.setMessage("此过程需要联网，建议您在wifi下观看" )  
				
				.setPositiveButton("是" ,new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent= new Intent();        
					    intent.setAction("android.intent.action.VIEW");    
					    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"直角转弯"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
						String isok=SocketClient.readinfo;
					    Uri content_url = Uri.parse(isok);
					    intent.setData(content_url);  
					    startActivity(intent);
					}
					
				})  
				.setNegativeButton("否" , null)  
				.show();  
			}
		});
		
		
		//=================================END===============
		
		
		
				
		
	}
	//去界面二
	public void goToSubjectTwo()
	{
		subjectTwo=(Button)findViewById(R.id.buttonsubjectTwoThree);
		
		subjectTwo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.main_kemuer);
				
				
				//去了界面二后各个按钮的监听------BEGIN------------
				subjectTwoKaoGui=(Button)findViewById(R.id.buttonkaoguitwo);
				subjectTwoMiJi=(Button)findViewById(R.id.buttonmijitwo);
				
				//科目二考规监听
				subjectTwoKaoGui.setOnClickListener(new View.OnClickListener()
				{
		
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent();
						intent.setClass(KeMuerActivity.this, KeMuErKaoGui.class);
						startActivity(intent);
					}
					
				}
				);
				//科目二秘籍监听
				subjectTwoMiJi.setOnClickListener(new View.OnClickListener()
				{
		
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent();
						intent.setClass(KeMuerActivity.this, KeMuErMiJi.class);
						startActivity(intent);
					}
					
				}
				);		
				//---------------各个按钮监听---------EDN---------
				Button buttondaoCheRuKu=(Button)findViewById(R.id.buttondaoCheRuKu);//倒车入库
				Button button_sluxingshi=(Button)findViewById(R.id.button_sluxingshi);//S
				Button buttonpoDaoStopAndBegin=(Button)findViewById(R.id.buttonpoDaoStopAndBegin);//坡道定点停车
				Button button_cefangtingche=(Button)findViewById(R.id.button_cefangtingche);//侧方
				Button button_zhijiaozhuanwan=(Button)findViewById(R.id.button_zhijiaozhuanwan);//直角
				//===========================BEGIN================================	
				//倒车入库
				buttondaoCheRuKu.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"倒车入库"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_sluxingshi.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"S路行驶"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				buttonpoDaoStopAndBegin.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"坡道顶点停车和起步"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				
				button_cefangtingche.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"侧方停车"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_zhijiaozhuanwan.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"直角转弯"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				
				
				//=================================END===============
				
				//去界面三
				goToSubjectThree();
			}
		});
		

	}
	public void goToSubjectThree()
	{
		subjectThree=(Button)findViewById(R.id.buttonsubjectThree);
		subjectThree.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.main_kemusan);
				
				//这里写界面三各个按钮的监听------BEGIN------------
			
				subjectThreeKaoGui=(Button)findViewById(R.id.buttonkaoguithree);
				subjectThreeMiJi=(Button)findViewById(R.id.buttonmijithree);
				
				//科目三考规监听
				subjectThreeKaoGui.setOnClickListener(new View.OnClickListener()
				{
		
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						System.out.println("fdfffffffffffffff");
						Intent intent=new Intent();
						intent.setClass(KeMuerActivity.this, KeMuSanKaoGui.class);
						startActivity(intent);
					}
					
				}
				);
				//科目san秘籍监听
				subjectThreeMiJi.setOnClickListener(new View.OnClickListener()
				{
		
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent();
						intent.setClass(KeMuerActivity.this, KeMuSanMiJi.class);
						startActivity(intent);
					}
					
				}
				);		
				Button button_shangchezhunbei=(Button)findViewById(R.id.button_shangchezhunbei);
				Button buttonshang_yejiandengguang=(Button)findViewById(R.id.buttonshang_yejiandengguang);	
				Button button_renxingdao=(Button)findViewById(R.id.button_renxingdao);
				Button button_qibu=(Button)findViewById(R.id.button_qibu);
				Button button_xuexiao=(Button)findViewById(R.id.button_xuexiao);
				Button button_zhixian=(Button)findViewById(R.id.button_zhixian);
				Button button_gonggongqiche=(Button)findViewById(R.id.button_gonggongqiche);
				Button button_jiajiansu=(Button)findViewById(R.id.button_jiajiansu);
				Button button_huiche=(Button)findViewById(R.id.button_huiche);
				Button button_biangengchedao=(Button)findViewById(R.id.button_biangengchedao);
				Button button_chaoche=(Button)findViewById(R.id.button_chaoche);
				Button button_kaobiantingche=(Button)findViewById(R.id.button_kaobiantingche);
				Button button_yejianxingshi=(Button)findViewById(R.id.button_yejianxingshi);
				Button buttonzhaiLuDiaoTou=(Button)findViewById(R.id.buttonzhaiLuDiaoTou);
				
				buttonzhaiLuDiaoTou.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"窄路掉头"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);   
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				
				buttonshang_yejiandengguang.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"模拟夜间场景灯光使用"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);   
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_shangchezhunbei.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"上车准备"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_renxingdao.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"通过人行横道"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_qibu.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"起步"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_xuexiao.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"通过学校区域"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_zhixian.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"直线行驶"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_gonggongqiche.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"通过公共汽车站"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_jiajiansu.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"加减档操作"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_huiche.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"会车"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_biangengchedao.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"变更车道"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_chaoche.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"超车"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_kaobiantingche.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"靠边停车"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				button_yejianxingshi.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new  AlertDialog.Builder(KeMuerActivity.this)   
						.setTitle("小驾提醒您：" )  
						.setMessage("此过程需要联网，建议您在wifi下观看" )  
						
						.setPositiveButton("是" ,new OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent= new Intent();        
							    intent.setAction("android.intent.action.VIEW");    
							    SocketClient.ConnectSevert(Constant.GET_ADDRESS_BY_NAMEANDCARID+"夜间行驶"+Constant.GET_ADDRESS_BY_NAMEANDCARID+"01");
								String isok=SocketClient.readinfo;
							    Uri content_url = Uri.parse(isok);  
							    intent.setData(content_url);  
							    startActivity(intent);
							}
							
						})  
						.setNegativeButton("否" , null)  
						.show();  
					}
				});
				
				goToSubjectTwo();
				
			}	
		});
				
	}
}
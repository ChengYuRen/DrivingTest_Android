package com.bn.Begin;
import com.bn.driversystem_android.MainActivity;
import com.bn.driversystem_android.R;
import com.bn.fyq.Constant.ResetConstant;
import com.bn.util.DBUtil;
import com.bn.util.DataUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class ChooseCar extends Activity{
	
	private String ChooseCar_Get;
	public static int Car_Down=0;  //记录小车按钮是否被按下  未按下为0  按下为1
	public static int Huo_Down=0;  //货车
	public static int Ke_Down=0;  //客车
	public static int Mo_Down=0;  //摩托
	public static int KeYun_Down=0;  //客车运输
	public static int HuoYun_Down=0;  //货车运输
	public static int Wei_Down=0;  //危险品
	public static int Jiao_Down=0;  //教练证
	
	public static int ChooseCar=0;//是否从滑动界面进入的标志位
	
	public static String GetCarTypeByCunCu;//得到存储的数据
	
	public static boolean CunCuOkOrNot=false;//是否存储标志位
	
	public static int chexing;

	//int diyicijiazai_car=0;//加载选项  看看是不是第一次加载  0是没有加载
	String gettestmsg_car;
	String testmsg_car;
	public static boolean NetIsOk_Car;//判断网络是否连接标志位
	
	Handler handler_car=new Handler()
	{
		Bundle b;
		public void handleMessage(Message msg) 
   		{
   			super.handleMessage(msg);
   			switch(msg.what)
   			{
				 case ResetConstant.TESTCONNECT://测试连接成功0
					 //获取消息中的数据
					  b=msg.getData();
					 //获取内容字符串
					  gettestmsg_car=b.getString("msg");
					  if(gettestmsg_car.equals("success"))
					   {
						  NetIsOk_Car=true;
					   }
			     break;
				 case ResetConstant.TESTCONNECTERROR://测试连接失败1
					 	 NetIsOk_Car=false;
						 Toast.makeText(ChooseCar.this,"连接服务器失败,请打开WiFi连接服务器.",
								 Toast.LENGTH_SHORT).show();
				 break;
   			}
   		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choosecar);
		testConnectThread();//实现标识位转换	
		
		
		//返回按钮
		 ImageView subject_two_kaogui_title;
		subject_two_kaogui_title=(ImageView)findViewById(R.id.button_subject_two_kaogui_title);
		subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//相当于手机的返回按钮
				ChooseCar.this.finish();
			}
			
		}
		);
		//定义8个汽车的图片按钮
		final Button button1;
		final Button button2;
		final Button button3;
		final Button button4;
		final Button button5;
		final Button button6;
		final Button button7;
		final Button button8;  
		//Button image;
		//初始化Button
		button1 = (Button)findViewById(R.id.ChooseCar_Button01);  
	    button2 = (Button)findViewById(R.id.ChooseCar_Button02);  
	    button3 = (Button)findViewById(R.id.ChooseCar_Button03);  
	    button4 = (Button)findViewById(R.id.ChooseCar_Button04);  
	    button5 = (Button)findViewById(R.id.ChooseCar_Button05);  
	    button6 = (Button)findViewById(R.id.ChooseCar_Button06);  
	    button7 = (Button)findViewById(R.id.ChooseCar_Button07);  
	    button8 = (Button)findViewById(R.id.ChooseCar_Button08);
	    
		 //以下分别为每个按钮设置事件监听 setOnTouchListener  
	    //小车
	    button1.setOnTouchListener(new View.OnTouchListener()
	    {
	    	
			public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				//重新设置按下时的背景图片
				((Button)v).setBackgroundResource((R.drawable.xiaoche_p));
				Car_Down=1;
				//小车按下后 其他车辆还原
				button2.setBackgroundResource((R.drawable.huoche_n));
				button3.setBackgroundResource((R.drawable.keche_n));
				button4.setBackgroundResource((R.drawable.motuo));
				button5.setBackgroundResource((R.drawable.keyun));
				button6.setBackgroundResource((R.drawable.huoyun));
				button7.setBackgroundResource((R.drawable.weixian));
				button8.setBackgroundResource((R.drawable.jiaolian));
				//选中后需要从数据库获取数据
			}
			else if(event.getAction() == MotionEvent.ACTION_UP)
			{
				//手势抬起  将数据统计表清空
				DBUtil.deleteAllSJTJ();
				//再修改为抬起时的正常图片
				((Button)v).setBackgroundResource((R.drawable.xiaoche_p));	
				testConnectThread();//实现标识位转换	
				if(NetIsOk_Car==false)
				{
					Toast.makeText(ChooseCar.this,"暂时未能连接到服务器，请您检查您的网络连接",Toast.LENGTH_SHORT).show();
				}
				else
				{
					//设置题库 等待3s后
					final CustomProgressDialog dialog =new CustomProgressDialog(ChooseCar.this, "正在获取试题信息...",R.anim.frame);
					
					//计时后结束 并跳转
					Handler handler = new Handler(); 
			        handler.postDelayed(new Runnable() { 

			            public void run() { 
			            	//获取小车科一题目数目
							String userinfo_one=null;
							userinfo_one=DBUtil.getquanbucount("科目一", "01");
							//获取小车 科目四 数目
							String userinfo_four=null;
							userinfo_four=DBUtil.getquanbucount("科目四", "01");
							
							TextView shiti_shumu=(TextView)findViewById(R.id.textView_Shitishu);
							shiti_shumu.setText("科目一共"+userinfo_one+"题，科目四共"+userinfo_four+"题");
							
			            	dialog.cancel();//过三秒让框消失
							
							//ChooseCar.this.finish();
							//this.
			            }
			        }, 2000);
					dialog.show();
				}
			
				//dialog.cancel();
			}		
			return false;
			}	
		});

	    //货车
	    button2.setOnTouchListener(new View.OnTouchListener()
	    {
	    	
			public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				//重新设置按下时的背景图片
				((Button)v).setBackgroundResource((R.drawable.huoche_p));
				//当此车按下时 其他还原
				button1.setBackgroundResource((R.drawable.xiaoche_n));
				//button2.setBackgroundResource((R.drawable.huoche));
				button3.setBackgroundResource((R.drawable.keche_n));
				button4.setBackgroundResource((R.drawable.motuo));
				button5.setBackgroundResource((R.drawable.keyun));
				button6.setBackgroundResource((R.drawable.huoyun));
				button7.setBackgroundResource((R.drawable.weixian));
				button8.setBackgroundResource((R.drawable.jiaolian));
				Huo_Down=1;
				//选中后需要从数据库获取数据
			}
			else if(event.getAction() == MotionEvent.ACTION_UP)
			{
				//手势抬起  将数据统计表清空
				DBUtil.deleteAllSJTJ();
				//再修改为抬起时的正常图片
				((Button)v).setBackgroundResource((R.drawable.huoche_p));	
				testConnectThread();//实现标识位转换
				if(NetIsOk_Car==false)
				{
					Toast.makeText(ChooseCar.this,"暂时未能连接到服务器，请您检查您的网络连接",Toast.LENGTH_SHORT).show();
				}
				else
				{
					//设置题库 等待3s后跳转
					final CustomProgressDialog dialog =new CustomProgressDialog(ChooseCar.this, "客官，请稍等...",R.anim.frame);
					//计时后结束 并跳转
					Handler handler = new Handler(); 
			        handler.postDelayed(new Runnable() { 
	
			            public void run() { 
	
			            	//获取小车科一题目数目
							String userinfo_one=null;
							userinfo_one=DBUtil.getquanbucount("科目一", "02");
							//获取小车 科目四 数目
							String userinfo_four=null;
							userinfo_four=DBUtil.getquanbucount("科目四", "02");
							
							TextView shiti_shumu=(TextView)findViewById(R.id.textView_Shitishu);
							shiti_shumu.setText("科目一共"+userinfo_one+"题，科目四共"+userinfo_four+"题");
							dialog.cancel();//过三秒让框消失
			            }
			        }, 2000);
					dialog.show();
				}
				
				
			}		
			//((button)v).setBackgroundResource((R.drawable.huoche));
			return false;
			}	
		});
	    //客车
	    button3.setOnTouchListener(new View.OnTouchListener()
	    {
	    	
			public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				//重新设置按下时的背景图片
				((Button)v).setBackgroundResource((R.drawable.keche_p));
				button1.setBackgroundResource((R.drawable.xiaoche_n));
				button2.setBackgroundResource((R.drawable.huoche_n));
				//button3.setBackgroundResource((R.drawable.keche));
				button4.setBackgroundResource((R.drawable.motuo));
				button5.setBackgroundResource((R.drawable.keyun));
				button6.setBackgroundResource((R.drawable.huoyun));
				button7.setBackgroundResource((R.drawable.weixian));
				button8.setBackgroundResource((R.drawable.jiaolian));
				Ke_Down=1;
				//选中后需要从数据库获取数据
			}
			else if(event.getAction() == MotionEvent.ACTION_UP)
			{
				//手势抬起  将数据统计表清空
				DBUtil.deleteAllSJTJ();
				//再修改为抬起时的正常图片
				((Button)v).setBackgroundResource((R.drawable.keche_p));
				testConnectThread();//实现标识位转换
				if(NetIsOk_Car==false)
				{
					Toast.makeText(ChooseCar.this,"暂时未能连接到服务器，请您检查您的网络连接",Toast.LENGTH_SHORT).show();
				}
				else
				{
					//设置题库 等待3s后跳转
					final CustomProgressDialog dialog =new CustomProgressDialog(ChooseCar.this, "客官，请稍等...",R.anim.frame);
					//计时后结束 并跳转
					Handler handler = new Handler(); 
			        handler.postDelayed(new Runnable() { 
	
			            public void run() { 
	
			            	
			            	//获取小车科一题目数目
							String userinfo_one=null;
							userinfo_one=DBUtil.getquanbucount("科目一", "03");
							//获取小车 科目四 数目
							String userinfo_four=null;
							userinfo_four=DBUtil.getquanbucount("科目四", "03");
							
							TextView shiti_shumu=(TextView)findViewById(R.id.textView_Shitishu);
							shiti_shumu.setText("科目一共"+userinfo_one+"题，科目四共"+userinfo_four+"题");
							dialog.cancel();//过三秒让框消失
			            }
			        }, 2000);
					dialog.show();
				}
				
			}
			return false;
			}	
		});
	    //摩托车
	    button4.setOnTouchListener(new View.OnTouchListener()
	    {
	    	
			public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				//重新设置按下时的背景图片
				((Button)v).setBackgroundResource((R.drawable.motuoanxia));
				button1.setBackgroundResource((R.drawable.xiaoche_n));
				button2.setBackgroundResource((R.drawable.huoche_n));
				button3.setBackgroundResource((R.drawable.keche_n));
				//button4.setBackgroundResource((R.drawable.muotuo));
				button5.setBackgroundResource((R.drawable.keyun));
				button6.setBackgroundResource((R.drawable.huoyun));
				button7.setBackgroundResource((R.drawable.weixian));
				button8.setBackgroundResource((R.drawable.jiaolian));
				Mo_Down=1;
				//选中后需要从数据库获取数据
			}
			else if(event.getAction() == MotionEvent.ACTION_UP)
			{
				//手势抬起  将数据统计表清空
				DBUtil.deleteAllSJTJ();
				//再修改为抬起时的正常图片
				((Button)v).setBackgroundResource((R.drawable.motuoanxia));	
				testConnectThread();//实现标识位转换
				if(NetIsOk_Car==false)
				{
					Toast.makeText(ChooseCar.this,"暂时未能连接到服务器，请您检查您的网络连接",Toast.LENGTH_SHORT).show();
				}
				else
				{
				
					chexing=1;
					//设置题库 等待3s后跳转
					final CustomProgressDialog dialog =new CustomProgressDialog(ChooseCar.this, "客官，请稍等...",R.anim.frame);
					//计时后结束 并跳转
					Handler handler = new Handler(); 
			        handler.postDelayed(new Runnable() { 
	
			            public void run() { 
	
			            	
			            	//获取小车科一题目数目
							String userinfo_one=null;
							userinfo_one=DBUtil.getquanbucount("科目一", "04");
							//获取小车 科目四 数目
							String userinfo_four=null;
							userinfo_four=DBUtil.getquanbucount("科目四", "04");
							
							TextView shiti_shumu=(TextView)findViewById(R.id.textView_Shitishu);
							shiti_shumu.setText("科目一共"+userinfo_one+"题，科目四共"+userinfo_four+"题");
							dialog.cancel();//过三秒让框消失
			            }
			        }, 2000);
					dialog.show();
				}
				
			}			
			return false;
			}	
		});
	    //客运
	    button5.setOnTouchListener(new View.OnTouchListener()
	    {
	    	
			public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				//重新设置按下时的背景图片
				((Button)v).setBackgroundResource((R.drawable.keyunanxia));
				//当此车按下时 其他还原
				button1.setBackgroundResource((R.drawable.xiaoche_n));
				button2.setBackgroundResource((R.drawable.huoche_n));
				button3.setBackgroundResource((R.drawable.keche_n));
				button4.setBackgroundResource((R.drawable.motuo));
				//button5.setBackgroundResource((R.drawable.keyun));
				button6.setBackgroundResource((R.drawable.huoyun));
				button7.setBackgroundResource((R.drawable.weixian));
				button8.setBackgroundResource((R.drawable.jiaolian));
				KeYun_Down=1;
				//选中后需要从数据库获取数据
			}
			else if(event.getAction() == MotionEvent.ACTION_UP)
			{
				//手势抬起  将数据统计表清空
				DBUtil.deleteAllSJTJ();
				//再修改为抬起时的正常图片
				((Button)v).setBackgroundResource((R.drawable.keyunanxia));	
				testConnectThread();//实现标识位转换
				if(NetIsOk_Car==false)
				{
					Toast.makeText(ChooseCar.this,"暂时未能连接到服务器，请您检查您的网络连接",Toast.LENGTH_SHORT).show();
				}
				else
				{
					chexing=2;
					//设置题库 等待3s后跳转
					final CustomProgressDialog dialog =new CustomProgressDialog(ChooseCar.this, "客官，请稍等...",R.anim.frame);
					//计时后结束 并跳转
					Handler handler = new Handler(); 
			        handler.postDelayed(new Runnable() { 
	
			            public void run() { 
	
			            	
			            	//获取小车科一题目数目
							String userinfo_one=null;
							userinfo_one=DBUtil.getquanbucount("科目一", "05");
							//获取小车 科目四 数目
							String userinfo_four=null;
							userinfo_four=DBUtil.getquanbucount("科目四", "05");
							
							TextView shiti_shumu=(TextView)findViewById(R.id.textView_Shitishu);
							shiti_shumu.setText("科目一共"+userinfo_one+"题，科目四共"+userinfo_four+"题");
							dialog.cancel();//过三秒让框消失
			            }
			        }, 2000);
					dialog.show();
				}
				
			}			
			return false;
			}	
		});
	    //货运
	    button6.setOnTouchListener(new View.OnTouchListener()
	    {
	    	
			public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				//重新设置按下时的背景图片
				((Button)v).setBackgroundResource((R.drawable.huoyunanxia));
				//当此车按下时 其他还原
				button1.setBackgroundResource((R.drawable.xiaoche_n));
				button2.setBackgroundResource((R.drawable.huoche_n));
				button3.setBackgroundResource((R.drawable.keche_n));
				button4.setBackgroundResource((R.drawable.motuo));
				button5.setBackgroundResource((R.drawable.keyun));
				//button6.setBackgroundResource((R.drawable.huoyun));
				button7.setBackgroundResource((R.drawable.weixian));
				button8.setBackgroundResource((R.drawable.jiaolian));
				HuoYun_Down=1;
				//选中后需要从数据库获取数据
			}
			else if(event.getAction() == MotionEvent.ACTION_UP)
			{
				//手势抬起  将数据统计表清空
				DBUtil.deleteAllSJTJ();
				//再修改为抬起时的正常图片
				((Button)v).setBackgroundResource((R.drawable.huoyunanxia));
				testConnectThread();//实现标识位转换
				if(NetIsOk_Car==false)
				{
					Toast.makeText(ChooseCar.this,"暂时未能连接到服务器，请您检查您的网络连接",Toast.LENGTH_SHORT).show();
				}
				else
				{
					chexing=2;
					//设置题库 等待3s后跳转
					final CustomProgressDialog dialog =new CustomProgressDialog(ChooseCar.this, "客官，请稍等...",R.anim.frame);
					//计时后结束 并跳转
					Handler handler = new Handler(); 
			        handler.postDelayed(new Runnable() { 
	
			            public void run() { 
	
			            	
			            	//获取小车科一题目数目
							String userinfo_one=null;
							userinfo_one=DBUtil.getquanbucount("科目一", "06");
							//获取小车 科目四 数目
							String userinfo_four=null;
							userinfo_four=DBUtil.getquanbucount("科目四", "06");
							
							TextView shiti_shumu=(TextView)findViewById(R.id.textView_Shitishu);
							shiti_shumu.setText("科目一共"+userinfo_one+"题，科目四共"+userinfo_four+"题");
							dialog.cancel();//过三秒让框消失
			            }
			        }, 2000);
					dialog.show();
				}
					
			}			
			return false;
			}	
		});
	    //危险
	    button7.setOnTouchListener(new View.OnTouchListener()
	    {
	    	
			public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				//重新设置按下时的背景图片
				((Button)v).setBackgroundResource((R.drawable.weixiananxia));
				//当此车按下时 其他还原
				button1.setBackgroundResource((R.drawable.xiaoche_n));
				button2.setBackgroundResource((R.drawable.huoche_n));
				button3.setBackgroundResource((R.drawable.keche_n));
				button4.setBackgroundResource((R.drawable.motuo));
				button5.setBackgroundResource((R.drawable.keyun));
				button6.setBackgroundResource((R.drawable.huoyun));
				//button7.setBackgroundResource((R.drawable.weixian));
				button8.setBackgroundResource((R.drawable.jiaolian));
				Wei_Down=1;
				//选中后需要从数据库获取数据
			}
			else if(event.getAction() == MotionEvent.ACTION_UP)
			{
				//手势抬起  将数据统计表清空
				DBUtil.deleteAllSJTJ();
				//再修改为抬起时的正常图片
				((Button)v).setBackgroundResource((R.drawable.weixiananxia));
				testConnectThread();//实现标识位转换
				if(NetIsOk_Car==false)
				{
					Toast.makeText(ChooseCar.this,"暂时未能连接到服务器，请您检查您的网络连接",Toast.LENGTH_SHORT).show();
				}
				else
				{
					chexing=2;
					//设置题库 等待3s后跳转
					final CustomProgressDialog dialog =new CustomProgressDialog(ChooseCar.this, "客官，请稍等...",R.anim.frame);
					//计时后结束 并跳转
					Handler handler = new Handler(); 
			        handler.postDelayed(new Runnable() { 
	
			            public void run() { 
	
			            	
			            	//获取小车科一题目数目
							String userinfo_one=null;
							userinfo_one=DBUtil.getquanbucount("科目一", "07");
							//获取小车 科目四 数目
							String userinfo_four=null;
							userinfo_four=DBUtil.getquanbucount("科目四", "07");
							
							TextView shiti_shumu=(TextView)findViewById(R.id.textView_Shitishu);
							shiti_shumu.setText("科目一共"+userinfo_one+"题，科目四共"+userinfo_four+"题");
							dialog.cancel();//过三秒让框消失
			            }
			        }, 2000);
					dialog.show();
				}
				
			}			
			return false;
			}	
		});
	    //教练
	    button8.setOnTouchListener(new View.OnTouchListener()
	    {
	    	
			public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				//重新设置按下时的背景图片
				((Button)v).setBackgroundResource((R.drawable.jiaoliananxia));
				//当此车按下时 其他还原
				button1.setBackgroundResource((R.drawable.xiaoche_n));
				button2.setBackgroundResource((R.drawable.huoche_n));
				button3.setBackgroundResource((R.drawable.keche_n));
				button4.setBackgroundResource((R.drawable.motuo));
				button5.setBackgroundResource((R.drawable.keyun));
				button6.setBackgroundResource((R.drawable.huoyun));
				button7.setBackgroundResource((R.drawable.weixian));
				//button8.setBackgroundResource((R.drawable.jiaolian));
				Jiao_Down=1;
				//选中后需要从数据库获取数据
			}
			else if(event.getAction() == MotionEvent.ACTION_UP)
			{
				//手势抬起  将数据统计表清空
				DBUtil.deleteAllSJTJ();
				//再修改为抬起时的正常图片
				((Button)v).setBackgroundResource((R.drawable.jiaoliananxia));
				testConnectThread();//实现标识位转换
				if(NetIsOk_Car==false)
				{
					Toast.makeText(ChooseCar.this,"暂时未能连接到服务器，请您检查您的网络连接",Toast.LENGTH_SHORT).show();
				}
				else
				{
					chexing=2;
					//设置题库 等待3s后跳转
					final CustomProgressDialog dialog =new CustomProgressDialog(ChooseCar.this, "客官，请稍等...",R.anim.frame);
					//计时后结束 并跳转
					Handler handler = new Handler(); 
			        handler.postDelayed(new Runnable() { 

			            public void run() { 

			            	
			            	//获取小车科一题目数目
							String userinfo_one=null;
							userinfo_one=DBUtil.getquanbucount("科目一", "08");
							//获取小车 科目四 数目
							String userinfo_four=null;
							userinfo_four=DBUtil.getquanbucount("科目四", "08");
							
							TextView shiti_shumu=(TextView)findViewById(R.id.textView_Shitishu);
							shiti_shumu.setText("科目一共"+userinfo_one+"题，科目四共"+userinfo_four+"题");
							dialog.cancel();//过三秒让框消失
			            }
			        }, 2000);
					dialog.show();
				}
					
				}			
			return false;
			}	
		});
	    //==========Button监听END================
	    
	    
	   
	    
	  //=========Button完成按钮=====Begin===
	    Button buttonOk;
	    buttonOk=(Button)findViewById(R.id.button01);
	    buttonOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				if(Car_Down==1||Huo_Down==1||Ke_Down==1||Mo_Down==1||
						KeYun_Down==1||HuoYun_Down==1||Wei_Down==1||Jiao_Down==1)
				{
					ChooseCar=1;
					
					//设置题库 等待3s后跳转
					CustomProgressDialog dialog =new CustomProgressDialog(ChooseCar.this, "正在设置题库中...",R.anim.frame);
					//计时后结束 并跳转
					Handler handler = new Handler(); 
			        handler.postDelayed(new Runnable() { 

			            public void run() { 

			            	CustomProgressDialog.mAnimation.stop();
			            	
			            	Intent intent=new Intent();
							intent.setClass(ChooseCar.this, MainActivity.class);
							if(Car_Down==1)
							{
								ChooseCar_Get="小车";
							}
							else if(Huo_Down==1)
							{
								ChooseCar_Get="货车";
							}
							else if(Ke_Down==1)
							{
								ChooseCar_Get="客车";
							}
							else if(Mo_Down==1)
							{
								ChooseCar_Get="摩托车";
							}
							else if(KeYun_Down==1)
							{
								ChooseCar_Get="客运";
							}
							else if(HuoYun_Down==1)
							{
								ChooseCar_Get="货运";
							}
							else if(Wei_Down==1)
							{
								ChooseCar_Get="危险品";
							}
							else 
							{
								ChooseCar_Get="教练";
							}
							
							DBUtil.deleteCar();//删除
							DBUtil.insertCar(ChooseCar_Get);//插入 
							//有储存 把储存的标志位设为 真
							CunCuOkOrNot=true;
							//loadData(ChooseCar.this);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置 
							startActivity(intent);  
							ChooseCar.this.finish();
			            } 

			        }, 2000);
					dialog.show();
					
					
					//ChooseCar.this.finish();
				}
				else
				{
					Toast toast=Toast.makeText(getApplicationContext(), "请选择所报车型", Toast.LENGTH_SHORT);
					toast.show(); 
					
				}
			}
		});
	}
	
	//发送消息的进程
	 public void testConnectThread()//每次运行都调用此方法 实现判断是否联网
	 {
	   	  //开启线程进行判断
		  new Thread()
		  {
			  @Override
			  public void run()
			   {
				 try 
				 {
					 DataUtil.testConnect(handler_car);				 
				 }
				 catch (Exception e) 
				  {//如果捕获到异常就发送handler
					 Message msg=new Message();
			    	 msg.what=0;
			         Bundle b=new Bundle();
			         b.putString("msg","网络未连接，请检查您的网络后重新登陆");
			         msg.setData(b);
			         handler_car.sendMessage(msg);
			    	 return;
				}
			  }
		  }.start();
	   }
	
//	/*
//	 * 此处为设置完全退出程序后 再次进入 保留自己所选择的车型
//	 */
//	//数据读取方法
//	private void loadData(Context context) {
//	    SharedPreferences sp = context.getSharedPreferences("config", MODE_PRIVATE);
//	    //Toast.makeText(this, sp.getString("content", "").toString(), 0).show();
//	    GetCarTypeByCunCu=sp.getString("content", "").toString();
//	  }
//	//对数据进行存储
//	private void saveData(Context context,String string){
//	    SharedPreferences sp = context.getSharedPreferences("config", MODE_PRIVATE);
//	    Editor editor = sp.edit();
//	    editor.putString("content", string);
//	    editor.commit();
//	  }
	
}

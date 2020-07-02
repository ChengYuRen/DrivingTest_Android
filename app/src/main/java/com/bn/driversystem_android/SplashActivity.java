package com.bn.driversystem_android;

import java.io.File;

import com.bn.fyq.Constant.ResetConstant;
import com.bn.util.DataUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


@SuppressLint("HandlerLeak")
public class SplashActivity extends Activity {
	
		//测试连接专用
		//--------------------------------
		int diyicijiazai=0;//加载选项  看看是不是第一次加载  0是没有加载
		String gettestmsg;
		String testmsg;
		public static boolean NetIsOk=true;//判断网络是否连接标志位
		
		Handler handler=new Handler()
		{
			Bundle b;
			@SuppressLint("HandlerLeak")
			public void handleMessage(Message msg) 
	   		{
	   			super.handleMessage(msg);
	   			switch(msg.what)
	   			{
					 case ResetConstant.TESTCONNECT://测试连接成功0
						 //获取消息中的数据
						  b=msg.getData();
						 //获取内容字符串
						  gettestmsg=b.getString("msg");
						  if(gettestmsg.equals("success"))
						   {
							if(diyicijiazai==1)//如果是  第一次就直接加载
							{
								//加载数据库
						        try
						        {
						        	 DataUtil.jiazaiquestion();
						        	 DataUtil.jiazaianswer();
						        	 DataUtil.jiazaitupian();
						        	 DataUtil.jiazaiqzhengli();
						        }
						        catch(Exception e)
						        {
						        	e.printStackTrace();
						        }
							}
							else 
							{
								String paths = Environment.getExternalStorageDirectory().toString()+"/Drive";
								 File destDir = new File(paths);
								 delete(destDir);
								 try
							        {
							        	 DataUtil.jiazaiquestion();
							        	 DataUtil.jiazaianswer();
							        	 DataUtil.jiazaitupian();
							        	 DataUtil.jiazaiqzhengli();
							        }
							        catch(Exception e)
							        {
							        	e.printStackTrace();
							        }
							}
							  
						   }
				     break;
					 case ResetConstant.TESTCONNECTERROR://测试连接失败1
						 	 NetIsOk=false;
							 Toast.makeText(SplashActivity.this,"连接服务器失败,请打开WiFi连接服务器.",Toast.LENGTH_SHORT).show();
					 break;
	   			}
	   		}
		};
		//--------------------------------------------------------
    boolean isFirstIn = false;

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    // 延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 3000;

    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    
    public static int HOME_GUIDE=0;//0——Home 1——GUIDE
    //public static int GUIDE=0;

    /**
     * Handler:跳转到不同界面  
     * 
     * 通过此处来实现是否进入选车 选驾校界面
     * 
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case GO_HOME:
                goHome();
                HOME_GUIDE=0;
                System.out.println("home+++++++++++");
                break;
            case GO_GUIDE:
                goGuide();
                HOME_GUIDE=1;
                System.out.println("guide+++++++++++");
                break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        init();
    }

    private void init() {
        // 读取SharedPreferences中需要的数据
        // 使用SharedPreferences来记录程序的使用次数
        SharedPreferences preferences = getSharedPreferences(
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);

        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = preferences.getBoolean("isFirstIn", true);

        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (!isFirstIn) {
            // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }

    }

    private void goHome() {
    	testConnectThread();//实现标志位转化
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }

    private void goGuide() {
    	//走   引导页   然后判断是否连接成功
    	diyicijiazai=1;//走引导页
    	testConnectThread();//实现标志位转化
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }
    
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
					 DataUtil.testConnect(handler);				 
				 }
				 catch (Exception e) 
				  {//如果捕获到异常就发送handler
					 Message msg=new Message();
			    	 msg.what=0;
			         Bundle b=new Bundle();
			         b.putString("msg","网络未连接，请检查您的网络后重新登陆");
			         msg.setData(b);
			    	 handler.sendMessage(msg);
			    	 return;
				}
			  }
		  }.start();
	   }
    public static void delete(File file) {  
    	if (file.isFile()) 
    	{  
    		file.delete();  
    		return;  
    	 }  
    	 if(file.isDirectory()){  
    	File[] childFiles = file.listFiles();  
    	  if (childFiles == null || childFiles.length == 0) {  
    	   return;  
    	  }  
    	       for (int i = 0; i < childFiles.length; i++) {  
    	                delete(childFiles[i]);  
    	            }  
    	           file.delete();  
    	       } 
    	 file.delete(); 
    	   } 
}
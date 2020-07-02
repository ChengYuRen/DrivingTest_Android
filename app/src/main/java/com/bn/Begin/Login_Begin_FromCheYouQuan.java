package com.bn.Begin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bn.driversystem_android.R;

import com.bn.driversystem_android.cheyouquan.keyijiaoliuActivity;
import com.bn.fyq.Constant.Constant;
import com.bn.util.SocketClient;
public class Login_Begin_FromCheYouQuan extends Activity{

	public static String GET_ZHANGHAO;//得到账号
	   // public static int LOGIN_IT=0;//登录标志位  未登录为0 登录为1
	   //public static int LOGIN_FROMOTHERS=0;//判断是否从其他界面进入且登录成功 如果是则直接进入界面 否则执行登录界面
	    
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消顶部Title
	        setContentView(R.layout.login);
	        //返回按钮
	        Button subject_two_kaogui_title;
			subject_two_kaogui_title=(Button)findViewById(R.id.button_subject_two_kaogui_title);
			subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//相当于手机的返回按钮
					//当返回时 把其标志位变回原来的0 防止再从正规界面进入时还进入车友圈中
					//-----------------这里需要增加东西
					
					
					keyijiaoliuActivity.CheYouQuan_KeYi=0;
					
					
					Login_Begin_FromCheYouQuan.this.finish();
					
				}
				
			}
			);
			
			TextView text_zhuCe;
			
			text_zhuCe=(TextView)findViewById(R.id.register_link);
			text_zhuCe.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent();
					intent.setClass(Login_Begin_FromCheYouQuan.this, Login_ZhuCe.class);
					startActivity(intent);
				}
			});
			//密保
			TextView text_mibao;
			text_mibao=(TextView)findViewById(R.id.register_mibao);
			text_mibao.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent();
					intent.setClass(Login_Begin_FromCheYouQuan.this, Login_ZhaoHuiMiMa.class);
					startActivity(intent);
				}
			});
			//账号框
			final EditText zhangHao=(EditText)findViewById(R.id.username_edit);
			//密码框
			final EditText miMa=(EditText)findViewById(R.id.password_edit);
			
			//登录按钮
			Button login_Begin;
			login_Begin=(Button)findViewById(R.id.signin_button);
			login_Begin.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//需要判断网络是否OK
					
					
					String userId=zhangHao.getText().toString().trim();
					String userPwd=miMa.getText().toString().trim();
					String userinfo=null;
					
					GET_ZHANGHAO=userId;
					
					try
					{
						SocketClient.ConnectSevert(Constant.PHONE_LOGIN+userId+Constant.PHONE_LOGIN+userPwd);
						userinfo=SocketClient.readinfo;
					}
					catch(Exception e)
					{
						//System.out.println("adfadsfdasfasd");
						e.printStackTrace();
					}
					
					//调试 省去登录一步
					
					if(userinfo.equals("ok"))
					//if(true)
					{
						Toast toast=Toast.makeText(getApplicationContext(), "恭喜您，登录成功", Toast.LENGTH_SHORT);
						toast.show();  
						//把登录的标志位设为1
						Constant.LOGIN_OK=1;
						//若是从车辆交易等页面进入的则进入其页面
						if(keyijiaoliuActivity.CheYouQuan_KeYi==1)
						{
							Intent intent=new Intent();
							intent.setClass(Login_Begin_FromCheYouQuan.this, keyijiaoliuActivity.class);
							startActivity(intent);
							Login_Begin_FromCheYouQuan.this.finish();
						}
						
						
						/*
						 * 我们知道Android的窗口类提供了历史栈，我们可以通过stack的原理来巧妙的实现，这里我们在A窗口打开B窗口时在Intent中直接加入标 志     Intent.FLAG_ACTIVITY_CLEAR_TOP，这样开启B时将会清除该进程空间的所有Activity。

							在A窗口中使用下面的代码调用B窗口
							
							Intent intent = new Intent(); 
							intent.setClass(Android123.this, CWJ.class); 
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置 
							startActivity(intent);
						 */
						
//						//否则进入个人界面
//						else
//						{
//							
//							//进行跳转 从登录界面跳转到主界面
//							Intent intent=new Intent();
//							intent.setClass(Login_Begin_FromCheYouQuan.this, PerSon_Home.class);
//							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
//							startActivity(intent);
//						}
						
					}
					else if(userinfo.equals("jinzhi"))
					{
						Toast toast=Toast.makeText(getApplicationContext(), "您的账号已被禁止登陆", Toast.LENGTH_SHORT);
						toast.show(); 
					}
					else
					{
						Toast toast=Toast.makeText(getApplicationContext(), "请输入正确的账号/密码", Toast.LENGTH_SHORT);
						toast.show(); 
					}
				}
			});
			
			
	 }
		
		@Override
	    public boolean onKeyDown(int keyCode, KeyEvent event)
	    
	    {
		 	if(keyCode == KeyEvent.KEYCODE_BACK)
		 	{
		 		//同理
		 		keyijiaoliuActivity.CheYouQuan_KeYi=0;
		 		Login_Begin_FromCheYouQuan.this.finish();
		 	}
			return false;
		 
	    }
}

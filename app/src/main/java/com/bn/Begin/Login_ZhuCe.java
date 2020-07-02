package com.bn.Begin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bn.driversystem_android.R;
import com.bn.fyq.Constant.Constant;
import com.bn.util.SocketClient;
public class Login_ZhuCe extends Activity{

		String userMaxId;//接收最大ID用
		int zhanghaoOKOrNot=1;//0 不可用 1可用
		//private Button 
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消顶部Title
	        setContentView(R.layout.login_zhuce);
	        //返回按钮
	        Button subject_two_kaogui_title;
			subject_two_kaogui_title=(Button)findViewById(R.id.button_subject_two_kaogui_title);
			subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
			{
	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//相当于手机的返回按钮
					Login_ZhuCe.this.finish();
				}
				
			}
			);
			//注册账号框
			final EditText zhuCe_ZhangHao=(EditText)findViewById(R.id.username_edit_zhuce);

			//注册密码框
			final EditText zhuCe_MiMa=(EditText)findViewById(R.id.password_edit_zhuce);
			
			//注册 安全码 anquamma_edit_zhuce
			final EditText zhuCe_AnQuanMa=(EditText)findViewById(R.id.anquamma_edit_zhuce);
			
			//注册按钮
			Button zhuCeButton=(Button)findViewById(R.id.signin_button_zhuce);
			zhuCeButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//判断联网
					
					//得到所有   账号
					String userinfo=null;
		      		try
					{
		      			SocketClient.ConnectSevert(Constant.GET_USER_ZHANGHAO_FORPHONE);
						userinfo=SocketClient.readinfo;
						//System.out.println(userinfo+"sdsafsdaf");
		      			
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
		 
		      		 String[] sgo=userinfo.split("#");//删除最后的#号  将每条数据写于 数组中  接收为： s[0]=“10001η范雨晴η男”  s[1]同理
		      		 String[] sreply=null;
					 String[] getIt=new String[sgo.length];//用于接收最后的各个账号
		      		 //System.out.println(""+sgo[0]);
					 
					 for(int i=0;i<sgo.length;i++)
					 {
						 sreply=sgo[i].split("η");
						 //System.out.println("string="+sreply[0]);//srepley[0]是分割后的 u1 u2等
						 getIt[i]=sreply[0];
					 }
					 //逐个判断账号是否存在
					 for(int j=0;j<getIt.length;j++) 
					 {
						if(zhuCe_ZhangHao.getText().toString().trim().equals(getIt[j]))
						{
							zhanghaoOKOrNot=0;
						}
						
					}
					 	if(zhanghaoOKOrNot==0)
					 	{
					 		Toast toast=Toast.makeText(getApplicationContext(), "对不起，此账号已被其他用户使用", Toast.LENGTH_SHORT);
							toast.show(); 
							zhanghaoOKOrNot=1;//标志位设为原来的
							return;
					 	}
					 	else if(zhuCe_MiMa.getText().toString().trim().length()<6)
						{
							Toast toast=Toast.makeText(getApplicationContext(), "您的密码过于简单，请重新设置", Toast.LENGTH_SHORT);
							toast.show(); 
						}
						else
						{
							StringBuilder s=new StringBuilder();
							
							s.append(Constant.FORPHONE+getUerMaxId());//加ID
							s.append(Constant.FORPHONE+zhuCe_ZhangHao.getText().toString().trim());//加账号
							s.append(Constant.FORPHONE+"01");//默认是01 小车
							s.append(Constant.FORPHONE+"001");//默认是001 百纳驾校 此处应该是未报考---------------------------
							s.append(Constant.FORPHONE+zhuCe_MiMa.getText().toString().trim());//密码
							s.append(Constant.FORPHONE+zhuCe_ZhangHao.getText().toString().trim());//昵称默认账号名
							s.append(Constant.FORPHONE+"空");//头像默认空 --------------------
							s.append(Constant.FORPHONE+"1");//年龄默认 一岁
							s.append(Constant.FORPHONE+"男");//年龄默认男
							s.append(Constant.FORPHONE+"1234567");//电话默认 1234567 电话可以显示在个人资料 ----------
							s.append(Constant.FORPHONE+"唐山");//地址默认唐山  地址可显示在个人资料
							s.append(Constant.FORPHONE+"是"); //能否登录 默认能
							s.append(Constant.FORPHONE+zhuCe_AnQuanMa.getText().toString().trim());//添加安全码
							//System.out.println("fdfdfdafdsafd==="+s.toString());
							
							SocketClient.ConnectSevert(s.toString());
							String isok=SocketClient.readinfo;
							if(isok.equals("ok"))
							{
								System.out.println("OK");
								Toast toast=Toast.makeText(getApplicationContext(), "恭喜您注册成功", Toast.LENGTH_SHORT);
								toast.show(); 
								
								//进行跳转 到登录界面
								Intent intent=new Intent();
								intent.setClass(Login_ZhuCe.this, Login_Begin_PerSon.class);
								intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
								startActivity(intent);
							}
							else
							{
								Toast toast=Toast.makeText(getApplicationContext(), "对不起，您未能注册成功", Toast.LENGTH_SHORT);
								toast.show();
							}
					 }
					 
					
					
				}
			});
						
			
	 }
	//获取用户最大ID+1 的方法	
	public String getUerMaxId()
	{
		SocketClient.ConnectSevert(Constant.GET_MAXUSER_ID_FORPHOEN);
		userMaxId=SocketClient.readinfo;
		int userMax=Integer.parseInt(userMaxId)+1;
		if(userMax<10)
		{
			return "0000"+userMax;
		}
		else if(userMax>=10&&userMax<100)
		{
			return "000"+userMax;
		}
		else if(userMax>=100&&userMax<1000)
		{
			return "00"+userMax;
		}
		else if(userMax>=1000&&userMax<10000)
		{
			return "0"+userMax;
		}
		else
		{
			return ""+userMax;
		}
		//return null;
		
	}
}

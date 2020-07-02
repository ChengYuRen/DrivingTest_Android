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
public class Login_ZhaoHuiMiMa extends Activity{

	
		//private Button 
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消顶部Title
	        setContentView(R.layout.login_zhaohuimima);
	        //返回按钮
	        Button subject_two_kaogui_title;
			subject_two_kaogui_title=(Button)findViewById(R.id.button_subject_two_kaogui_title);
			subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
			{
	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//相当于手机的返回按钮
					finish();
				}
				
			}
			);
			final EditText zhaohui_zhanghao=(EditText)findViewById(R.id.username_mibao);
			final EditText zhaohui_anquanma=(EditText)findViewById(R.id.anquanma_mibao);
			Button zhaohui_button=(Button)findViewById(R.id.mibao_button);
			
			zhaohui_button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					SocketClient.ConnectSevert(Constant.GET_USER_PASSWORD_FORPHONE+zhaohui_zhanghao.getText().toString().trim()
							+Constant.GET_USER_PASSWORD_FORPHONE+zhaohui_anquanma.getText().toString().trim());
					String isok=SocketClient.readinfo;
					
					if(isok=="1")
					{
						Toast toast=Toast.makeText(getApplicationContext(), "您的账号/安全码有误", Toast.LENGTH_SHORT);
						toast.show();
						return;
					}
					
					Toast toast=Toast.makeText(getApplicationContext(), "您的密码是:"+isok, Toast.LENGTH_LONG);
					toast.show();
					//进行跳转 到登录界面
					Intent intent=new Intent();
					intent.setClass(Login_ZhaoHuiMiMa.this, Login_Begin_PerSon.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
					startActivity(intent);
					//System.out.println("isok="+isok);
				}
			});
			
			
			
			
			
	 }
}

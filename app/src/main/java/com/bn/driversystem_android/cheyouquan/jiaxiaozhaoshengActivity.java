package com.bn.driversystem_android.cheyouquan;

import com.bn.Begin.Login_Begin_FromCheYouQuan;
import com.bn.driversystem_android.R;
import com.bn.fyq.Constant.Constant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class jiaxiaozhaoshengActivity extends Activity{//我的错题
	
	public static int CheYouQuan_ZhaoSheng=0;//是否从...界面进入 其他界面同理
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				//如果登录标志位为0 则调到登录界面
				if(Constant.LOGIN_OK==0)
				{
					Toast toast=Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT);
					toast.show(); 
					CheYouQuan_ZhaoSheng=1;
					//跳转到登录界面
					Intent intent=new Intent();
					intent.setClass(jiaxiaozhaoshengActivity.this, Login_Begin_FromCheYouQuan.class);
					startActivity(intent);
					jiaxiaozhaoshengActivity.this.finish();
				}
				else
				{
					setContentView(R.layout.main_kemusi);
				}
	}
				
			 @Override
			    public boolean onKeyDown(int keyCode, KeyEvent event)
			    
			    {
				 	if(keyCode == KeyEvent.KEYCODE_BACK)
				 	{
				 		jiaxiaozhaoshengActivity.this.finish();
				 	}
					return false;
				 
			    }
}
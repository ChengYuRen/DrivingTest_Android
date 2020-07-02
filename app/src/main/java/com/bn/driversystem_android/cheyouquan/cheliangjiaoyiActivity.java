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

public class cheliangjiaoyiActivity extends Activity{//我的错题
	
	//public static int CHELIANGJIAOYI=0;
	
	public static int CheYouQuan_JiaoYi=0;//此为表示是否从车辆交易进入的登录界面
	public static int CheYouQuan_Out=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				//如果登录标志位为0 则调到登录界面
				if(Constant.LOGIN_OK==0)
				{
					Toast toast=Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT);
					toast.show(); 
					CheYouQuan_JiaoYi=1;
					//跳转到登录界面
					
					Intent intent=new Intent();
					intent.setClass(cheliangjiaoyiActivity.this, Login_Begin_FromCheYouQuan.class);
					startActivity(intent);
					//CheYouQuan_JiaoYi=1;
					cheliangjiaoyiActivity.this.finish();
				}
				else
				{
					//进入所需界面
					setContentView(R.layout.main_kemuer);
				}
				
	}
	 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event)
	    
	    {
		 	if(keyCode == KeyEvent.KEYCODE_BACK)
		 	{
		 		cheliangjiaoyiActivity.this.finish();
		 	}
			return false;
		 
	    }

}
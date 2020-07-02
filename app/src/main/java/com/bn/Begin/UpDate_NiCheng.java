package com.bn.Begin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.bn.driversystem_android.R;

public class UpDate_NiCheng extends Activity{

	public static String GET_NiCheng=null;//获取点击确定后的昵称
	EditText edittext;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消顶部Title
        setContentView(R.layout.update_nicheng);
        
      //返回按钮
  		Button subject_two_kaogui_title;
  		subject_two_kaogui_title=(Button)findViewById(R.id.button_subject_two_kaogui_title);
  		subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
  		{

  			@Override
  			public void onClick(View v) {
  				// TODO Auto-generated method stub
  				//相当于手机的返回按钮
  				UpDate_NiCheng.this.finish();
  			}
  			
  		}
  		);
  		//edittext_nicheng
  		edittext=(EditText)findViewById(R.id.edittext_nicheng);
  		edittext.setText(PerSon_Home.NiChengGet);
  		
  		Button nicheng_OK=(Button)findViewById(R.id.button_nicheng_ok);
  		nicheng_OK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GET_NiCheng=edittext.getText().toString();
				//System.out.println("getNiCheng"+getNiCheng);
				Intent intent=new Intent();
				intent.setClass(UpDate_NiCheng.this, PerSon_Home.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
				startActivity(intent);
				
			}
		});
	}
}

package com.bn.Begin;

import com.bn.driversystem_android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class GuanYu_WoMen extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消顶部Title
	        setContentView(R.layout.guanyu_women);

	      //返回按钮
			ImageView subject_two_kaogui_title;
			subject_two_kaogui_title=(ImageView)findViewById(R.id.button_subject_two_kaogui_title);
			subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//相当于手机的返回按钮
					GuanYu_WoMen.this.finish();
				}
				
			}
			);
	 }
	 
}

//

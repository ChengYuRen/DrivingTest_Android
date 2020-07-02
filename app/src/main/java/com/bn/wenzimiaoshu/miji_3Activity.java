package com.bn.wenzimiaoshu;

import com.bn.driversystem_android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class miji_3Activity extends Activity{
	ImageView fanhui_b3;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyimiji_3);
		
		fanhui_b3=(ImageView)findViewById(R.id.fanhui_b3);
		OnClickListener clickListener = new OnClickListener();
		fanhui_b3.setOnClickListener(clickListener);
		
	}
	private class OnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View arg0) {
			finish();
			
		}
		
	}
}
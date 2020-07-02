package com.bn.wenzimiaoshu;

import com.bn.driversystem_android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class miji_6Activity extends Activity{
	ImageView fanhui_b6;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyimiji_6);
		
		fanhui_b6=(ImageView)findViewById(R.id.fanhui_b6);
		OnClickListener clickListener = new OnClickListener();
		fanhui_b6.setOnClickListener(clickListener);
		
	}
	private class OnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View arg0) {
			finish();
			
		}
		
	}
}
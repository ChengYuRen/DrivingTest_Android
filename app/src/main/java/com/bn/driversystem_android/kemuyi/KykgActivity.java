package com.bn.driversystem_android.kemuyi;

import com.bn.driversystem_android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class KykgActivity extends Activity{//科一考规
	private ImageView fanhui;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.kemuyi_keyikaogui);
				
				fanhui=(ImageView)findViewById(R.id.fanhui);
				OnClickListener clickListener = new OnClickListener();
				fanhui.setOnClickListener(clickListener);
				
	}
	private class OnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			finish();
			
		}
		
	}
}
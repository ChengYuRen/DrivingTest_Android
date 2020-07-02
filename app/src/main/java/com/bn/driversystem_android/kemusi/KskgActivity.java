package com.bn.driversystem_android.kemusi;

import com.bn.driversystem_android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class KskgActivity extends Activity{//科四考规
	private ImageView fanhui_4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.kemusi_kesikaogui);
				
				fanhui_4=(ImageView)findViewById(R.id.fanhui_4);
				OnClickListener clickListener = new OnClickListener();
				fanhui_4.setOnClickListener(clickListener);
				
	}
	private class OnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			finish();
			
		}
	}
}
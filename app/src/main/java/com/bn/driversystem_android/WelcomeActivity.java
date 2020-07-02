package com.bn.driversystem_android;

import com.bn.driversystem_android.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class WelcomeActivity extends Activity{
	private ImageView welcomeImg = null;  
	ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.welcome);
				
				 welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);  
			     AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);  
			     anima.setDuration(3000);// 设置动画显示时间  
			     
			     welcomeImg.startAnimation(anima);  
			     anima.setAnimationListener(new AnimationImpl());  
				
	}
	  private class AnimationImpl implements AnimationListener {  
		  
	        @Override  
	        public void onAnimationStart(Animation animation) {  
	            welcomeImg.setBackgroundResource(R.drawable.welcome);  
	        }  
	  
	        @Override  
	        public void onAnimationEnd(Animation animation) {  
	            skip(); // 动画结束后跳转到别的页面  
	        }  
	  
	        @Override  
	        public void onAnimationRepeat(Animation animation) {  
	  
	        }  
	  
	    }  
	  
	    private void skip() {  
	        startActivity(new Intent(this, MainActivity.class));  
	        finish();  
	    }
}
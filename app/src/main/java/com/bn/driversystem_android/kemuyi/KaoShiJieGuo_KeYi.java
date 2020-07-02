package com.bn.driversystem_android.kemuyi;
import com.bn.driversystem_android.MainActivity;
import com.bn.driversystem_android.R;
import com.bn.util.DBUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class KaoShiJieGuo_KeYi extends Activity{

	String fenshu;
	int  shijian,shi,fen,miao;
	TextView textView_time,textView_subject;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kaoshijieshu_keyi);
		
		//返回按钮
		 ImageView subject_two_kaogui_title;
		subject_two_kaogui_title=(ImageView)findViewById(R.id.button_malu_fanhui);
		subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//相当于手机的返回按钮
				KaoShiJieGuo_KeYi.this.finish();
			}
			
		}
		);
		TextView textScore=(TextView)findViewById(R.id.textView_score);
		ImageView maLu=(ImageView)findViewById(R.id.ImageView_malu);
		textView_time=(TextView)findViewById(R.id.textView_time);
		textView_subject=(TextView)findViewById(R.id.textView_subject);
		if(kaoshiActivity.GET_SCORE.equals("a"))//科目一考试
		{
			fenshu=kaoshiBHYduo.GET_SCORE;
			shijian=kaoshiBHYduo.cankaoshijian;
			kaoshiBHYduo.cankaoshijian=0;
		}
		else//科目四考试
		{
			fenshu=kaoshiActivity.GET_SCORE;
			shijian=kaoshiActivity.cankaoshijian;
			kaoshiActivity.cankaoshijian=0;
		}
		textScore.setText(fenshu);
		shi=(int)(shijian/60/60);//描述  切换成小时
        fen=(int)(shijian/60%60);
        miao=(int)(shijian%60);
        textView_time.setText(shi+":"+fen+":"+miao); 
        String kemu11=MainActivity.DianJiKeMu;
        String car11=DBUtil.getCar();
        textView_subject.setText(car11+"--"+kemu11);
		if(Integer.parseInt(fenshu)>1)
		{
			maLu.setImageResource(R.drawable.bg_cheshen3);
		}
		
		
	}
	
	
}

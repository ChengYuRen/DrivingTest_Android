package com.bn.shuju_tongji;
import com.bn.driversystem_android.MainActivity;
import com.bn.driversystem_android.R;
import com.bn.util.DBUtil;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShuJu_TongJi_Main extends Activity {

	@SuppressWarnings("unused")
	private String[] colors = { "#000000", "#ff0000", "#ff6666", "#ff80FF", "#ffFF00", "#ffE685" };
	private float items[]=new float[3];
	// private float[] items = { (float) 20.0, (float) 20.0, (float) 10.0 };
	@SuppressWarnings("unused")
	private int total = 150;
	private int radius =200;
	private int strokeWidth = 0;
	private String strokeColor = "#000000";
	private float animSpeed = (float) 2;

	private PieChartView pieChart;

	private TextView textInfo,textview_qkTJ;
	private TextView textview_shujutongji;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shuju_tongji);
		
		
		//返回按钮
		 ImageView subject_two_kaogui_title;
		subject_two_kaogui_title=(ImageView)findViewById(R.id.button_subject_two_kaogui_title);
		subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//相当于手机的返回按钮
				ShuJu_TongJi_Main.this.finish();
			}
			
		}
		);
		textview_qkTJ=(TextView)findViewById(R.id.textview_qkTJ);
		textview_qkTJ.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ShuJu_TongJi_Main.this, "已经清空做题记录！！！", Toast.LENGTH_SHORT).show();
				DBUtil.deleteAllSJTJ();
			}
		});
		textview_shujutongji=(TextView)findViewById(R.id.shuju_text);
		String zhengque=DBUtil.getSJTJcount("yes");
		String cuowu=DBUtil.getSJTJcount("no");
		String quanbu=DBUtil.getquanbucount(MainActivity.DianJiKeMu, MainActivity.XuanZeCheXing);
		
		int zong=Integer.parseInt(cuowu)+Integer.parseInt(zhengque);
		//将字符串 等 转换为浮点
		items[0]=(float)(Float.parseFloat(quanbu))-(float)zong;//未做
		items[1]=(float)(Float.parseFloat(zhengque));//做对
		items[2]=(float)(Float.parseFloat(cuowu));//做错
		int weizuo=(int)items[0];
		textview_shujutongji.setText("共做了"+zong+"道题\n正确"+zhengque+"道(绿色),错误"+cuowu+"道(黄色)\n未做"+weizuo+"道题(点击饼图查看详情)");

		textInfo = (TextView) findViewById(R.id.text_item_info);
		pieChart = (PieChartView) findViewById(R.id.parbar_view);
		// pb.setShowItem(5, true);//设置显示的块
		// pb.setAnimEnabled(false);//是否开启动画
		pieChart.setItemsSizes(items);// 设置各个块的值
		// pieChart.setTotal(total);//设置整体的值, 默认为和
		// pieChart.setItemsColors(colors);//设置各个块的颜色
		pieChart.setRotateSpeed(animSpeed);// 设置旋转速度
		pieChart.setRaduis(radius);// 设置饼状图半径，不包含边缘的圆环
		pieChart.setStrokeWidth(strokeWidth);// 设置边缘的圆环粗度
		pieChart.setStrokeColor(strokeColor);// 设置边缘的圆环颜色
		 pieChart.setRotateWhere(PieChartView.TO_RIGHT);//设置选中的item停靠的位置，默认在右侧  （no不旋转）
		pieChart.setSeparateDistence(15);// 设置旋转的item分离的距离
		// 也可以不使用xml布局，更多细节请看DOC

		pieChart.setOnItemSelectedListener(new OnPieChartItemSelectedLinstener() {

			public void onPieChartItemSelected(PieChartView view, int position, String colorRgb, float size, float rate, boolean isFreePart, float rotateTime) {
				// TODO Auto-generated method stub
				Log.e("Main", "onClicked item : " + position);
				if (isFreePart) {
					//size指个数  rate指所占百分比
					textInfo.setText("试题个数: " + size+ "\r\n所占比例: " +rate*100+"%");
				} else {
					textInfo.setText("试题个数: " + size+ "\r\n所占比例: " + rate*100+"%");
				}
				textInfo.setVisibility(View.VISIBLE);
				Animation myAnimation_Alpha = new AlphaAnimation(0.1f, 1.0f);
				myAnimation_Alpha.setDuration((int) (3 * rotateTime));
				textInfo.startAnimation(myAnimation_Alpha);
			}

		});
	}
}

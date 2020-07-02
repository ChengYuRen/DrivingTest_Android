package com.bn.driversystem_android.kemuyi;
import com.bn.driversystem_android.MainActivity;
import com.bn.driversystem_android.R;
import com.bn.util.DBUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings("deprecation")
public class fenleiActivity extends Activity{//我的错题
	String zhangjiemingcheng[]=new String[3];
	private ListView listview_zhangjie;
	private LinearLayout linear_qingkongSC;
	private TextView textview_biaoti_dati;
	String DianJiKeMu;//判断是那个科目
	static int fenleiBZ;//0  判断题  1  单项  2  多项
	//对listview  的图片进行设置   现在写成一样的图片   一会记得改
	int zhangjieimage[]={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
	int fenleiimage[]={R.drawable.pan,R.drawable.dan,R.drawable.duo};
	//Typeface customFont2 = Typeface.createFromAsset(this.getAssets(), "fonts/newfont.ttf");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.zhangjie);
				
				//返回按钮
				 ImageView subject_two_kaogui_title;
				subject_two_kaogui_title=(ImageView)findViewById(R.id.back);
				subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
				{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//相当于手机的返回按钮
						fenleiActivity.this.finish();
					}
					
				}
				);
				listview_zhangjie=(ListView)findViewById(R.id.ListView_zhangjie);
				linear_qingkongSC=(LinearLayout)findViewById(R.id.Linear_qingkongSC);
				linear_qingkongSC.setVisibility(View.GONE);
				textview_biaoti_dati=(TextView)findViewById(R.id.textview_biaoti_dati);
				textview_biaoti_dati.setText("分类练习");
				DianJiKeMu=MainActivity.DianJiKeMu;
				//得到  分类练习的各个名称
				zhangjiemingcheng=DBUtil.getfenleiName();
				//初始化ListView
				BaseAdapter ba=new BaseAdapter()
		        {
					@Override
					public int getCount() {
						if(DianJiKeMu.equals("科目一"))
						return 2;//现在就俩题   先写俩
						else{
							return 3;
						}
						}

					@Override
					public Object getItem(int arg0) { return null; }

					@Override
					public long getItemId(int arg0) { return 0; }

					@Override
					public View getView(int arg0, View arg1, ViewGroup arg2) {
						/*
						 * 动态生成每个下拉项对应的View，每个下拉项View由LinearLayout
						 *中包含一个TextView构成
						*/
						
						//初始化LinearLayout
						LinearLayout ll=new LinearLayout(fenleiActivity.this);
						ll.setOrientation(LinearLayout.HORIZONTAL);		//设置朝向	
						ll.setPadding(5,5,5,5);//设置四周留白
						
						//初始化ImageView    //记得更改图片大小  或者上网查设置
						ImageView  ii=new ImageView(fenleiActivity.this);
						ii.setImageDrawable(getResources().getDrawable(fenleiimage[arg0]));//设置图片
						ii.setScaleType(ImageView.ScaleType.FIT_XY);
						ii.setLayoutParams(new Gallery.LayoutParams(100,98));
						ll.addView(ii);//添加到LinearLayout中
						
						//初始化TextView
						TextView tv=new TextView(fenleiActivity.this);
						tv.setText(zhangjiemingcheng[arg0]);//设置内容
						tv.setTextSize(24);//设置字体大小
						//tv.setTypeface(customFont2);
						tv.setTextColor(fenleiActivity.this.getResources().getColor(R.color.slateblue));//设置字体颜色
						tv.setPadding(5,5,5,5);//设置四周留白
					    tv.setGravity(Gravity.LEFT);
						ll.addView(tv);//添加到LinearLayout中				
						return ll;
					}        	
		        };
		        listview_zhangjie.setAdapter(ba);//为ListView设置内容适配器
		        listview_zhangjie.setOnItemClickListener(
		 	           new OnItemClickListener()
		 	           {
		 				@Override
		 				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		 						long arg3) 
		 				{
		 					//重写选项被单击事件的处理方法
		 					LinearLayout ll=(LinearLayout)arg1;//获取当前选中选项对应的LinearLayout
		 					TextView tvn=(TextView)ll.getChildAt(1);//获取其中的TextView 
		 					String zjName=tvn.getText().toString();//得到了你选择的选项中的内容
		 					if(zjName.equals("判断题"))
		 					{
		 						fenleiBZ=0;
		 						startActivity(new Intent().setClass(fenleiActivity.this, shunxuActivity.class));
		 					}
		 					else if(zjName.equals("单项选择题"))
		 					{
		 						fenleiBZ=1;
		 						startActivity(new Intent().setClass(fenleiActivity.this, shunxuActivity.class));
		 					}
		 					else if(zjName.equals("多项选择题"))//数据库还没有多项  现在先不写  一会重新写布局文件  以及   activity
		 					{
		 						startActivity(new Intent().setClass(fenleiActivity.this, duoxiangdatiActivity.class));
		 					}
		 				}
		 	           }
		 	        );
				
	}
}
package com.bn.driversystem_android.kemuyi;
import java.util.Random;
import com.bn.driversystem_android.MainActivity;
import com.bn.driversystem_android.R;
import com.bn.util.DBUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class shunxuActivity extends Activity{
	int xuanxiangtupian[]={R.drawable.axuanxiang,R.drawable.bxuanxiang,R.drawable.cxuanxiang,R.drawable.dxuanxiang};
	String shitixinxi[][]=new String[1][5];
	String shitixuanxiang[][]=new String[4][];
	String shunxushitiID[];//这里讲顺序练习的ID  放入数组中  将多项选择题排除出去
	String ceshishitiID[];//数据库  试题ID数组  章节练习ID
	String fenleishitiID[];//数据库ID   分类练习专用
	int shitidebianhao=1;//在界面中显示的试题的编号
	int shoucangmuyang=0;//0则是原样   1是点击后的样子   收藏   标志
	int xueximuyang=0;//0是原样   学习模式
	String getData[];//这个是得到答案内容   如 正确  错误  ...
	int lianximoshi;//0  为顺序练习  1位章节练习  2位 分类联系  3为随机练习  4为收藏  5位错题
	String DianJiKeMu;//这里分为科目一  与科目四
	String DAjiexi=null;
	//随机练习试题ID   改  现在先这样
	String sjlxSTid[];
	String sJkaishishitiid[];//存放一开始试题id 跟shunxuid一样 
	String [][]zuotixuangzejieguo;
	private TextView textview_timu;
	private TextView textview_daan;
	private TextView textview_jiexineirong;
	private TextView textview_jiexitimu;
	private TextView textview_a;
	private TextView textview_b;
	private TextView textview_c;
	private TextView textview_d;
	private TextView textview_shoucang;
	private TextView textview_xuexi;
	private TextView textview_biaoti_dati;
	private TextView textview_CTshanchu;
	private LinearLayout linear_jiexi,linear_daanxuanxiang,linear_timu,linear_a,linear_b,linear_c,linear_d,linear_shangyiti,linear_xiayiti,dati_shoucang,linear_xuexi,dati_down;
	private ImageView imageview_a,imageview_b,imageview_c,imageview_d,imageview_shoucang,imageview_xuexi,imageview_datiimage,imageview_querendati;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.datijiemian);
				//返回按钮
				 ImageView subject_two_kaogui_title;
				subject_two_kaogui_title=(ImageView)findViewById(R.id.back);
				subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
				{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//相当于手机的返回按钮
						shunxuActivity.this.finish();
					}
					
				}
				);
				String shujukupanduan=DBUtil.getQuestionCount();
				 if(shujukupanduan.equals("0"))
				 {
					 Toast.makeText(shunxuActivity.this, "试题库访问失败", Toast.LENGTH_SHORT).show();
					 return;
				 }

				   textview_jiexitimu= (TextView) findViewById(R.id.textview_jiexitimu); 
					textview_jiexitimu.setVisibility(View.GONE);//设置组件不可见
					linear_daanxuanxiang=(LinearLayout)findViewById(R.id.linear_daanxuanxiang);
					linear_daanxuanxiang.setVisibility(View.GONE);
					linear_jiexi=(LinearLayout)findViewById(R.id.linear_jiexi);
					linear_jiexi.setVisibility(View.GONE);
					textview_jiexineirong=(TextView)findViewById(R.id.textview_jiexineirong);
					linear_timu=(LinearLayout)findViewById(R.id.linear_timu);
					linear_shangyiti=(LinearLayout)findViewById(R.id.linear_shangyiti);
					linear_xiayiti=(LinearLayout)findViewById(R.id.linear_xiayiti);
					textview_a=(TextView)findViewById(R.id.TextView_a);
					textview_b=(TextView)findViewById(R.id.TextView_b);
					textview_c=(TextView)findViewById(R.id.TextView_c);
					textview_d=(TextView)findViewById(R.id.TextView_d);
					imageview_a=(ImageView)findViewById(R.id.ImageView_a);
					imageview_b=(ImageView)findViewById(R.id.ImageView_b);
					imageview_c=(ImageView)findViewById(R.id.ImageView_c);
					imageview_d=(ImageView)findViewById(R.id.ImageView_d);
					linear_a=(LinearLayout)findViewById(R.id.linear_a);
					linear_b=(LinearLayout)findViewById(R.id.linear_b);
					linear_c=(LinearLayout)findViewById(R.id.linear_c);
					linear_d=(LinearLayout)findViewById(R.id.linear_d);
					dati_shoucang=(LinearLayout)findViewById(R.id.dati_shoucang);
					imageview_shoucang=(ImageView)findViewById(R.id.imageview_shoucang);
					textview_shoucang=(TextView)findViewById(R.id.textview_shoucang);
					linear_xuexi=(LinearLayout)findViewById(R.id.linear_xuexi);
					imageview_xuexi=(ImageView)findViewById(R.id.imageview_xuexi);
					textview_xuexi=(TextView)findViewById(R.id.textview_xuexi);
					imageview_datiimage=(ImageView)findViewById(R.id.ImageView_datiimage);
					textview_biaoti_dati=(TextView)findViewById(R.id.textview_biaoti_dati);
					textview_CTshanchu=(TextView)findViewById(R.id.textview_CTshanchu);
					textview_CTshanchu.setVisibility(View.GONE);
					dati_down=(LinearLayout)findViewById(R.id.dati_down);
					imageview_querendati=(ImageView)findViewById(R.id.ImageView_querendati);
					imageview_querendati.setVisibility(View.GONE);
					//dati_down.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
					//对数组按照最大大小来定义
					zuotixuangzejieguo=new String[Integer.parseInt(DBUtil.getQuestionCount())][2];
					//判断属于哪个练习模式
					lianximoshi=MainActivity.LXmoshi;
					//判断属于哪个科目
					DianJiKeMu=MainActivity.DianJiKeMu;
					//对选择前的图片  进行设置
					Resources resources = getBaseContext().getResources();   
					Drawable imageDrawable = resources.getDrawable(R.drawable.axuanxiang); //图片在drawable目录下
					imageview_a.setImageDrawable(imageDrawable);
					imageDrawable = resources.getDrawable(R.drawable.bxuanxiang); //图片在drawable目录下
					imageview_b.setImageDrawable(imageDrawable);
					imageDrawable = resources.getDrawable(R.drawable.cxuanxiang); //图片在drawable目录下
					imageview_c.setImageDrawable(imageDrawable);
					imageDrawable = resources.getDrawable(R.drawable.dxuanxiang); //图片在drawable目录下
					imageview_d.setImageDrawable(imageDrawable);
					//得到试题  的题目   和   各个选项内容   并添加  1：(题号)显示出来
					if(lianximoshi==0)
					{
						textview_biaoti_dati.setText("顺序练习");
						
						int changdu=Integer.parseInt(DBUtil.getCountBUYduoxiang(DianJiKeMu,MainActivity.XuanZeCheXing));
						shunxushitiID=new String[changdu];
						shunxushitiID=DBUtil.getQuestionIdBUYduoxiang(DianJiKeMu,MainActivity.XuanZeCheXing);
						TMshengcheng(shunxushitiID[shitidebianhao-1]);//顺序练习 第一个 自然为00001
					}
					else if(lianximoshi==4)//这个是我的收藏
					{
						textview_biaoti_dati.setText("我的收藏");
						String id=shoucangActivity.SCshitiID;
						TMshengcheng(id);
					}
					else if(lianximoshi==3)//随机练习
					{
						textview_biaoti_dati.setText("随机练习");
						//拿到试题个数//随机练习  试题其实跟顺序练习一个样
						int changdu=Integer.parseInt(DBUtil.getCountBUYduoxiang(DianJiKeMu,MainActivity.XuanZeCheXing));
						sJkaishishitiid=new String[changdu];
						sjlxSTid=new String[changdu];
						//拿到所有的试题
						sJkaishishitiid=DBUtil.getQuestionIdBUYduoxiang(DianJiKeMu,MainActivity.XuanZeCheXing);
						 int sjSTid[]=new int[changdu];//存放随机ID  //即作为shuxushitiid的下表
						sjSTid=genNum(changdu,changdu);
						for(int w=0;w<changdu;w++)
						{
							sjlxSTid[w]=sJkaishishitiid[sjSTid[w]-1];
						}
						TMshengcheng(sjlxSTid[shitidebianhao-1]);
					}
					else if(lianximoshi==1)//章节练习
					{
						textview_biaoti_dati.setText("章节练习");
						//判断  点击的是哪个章节名称
						if(zhangjieXUANZE.zhangjieBZ==0)//对应 章节名称 无
						{
							int changdu=Integer.parseInt(DBUtil.getZJCount("无",DianJiKeMu,MainActivity.XuanZeCheXing));
							ceshishitiID=new String [changdu];
							ceshishitiID=DBUtil.getQuestionIdByZJ("无",DianJiKeMu,MainActivity.XuanZeCheXing);
						}
						else if(zhangjieXUANZE.zhangjieBZ==1)//对应章节名称  标志题
						{
							int changdu=Integer.parseInt(DBUtil.getZJCount("标志题",DianJiKeMu,MainActivity.XuanZeCheXing));
							ceshishitiID=new String [changdu];
							ceshishitiID=DBUtil.getQuestionIdByZJ("标志题",DianJiKeMu,MainActivity.XuanZeCheXing);
						}
						else if(zhangjieXUANZE.zhangjieBZ==2)//对应章节名称  手势题
						{
							int changdu=Integer.parseInt(DBUtil.getZJCount("手势题",DianJiKeMu,MainActivity.XuanZeCheXing));
							ceshishitiID=new String [changdu];
							ceshishitiID=DBUtil.getQuestionIdByZJ("手势题",DianJiKeMu,MainActivity.XuanZeCheXing);
						}
						else if(zhangjieXUANZE.zhangjieBZ==3)//对应章节名称  灯光题
						{
							int changdu=Integer.parseInt(DBUtil.getZJCount("灯光题",DianJiKeMu,MainActivity.XuanZeCheXing));
							ceshishitiID=new String [changdu];
							ceshishitiID=DBUtil.getQuestionIdByZJ("灯光题",DianJiKeMu,MainActivity.XuanZeCheXing);
						}
						TMshengcheng(ceshishitiID[shitidebianhao-1]);
					}
					else if(lianximoshi==5)//我的错题模式
					{
						linear_xiayiti.setVisibility(View.GONE);
						linear_shangyiti.setVisibility(View.GONE);
						dati_shoucang.setVisibility(View.GONE);
						linear_xuexi.setVisibility(View.GONE);
						textview_CTshanchu.setVisibility(View.VISIBLE);
						textview_biaoti_dati.setText("我的错题");
						dati_down.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
						TMshengcheng(cuotiActivity.CUOTIdeid);
					}
					else if(lianximoshi==2)//分类练习
					{
						textview_biaoti_dati.setText("分类练习");
						if(fenleiActivity.fenleiBZ==0)//判断题
						{
							int changdu=Integer.parseInt(DBUtil.getFLCount("判断题",DianJiKeMu,MainActivity.XuanZeCheXing));
							fenleishitiID=new String [changdu];
							fenleishitiID=DBUtil.getQuestionIdByFL("判断题",DianJiKeMu,MainActivity.XuanZeCheXing);
						}
						else if(fenleiActivity.fenleiBZ==1)//单选题
						{
							int changdu=Integer.parseInt(DBUtil.getFLCount("单项选择题",DianJiKeMu,MainActivity.XuanZeCheXing));
							fenleishitiID=new String [changdu];
							fenleishitiID=DBUtil.getQuestionIdByFL("单项选择题",DianJiKeMu,MainActivity.XuanZeCheXing);
						}
						TMshengcheng(fenleishitiID[shitidebianhao-1]);
					}
					panduanxuanxiang();
					if(!shitixinxi[0][2].equals("空"))
					{
						tianjiaimage();
					}
					shifouCC();
					
					textview_a.setOnTouchListener(new OnTouchListener(){
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							// TODO Auto-generated method stub
							if(event.getAction()==MotionEvent.ACTION_DOWN)
							{
								huifuyuanyang();
								linear_a.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.yellow));
							}
							else if(event.getAction()==MotionEvent.ACTION_UP)
							{
								linear_a.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
								String daanneirong=textview_a.getText().toString();
								zuotixuangzejieguo[shitidebianhao][0]="A";
								try
								{
									String ZQXXneirong=getData[0];
									if(daanneirong.equals(ZQXXneirong))//选项正确
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
										imageview_a.setImageDrawable(imageDrawable);
										textview_a.setTextColor(shunxuActivity.this.getResources().getColor(R.color.green));//设置字体颜色
										zhengquedati();
										if(xueximuyang==1)//现在是学习模式
										{
											xianshiJIEXI();
										}
										}
									else 
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
										imageview_a.setImageDrawable(imageDrawable);
										textview_a.setTextColor(shunxuActivity.this.getResources().getColor(R.color.red));//设置字体颜色
										cuoqudati();
									}
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
			                }
							
							return true;
						}
						
					});
					textview_b.setOnTouchListener(new OnTouchListener(){

						@SuppressLint("ResourceAsColor")
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							// TODO Auto-generated method stub
							if(event.getAction()==MotionEvent.ACTION_DOWN)
							{
								huifuyuanyang();
								linear_b.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.yellow));
							}
							else if(event.getAction()==MotionEvent.ACTION_UP)
							{
								linear_b.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
								String daanneirong=textview_b.getText().toString();
								zuotixuangzejieguo[shitidebianhao][0]="B";
								try
								{
									String ZQXXneirong=getData[0];
									linear_d.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
									if(daanneirong.equals(ZQXXneirong))//选项正确
									{
										
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
										imageview_b.setImageDrawable(imageDrawable);
										textview_b.setTextColor(shunxuActivity.this.getResources().getColor(R.color.green));//设置字体颜色
										zhengquedati();
										if(xueximuyang==1)//现在是学习模式
										{
											xianshiJIEXI();
											
										}
									}
									else 
									{
										
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
										imageview_b.setImageDrawable(imageDrawable);
										textview_b.setTextColor(shunxuActivity.this.getResources().getColor(R.color.red));//设置字体颜色
										cuoqudati();
									}
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
			                }
							
							return true;
						}
						
					});
					textview_c.setOnTouchListener(new OnTouchListener(){

						@SuppressLint("ResourceAsColor")
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							// TODO Auto-generated method stub
							if(event.getAction()==MotionEvent.ACTION_DOWN)
							{
								huifuyuanyang();
								linear_c.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.yellow));
							}
							else if(event.getAction()==MotionEvent.ACTION_UP)
							{
								linear_c.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
								String daanneirong=textview_c.getText().toString();
								zuotixuangzejieguo[shitidebianhao][0]="C";
								try
								{
									String ZQXXneirong=getData[0];
									linear_d.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
									if(daanneirong.equals(ZQXXneirong))//选项正确
									{
										
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
										imageview_c.setImageDrawable(imageDrawable);
										textview_c.setTextColor(shunxuActivity.this.getResources().getColor(R.color.green));//设置字体颜色
										zhengquedati();
										if(xueximuyang==1)//现在是学习模式
										{
											xianshiJIEXI();
										}
									}
									else 
									{
										
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
										imageview_c.setImageDrawable(imageDrawable);
										textview_c.setTextColor(shunxuActivity.this.getResources().getColor(R.color.red));//设置字体颜色
										linear_timu.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.pink));//题目背景上设置为茶绿色
										textview_a.setEnabled(false);//答过一次题后   不可以再次点击答题
										textview_b.setEnabled(false);
										textview_c.setEnabled(false);
										textview_d.setEnabled(false);
										cuoqudati();
									}
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
			                }
							
							return true;
						}
						
					});
					textview_d.setOnTouchListener(new OnTouchListener(){

						@SuppressLint("ResourceAsColor")
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							// TODO Auto-generated method stub
							if(event.getAction()==MotionEvent.ACTION_DOWN)
							{
								huifuyuanyang();
								linear_d.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.yellow));
							}
							else if(event.getAction()==MotionEvent.ACTION_UP)
							{
								linear_d.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
								String daanneirong=textview_d.getText().toString();
								zuotixuangzejieguo[shitidebianhao][0]="D";
								try
								{
									String ZQXXneirong=getData[0];
									if(daanneirong.equals(ZQXXneirong))//选项正确
									{
										
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
										imageview_d.setImageDrawable(imageDrawable);
										textview_d.setTextColor(shunxuActivity.this.getResources().getColor(R.color.green));//设置字体颜色
										zhengquedati();
										if(xueximuyang==1)//现在是学习模式
										{
											xianshiJIEXI();
										}
									}
									else 
									{
										
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
										imageview_d.setImageDrawable(imageDrawable);
										textview_d.setTextColor(shunxuActivity.this.getResources().getColor(R.color.red));//设置字体颜色
										cuoqudati();
									}
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
			                }
							
							return true;
						}
						
					});
					linear_xiayiti.setOnClickListener( new View.OnClickListener()
					{

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							shitidebianhao++;//点击下一题  试题编号  ++
							if(lianximoshi==0)//为顺序练习
							{
								int timuzongshu=Integer.parseInt(DBUtil.getCountBUYduoxiang(DianJiKeMu,MainActivity.XuanZeCheXing));
								if(shitidebianhao>timuzongshu)//这里判断  不正确  等回来修改  总是闪退
								{
									shitidebianhao--;
									Toast.makeText(shunxuActivity.this, "这是最后一道题！！！", Toast.LENGTH_SHORT).show();
									return;
								}
					 			TMshengcheng(shunxushitiID[shitidebianhao-1]);
							}
							else if(lianximoshi==3)//随机练习
							{
								int timuzongshu=Integer.parseInt(DBUtil.getCountBUYduoxiang(DianJiKeMu,MainActivity.XuanZeCheXing));
								if(shitidebianhao>timuzongshu)
								{
									shitidebianhao--;
									Toast.makeText(shunxuActivity.this, "这是最后一道题！！！", Toast.LENGTH_SHORT).show();
									return;
								}
								TMshengcheng(sjlxSTid[shitidebianhao-1]);
							}
							else if(lianximoshi==1)//这个是  章节练习
							{
								int timuzongshu=20;
								if(zhangjieXUANZE.zhangjieBZ==0)//对应 章节名称 无
								{
									timuzongshu=Integer.parseInt(DBUtil.getZJCount("无",DianJiKeMu,MainActivity.XuanZeCheXing));
								}
								else if(zhangjieXUANZE.zhangjieBZ==1)//对应 章节名称 标志题
								{
									timuzongshu=Integer.parseInt(DBUtil.getZJCount("标志题",DianJiKeMu,MainActivity.XuanZeCheXing));
								}
								else if(zhangjieXUANZE.zhangjieBZ==2)//对应 章节名称 手势题
								{
									timuzongshu=Integer.parseInt(DBUtil.getZJCount("手势题",DianJiKeMu,MainActivity.XuanZeCheXing));
								}
								else if(zhangjieXUANZE.zhangjieBZ==3)//对应 章节名称 灯光题
								{
									timuzongshu=Integer.parseInt(DBUtil.getZJCount("灯光题",DianJiKeMu,MainActivity.XuanZeCheXing));
								}
								//对  题目总数进行了分析  现在得到的就是各个章节的题目总数
								
								if(shitidebianhao>timuzongshu)//这里判断  不正确  等回来修改  总是闪退
								{
									shitidebianhao--;
									Toast.makeText(shunxuActivity.this, "这是最后一道题！！！", Toast.LENGTH_SHORT).show();
									return;
								}
								TMshengcheng(ceshishitiID[shitidebianhao-1]);
							}
							else if(lianximoshi==4)//我的收藏
							{
								Toast.makeText(shunxuActivity.this, "请点击返回查看题目", Toast.LENGTH_SHORT).show();
							}
							else if(lianximoshi==2)//这个是  分类练习
							{
								int timuzongshu=20;
								if(fenleiActivity.fenleiBZ==0)//对应 分类名判断  
								{
									timuzongshu=Integer.parseInt(DBUtil.getFLCount("判断题",DianJiKeMu,MainActivity.XuanZeCheXing));
								}
								else if(fenleiActivity.fenleiBZ==1)//对应 分类名判断  
								{
									timuzongshu=Integer.parseInt(DBUtil.getFLCount("单项选择题",DianJiKeMu,MainActivity.XuanZeCheXing));
								}
								//对  题目总数进行了分析  现在得到的就是各个章节的题目总数
								if(shitidebianhao>timuzongshu)//这里判断  不正确  等回来修改  总是闪退
								{
									shitidebianhao--;
									Toast.makeText(shunxuActivity.this, "这是最后一道题！！！", Toast.LENGTH_SHORT).show();
									return;
								}
								TMshengcheng(fenleishitiID[shitidebianhao-1]);
							}
							imageview_datiimage.setVisibility(View.GONE);
							//得到试题
							//得到答案
							huifuyuanyang();
							//根据收藏模式  选择收藏按钮
							shifouCC();
							if(xueximuyang==1)//现在是学习模式
							{
								xianshiJIEXI();
								
							}
							//-------------------得到选项各个内容
							panduanxuanxiang();
							//如果有图片  就出现图片
							if(!shitixinxi[0][2].equals("空"))
							{
								imageview_datiimage.setVisibility(View.VISIBLE);
								tianjiaimage();
							}
							 String shangyiti_zuotixuanzejieguo=zuotixuangzejieguo[shitidebianhao][1];
							 if(shangyiti_zuotixuanzejieguo==null)
						        {
						        	return;
						        }
						        else if(shangyiti_zuotixuanzejieguo.equals("yes"))
						        {
						        	zuotizhengque();
				 		        }
						        else if(shangyiti_zuotixuanzejieguo.equals("no"))
						        {
						        	zuoticuowu();
						        	//做错的题   出现解析
						        	xianshiJIEXI();
						        }
						}
						
					
					});
				linear_shangyiti.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					shitidebianhao--;//点击 上一题  则试题编号--
					if(shitidebianhao<1)
					{
						shitidebianhao++;
						Toast.makeText(shunxuActivity.this, "这是第一道题！！！", Toast.LENGTH_SHORT).show();
						return;
					}
					if(lianximoshi==0)//顺序练习
					{
						TMshengcheng(shunxushitiID[shitidebianhao-1]);
					}
					else if(lianximoshi==3)//随机练习
					{
						TMshengcheng(sjlxSTid[shitidebianhao-1]);
					}
					else if(lianximoshi==1)//章节练习
					{
						TMshengcheng(ceshishitiID[shitidebianhao-1]);
					}
					else if(lianximoshi==4)//我的收藏
					{
						Toast.makeText(shunxuActivity.this, "请点击返回查看题目", Toast.LENGTH_SHORT).show();
					}
					else if(lianximoshi==2)//分类练习
					{
						TMshengcheng(fenleishitiID[shitidebianhao-1]);
					}
					huifuyuanyang();
					shifouCC();
					
					//得到试题
					//得到答案
					if(xueximuyang==1)//现在是学习模式
					{
						xianshiJIEXI();
					}
					
					//-------------------得到选项各个内容
					panduanxuanxiang();
					//如果有图片  就出现图片
					if(!shitixinxi[0][2].equals("空"))
					{
						imageview_datiimage.setVisibility(View.VISIBLE);
						tianjiaimage();
					}
					//到此为止  写完  各个实体的题目  及各个选项
					 String shangyiti_zuotixuanzejieguo=zuotixuangzejieguo[shitidebianhao][1];
					 if(shangyiti_zuotixuanzejieguo==null)
				        {
				        	return;
				        }
				        else if(shangyiti_zuotixuanzejieguo.equals("yes"))
				        {
				        	zuotizhengque();
		 		        }
				        else if(shangyiti_zuotixuanzejieguo.equals("no"))
				        {
				        	zuoticuowu();
				        	//做错的题   出现解析
				        	xianshiJIEXI();
				        }
					// shifouCC();
				}
			
				
			});
					dati_shoucang.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Resources resources = getBaseContext().getResources();
							if(shoucangmuyang==0)
							{
								Drawable imageDrawable = resources.getDrawable(R.drawable.shoucang_p); //图片在drawable目录下
								imageview_shoucang.setImageDrawable(imageDrawable);
								textview_shoucang.setTextColor(shunxuActivity.this.getResources().getColor(R.color.magenta));//设置字体颜色
								shoucangmuyang=1;
								if(lianximoshi==0)
								{
									DBUtil.insertSC(shunxushitiID[shitidebianhao-1]);
								}
								else if(lianximoshi==1)
								{
									DBUtil.insertSC(ceshishitiID[shitidebianhao-1]);
								}
								else if(lianximoshi==3)
								{
									DBUtil.insertSC(sjlxSTid[shitidebianhao-1]);
								}
								else if(lianximoshi==4)
								{
									DBUtil.insertSC(shoucangActivity.SCshitiID);
								}
								else if(lianximoshi==2)
								{
									DBUtil.insertSC(fenleishitiID[shitidebianhao-1]);
								}
								Toast.makeText(shunxuActivity.this, "该题加入收藏", Toast.LENGTH_SHORT).show();
							}
							else if(shoucangmuyang==1)
							{
								Drawable imageDrawable = resources.getDrawable(R.drawable.shoucang); //图片在drawable目录下
								imageview_shoucang.setImageDrawable(imageDrawable);
								textview_shoucang.setTextColor(shunxuActivity.this.getResources().getColor(R.color.black));//设置字体颜色
								shoucangmuyang=0;
								if(lianximoshi==0)
								{
									DBUtil.deleteSC(shunxushitiID[shitidebianhao-1]);
								}
								else if(lianximoshi==1)
								{
									DBUtil.deleteSC(ceshishitiID[shitidebianhao-1]);
								}
								else if(lianximoshi==3)
								{
									DBUtil.deleteSC(sjlxSTid[shitidebianhao-1]);
								}
								else if(lianximoshi==4)
								{
									DBUtil.deleteSC(shoucangActivity.SCshitiID);
								}
								else if(lianximoshi==2)
								{
									DBUtil.deleteSC(fenleishitiID[shitidebianhao-1]);
								}
								Toast.makeText(shunxuActivity.this, "该题取消收藏", Toast.LENGTH_SHORT).show();
							}
						}
					});
					linear_xuexi.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Resources resources = getBaseContext().getResources();
							if(xueximuyang==0)//现在是原样
							{
								Drawable imageDrawable = resources.getDrawable(R.drawable.chakan_p); //图片在drawable目录下
								imageview_xuexi.setImageDrawable(imageDrawable);
								textview_xuexi.setTextColor(shunxuActivity.this.getResources().getColor(R.color.magenta));//设置字体颜色
								xueximuyang=1;
								xianshiJIEXI();
								
							}
							else if(xueximuyang==1)
							{
								Drawable imageDrawable = resources.getDrawable(R.drawable.chakan); //图片在drawable目录下
								imageview_xuexi.setImageDrawable(imageDrawable);
								textview_xuexi.setTextColor(shunxuActivity.this.getResources().getColor(R.color.black));//设置字体颜色
								xueximuyang=0;
								textview_jiexitimu.setVisibility(View.GONE);//设置组件bu可见
								linear_daanxuanxiang.setVisibility(View.GONE);
								linear_jiexi.setVisibility(View.GONE);
							}
						}
					});
			//对单独删除试题  进行监听
					textview_CTshanchu.setOnTouchListener(new View.OnTouchListener() {
						
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							// TODO Auto-generated method stub
							if(event.getAction()==MotionEvent.ACTION_DOWN)
							{
								textview_CTshanchu.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.red));
							}
							else if(event.getAction()==MotionEvent.ACTION_UP)
							{
								textview_CTshanchu.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
								DBUtil.deleteCUOTI(cuotiActivity.CUOTIdeid);
								Toast.makeText(shunxuActivity.this, "该道题被删除", Toast.LENGTH_SHORT).show();
							}
							return true;
						}
					});
	}
	//将所有的事件回复原样
	public void huifuyuanyang()
	{
		textview_a.setEnabled(true);//重新开始一道题  可以点击
		textview_b.setEnabled(true);
		textview_c.setEnabled(true);
		textview_d.setEnabled(true);
		Resources resources = getBaseContext().getResources();   
		Drawable imageDrawable = resources.getDrawable(R.drawable.axuanxiang); //图片在drawable目录下
		imageview_a.setImageDrawable(imageDrawable);
		imageDrawable = resources.getDrawable(R.drawable.bxuanxiang); //图片在drawable目录下
		imageview_b.setImageDrawable(imageDrawable);
		imageDrawable = resources.getDrawable(R.drawable.cxuanxiang); //图片在drawable目录下
		imageview_c.setImageDrawable(imageDrawable);
		imageDrawable = resources.getDrawable(R.drawable.dxuanxiang); //图片在drawable目录下
		imageview_d.setImageDrawable(imageDrawable);
		linear_timu.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));//每点击下一题  就将已经更改的题目背景改为白色
		linear_a.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
		linear_b.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
		linear_c.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.white));
		textview_jiexitimu.setVisibility(View.GONE);//设置组件不可见
		linear_daanxuanxiang.setVisibility(View.GONE);
		linear_jiexi.setVisibility(View.GONE);
		textview_a.setTextColor(shunxuActivity.this.getResources().getColor(R.color.black));//设置字体颜色
		textview_b.setTextColor(shunxuActivity.this.getResources().getColor(R.color.black));//设置字体颜色
		textview_c.setTextColor(shunxuActivity.this.getResources().getColor(R.color.black));//设置字体颜色
		textview_d.setTextColor(this.getResources().getColor(R.color.black));//设置字体颜色
	}
	//如果是  学习模式  将答案与解析显示出来
	public void xianshiJIEXI()
	{
		textview_jiexitimu.setVisibility(View.VISIBLE);//设置组件可见
		linear_daanxuanxiang.setVisibility(View.VISIBLE);
		linear_jiexi.setVisibility(View.VISIBLE);
		//学习界面  更新试题答案与解析
		try
		{
			String getdaan[]=null;
			if(lianximoshi==0)
			{
				getdaan=DBUtil.getAnswerzhengqueeByid(shunxushitiID[shitidebianhao-1]);//得到某个试题的正确答案选项   如 A  B  C  D 
			}
			else if(lianximoshi==3)
			{
				getdaan=DBUtil.getAnswerzhengqueeByid(sjlxSTid[shitidebianhao-1]);//得到某个试题的正确答案选项
			}
			else if(lianximoshi==1)
			{
				getdaan=DBUtil.getAnswerzhengqueeByid(ceshishitiID[shitidebianhao-1]);//得到某个试题的正确答案选项
			}
			else if(lianximoshi==4)
			{
				getdaan=DBUtil.getAnswerzhengqueeByid(shoucangActivity.SCshitiID);//得到某个试题的正确答案选项
			}
			else if(lianximoshi==5)
			{
				getdaan=DBUtil.getAnswerzhengqueeByid(cuotiActivity.CUOTIdeid);//得到某个试题的正确答案选项
			}
			else if(lianximoshi==2)
			{
				getdaan=DBUtil.getAnswerzhengqueeByid(fenleishitiID[shitidebianhao-1]);//得到某个试题的正确答案选项
			}
			textview_daan=(TextView)findViewById(R.id.textview_daanxuanxiang);
			textview_daan.setText(getdaan[0]);//这里得到的就是答案   如 A  B。。。
			textview_jiexineirong.setText(DAjiexi);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//对含有图片的试题  添加图片   其实好多应该传进ID  懒得改了 以后也不会改了   Hhhhh
		public void  tianjiaimage()
		{
			String imageid=null;
			if(lianximoshi==0)
			{
				imageid=shunxushitiID[shitidebianhao-1];
			}
			else if(lianximoshi==3)
			{
				imageid=sjlxSTid[shitidebianhao-1];
			}
			else if(lianximoshi==1)
			{
				imageid=ceshishitiID[shitidebianhao-1];
			}
			else if(lianximoshi==4)
			{
				imageid=shoucangActivity.SCshitiID;
			}
			else if(lianximoshi==5)
			{
				imageid=cuotiActivity.CUOTIdeid;
			}
			else if(lianximoshi==2)
			{
				imageid=fenleishitiID[shitidebianhao-1];
			}
			String fileName =Environment.getExternalStorageDirectory().toString()+"/Drive/Image/"+imageid+".png";
			try {
				Bitmap bm = BitmapFactory.decodeFile(fileName);
				imageview_datiimage.setImageBitmap(bm);
			} catch (Exception e) {}
		}
	//正确答题
	public void zhengquedati()
	{
		zuotixuangzejieguo[shitidebianhao][1]="yes";
		String cuotiID=null;
		linear_timu.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.chartreuse));//题目背景上设置为茶绿色
		textview_a.setEnabled(false);//答过一次题后   不可以再次点击答题
		textview_b.setEnabled(false);
		textview_c.setEnabled(false);
		textview_d.setEnabled(false);
		if(lianximoshi==0)
		{
			cuotiID=shunxushitiID[shitidebianhao-1];
		}
		else if(lianximoshi==3)
		{
			cuotiID=sjlxSTid[shitidebianhao-1];
		}
		else if(lianximoshi==1)
		{
			cuotiID=ceshishitiID[shitidebianhao-1];
		}
		else if(lianximoshi==2)
		{
			cuotiID=fenleishitiID[shitidebianhao-1];
		}
		DBUtil.insertSJTJ(cuotiID, "yes");//插入数据统计表
	}
	//错误答题
	public void cuoqudati()
	{
		zuotixuangzejieguo[shitidebianhao][1]="no";
		linear_timu.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.pink));//题目背景上设置为茶绿色
		textview_a.setEnabled(false);//答过一次题后   不可以再次点击答题
		textview_b.setEnabled(false);
		textview_c.setEnabled(false);
		textview_d.setEnabled(false);
		String cuotiID=null;
		xianshiJIEXI();
		//答题错误时  将错误试题的题号  加入错题表   只有练习才会加入错题表  
		if(lianximoshi==0)
		{
			cuotiID=shunxushitiID[shitidebianhao-1];
		}
		else if(lianximoshi==3)
		{
			cuotiID=sjlxSTid[shitidebianhao-1];
		}
		else if(lianximoshi==1)
		{
			cuotiID=ceshishitiID[shitidebianhao-1];
		}
		else if(lianximoshi==2)
		{
			cuotiID=fenleishitiID[shitidebianhao-1];
		}
		DBUtil.insertCUOTI(cuotiID);
		DBUtil.insertSJTJ(cuotiID, "no");//插入数据统计表
	}
	//判断  答题界面应该有几个选项
	public void panduanxuanxiang()
	{
		if(shitixinxi[0][3].equals("判断题"))
		{
		textview_a.setText(shitixuanxiang[0][1]);
		textview_b.setText(shitixuanxiang[1][1]);
		textview_c.setVisibility(View.GONE);//如果是判断题  则只有两个选项
		textview_d.setVisibility(View.GONE);
		imageview_c.setVisibility(View.GONE);
		imageview_d.setVisibility(View.GONE);
		}
		else
		{
			textview_c.setVisibility(View.VISIBLE);//如果不是判断题  则有四个个选项
			textview_d.setVisibility(View.VISIBLE);
			imageview_c.setVisibility(View.VISIBLE);
			imageview_d.setVisibility(View.VISIBLE);
			textview_a.setText(shitixuanxiang[0][1]);
			textview_b.setText(shitixuanxiang[1][1]);
			textview_c.setText(shitixuanxiang[2][1]);
			textview_d.setText(shitixuanxiang[3][1]);
		}
	}
	//对是否收藏进行判断
	public void shifouCC()
	{
		String shifouchoucang=null;
		if(lianximoshi==0)
		{
			shifouchoucang=DBUtil.SFshoucang(shunxushitiID[shitidebianhao-1]);
		}
		else if(lianximoshi==1)
		{
			shifouchoucang=DBUtil.SFshoucang(ceshishitiID[shitidebianhao-1]);
		}
		else if(lianximoshi==3)
		{
			shifouchoucang=DBUtil.SFshoucang(sjlxSTid[shitidebianhao-1]);
		}
		else if(lianximoshi==4)
		{
			shifouchoucang=DBUtil.SFshoucang(shoucangActivity.SCshitiID);
		}
		else if(lianximoshi==5)
		{
			shifouchoucang="no";//进入我的错题  收藏是看不见的  但是会出错  这里其实可以随便写
		}
		else if(lianximoshi==2)
		{
			shifouchoucang=DBUtil.SFshoucang(fenleishitiID[shitidebianhao-1]);
		}
			
		 if(shifouchoucang.equals("no"))
		 {
			 Resources resources = getBaseContext().getResources();
			 Drawable imageDrawable = resources.getDrawable(R.drawable.shoucang); //图片在drawable目录下
				imageview_shoucang.setImageDrawable(imageDrawable);
				textview_shoucang.setTextColor(shunxuActivity.this.getResources().getColor(R.color.black));//设置字体颜色
				shoucangmuyang=0;//点击下一题  这道题没有被收藏  则收藏点击  应该是0   为原样
		 }
		 else
		 {
			 Resources resources = getBaseContext().getResources();
			 Drawable imageDrawable = resources.getDrawable(R.drawable.shoucang_p); //图片在drawable目录下
				imageview_shoucang.setImageDrawable(imageDrawable);
				textview_shoucang.setTextColor(shunxuActivity.this.getResources().getColor(R.color.magenta));//设置字体颜色
				shoucangmuyang=1;//点击下一题  这道题已经被收藏  则收藏点击  应该是1   为点击后模样
		 }
	}
	//点击  上一题  或者下一题时   你已经做过题  且正确
	public void zuotizhengque()
	{
		linear_timu.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.chartreuse));//回答正确   题目变为绿色
    	Resources resources = getBaseContext().getResources();   
		String shangyiti_zuotixuanzexuanxiang=zuotixuangzejieguo[shitidebianhao][0];//A   B   C  ..
    	if(shangyiti_zuotixuanzexuanxiang.equals("A"))//选择的是A
    	{
    		Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
			imageview_a.setImageDrawable(imageDrawable);
			textview_a.setTextColor(shunxuActivity.this.getResources().getColor(R.color.green));//设置字体颜色
    	}
    	else if(shangyiti_zuotixuanzexuanxiang.equals("B"))//选择的是A
    	{
    		Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
			imageview_b.setImageDrawable(imageDrawable);
			textview_b.setTextColor(shunxuActivity.this.getResources().getColor(R.color.green));//设置字体颜色
    	}
    	else if(shangyiti_zuotixuanzexuanxiang.equals("C"))//选择的是A
    	{
    		Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
			imageview_c.setImageDrawable(imageDrawable);
			textview_c.setTextColor(shunxuActivity.this.getResources().getColor(R.color.green));//设置字体颜色
    	}
    	else if(shangyiti_zuotixuanzexuanxiang.equals("D"))//选择的是A
    	{
    		Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
			imageview_d.setImageDrawable(imageDrawable);
			textview_d.setTextColor(shunxuActivity.this.getResources().getColor(R.color.green));//设置字体颜色
    	}
	}
	//当点击 上一题  或者下一题是   已经做过这道题  但是做错了
	public void zuoticuowu()
	{
		linear_timu.setBackgroundColor(shunxuActivity.this.getResources().getColor(R.color.pink));
    	//根据选择选的选项    变换颜色    记得写
    	Resources resources = getBaseContext().getResources();   
		String shangyiti_zuotixuanzexuanxiang=zuotixuangzejieguo[shitidebianhao][0];//A   B   C  ..
    	if(shangyiti_zuotixuanzexuanxiang.equals("A"))//选择的是A
    	{
    		Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
			imageview_a.setImageDrawable(imageDrawable);
			textview_a.setTextColor(shunxuActivity.this.getResources().getColor(R.color.red));//设置字体颜色
    	}
    	else if(shangyiti_zuotixuanzexuanxiang.equals("B"))//选择的是A
    	{
    		Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
			imageview_b.setImageDrawable(imageDrawable);
			textview_b.setTextColor(shunxuActivity.this.getResources().getColor(R.color.red));//设置字体颜色
    	}
    	else if(shangyiti_zuotixuanzexuanxiang.equals("C"))//选择的是A
    	{
    		Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
			imageview_c.setImageDrawable(imageDrawable);
			textview_c.setTextColor(shunxuActivity.this.getResources().getColor(R.color.red));//设置字体颜色
    	}
    	else if(shangyiti_zuotixuanzexuanxiang.equals("D"))//选择的是A
    	{
    		Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
			imageview_d.setImageDrawable(imageDrawable);
			textview_d.setTextColor(shunxuActivity.this.getResources().getColor(R.color.red));//设置字体颜色
    	}
	}
	//生成题目
	public void TMshengcheng(String ceshistid)
	{
		shitixinxi=fangfa.getshiti(ceshistid);
		shitixuanxiang=fangfa.getdaanxuanxiangneirong(ceshistid);
		DAjiexi=shitixinxi[0][5];
		//写出题目
		StringBuilder sb=new StringBuilder();//用StringBuilder动态生成信息
		sb.append(shitidebianhao+""+" :");//加上题号
		sb.append(shitixinxi[0][0]);
		textview_timu= (TextView) findViewById(R.id.TextView_timu);
		textview_timu.setText(sb);
		getData=DBUtil.getAnswerneirongByid(ceshistid);//得到题目的答案内容
	}
	public static int[] genNum(int num, int value) 
//	throws TargetMinException 
{  
int[] arr = new int[num];// 保存最终生成结果  
int index = 0;
boolean result = true;  
while (result) {// 控制是否继续生成随机数  
    Random rd = new Random();  
    int tempRandomNum = rd.nextInt(value)+1 ;  
    if (arr[arr.length - 1] == 0) {// 决定是否继续生成随机数进行赋值  
        if (isHas(tempRandomNum, arr, index)) {// 判断已生成随机数是否与数组中已有数值重复  
            continue;  
        }  
        arr[index] = tempRandomNum;// 将生成的不重复发的随机数放入数组中  
        index++;
    } else  
        result = false;  
}  
return arr;  
} 
	private static boolean isHas(int tempRandomNum, int[] arr, int index) {  
		for (int i = 0; i < index; i++) {  
		    if (tempRandomNum == arr[i]) {  
		        return true;  
		    }  
		}  
		return false;  
		} 
}
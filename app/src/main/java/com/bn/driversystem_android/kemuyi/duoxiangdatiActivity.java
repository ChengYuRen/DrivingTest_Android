package com.bn.driversystem_android.kemuyi;

import com.bn.driversystem_android.MainActivity;
import com.bn.driversystem_android.R;
import com.bn.util.DBUtil;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class duoxiangdatiActivity extends Activity{
	private TextView textview_biaoti_dati;
	String DAjiexi=null;
	int putongmoshi;//这个普通模式  指的是 从多项选择题点击进入  当等于1时  指从收藏点击进入   记录好关系  不要出现逻辑错误
	String duoxiangSTid[];
	int xuanxiangtupian[]={R.drawable.axuanxiang,R.drawable.bxuanxiang,R.drawable.cxuanxiang,R.drawable.dxuanxiang};
	String shitixinxi[][]=new String[1][5];
	String shitixuanxiang[][]=new String[4][];
	int shitidebianhao=1;//在界面中显示的试题的编号
	int shoucangmuyang=0;//0则是原样   1是点击后的样子   收藏   标志
	int xueximuyang=0;//0是原样   学习模式
	String getzhengquexuanxiang[];//这个是得到答案选项  如 A  B  C  D  ...
	int A_moyang=0; int B_moyang=0; int C_moyang=0; int D_moyang=0;//0为各个选项的本来模样   变为1时则变为点击状态
	int daanshuliang=0;//这里放置的是答案的数量  当答案数量少于两个时   不能交答案
	String datijieguo[][];
	String zuotixuangze[]=new String [4];//这个存储的是  用户点击以后的
	String DianJiKeMu;//这里分为科目一  与科目四
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
	private TextView textview_CTshanchu;
	@SuppressWarnings("unused")
	private LinearLayout linear_jiexi,linear_daanxuanxiang,linear_timu,linear_a,linear_b,linear_c,linear_d,linear_shangyiti,linear_xiayiti,dati_shoucang,linear_xuexi,dati_down;
	private ImageView imageview_a,imageview_b,imageview_c,imageview_d,imageview_shoucang,imageview_xuexi,imageview_datiimage,imageview_querendati;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.datijiemian);
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
				textview_biaoti_dati=(TextView)findViewById(R.id.textview_biaoti_dati);
				textview_biaoti_dati.setText("分类练习");
				putongmoshi=shoucangActivity.putongmoshi;
				//判断属于哪个科目
				DianJiKeMu=MainActivity.DianJiKeMu;
				//对各个选线 的图片 进行设置
				Resources resources = getBaseContext().getResources();   
				Drawable imageDrawable = resources.getDrawable(R.drawable.axuanxiang); //图片在drawable目录下
				imageview_a.setImageDrawable(imageDrawable);
				imageDrawable = resources.getDrawable(R.drawable.bxuanxiang); //图片在drawable目录下
				imageview_b.setImageDrawable(imageDrawable);
				imageDrawable = resources.getDrawable(R.drawable.cxuanxiang); //图片在drawable目录下
				imageview_c.setImageDrawable(imageDrawable);
				imageDrawable = resources.getDrawable(R.drawable.dxuanxiang); //图片在drawable目录下
				imageview_d.setImageDrawable(imageDrawable);
				datijieguo=new String [Integer.parseInt(DBUtil.getQuestionCount())][4];
				if(putongmoshi==0)
				{
					int shitizongshu=Integer.parseInt(DBUtil.getFLCount("多项选择题",DianJiKeMu,MainActivity.XuanZeCheXing));
				duoxiangSTid=new String[shitizongshu];//按照有多少题  就设置数组大小是多少
				duoxiangSTid=DBUtil.getQuestionIdByFL("多项选择题",DianJiKeMu,MainActivity.XuanZeCheXing);//将多项选择题的ID  放入数组中
				TMshengcheng(duoxiangSTid[shitidebianhao-1]);
				}
				else if(putongmoshi==1)
				{
					TMshengcheng(shoucangActivity.SCshitiID);
				}
				panduanxuanxiang();
				if(!shitixinxi[0][2].equals("空"))
				{
					tianjiaimage();
				}
				shifouCC();
				textview_a.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(A_moyang==0)//现在是原样     点击以后会变为  1
						{
							daanshuliang++;
							linear_a.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.yellow));
							A_moyang=1;
						}
						else if(A_moyang==1)
						{
							daanshuliang--;
							linear_a.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
							A_moyang=0;
						}
					}
				});
				textview_b.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(B_moyang==0)//现在是原样     点击以后会变为  1
						{
							daanshuliang++;
							linear_b.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.yellow));
							B_moyang=1;
						}
						else if(B_moyang==1)
						{
							daanshuliang--;
							linear_b.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
							B_moyang=0;
						}
					}
				});
				textview_c.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(C_moyang==0)//现在是原样     点击以后会变为  1
						{
							daanshuliang++;
							linear_c.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.yellow));
							C_moyang=1;
						}
						else if(C_moyang==1)
						{
							daanshuliang--;
							linear_c.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
							C_moyang=0;
						}
					}
				});
				textview_d.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(D_moyang==0)//现在是原样     点击以后会变为  1
						{
							daanshuliang++;
							linear_d.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.yellow));
							D_moyang=1;
						}
						else if(D_moyang==1)
						{
							daanshuliang--;
							linear_d.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
							D_moyang=0;
						}
					}
				});
				imageview_querendati.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(daanshuliang<2)
						{
							Toast.makeText(duoxiangdatiActivity.this, "少于两个答案不可以答题", Toast.LENGTH_SHORT).show();
						}
						else
						{
							for(int huanyuan=0;huanyuan<datijieguo[shitidebianhao].length;huanyuan++)
							{	
								datijieguo[shitidebianhao][huanyuan]=null;//提交答案时  将以前写过的答案清楚
							}
							huifuyuanyang();
							textview_a.setEnabled(false);//将题目 变为不可以点击
							textview_b.setEnabled(false);
							textview_c.setEnabled(false);
							textview_d.setEnabled(false);
							if(A_moyang==1)//当用户选择了A
							{
								linear_a.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
								for(int i=0;i<getzhengquexuanxiang.length;i++)//对答案惊醒遍历
								{
									if(getzhengquexuanxiang[i]==null)
									{
										continue;//为空  啥也不干  退出
									}
									else if(getzhengquexuanxiang[i].equals("A"))//若其中一个答案为A   
									{//说明用户选择正确    则A选项  变为你帽子的颜色
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
										imageview_a.setImageDrawable(imageDrawable);
										textview_a.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
										datijieguo[shitidebianhao][0]="yes";//正确答题   是yes
										break;
									}
									else//答案里面并没有用户的选择  所以选择错误
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
										imageview_a.setImageDrawable(imageDrawable);
										textview_a.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.red));//设置字体颜色
										datijieguo[shitidebianhao][0]="no";//错误答题  是no   0指的是A
									}
								}
							}
							if(B_moyang==1)//当用户选择了A
							{
								linear_b.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
								for(int i=0;i<getzhengquexuanxiang.length;i++)//对答案惊醒遍历
								{
									if(getzhengquexuanxiang[i]==null)
									{
										continue;//为空  啥也不干  退出
									}
									else if(getzhengquexuanxiang[i].equals("B"))//若其中一个答案为A   
									{//说明用户选择正确    则A选项  变为你帽子的颜色
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
										imageview_b.setImageDrawable(imageDrawable);
										textview_b.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
										datijieguo[shitidebianhao][1]="yes";
										break;
									}
									else//答案里面并没有用户的选择  所以选择错误
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
										imageview_b.setImageDrawable(imageDrawable);
										textview_b.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.red));//设置字体颜色
										datijieguo[shitidebianhao][1]="no";
									}
								}
							}
							if(C_moyang==1)//当用户选择了A
							{
								linear_c.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
								for(int i=0;i<getzhengquexuanxiang.length;i++)//对答案惊醒遍历
								{
									if(getzhengquexuanxiang[i]==null)
									{
										continue;//为空  啥也不干  退出
									}
									else if(getzhengquexuanxiang[i].equals("C"))//若其中一个答案为A   
									{//说明用户选择正确    则A选项  变为你帽子的颜色
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
										imageview_c.setImageDrawable(imageDrawable);
										textview_c.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
										datijieguo[shitidebianhao][2]="yes";
										break;
									}
									else//答案里面并没有用户的选择  所以选择错误
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
										imageview_c.setImageDrawable(imageDrawable);
										textview_c.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.red));//设置字体颜色
										datijieguo[shitidebianhao][2]="no";
									}
								}
							}
							if(D_moyang==1)//当用户选择了A
							{
								linear_d.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
								for(int i=0;i<getzhengquexuanxiang.length;i++)//对答案惊醒遍历
								{
									if(getzhengquexuanxiang[i]==null)
									{
										continue;//为空  啥也不干  退出
									}
									else if(getzhengquexuanxiang[i].equals("D"))//若其中一个答案为A   
									{//说明用户选择正确    则A选项  变为你帽子的颜色
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
										imageview_d.setImageDrawable(imageDrawable);
										textview_d.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
										datijieguo[shitidebianhao][3]="yes";
										break;
									}
									else//答案里面并没有用户的选择  所以选择错误
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
										imageview_d.setImageDrawable(imageDrawable);
										textview_d.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.red));//设置字体颜色
										datijieguo[shitidebianhao][3]="no";
									}
								}
							}//if的结束
							//这里判断用户没有选择的答案选项     若是有正确答案包含   应该变换颜色   这里写成了蓝色
							for(int i=0;i<getzhengquexuanxiang.length;i++)//对答案惊醒遍历
							{
								if(getzhengquexuanxiang[i]==null)
								{
									continue;//为空  啥也不干  退出
								}
								else if(getzhengquexuanxiang[i].equals("A"))//答案里面包含了A 选项
								{//A选项并没有被选中时   则变换颜色  为蓝色
									if(A_moyang==0)
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zqweida); //图片在drawable目录下
										imageview_a.setImageDrawable(imageDrawable);
										textview_a.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
										datijieguo[shitidebianhao][0]="weida";
									}
								}
								else if(getzhengquexuanxiang[i].equals("B"))//答案里面包含了A 选项
								{
									if(B_moyang==0)
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zqweida); //图片在drawable目录下
										imageview_b.setImageDrawable(imageDrawable);
										textview_b.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
										datijieguo[shitidebianhao][1]="weida";
									}
								}
								else if(getzhengquexuanxiang[i].equals("C"))//答案里面包含了A 选项
								{//A选项并没有被选中时   则变换颜色  为蓝色
									if(C_moyang==0)
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zqweida); //图片在drawable目录下
										imageview_c.setImageDrawable(imageDrawable);
										textview_c.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
										datijieguo[shitidebianhao][2]="weida";
									}
								}
								else if(getzhengquexuanxiang[i].equals("D"))//答案里面包含了A 选项
								{//A选项并没有被选中时   则变换颜色  为蓝色
									if(D_moyang==0)
									{
										Resources resources = getBaseContext().getResources();   
										Drawable imageDrawable = resources.getDrawable(R.drawable.zqweida); //图片在drawable目录下
										imageview_d.setImageDrawable(imageDrawable);
										textview_d.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
										datijieguo[shitidebianhao][3]="weida";
									}
								}
							}
						}
					}
				});
				//对收藏模式的点击
				dati_shoucang.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Resources resources = getBaseContext().getResources();
						if(shoucangmuyang==0)
						{
							Drawable imageDrawable = resources.getDrawable(R.drawable.shoucang_p); //图片在drawable目录下
							imageview_shoucang.setImageDrawable(imageDrawable);
							textview_shoucang.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.magenta));//设置字体颜色
							shoucangmuyang=1;
							if(putongmoshi==0)
							{
								DBUtil.insertSC(duoxiangSTid[shitidebianhao-1]);
							}
							else if(putongmoshi==1)
							{
								DBUtil.insertSC(shoucangActivity.SCshitiID);
							}
							Toast.makeText(duoxiangdatiActivity.this, "该题加入收藏", Toast.LENGTH_SHORT).show();
						}
						else if(shoucangmuyang==1)
						{
							Drawable imageDrawable = resources.getDrawable(R.drawable.shoucang); //图片在drawable目录下
							imageview_shoucang.setImageDrawable(imageDrawable);
							textview_shoucang.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.black));//设置字体颜色
							shoucangmuyang=0;
							if(putongmoshi==0)
							{
								DBUtil.deleteSC(duoxiangSTid[shitidebianhao-1]);
							}
							else if(putongmoshi==1)
							{
								DBUtil.deleteSC(shoucangActivity.SCshitiID);
							}
							Toast.makeText(duoxiangdatiActivity.this, "该题取消收藏", Toast.LENGTH_SHORT).show();
						}
					}
				});
				//学习模式的点击
				linear_xuexi.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Resources resources = getBaseContext().getResources();
						if(xueximuyang==0)//现在是原样
						{
							Drawable imageDrawable = resources.getDrawable(R.drawable.chakan_p); //图片在drawable目录下
							imageview_xuexi.setImageDrawable(imageDrawable);
							textview_xuexi.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.magenta));//设置字体颜色
							xueximuyang=1;
							xianshiJIEXI();
							
						}
						else if(xueximuyang==1)
						{
							Drawable imageDrawable = resources.getDrawable(R.drawable.chakan); //图片在drawable目录下
							imageview_xuexi.setImageDrawable(imageDrawable);
							textview_xuexi.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.black));//设置字体颜色
							xueximuyang=0;
							textview_jiexitimu.setVisibility(View.GONE);//设置组件bu可见
							linear_daanxuanxiang.setVisibility(View.GONE);
							linear_jiexi.setVisibility(View.GONE);
						}
					}
				});
				//对下一题
				linear_xiayiti.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(putongmoshi==1)
						{
							Toast.makeText(duoxiangdatiActivity.this, "请点击返回查看题目", Toast.LENGTH_SHORT).show();
						}
						else if(putongmoshi==0)
						{
							shitidebianhao++;//点击下一题  试题编号  ++
							if(shitidebianhao>duoxiangSTid.length)//超出总题数
							{
								shitidebianhao--;
								Toast.makeText(duoxiangdatiActivity.this, "这是最后一道题！！！", Toast.LENGTH_SHORT).show();
								return;
							}
							TMshengcheng(duoxiangSTid[shitidebianhao-1]);
							imageview_datiimage.setVisibility(View.GONE);
							//得到试题
							//得到答案
							huifuyuanyang();
							A_moyang=0; B_moyang=0; C_moyang=0; D_moyang=0;//0为各个选项的本来模样   变为1时则变为点击状态
							daanshuliang=0;//这里放置的是答案的数量  当答案数量少于两个时   不能交答案
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
							jieguopanduan();
						}
					}
				});
				linear_shangyiti.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(putongmoshi==1)
						{
							Toast.makeText(duoxiangdatiActivity.this, "请点击返回查看题目", Toast.LENGTH_SHORT).show();
						}
						else if(putongmoshi==0)
						{
							shitidebianhao--;//点击 上一题  则试题编号--
							if(shitidebianhao<1)
							{
								shitidebianhao++;
								Toast.makeText(duoxiangdatiActivity.this, "这是第一道题！！！", Toast.LENGTH_SHORT).show();
								return;
							}
							TMshengcheng(duoxiangSTid[shitidebianhao-1]);
							huifuyuanyang();
							A_moyang=0; B_moyang=0; C_moyang=0; D_moyang=0;//0为各个选项的本来模样   变为1时则变为点击状态
							daanshuliang=0;//这里放置的是答案的数量  当答案数量少于两个时   不能交答案
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
							jieguopanduan();
						}
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
			linear_timu.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));//每点击下一题  就将已经更改的题目背景改为白色
			linear_a.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
			linear_b.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
			linear_c.setBackgroundColor(duoxiangdatiActivity.this.getResources().getColor(R.color.white));
			textview_jiexitimu.setVisibility(View.GONE);//设置组件不可见
			linear_daanxuanxiang.setVisibility(View.GONE);
			linear_jiexi.setVisibility(View.GONE);
			textview_a.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.black));//设置字体颜色
			textview_b.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.black));//设置字体颜色
			textview_c.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.black));//设置字体颜色
			textview_d.setTextColor(this.getResources().getColor(R.color.black));//设置字体颜色
		}
	//判断  答题界面应该有几个选项    现在是多项  根本不用判断  反正是粘贴  随便写吧
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
			getzhengquexuanxiang=DBUtil.getAnswerzhengqueeByid(ceshistid);//得到题目的答案内容
		}
		//对含有图片的试题  添加图片   其实好多应该传进ID  懒得改了 以后也不会改了   Hhhhh
				public void  tianjiaimage()
				{
					String imageid=null;
					if(putongmoshi==0)
					{
						imageid=duoxiangSTid[shitidebianhao-1];
					}
					else if(putongmoshi==1)
					{
						imageid=shoucangActivity.SCshitiID;
					}
					String fileName =Environment.getExternalStorageDirectory().toString()+"/Drive/Image/"+imageid+".png";
					try {
						Bitmap bm = BitmapFactory.decodeFile(fileName);
						imageview_datiimage.setImageBitmap(bm);
					} catch (Exception e) {}
				}
		//对是否收藏进行判断
		public void shifouCC()
		{
			String shifouchoucang=null;
			if(putongmoshi==0)
			{
				shifouchoucang=DBUtil.SFshoucang(duoxiangSTid[shitidebianhao-1]);
			}
			else if(putongmoshi==1)
			{
				shifouchoucang=DBUtil.SFshoucang(shoucangActivity.SCshitiID);
			}
			 if(shifouchoucang.equals("no"))
			 {
				 Resources resources = getBaseContext().getResources();
				 Drawable imageDrawable = resources.getDrawable(R.drawable.shoucang); //图片在drawable目录下
					imageview_shoucang.setImageDrawable(imageDrawable);
					textview_shoucang.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.black));//设置字体颜色
					shoucangmuyang=0;//点击下一题  这道题没有被收藏  则收藏点击  应该是0   为原样
			 }
			 else
			 {
				 Resources resources = getBaseContext().getResources();
				 Drawable imageDrawable = resources.getDrawable(R.drawable.shoucang_p); //图片在drawable目录下
					imageview_shoucang.setImageDrawable(imageDrawable);
					textview_shoucang.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.magenta));//设置字体颜色
					shoucangmuyang=1;//点击下一题  这道题已经被收藏  则收藏点击  应该是1   为点击后模样
			 }
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
				if(putongmoshi==0)
				{
					getdaan=DBUtil.getAnswerzhengqueeByid(duoxiangSTid[shitidebianhao-1]);
				}
				else if(putongmoshi==1)
				{
					getdaan=DBUtil.getAnswerzhengqueeByid(shoucangActivity.SCshitiID);
				}
				textview_daan=(TextView)findViewById(R.id.textview_daanxuanxiang);
				if(getdaan[2]==null)//第三个答案为null  则只有两个答案
				{
					textview_daan.setText(getdaan[0]+"、 "+getdaan[1]);//这里得到的就是答案   如 A  B。。。
				}
				else if(getdaan[3]==null)
				{
					textview_daan.setText(getdaan[0]+"、 "+getdaan[1]+"、 "+getdaan[2]);//这里得到的就是答案   如 A  B。。。
				}
				else 
				{
					textview_daan.setText(getdaan[0]+"、 "+getdaan[1]+"、 "+getdaan[2]+"、 "+getdaan[3]);//这里得到的就是答案   如 A  B。。。
				}
				textview_jiexineirong.setText(DAjiexi);
				}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//对  上一题  的做题结果  进行判断
		public void jieguopanduan()
		{
			for(int i=0;i<4;i++)
			{
				if(datijieguo[shitidebianhao][i]==null)
				{
					continue;
				}
				else if(datijieguo[shitidebianhao][i].equals("yes"))
				{
					if(i==0)//A
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
						imageview_a.setImageDrawable(imageDrawable);
						textview_a.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
					}
					else if(i==1)
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
						imageview_b.setImageDrawable(imageDrawable);
						textview_b.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
					}
					else if(i==2)
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
						imageview_c.setImageDrawable(imageDrawable);
						textview_c.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
					}
					else 
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.zhengque); //图片在drawable目录下
						imageview_d.setImageDrawable(imageDrawable);
						textview_d.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
					}
				}
				else if(datijieguo[shitidebianhao][i].equals("no"))
				{
					if(i==0)//A
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
						imageview_a.setImageDrawable(imageDrawable);
						textview_a.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.red));//设置字体颜色
					}
					else if(i==1)
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
						imageview_b.setImageDrawable(imageDrawable);
						textview_b.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.red));//设置字体颜色
					}
					else if(i==2)
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
						imageview_c.setImageDrawable(imageDrawable);
						textview_c.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.red));//设置字体颜色
					}
					else 
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.cuowu); //图片在drawable目录下
						imageview_d.setImageDrawable(imageDrawable);
						textview_d.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.red));//设置字体颜色
					}
				}
				else if(datijieguo[shitidebianhao][i].equals("weida"))
				{
					if(i==0)//A
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.zqweida); //图片在drawable目录下
						imageview_a.setImageDrawable(imageDrawable);
						textview_a.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
					}
					else if(i==1)
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.zqweida); //图片在drawable目录下
						imageview_b.setImageDrawable(imageDrawable);
						textview_b.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
					}
					else if(i==2)
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.zqweida); //图片在drawable目录下
						imageview_c.setImageDrawable(imageDrawable);
						textview_c.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
					}
					else 
					{
						Resources resources = getBaseContext().getResources();   
						Drawable imageDrawable = resources.getDrawable(R.drawable.zqweida); //图片在drawable目录下
						imageview_d.setImageDrawable(imageDrawable);
						textview_d.setTextColor(duoxiangdatiActivity.this.getResources().getColor(R.color.blue));//设置字体颜色
					}
				}
			}
		}
	}
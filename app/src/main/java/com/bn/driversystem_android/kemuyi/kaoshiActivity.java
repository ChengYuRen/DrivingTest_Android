package com.bn.driversystem_android.kemuyi;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import com.bn.driversystem_android.MainActivity;
import com.bn.driversystem_android.R;
import com.bn.util.DBUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ClickableViewAccessibility")
public class kaoshiActivity extends Activity{//考试记录
	private TextView textview_jiexitimu,textview_biaoti_dati,textview_CTshanchu,textview_timu,textview_a,textview_b,textview_c,textview_d,textview_shijian,textview_jiexineirong,textview_daan,textview_jiaojuan;
	private LinearLayout linear_daanxuanxiang,linear_jiexi,dati_shoucang,linear_xuexi,linear_xiayiti,linear_shangyiti,linear_a,linear_b,linear_c,linear_d;
	private ImageView ImageView_querendati,imageview_timuleixing,imageview_a,imageview_b,imageview_c,imageview_d,imageview_datiimage;
	int danTMshuliang=1;//由于题目较少   这里单选题设置5个
	int duoTMshuliang=1;
	int panTMshuliang=1;
	String[] danTMid;//这里面存储的是 一会从数据库拿出来的  试题ID
	String[] duoTMid;
	String[] panTMid;
	int[] danSJ=new int[danTMshuliang];//存放随机数  用来随机选取试题ID
	int[] duoSJ=new int[duoTMshuliang];
	int[] panSJ=new int[panTMshuliang];
	static String[] queid;//存放试题ID
	String shitixinxi[][]=new String[1][5];
	String shitixuanxiang[][]=new String[4][];
	int shitidebianhao=1;//在界面中显示的试题的编号
	String userxuanxiang[][]=new String[danTMshuliang+duoTMshuliang+panTMshuliang][4];//这里存放的是用户点击后的选项
	int A_moyang=0; int B_moyang=0; int C_moyang=0; int D_moyang=0;//0为各个选项的本来模样   变为1时则变为点击状态
	String duoxianglinshi[]=new String[4];//完全是为了去凑确认答题 那个选项  要不也没必要
	int weidatiSL=0;//没有答题的数量
	static int fenshu;//这个是分数  一道题一分
	String DianJiKeMu;//这里分为科目一  与科目四
	public static String GET_SCORE="a";//得到分数
	public static int cankaoshijian=0;
	private int recLen = 1800;//总共一共1800s  考试时间
	 Timer timer = new Timer();
	    int shi=0;
	    int fen=0;
	    int miao=0;  //计时专用   时分秒
	    String DAjiexi=null;
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
						timer.cancel();
						kaoshiActivity.this.finish();
					}
					
				}
				);
				textview_jiexineirong=(TextView)findViewById(R.id.textview_jiexineirong);
				textview_daan=(TextView)findViewById(R.id.textview_daanxuanxiang);
				linear_a=(LinearLayout)findViewById(R.id.linear_a);
				linear_b=(LinearLayout)findViewById(R.id.linear_b);
				linear_c=(LinearLayout)findViewById(R.id.linear_c);
				linear_d=(LinearLayout)findViewById(R.id.linear_d);
				textview_shijian=(TextView)findViewById(R.id.textview_shijian);
				textview_shijian.setVisibility(View.VISIBLE);
				timer.schedule(task, 1000, 1000);       // 开始计时
				linear_xiayiti=(LinearLayout)findViewById(R.id.linear_xiayiti);
				linear_shangyiti=(LinearLayout)findViewById(R.id.linear_shangyiti);
				textview_jiexitimu=(TextView)findViewById(R.id.textview_jiexitimu);
				imageview_datiimage=(ImageView)findViewById(R.id.ImageView_datiimage);
				imageview_datiimage.setVisibility(View.GONE);
				textview_jiexitimu.setVisibility(View.GONE);//将答案解析题目  删除
				textview_jiaojuan=(TextView)findViewById(R.id.textview_jiaojuan);
				textview_jiaojuan.setVisibility(View.VISIBLE);
				linear_daanxuanxiang=(LinearLayout)findViewById(R.id.linear_daanxuanxiang);
				linear_daanxuanxiang.setVisibility(View.GONE);//答案选项删除
				linear_jiexi=(LinearLayout)findViewById(R.id.linear_jiexi);
				linear_jiexi.setVisibility(View.GONE);//解析删除
				dati_shoucang=(LinearLayout)findViewById(R.id.dati_shoucang);
				dati_shoucang.setVisibility(View.GONE);//删除收藏
				linear_xuexi=(LinearLayout)findViewById(R.id.linear_xuexi);
				linear_xuexi.setVisibility(View.GONE);//学习模式删除
				ImageView_querendati=(ImageView)findViewById(R.id.ImageView_querendati);
				ImageView_querendati.setVisibility(View.GONE);//现将确认答题删除
				textview_biaoti_dati=(TextView)findViewById(R.id.textview_biaoti_dati);
				textview_biaoti_dati.setText("模拟考试");
				imageview_timuleixing=(ImageView)findViewById(R.id.imageview_timuleixing);
				imageview_timuleixing.setImageResource(R.drawable.dan);//由于一开始是单选  这里先显示单选模式图片
				textview_CTshanchu=(TextView)findViewById(R.id.textview_CTshanchu);
				textview_CTshanchu.setVisibility(View.GONE);//最下面的删除试题  删除
				imageview_a=(ImageView)findViewById(R.id.ImageView_a);
				imageview_b=(ImageView)findViewById(R.id.ImageView_b);
				imageview_c=(ImageView)findViewById(R.id.ImageView_c);
				imageview_d=(ImageView)findViewById(R.id.ImageView_d);
				imageview_a.setImageResource(R.drawable.axuanxiang);
				imageview_b.setImageResource(R.drawable.bxuanxiang);
				imageview_c.setImageResource(R.drawable.cxuanxiang);
				imageview_d.setImageResource(R.drawable.dxuanxiang);//a b c d选项图片设置完成
				textview_a=(TextView)findViewById(R.id.TextView_a);
				textview_b=(TextView)findViewById(R.id.TextView_b);
				textview_c=(TextView)findViewById(R.id.TextView_c);
				textview_d=(TextView)findViewById(R.id.TextView_d);
				//判断属于哪个科目
				DianJiKeMu=MainActivity.DianJiKeMu;
				if(MainActivity.kaoshijilu==0)
				{
					
				
				String danSUM=DBUtil.getFLCount("单项选择题",MainActivity.DianJiKeMu,MainActivity.XuanZeCheXing);//得到单项选择题试题 总数
				danTMid=new String[Integer.parseInt(danSUM)];
				String duoSUM=DBUtil.getFLCount("多项选择题",MainActivity.DianJiKeMu,MainActivity.XuanZeCheXing);
				duoTMid=new String[Integer.parseInt(duoSUM)];
				String panSUM=DBUtil.getFLCount("判断题",MainActivity.DianJiKeMu,MainActivity.XuanZeCheXing);
				panTMid=new String[Integer.parseInt(panSUM)];
				//Toast.makeText(kaoshiActivity.this, danTMid.length+"-"+duoTMid.length+"-"+panTMid.length, Toast.LENGTH_SHORT).show();
				danTMid=DBUtil.getQuestionIdByFL("单项选择题",MainActivity.DianJiKeMu,MainActivity.XuanZeCheXing);//得到单项选择题的试题ID
				duoTMid=DBUtil.getQuestionIdByFL("多项选择题",MainActivity.DianJiKeMu,MainActivity.XuanZeCheXing);
				panTMid=DBUtil.getQuestionIdByFL("判断题",MainActivity.DianJiKeMu,MainActivity.XuanZeCheXing);
				danSJ=genNum(danTMshuliang,danTMid.length);//得到各个随机ID
				duoSJ=genNum(duoTMshuliang,duoTMid.length);
				panSJ=genNum(panTMshuliang,panTMid.length);
				int queCD=danTMshuliang+duoTMshuliang+panTMshuliang;
				queid=new String[queCD];//定义试题ID长度
				for(int i=0;i<queCD;i++)//循环产出  试题ID
				{
					if(i<danTMshuliang)
					{
						queid[i]=danTMid[danSJ[i]-1];
					}
					else if(i<danTMshuliang+panTMshuliang)
					{
						queid[i]=panTMid[panSJ[i-danTMshuliang]-1];
					}
					else
					{
						queid[i]=duoTMid[duoSJ[i-danTMshuliang-panTMshuliang]-1];
					}
				}
				}
				else if(MainActivity.kaoshijilu==2)
				{
					textview_shijian.setVisibility(View.GONE);
					textview_jiaojuan.setVisibility(View.GONE);
					queid=DBUtil.getSTidFromKSByks_id(kaoshijiluActivity.KSID);
					//先不让点击选项
					textview_a.setEnabled(false);
					textview_b.setEnabled(false);
					textview_c.setEnabled(false);
					textview_d.setEnabled(false);
					ImageView_querendati.setEnabled(false);
					textview_jiexitimu.setVisibility(View.VISIBLE);
					linear_jiexi.setVisibility(View.VISIBLE);
					linear_daanxuanxiang.setVisibility(View.VISIBLE);
					textview_biaoti_dati.setText("考试记录");
				}
				TMshengcheng(queid[shitidebianhao-1]);//生成题目
				panduanxuanxiang();//判断选项
				tianjiaimage(queid[shitidebianhao-1]);//添加图片
				leixingpanduan();//判断试题类型
				textview_jiexineirong.setText(DAjiexi);
				String getdaan[]=DBUtil.getAnswerzhengqueeByid(queid[shitidebianhao-1]);
				StringBuilder s=new StringBuilder();
				for(int m=0;m<4;m++)
				{
					if(getdaan[m]!=null)
					{
						s.append(getdaan[m]);
					}
				}
				textview_daan.setText(s.toString());
				textview_a.setOnTouchListener(new View.OnTouchListener() {
					
					@SuppressLint("ClickableViewAccessibility")
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN)
						{
							linear_a.setBackgroundColor(kaoshiActivity.this.getResources().getColor(R.color.yellow));
						}
						else if(event.getAction()==MotionEvent.ACTION_UP)
						{
							linear_a.setBackgroundColor(kaoshiActivity.this.getResources().getColor(R.color.white));
							if(!shitixinxi[0][3].equals("多项选择题"))
							{
								huifuyuanyang();//恢复原样
								imageview_a.setImageResource(R.drawable.zhengque);//点击A 图片改变  颜色改变
								textview_a.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
								userxuanxiang[shitidebianhao-1][0]="A";
								linear_xiayiti.performClick();
							}
							else//现在是多选题  则设置标志位  点击变色  以及再次点击变回原来模样  
							{
								if(A_moyang==0)//现在是原样     点击以后会变为  1
								{
									imageview_a.setImageResource(R.drawable.zhengque);
									textview_a.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));
									duoxianglinshi[0]="A";
									A_moyang=1;
								}
								else if(A_moyang==1)
								{
									imageview_a.setImageResource(R.drawable.axuanxiang);
									textview_a.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.black));
									duoxianglinshi[0]=null;
									A_moyang=0;
								}
							}
						}
						return true;
					}
				});
				textview_b.setOnTouchListener(new View.OnTouchListener() {
					
					@SuppressLint("ClickableViewAccessibility")
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN)
						{
							linear_b.setBackgroundColor(kaoshiActivity.this.getResources().getColor(R.color.yellow));
						}
						else if(event.getAction()==MotionEvent.ACTION_UP)
						{
							linear_b.setBackgroundColor(kaoshiActivity.this.getResources().getColor(R.color.white));
							if(!shitixinxi[0][3].equals("多项选择题"))
							{
								huifuyuanyang();//恢复原样
								imageview_b.setImageResource(R.drawable.zhengque);//点击A 图片改变  颜色改变
								textview_b.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
								userxuanxiang[shitidebianhao-1][0]="B";
								linear_xiayiti.performClick();
							}
							else//现在是多选题  则设置标志位  点击变色  以及再次点击变回原来模样  
							{
								if(B_moyang==0)//现在是原样     点击以后会变为  1
								{
									imageview_b.setImageResource(R.drawable.zhengque);
									textview_b.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));
									duoxianglinshi[1]="B";
									B_moyang=1;
								}
								else if(B_moyang==1)
								{
									imageview_b.setImageResource(R.drawable.axuanxiang);
									textview_b.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.black));
									duoxianglinshi[1]=null;
									B_moyang=0;
								}
							}
						}
						return true;
					}
				});
textview_c.setOnTouchListener(new View.OnTouchListener() {
					
					@SuppressLint("ClickableViewAccessibility")
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==MotionEvent.ACTION_DOWN)
						{
							linear_c.setBackgroundColor(kaoshiActivity.this.getResources().getColor(R.color.yellow));
						}
						else if(event.getAction()==MotionEvent.ACTION_UP)
						{
							linear_c.setBackgroundColor(kaoshiActivity.this.getResources().getColor(R.color.white));
							if(!shitixinxi[0][3].equals("多项选择题"))
							{
								huifuyuanyang();//恢复原样
								imageview_c.setImageResource(R.drawable.zhengque);//点击A 图片改变  颜色改变
								textview_c.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
								userxuanxiang[shitidebianhao-1][0]="C";
								linear_xiayiti.performClick();
							}
							else//现在是多选题  则设置标志位  点击变色  以及再次点击变回原来模样  
							{
								if(C_moyang==0)//现在是原样     点击以后会变为  1
								{
									imageview_c.setImageResource(R.drawable.zhengque);
									textview_c.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));
									duoxianglinshi[2]="C";
									C_moyang=1;
								}
								else if(C_moyang==1)
								{
									imageview_c.setImageResource(R.drawable.axuanxiang);
									textview_c.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.black));
									duoxianglinshi[2]=null;
									C_moyang=0;
								}
							}
						}
						return true;
					}
				});
textview_d.setOnTouchListener(new View.OnTouchListener() {
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_DOWN)
		{
			linear_d.setBackgroundColor(kaoshiActivity.this.getResources().getColor(R.color.yellow));
		}
		else if(event.getAction()==MotionEvent.ACTION_UP)
		{
			linear_d.setBackgroundColor(kaoshiActivity.this.getResources().getColor(R.color.white));
			if(!shitixinxi[0][3].equals("多项选择题"))
			{
				huifuyuanyang();//恢复原样
				imageview_d.setImageResource(R.drawable.zhengque);//点击A 图片改变  颜色改变
				textview_d.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
				userxuanxiang[shitidebianhao-1][0]="D";
				linear_xiayiti.performClick();
			}
			else//现在是多选题  则设置标志位  点击变色  以及再次点击变回原来模样  
			{
				if(D_moyang==0)//现在是原样     点击以后会变为  1
				{
					imageview_d.setImageResource(R.drawable.zhengque);
					textview_d.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));
					duoxianglinshi[3]="D";
					D_moyang=1;
				}
				else if(D_moyang==1)
				{
					imageview_d.setImageResource(R.drawable.axuanxiang);
					textview_d.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.black));
					duoxianglinshi[3]=null;
					D_moyang=0;
				}
			}
		}
		return true;
	}
});
ImageView_querendati.setOnTouchListener(new View.OnTouchListener() {
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_DOWN)
		{
			ImageView_querendati.setImageResource(R.drawable.zhengque);
		}
		else if(event.getAction()==MotionEvent.ACTION_UP)
		{
			ImageView_querendati.setImageResource(R.drawable.querendati);
			for(int i=0;i<4;i++)
			{
				userxuanxiang[shitidebianhao-1][i]=duoxianglinshi[i];
			}
			linear_xiayiti.performClick();
		}
		return true;
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
							Toast.makeText(kaoshiActivity.this, "这是第一道题！！！", Toast.LENGTH_SHORT).show();
							return;
						}
						huifuyuanyang();//恢复原样
						DXlinshiqingkong();
						A_moyang=0; B_moyang=0; C_moyang=0; D_moyang=0;//0为各个选项的本来模样   变为1时则变为点击状态
						TMshengcheng(queid[shitidebianhao-1]);
						panduanxuanxiang();
						tianjiaimage(queid[shitidebianhao-1]);
						leixingpanduan();//判断试题类型
						//下面对点击上一题  看是否是答过题  shangyiti 结果userxuanxiang[shitidebianhao-1][];
						datiqingkuang(userxuanxiang[shitidebianhao-1]);
						textview_jiexineirong.setText(DAjiexi);
						String getdaan[]=DBUtil.getAnswerzhengqueeByid(queid[shitidebianhao-1]);
						StringBuilder s=new StringBuilder();
						for(int m=0;m<4;m++)
						{
							if(getdaan[m]!=null)
							{
								s.append(getdaan[m]);
							}
						}
						textview_daan.setText(s.toString());
					}
				});
				linear_xiayiti.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						shitidebianhao++;//点击下一题  试题编号  ++
						if(shitidebianhao>queid.length)
						{
							shitidebianhao--;
							Toast.makeText(kaoshiActivity.this, "这是最后一道题！！！", Toast.LENGTH_SHORT).show();
							return;
						}
						huifuyuanyang();//恢复原样
						DXlinshiqingkong();
						A_moyang=0; B_moyang=0; C_moyang=0; D_moyang=0;//0为各个选项的本来模样   变为1时则变为点击状态
						TMshengcheng(queid[shitidebianhao-1]);
						panduanxuanxiang();
						tianjiaimage(queid[shitidebianhao-1]);
						leixingpanduan();//判断试题类型
						datiqingkuang(userxuanxiang[shitidebianhao-1]);
						textview_jiexineirong.setText(DAjiexi);
						String getdaan[]=DBUtil.getAnswerzhengqueeByid(queid[shitidebianhao-1]);
						StringBuilder s=new StringBuilder();
						for(int m=0;m<4;m++)
						{
							if(getdaan[m]!=null)
							{
								s.append(getdaan[m]);
							}
						}
						textview_daan.setText(s.toString());
					}
				});
				textview_jiaojuan.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						weidatiSL=0;
						for(int i=0;i<queid.length;i++)
						{
							if(i<danTMshuliang+panTMshuliang)
							{
								if(userxuanxiang[i][0]==null)
								{
									weidatiSL++;//一旦有空答案  就++
								}
							}
							else //现在对多选题进行判断
							{
								if(userxuanxiang[i][0]==null&&userxuanxiang[i][1]==null&&userxuanxiang[i][2]==null&&userxuanxiang[i][3]==null)
								{
									weidatiSL++;//一旦有空答案  就++
								}
							}
						}
						showNormalDia(weidatiSL);
					}
				});
				
				
				
				
				
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
//对含有图片的试题添加图片
		public void tianjiaimage(String shitiID)
		{
			imageview_datiimage.setVisibility(View.GONE);
			if(!shitixinxi[0][2].equals("空"))
			{
				imageview_datiimage.setVisibility(View.VISIBLE);
			
			String imageid=shitiID;
			String fileName =Environment.getExternalStorageDirectory().toString()+"/Drive/Image/"+imageid+".png";
			try {
				Bitmap bm = BitmapFactory.decodeFile(fileName);
				imageview_datiimage.setImageBitmap(bm);
			} catch (Exception e) {}
			}
		}
//这里先写   根据试题类型更换 类型图片 并显示 提交答案图片
	public void leixingpanduan()
	{
		ImageView_querendati.setVisibility(View.GONE);
		if(shitixinxi[0][3].equals("判断题"))
		{
			ImageView_querendati.setVisibility(View.GONE);
			imageview_timuleixing.setImageResource(R.drawable.pan);//类型图片更换为判断
		}
		else if(shitixinxi[0][3].equals("单项选择题"))
		{
			ImageView_querendati.setVisibility(View.GONE);
			imageview_timuleixing.setImageResource(R.drawable.dan);//类型图片更换为判断
		}
		else
		{
			ImageView_querendati.setVisibility(View.VISIBLE);
			imageview_timuleixing.setImageResource(R.drawable.duo);//类型图片更换为判断
		}
	}
//事件回复原样
	public void huifuyuanyang()
	{
		//首先  各个字体变为原来颜色
		textview_a.setTextColor(this.getResources().getColor(R.color.black));//设置字体颜色
		textview_b.setTextColor(this.getResources().getColor(R.color.black));//设置字体颜色
		textview_c.setTextColor(this.getResources().getColor(R.color.black));//设置字体颜色
		textview_d.setTextColor(this.getResources().getColor(R.color.black));//设置字体颜色
		//将选项图片设置原样
		imageview_a.setImageResource(R.drawable.axuanxiang);
		imageview_b.setImageResource(R.drawable.bxuanxiang);
		imageview_c.setImageResource(R.drawable.cxuanxiang);
		imageview_d.setImageResource(R.drawable.dxuanxiang);//a b c d选项图片设置完成
	}
//判断答题情况
	public void datiqingkuang(String qingkuang[])
	{
		if(!shitixinxi[0][3].equals("多项选择题"))
		{
			String QK=qingkuang[0];
			if(QK==null)
			{
				return;
			}
			else if(QK.equals("A"))
			{
				imageview_a.setImageResource(R.drawable.zhengque);//点击A 图片改变  颜色改变
				textview_a.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
			}
			else if(QK.equals("B"))
			{
				imageview_b.setImageResource(R.drawable.zhengque);//点击A 图片改变  颜色改变
				textview_b.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
			}
			else if(QK.equals("C"))
			{
				imageview_c.setImageResource(R.drawable.zhengque);//点击A 图片改变  颜色改变
				textview_c.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
			}
			else if(QK.equals("D"))
			{
				imageview_d.setImageResource(R.drawable.zhengque);//点击A 图片改变  颜色改变
				textview_d.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));//设置字体颜色
			}
		}
		else//对多项选择题进行判别
		{//越写越垃圾  就这么找吧  循环判定 不要有null
			if(qingkuang[0]==null)
			{
				
			}
			else if(qingkuang[0].equals("A"))
			{
				imageview_a.setImageResource(R.drawable.zhengque);
				textview_a.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));
				A_moyang=1;
			}
			if(qingkuang[1]==null)
			{
				
			}
			else if(qingkuang[1].equals("B"))
			{
				imageview_b.setImageResource(R.drawable.zhengque);
				textview_b.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));
				B_moyang=1;
			}
			if(qingkuang[2]==null)
			{
				
			}
			else if(qingkuang[2].equals("C"))
			{
				imageview_c.setImageResource(R.drawable.zhengque);
				textview_c.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));
				C_moyang=1;
			}
			if(qingkuang[3]==null)
			{
				
			}
			else if(qingkuang[3].equals("D"))
			{
				imageview_d.setImageResource(R.drawable.zhengque);
				textview_d.setTextColor(kaoshiActivity.this.getResources().getColor(R.color.green));
				D_moyang=1;
			}
			
		}
	}
//将临时多项数组  cunru已选择答案
	public void DXlinshiqingkong()
	{
		for(int i=0;i<4;i++)
		{
			duoxianglinshi[i]=userxuanxiang[shitidebianhao-1][i];
		}
	}
//写 交卷对话框
	private void showNormalDia(int shuliang)  
    {  
        //AlertDialog.Builder normalDialog=new AlertDialog.Builder(getApplicationContext());  
        AlertDialog.Builder normalDia=new AlertDialog.Builder(kaoshiActivity.this);  
        normalDia.setIcon(R.drawable.ic_launcher);  
        normalDia.setTitle("交卷");
        if(shuliang==0)
        {
        	normalDia.setMessage("答题完成,确定交卷吗?"); 
        }
        else
        {
        	normalDia.setMessage("您还有还有"+shuliang+"个题目未解答,确定交卷吗?");  
        }
        normalDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                // TODO Auto-generated method stub
            	GET_SCORE=panduandaan()+"";
            	Intent intent=new Intent();
				intent.setClass(kaoshiActivity.this, KaoShiJieGuo_KeYi.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
				startActivity(intent);
            	//点击交卷  将本次考试  insert  kaoshijilu
            	String ksid=DBUtil.getKSJLmaxid();
            	int int_sc_id=Integer.parseInt(ksid);
	    		int_sc_id++;
	    		ksid=fangfa.zhuanhuanSTid(int_sc_id);//转换成  00000  得到ks_id,试题ID  在queid ,科目MainActivity.DianJiKeMu  分数a
	    		String shijian=fangfa.huoqushijian();
	    		for(int i=0;i<queid.length;i++)
	    		{
	    			DBUtil.insertKSJL(ksid, queid[i], MainActivity.DianJiKeMu, shijian, GET_SCORE);
	    		}
	    		 timer.cancel();
	    		
				kaoshiActivity.this.finish();
            }  
        });  
        normalDia.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                // TODO Auto-generated method stub  
            	//Toast.makeText(kaoshiActivity.this,"fdsfadsfadsf", Toast.LENGTH_SHORT).show();
            	dialog.dismiss();  
            }  
        });  
        normalDia.create().show();  
    }
//对提交的答案进行判断
	public int panduandaan()
	{
		fenshu=0;
		for(int i=0;i<queid.length;i++)
		{
			if(i<danTMshuliang+panTMshuliang)
			{//对单选和判断题进行答案判断
				String daan[]=DBUtil.getAnswerzhengqueeByid(queid[i]);
				if(userxuanxiang[i][0]==null)
				{
					continue;
				}
				else if(userxuanxiang[i][0].equals(daan[0]))
				{
					fenshu++;
				}
			}
			else
			{//现在是多项选择题进行判断
				String daan2[]=DBUtil.getAnswerzhengqueeByid(queid[i]);
				int biaozhiDXpanduan=0;
				for(int j=0;j<4;j++)
				{
					if(daan2[j]==null)
					{
						continue;
					}
					else if(daan2[j].equals("A"))
					{//如果答案有A
						if(userxuanxiang[i][0]==null)
						{//答案是A  但是你的0  为空则出错  (0位是A 的存储位置)
							biaozhiDXpanduan=1;
						}
					}
					else if(daan2[j].equals("B"))
					{
						if(userxuanxiang[i][1]==null)
						{
							biaozhiDXpanduan=1;
						}
					}
					else if(daan2[j].equals("C"))
					{
						if(userxuanxiang[i][2]==null)
						{
							biaozhiDXpanduan=1;
						}
					}
					else if(daan2[j].equals("D"))
					{
						if(userxuanxiang[i][3]==null)
						{
							biaozhiDXpanduan=1;
						}
					}
				}
				if(biaozhiDXpanduan==0)
				{//当为0  就说明作对了
					fenshu++;
				}
				else if(biaozhiDXpanduan==1&&userxuanxiang[i][0]==null&&userxuanxiang[i][1]==null&&userxuanxiang[i][2]==null&&userxuanxiang[i][3]==null)
				{
					
				}
			}
		}
		return fenshu;
	}
	TimerTask task = new TimerTask() {  
        @Override  
        public void run() {  
  
            runOnUiThread(new Runnable() {      // UI thread   
                @Override  
                public void run() {  
                    recLen--;
                    cankaoshijian++;
                    shi=(int)(recLen/60/60);//描述  切换成小时
                    fen=(int)(recLen/60%60);
                    miao=(int)(recLen%60);
                    textview_shijian.setText(shi+":"+fen+":"+miao);  
                    if(recLen == 1780){  
                        Toast.makeText(kaoshiActivity.this, "考试时间都过了20s  还不交卷", Toast.LENGTH_SHORT).show(); 
                    }
                    else if(recLen==0)
                    {
                    	 timer.cancel();
                    	 Toast.makeText(kaoshiActivity.this, "时间到了  自动交卷", Toast.LENGTH_SHORT).show();
                    	 GET_SCORE=panduandaan()+"";
                     	Intent intent=new Intent();
         				intent.setClass(kaoshiActivity.this, KaoShiJieGuo_KeYi.class);
         				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
         				startActivity(intent);
                     	//点击交卷  将本次考试  insert  kaoshijilu
                     	String ksid=DBUtil.getKSJLmaxid();
                     	int int_sc_id=Integer.parseInt(ksid);
         	    		int_sc_id++;
         	    		ksid=fangfa.zhuanhuanSTid(int_sc_id);//转换成  00000  得到ks_id,试题ID  在queid ,科目MainActivity.DianJiKeMu  分数a
         	    		String shijian=fangfa.huoqushijian();
         	    		for(int i=0;i<queid.length;i++)
         	    		{
         	    			DBUtil.insertKSJL(ksid, queid[i], MainActivity.DianJiKeMu, shijian, GET_SCORE);
         	    		}
         				kaoshiActivity.this.finish();
                    }
                }  
            });  
        }  
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    
    {
	 	if(keyCode == KeyEvent.KEYCODE_BACK)
	 	{
	 		 timer.cancel();
	 		kaoshiActivity.this.finish();
	 	}
		return false;
	 
    }
}
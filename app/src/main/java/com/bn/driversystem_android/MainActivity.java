 package com.bn.driversystem_android;

import com.bn.Begin.ChooseCar;
import com.bn.Begin.ChooseCar_Begin;
import com.bn.Begin.CustomProgressDialog;
import com.bn.Begin.GuanYu_WoMen;
import com.bn.Begin.Login_Begin_PerSon;
import com.bn.Begin.PerSon_Home;
import com.bn.School.sortlistview.SchoolList;
import com.bn.fyq.Constant.Constant;
import com.bn.util.DBUtil;
import com.bn.util.Exit;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
@SuppressWarnings("deprecation")
public class MainActivity extends ActivityGroup {

	
	 // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
	@SuppressWarnings("unused")
	private Handler handler = new Handler(); 
	//Tab Activity Layout
	private LocalActivityManager localActivityManager = null;
    private LinearLayout mainTabContainer = null;
    private Intent mainTabIntent = null;
    public static int  LXmoshi;//各个练习的模式  0为顺序练习  1章节  2分类  3随机  4 收藏
    public static String   DianJiKeMu;//选中的  属于哪个科目
    public static int  kaoshijilu=0;//0为 考试状态  1为科目一考试记录   2为科目四  考试记录
    public static String XuanZeCheXing="01";//这里先写成小车
    
    
    private LinearLayout kemu1_xiamianLinear_kemu2;
    private LinearLayout kemu1_xiamianLinear_kemu4;
 
    //Tab banner title
    private TextView mainTabTitleTextView = null;
    //Tab ImageView
    private ImageView kemuyiImageView = null;
    private ImageView kemuerImageView = null;
    private ImageView kemusiImageView = null;
    private ImageView cheyouquanImageView = null;
    
    private SlidingMenu mMenu;  //滑动界面按钮（一下属于滑动界面 ）
    
    private LinearLayout linearLayout_TiKu;//题库按钮
    private TextView textView_TiKu,textView_XiaoChe;
    
    private LinearLayout linearLayout_JiaXiao;//驾校
    private TextView textView_JiaXiao,textView_WeiBaoKao;
    
    private LinearLayout linearLayout_GuanYu;//关于 
    private TextView textView_guanyu_us;
    
    
    private LinearLayout linearlayout_Login_Head;//登录框
    
    public TextView upDateTi;//滑动界面 车型试题更新框
    
    
//  //创建监听 用户头像 
//  	OnClickListener clickListenerTouXiang = new OnClickListener(){
//  		@Override
//  		public void onClick(View v) {
//  		if (v.getId()==R.id.cheyouquan_head)
//  		{//按钮1
//  			//showDialog();
//  			Intent intent=new Intent();
//  			intent.setClass(MainActivity.this, PerSon_Home.class);
//  			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
//  			startActivity(intent);
//  		}
//
//  	}
//  	};
    
	//创建BUtoon监听 按题库按钮进行选择题库
	OnClickListener clickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
		if ((v.getId()==R.id.linearlaout_tiku)||(v.getId()==R.id.textview_tiku)
				||(v.getId()==R.id.textview_xiaoche)){//按钮1
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, ChooseCar.class);
			startActivity(intent);
		}

	}
	};
	
	//创建BUtoon监听 按驾校选驾校
		OnClickListener clickListenerSchool = new OnClickListener(){
			@Override
			public void onClick(View v) {
			if ((v.getId()==R.id.linearlaout_jiaxiao)||(v.getId()==R.id.textview_jiaxiao)
					||(v.getId()==R.id.textview_weibaokao)){//按钮1
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SchoolList.class);
				startActivity(intent);
			}

		}
		};
		
		//创建BUtoon监听 关于我们
		OnClickListener clickListenerGuanYu = new OnClickListener(){
			@Override
			public void onClick(View v) {
			if ((v.getId()==R.id.linearlaout_guanyu)||(v.getId()==R.id.textview_guanyu)
			){//按钮1
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, GuanYu_WoMen.class);
				startActivity(intent);
			}

		}
		};
    
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消顶部Title
        String chexingzanshi=DBUtil.getCar();
        if(chexingzanshi.equals("小车"))
        {
        	XuanZeCheXing="01";
        }
        else if(chexingzanshi.equals("货车"))
        {
        	XuanZeCheXing="02";
        }
        else if(chexingzanshi.equals("客车"))
        {
        	XuanZeCheXing="03";
        }
        else  if(chexingzanshi.equals("摩托车"))
        {
        	XuanZeCheXing="04";
        }
        else if(chexingzanshi.equals("客运"))
        {
        	XuanZeCheXing="05";
        }
        else if(chexingzanshi.equals("货运"))
        {
        	XuanZeCheXing="06";
        }
        else if(chexingzanshi.equals("危险品"))
        {
        	XuanZeCheXing="07";
        }
        else if(chexingzanshi.equals("教练"))
        {
        	XuanZeCheXing="08";
        }
        if(SplashActivity.HOME_GUIDE==1)
        {
        	Intent goCar=new Intent();
        	goCar.setClass(MainActivity.this, ChooseCar_Begin.class);
        	startActivity(goCar);
        	//return;
        }
        else
        {
        	setContentView(R.layout.activity_main);
        }
       	
       	
        
        
        //!!!!!!!!!!!!!!!注意！！！！！！！！！当选了指定车型后 车型（题库）的text要跟着改变
        
        
      //滑动界面 
        mMenu = (SlidingMenu) findViewById(R.id.id_menu);
        //findview 注册监听  题库监听
        linearLayout_TiKu=(LinearLayout)findViewById(R.id.linearlaout_tiku);
        textView_TiKu=(TextView)findViewById(R.id.textview_tiku);
        textView_XiaoChe=(TextView)findViewById(R.id.textview_xiaoche);
        
        //如果从最开始选车界面进入 
        if(ChooseCar_Begin.ChooseCar_Begin==1)
        {
        	textView_XiaoChe.setText(DBUtil.getCar());
        	ChooseCar_Begin.ChooseCar_Begin=0;
        }
        else //若从滑动界面进入 
        {
        	//如果有储存的话 则进入时设置成储存的值
//        	if(ChooseCar.CunCuOkOrNot)
//        	{
//        		//textView_XiaoChe.setText(ChooseCar.GetCarTypeByCunCu);
        		textView_XiaoChe.setText(DBUtil.getCar().toString());
//        	}
//        	Bundle bundleThis = this.getIntent().getExtras(); 
//            if(ChooseCar.ChooseCar==1&&ChooseCar.Car_Down==1)
//            {
//            	String name = bundleThis.getString("Car");  
//            	textView_XiaoChe.setText(name);
//            	ChooseCar.Car_Down=0;
//            }
//            else if(ChooseCar.ChooseCar==1&&ChooseCar.Huo_Down==1)
//            {
//            	String name = bundleThis.getString("Huo");  
//            	textView_XiaoChe.setText(name);
//            	ChooseCar.Huo_Down=0;
//            }
//            else if(ChooseCar.ChooseCar==1&&ChooseCar.Ke_Down==1)
//            {
//            	String name = bundleThis.getString("Ke");  
//            	textView_XiaoChe.setText(name);
//            	ChooseCar.Ke_Down=0;
//            }
//            else if(ChooseCar.ChooseCar==1&&ChooseCar.Mo_Down==1)
//            {
//            	String name = bundleThis.getString("Mo");  
//            	textView_XiaoChe.setText(name);
//            	ChooseCar.Mo_Down=0;
//            }
//            else if(ChooseCar.ChooseCar==1&&ChooseCar.KeYun_Down==1)
//            {
//            	String name = bundleThis.getString("KeYun");  
//            	textView_XiaoChe.setText(name);
//            	ChooseCar.KeYun_Down=0;
//            }
//            else if(ChooseCar.ChooseCar==1&&ChooseCar.HuoYun_Down==1)
//            {
//            	String name = bundleThis.getString("HuoYun");  
//            	textView_XiaoChe.setText(name);
//            	ChooseCar.HuoYun_Down=0;
//            }
//            else if(ChooseCar.ChooseCar==1&&ChooseCar.Wei_Down==1)
//            {
//            	String name = bundleThis.getString("Wei");  
//            	textView_XiaoChe.setText(name);
//            	ChooseCar.Wei_Down=0;
//            }
//            else if(ChooseCar.ChooseCar==1&&ChooseCar.Jiao_Down==1)
//            {
//            	String name = bundleThis.getString("Jiao");  
//            	textView_XiaoChe.setText(name);
//            	ChooseCar.Jiao_Down=0;
//            }
          	
        }
        
        
     	
        

        linearLayout_TiKu.setOnClickListener(clickListener);
        textView_TiKu.setOnClickListener(clickListener);
        textView_XiaoChe.setOnClickListener(clickListener);
        //=================
        
        //驾校监听
        linearLayout_JiaXiao=(LinearLayout)findViewById(R.id.linearlaout_jiaxiao);
        textView_JiaXiao=(TextView)findViewById(R.id.textview_jiaxiao);
        textView_WeiBaoKao=(TextView)findViewById(R.id.textview_weibaokao);
        
        if(SchoolList.SCHOOLLIST_OK==0)
        {
        	//如果存储成功 再次进入应用时设置 驾校值为存储值 
        	if(SchoolList.CunCuOkOrNot_School)
        	{
        		textView_WeiBaoKao.setText(SchoolList.GetSchoolListByCunCu);
        	}
        }
        else
        {
        	
        	textView_WeiBaoKao.setText(SchoolList.GET_SCHOOLLIST);
        	SchoolList.SCHOOLLIST_OK=0;
        }
        	
        
        linearLayout_JiaXiao.setOnClickListener(clickListenerSchool);
        textView_JiaXiao.setOnClickListener(clickListenerSchool);
        textView_WeiBaoKao.setOnClickListener(clickListenerSchool);
        //=============================
        
        //关于监听
        linearLayout_GuanYu=(LinearLayout)findViewById(R.id.linearlaout_guanyu);
        textView_guanyu_us=(TextView)findViewById(R.id.textview_guanyu);
        
        linearLayout_GuanYu.setOnClickListener(clickListenerGuanYu);
        textView_guanyu_us.setOnClickListener(clickListenerGuanYu);
        
        //滑动界面中  登录按钮监听
        linearlayout_Login_Head=(LinearLayout)findViewById(R.id.linearlayout_login_head);
        linearlayout_Login_Head.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//调试用=============直接进入个人界面 
			//============================================================
			//============================================================
			//============================================================
			//intent.setClass(MainActivity.this, PerSon_Home.class);
			//============================================================
			//============================================================
			if(Constant.LOGIN_OK==0)
			{
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, Login_Begin_PerSon.class);
				startActivity(intent);		
			}
			else
			{
				//设置题库 等待3s后
				final CustomProgressDialog dialog =new CustomProgressDialog(MainActivity.this, "正在读取个人信息，请稍后...",R.anim.frame);
				
				//计时后结束 并跳转
				Handler handler = new Handler(); 
		        handler.postDelayed(new Runnable() { 

		            public void run() { 

		            	Intent intent=new Intent();
						intent.setClass(MainActivity.this, PerSon_Home.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
						startActivity(intent);
						ImageView imageview_denglu_mao=(ImageView)findViewById(R.id.imageview_denglu_mao);
						//如果头像更新 则在下次登录时生效  即重启软件生效
						imageview_denglu_mao.setImageBitmap(PerSon_Home.bm_get);
						//imageview_denglu_mao.setOnClickListener(clickListenerTouXiang);	
						
						dialog.cancel();//过2秒让框消失
						//MainActivity.this.finish();
		            }
		        },2000);
		        dialog.show();
		        
				  
				 
			}		
		}
		});
        
        Exit.getInstance().addActivities(this);       //调用退出方法
        mainTabContainer = (LinearLayout)findViewById(R.id.main_tab_container);
        localActivityManager = getLocalActivityManager();
        

        setContainerView("kemuyi", KeMuyiActivity.class);
             
        initTab();
         
    }
 
    //滑动按钮的点击监听 
    public void toggleMenu(View view)
	{
		mMenu.toggle();
	}
     
    /**
     * 初始化Tab项
     */
    private void initTab() {
        mainTabTitleTextView = (TextView)findViewById(R.id.main_tab_banner_title);
        kemuyiImageView = (ImageView)findViewById(R.id.kemuyi);
        kemuerImageView = (ImageView)findViewById(R.id.kemuer);
        kemusiImageView = (ImageView)findViewById(R.id.kemusi);
        cheyouquanImageView = (ImageView)findViewById(R.id.cheyouquan);
        kemu1_xiamianLinear_kemu2=(LinearLayout)findViewById(R.id.kemu1_xiamianLinear_kemu2);
        kemu1_xiamianLinear_kemu4=(LinearLayout)findViewById(R.id.kemu1_xiamianLinear_kemu4);
        Typeface customFont = Typeface.createFromAsset(this.getAssets(), "fonts/newfont.ttf");
        
        if(Integer.parseInt(XuanZeCheXing)==4)
        {
        	kemu1_xiamianLinear_kemu2.setVisibility(View.INVISIBLE);
        }
        else if(Integer.parseInt(XuanZeCheXing)>4)
        {
        	kemu1_xiamianLinear_kemu2.setVisibility(View.INVISIBLE);
        	kemu1_xiamianLinear_kemu4.setVisibility(View.INVISIBLE);
        }

        
        mainTabTitleTextView.setTypeface(customFont);
         
        //科目一
        kemuyiImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mainTabTitleTextView.setText("科目一：理论考试");
                setContainerView("kemuyi", KeMuyiActivity.class);
                kemuyiImageView.setImageResource(R.drawable.ico_img1_p);
                kemuerImageView.setImageResource(R.drawable.ico_img2);
                kemusiImageView.setImageResource(R.drawable.ico_img3);
                cheyouquanImageView.setImageResource(R.drawable.ico_img5);
            }
        });
         
        //科目二三
        kemuerImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTabTitleTextView.setText("科目二/三：大小路考");
                setContainerView("kemuer", KeMuerActivity.class);
                kemuyiImageView.setImageResource(R.drawable.ico_img1);
                kemuerImageView.setImageResource(R.drawable.ico_img2_p);
                kemusiImageView.setImageResource(R.drawable.ico_img3);
                cheyouquanImageView.setImageResource(R.drawable.ico_img5);
       
            }
        });
         
        //科目四
        kemusiImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTabTitleTextView.setText("科目四:理论考试");
                setContainerView("kemusi", KeMusiActivity.class);
                kemuyiImageView.setImageResource(R.drawable.ico_img1);
                kemuerImageView.setImageResource(R.drawable.ico_img2);
                kemusiImageView.setImageResource(R.drawable.ico_img3_p);
                cheyouquanImageView.setImageResource(R.drawable.ico_img5);
            }
        });
         
        //车友圈
        cheyouquanImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTabTitleTextView.setText("车友圈");
                setContainerView("cheyouquan", CheYouQuanActivity.class);
                kemuyiImageView.setImageResource(R.drawable.ico_img1);
                kemuerImageView.setImageResource(R.drawable.ico_img2);
                kemusiImageView.setImageResource(R.drawable.ico_img3);
                cheyouquanImageView.setImageResource(R.drawable.ico_img5_p);
            }
        });
         
      
    }
     
    public void setContainerView(String id,Class<?> activity){
        mainTabContainer.removeAllViews();
        mainTabIntent = new Intent(this,activity);
        mainTabContainer.addView(localActivityManager.startActivity(id, mainTabIntent).getDecorView());
    }
    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    
//    {
//    	// 如果是Home,直接返回到桌面
//    	if(keyCode == KeyEvent.KEYCODE_HOME||keyCode == KeyEvent.KEYCODE_BACK)
//    	{
//    		showExitGameAlert();
//    	}
//    	//如果是选车型界面 
//    	else if(ChooseCar.ChooseCar==1)
//    	{
//    		showExitGameAlert();
//    	}
//    	//================这里边可能也要添加选驾校的东西
//    	return super.onKeyDown(keyCode, event);
//    }
//    	private void showExitGameAlert() {
//    	final AlertDialog dlg = new AlertDialog.Builder(this).create();
//    	dlg.show();
//    	Window window = dlg.getWindow();
//    	// *** 主要就是在这里实现这种效果的.
//    	// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
//    	window.setContentView(R.layout.main_exit);
//    	// 为确认按钮添加事件,执行退出应用操作
//    	ImageButton ok = (ImageButton) window.findViewById(R.id.btn_ok);
//    	ok.setOnClickListener(new View.OnClickListener() {
//    	public void onClick(View v) {
//    		MainActivity.this.finish(); 
//    	}
//    	});
//    	// 关闭alert对话框架
//    	ImageButton cancel = (ImageButton) window.findViewById(R.id.btn_cancel);
//    	cancel.setOnClickListener(new View.OnClickListener() {
//    	public void onClick(View v) {
//    	dlg.cancel();
//    	}
//    	});
//    	}
}

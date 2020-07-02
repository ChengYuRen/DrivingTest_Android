package com.bn.Begin;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bn.driversystem_android.R;
import com.bn.fyq.Constant.Constant;
import com.bn.picture.Tools;
import com.bn.util.DataUtil;
import com.bn.util.SocketClient;

public class PerSon_Home extends Activity {

	
	public static String SexGet,NiChengGet,PhoneGet;//获取性别 获取昵称
	String userZhangHao=Login_Begin_PerSon.GET_ZHANGHAO;//得到账号
	
	
	public static Bitmap bm_get;
	
	private ImageView touxiang;//头像
	int []moren_picture={R.drawable.jiakao_guide_user_blue};//默认图片
	private String[] items = new String[] { "选择本地图片", "拍照" };
	/* 头像名称 */
	private static final String IMAGE_FILE_NAME = "faceImage.jpg";
	
	public String get_Picture_String;//用于接收图片转换字符串 后的字符串
	
	/* 请求码 */
	private static final int IMAGE_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;

	
	//创建监听 用户头像 
	OnClickListener clickListenerTouXiang = new OnClickListener(){
		@Override
		public void onClick(View v) {
		if (v.getId()==R.id.imageTouXiang)
		{//按钮1
			showDialog();
		}

	}
	};
	
	//创建监听 昵称
	OnClickListener clickListenerNiCheng = new OnClickListener(){
		@Override
		public void onClick(View v) {
		if ((v.getId()==R.id.LinearLayout_nicheng)||(v.getId()==R.id.textview_nicheng)
				||(v.getId()==R.id.textview_nicheng02)||(v.getId()==R.id.ImageView_nicheng)
		){//按钮1
//			Toast toast=Toast.makeText(getApplicationContext(), "这是昵称", Toast.LENGTH_SHORT);
//			toast.show(); 
			Intent intent=new Intent();
			intent.setClass(PerSon_Home.this, UpDate_NiCheng.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
			startActivity(intent);
		}

	}
	};
	
		//创建监听 性别
		OnClickListener clickListenerXingBie = new OnClickListener(){
			@Override
			public void onClick(View v) {
			if ((v.getId()==R.id.LinearLayout_xingbie)||(v.getId()==R.id.textview_xingbie)
					||(v.getId()==R.id.textview_xingbie01)||(v.getId()==R.id.ImageView_xingbie)
			){//按钮1

				if(SexGet.equals("女"))
				{
					SocketClient.ConnectSevert(Constant.UPDATE_PERSON_SEX_MAN+userZhangHao);
					String isok=SocketClient.readinfo;
					if(isok.equals("ok"))
					{
						TextView textView_SEX=(TextView)findViewById(R.id.textview_xingbie01);
						textView_SEX.setText("男");
						SexGet="男";
					}
				}
				else
				{
					SocketClient.ConnectSevert(Constant.UPDATE_PERSON_SEX_WOMAN+userZhangHao);
					String isok=SocketClient.readinfo;
					if(isok.equals("ok"))
					{
						TextView textView_SEX=(TextView)findViewById(R.id.textview_xingbie01);
						textView_SEX.setText("女");
						SexGet="女";
					}
				}
				
				
				
			}

		}
		};
		
		//创建监听 电话
		OnClickListener clickListenerPhone = new OnClickListener(){
			@Override
			public void onClick(View v) {
			if ((v.getId()==R.id.LinearLayout_phone)||(v.getId()==R.id.textview_phone)||(v.getId()==R.id.ImageView_phone)
					||(v.getId()==R.id.textview_phone01))
			{//按钮1
//				Toast toast=Toast.makeText(getApplicationContext(), "这是所在地", Toast.LENGTH_SHORT);
//				toast.show(); 
				Intent intent=new Intent();
				intent.setClass(PerSon_Home.this, UpDate_Phone.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
				startActivity(intent);
			}

		}
		};
			
		
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消顶部Title
        setContentView(R.layout.login_person_home);
        	//返回按钮
      		Button subject_two_kaogui_title;
      		subject_two_kaogui_title=(Button)findViewById(R.id.button_subject_two_kaogui_title);
      		subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
      		{

      			@Override
      			public void onClick(View v) {
      				// TODO Auto-generated method stub
      				//相当于手机的返回按钮
      				PerSon_Home.this.finish();
      			}
      			
      		}
      		);
      		
      		//从数据库得到用户的信息
      		//调试用  直接把账号 设为u2
      		//String userZhangHao=Login_Begin_PerSon.GET_ZHANGHAO;		
      		
			String userinfo=null;
  			SocketClient.ConnectSevert(Constant.PERSON_ALL+userZhangHao);
			userinfo=SocketClient.readinfo;
			//System.out.println(userinfo+"sdsafsdaf");
 
      		 String[] s=userinfo.split("#");//删除最后的#号  将每条数据写于 数组中  接收为： s[0]=“10001η范雨晴η男”  s[1]同理
			 
			 String[] sreply=null;//接收为：当分割s[0]时   sreply[0]=10001   当分割s[1]时    sreply[0]=10002
			
			 sreply=s[0].split("η");//通过η将数组中的每个数据分割开来
//			 for(int i=0;i<sreply.length;i++)  //车型 驾校 昵称 头像 年龄 性别 电话 地址 
//			 {
//				 System.out.println(sreply[i]);
//			 }
			 
			 
			 //各个信息监听
			 //头像系列
			 touxiang=(ImageView)findViewById(R.id.imageTouXiang);
			//发送数据到服务器
			String userinfo_getId=null;//接收返回值的String
			SocketClient.ConnectSevert(Constant.GETID_BY_ZHANGHAO+Login_Begin_PerSon.GET_ZHANGHAO);
			userinfo_getId=SocketClient.readinfo;//得到的ID
			
			SocketClient.ConnectSevert(Constant.getUsertouxiangByid+userinfo_getId);
			String getreply=SocketClient.readinfo;//得到图片信息
			if(getreply.equals("空"))
			{//如果为空  就用默认
				touxiang.setImageDrawable(getResources().getDrawable(moren_picture[0]));//设置图片
			}
			else 
			{
				Bitmap bm=DataUtil.stringtoBitmap(getreply);
				bm_get=bm;
				touxiang.setImageBitmap(bm);
			}
		 
		 
			 touxiang.setOnClickListener(clickListenerTouXiang);	 
			 
			 //用户昵称
			 LinearLayout linearLayout_nicheng=(LinearLayout)findViewById(R.id.LinearLayout_nicheng);
			 TextView textView_nicheng=(TextView)findViewById(R.id.textview_nicheng);
			 TextView textView_nicheng02=(TextView)findViewById(R.id.textview_nicheng02);
			 ImageView imageView_nicheng=(ImageView)findViewById(R.id.ImageView_nicheng);
			 
			 linearLayout_nicheng.setOnClickListener(clickListenerNiCheng);
			 textView_nicheng.setOnClickListener(clickListenerNiCheng);
			 textView_nicheng02.setOnClickListener(clickListenerNiCheng);
			 imageView_nicheng.setOnClickListener(clickListenerNiCheng);
			 
			 //性别
			 LinearLayout linearLayout_xingbie=(LinearLayout)findViewById(R.id.LinearLayout_xingbie);
			 TextView textView_xingbie=(TextView)findViewById(R.id.textview_xingbie);
			 TextView textView_xingbie02=(TextView)findViewById(R.id.textview_xingbie01);
			 ImageView imageView_xingbie=(ImageView)findViewById(R.id.ImageView_xingbie);

			 linearLayout_xingbie.setOnClickListener(clickListenerXingBie);
			 textView_xingbie.setOnClickListener(clickListenerXingBie);
			 textView_xingbie02.setOnClickListener(clickListenerXingBie);
			 imageView_xingbie.setOnClickListener(clickListenerXingBie);
			 
			 //所在地 clickListenerSuoZaiDi
			 LinearLayout linearLayout_phone=(LinearLayout)findViewById(R.id.LinearLayout_phone);
			 TextView textview_phone=(TextView)findViewById(R.id.textview_phone);
			 TextView textview_phone01=(TextView)findViewById(R.id.textview_phone01);
			 ImageView ImageView_phone=(ImageView)findViewById(R.id.ImageView_phone);
			 
			 linearLayout_phone.setOnClickListener(clickListenerPhone);
			 textview_phone.setOnClickListener(clickListenerPhone);
			 textview_phone01.setOnClickListener(clickListenerPhone);
			 ImageView_phone.setOnClickListener(clickListenerPhone);
			 
			 
			 //对昵称进行判断
			 if(UpDate_NiCheng.GET_NiCheng==null)
			 {
				 textView_nicheng02.setText(sreply[2]);//昵称
			 }
			 else
			 {
				 textView_nicheng02.setText(UpDate_NiCheng.GET_NiCheng);//昵称
				 //UpDate_NiCheng.GET_NiCheng=null;
				 
				 StringBuilder s_nicheng=new StringBuilder();
				 s_nicheng.append(Constant.UPDATE_PERSON_NICHENG+userZhangHao);
				 s_nicheng.append(Constant.UPDATE_PERSON_NICHENG+UpDate_NiCheng.GET_NiCheng);
				 SocketClient.ConnectSevert(s_nicheng.toString());
				 
				 String isok=SocketClient.readinfo;
				 if(isok.equals("ok"))
				 {
					 //System.out.println("OOOOOOOOOOOOOOOOOOOK");
					 UpDate_NiCheng.GET_NiCheng=null;
				 }
			 }
			 
			//对 电话 进行判断
			 if(UpDate_Phone.GET_Phone==null)
			 {
				 textview_phone01.setText(sreply[6]);//电话
			 }
			 else
			 {
				 textview_phone01.setText(UpDate_Phone.GET_Phone);//联系方式
				 //UpDate_NiCheng.GET_NiCheng=null;
				 
				 StringBuilder s_nicheng=new StringBuilder();
				 s_nicheng.append(Constant.UPDATE_PERSON_PHONE+userZhangHao);
				 s_nicheng.append(Constant.UPDATE_PERSON_PHONE+UpDate_Phone.GET_Phone);
				 SocketClient.ConnectSevert(s_nicheng.toString());
				 
				 String isok=SocketClient.readinfo;
				 if(isok.equals("ok"))
				 {
					 System.out.println("OOOOOOOOOOOOOOOOOOOK");
					 UpDate_Phone.GET_Phone=null;
				 }
			 }
			 
			 textView_xingbie02.setText(sreply[5]);//性别
			 SexGet=textView_xingbie02.getText().toString();//得到性别
			 NiChengGet=textView_nicheng02.getText().toString();
			 PhoneGet=textview_phone01.getText().toString();	
        
	}
	
	/**
	 * 显示选择对话框
	 */
	private void showDialog() {

		new AlertDialog.Builder(this)
				.setTitle("设置头像")
				.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							//System.out.print("OK====showDialoghahahahahahah");
							Intent intentFromGallery = new Intent();
							intentFromGallery.setType("image/*"); // 设置文件类型
							intentFromGallery
									.setAction(Intent.ACTION_GET_CONTENT);
							startActivityForResult(intentFromGallery,
									IMAGE_REQUEST_CODE);
							break;
						case 1:
							//System.out.print("OK====showDialog01");
							Intent intentFromCapture = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);
							// 判断存储卡是否可以用，可用进行存储
							if (Tools.hasSdcard()) {
								//System.out.print("OK====showDialog");
								intentFromCapture.putExtra(
										MediaStore.EXTRA_OUTPUT,
										Uri.fromFile(new File(Environment
												.getExternalStorageDirectory(),
												IMAGE_FILE_NAME)));
							}
							
							startActivityForResult(intentFromCapture,
									CAMERA_REQUEST_CODE);
							break;
						}
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//结果码不等于取消时候
		if (resultCode != RESULT_CANCELED) {

			switch (requestCode) {
			case IMAGE_REQUEST_CODE://相册
				startPhotoZoom(data.getData());
				
				break;
			case CAMERA_REQUEST_CODE://相机
//				if (Tools.hasSdcard()) {
//					File tempFile = new File(
//							Environment.getExternalStorageDirectory()
//									+ IMAGE_FILE_NAME);
//					//System.out.print("OK====");
//					startPhotoZoom(Uri.fromFile(tempFile));
//				} else {
//					Toast.makeText(PerSon_Home.this, "未找到存储卡，无法存储照片！",
//							Toast.LENGTH_LONG).show();
//				}
//
//				break;
				if (Tools.hasSdcard()) {
					//要这样传输才能顺利加载图片
                    startPhotoZoom(Uri.fromFile(new File(Environment
                                       .getExternalStorageDirectory(),
                                       IMAGE_FILE_NAME)));
            } else {
                    Toast.makeText(PerSon_Home.this, "未找到存储卡，无法存储照片！",
                                    Toast.LENGTH_SHORT).show();
            }

            break;
			case RESULT_REQUEST_CODE:
				if (data != null) {
					getImageToView(data);
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
//	/**
//	 * Android 开发过程中，可能会用到的，通过一个uri获取一个Bitmap对象
//	 * 
//	 */
//	private Bitmap getBitmapFromUri(Uri uri)
//	 {
//	  try
//	  {
//	   // 读取uri所在的图片
//	   Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//	   return bitmap;
//	  }
//	  catch (Exception e)
//	  {
//	   Log.e("[Android]", e.getMessage());
//	   Log.e("[Android]", "目录为：" + uri);
//	   e.printStackTrace();
//	   return null;
//	  }
//	 }

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);	
		startActivityForResult(intent, 2);
		
	}

	/**
     * 转换图片成圆形
     * @param bitmap 传入Bitmap对象
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float roundPx;
            float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom;
            if (width <= height) {
                    roundPx = width / 2;
                    top = 0;
                    bottom = width;
                    left = 0;
                    right = width;
                    height = width;
                    dst_left = 0;
                    dst_top = 0;
                    dst_right = width;
                    dst_bottom = width;
            } else {
                    roundPx = height / 2;
                    float clip = (width - height) / 2;
                    left = clip;
                    right = width - clip;
                    top = 0;
                    bottom = height;
                    width = height;
                    dst_left = 0;
                    dst_top = 0;
                    dst_right = height;
                    dst_bottom = height;
            }
              
            Bitmap output = Bitmap.createBitmap(width,
                            height, Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
              
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
            final Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
            final RectF rectF = new RectF(dst);

            paint.setAntiAlias(true);
              
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, dst, paint);
            return output;
    }
	
	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	@SuppressWarnings("deprecation")
	private void getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
		    Bitmap bit_GetPic;
		    bit_GetPic= extras.getParcelable("data");
		    
		    Bitmap yuan_Picture=toRoundBitmap(bit_GetPic);//=====此步骤把得到的矩形图片设置成圆形
		    
		    get_Picture_String=convertIconToString(yuan_Picture);//对图片进行转换 用get_Picture_String接收
		    Drawable drawable = new BitmapDrawable(yuan_Picture);
		    touxiang.setBackgroundDrawable(drawable);
			//发送数据到服务器
			String userinfo_getId=null;//接收返回值的String
			SocketClient.ConnectSevert(Constant.GETID_BY_ZHANGHAO+Login_Begin_PerSon.GET_ZHANGHAO);
			userinfo_getId=SocketClient.readinfo;//得到的ID
			
			
			//发送数据到服务器
			String userinfo=null;//接收返回值的String
			SocketClient.ConnectSevert(Constant.updateUserTouxiang+userinfo_getId+Constant.updateUserTouxiang+get_Picture_String);
			userinfo=SocketClient.readinfo;
			if(userinfo.equals("ok"))
			{
				//touxiang.setImageBitmap(photo);
				bm_get=yuan_Picture;
				touxiang.setImageBitmap(yuan_Picture);//在界面中设置圆形图片
			}
		}
	}
	//将图片转换为数据 
	public static String convertIconToString(Bitmap bitmap)  
    {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream  
        bitmap.compress(CompressFormat.PNG, 100, baos);  
        byte[] appicon = baos.toByteArray();// 转为byte数组  
        return Base64.encodeToString(appicon, Base64.DEFAULT);  
  
    } 

	
	
	
}

package com.bn.driversystem_android;

import com.bn.Begin.Login_Begin_PerSon;
import com.bn.Begin.PerSon_Home;
import com.bn.driversystem_android.R;
import com.bn.driversystem_android.cheyouquan.*;
import com.bn.fyq.Constant.Constant;
import com.bn.picture.Tools;
import com.bn.util.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
@SuppressLint("SdCardPath")
public class CheYouQuanActivity extends Activity{
	@SuppressWarnings("unused")
	private ImageView cheyouquan_1,cheyouquan_2,cheyouquan_3,cheyouquan_4,cheyouquan_5,cheyouquan_head;
	public static String luntanXX;
	public static String luntanMZ;
	
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
		if (v.getId()==R.id.cheyouquan_head)
		{//按钮1
			//showDialog();
			Intent intent=new Intent();
			intent.setClass(CheYouQuanActivity.this, PerSon_Home.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
			startActivity(intent);
		}

	}
	};
		
	
	
	//创建监听 用户头像  未登录状态
	OnClickListener clickListenerTouXiang_UnLoad= new OnClickListener(){
		@Override
		public void onClick(View v) {
		if (v.getId()==R.id.cheyouquan_head)
		{//按钮1
			//showDialog();
			//Toast.makeText(getApplicationContext(), "没登录", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent();
			intent.setClass(CheYouQuanActivity.this, Login_Begin_PerSon.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
			startActivity(intent);
			
		}

	}
	};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.main_cheyouquan);
				
				initView();
				
				//各个信息监听
				 //头像系列
				 touxiang=(ImageView)findViewById(R.id.cheyouquan_head);
				 if(Login_Begin_PerSon.LOGIN_IT==0)
				 {
					 touxiang.setOnClickListener(clickListenerTouXiang_UnLoad);	 
				 }
				 else
				 {
//					//发送数据到服务器
//					String userinfo_getId=null;//接收返回值的String
//					SocketClient.ConnectSevert(Constant.GETID_BY_ZHANGHAO+Login_Begin_PerSon.GET_ZHANGHAO);
//					userinfo_getId=SocketClient.readinfo;//得到的ID
//					
//					SocketClient.ConnectSevert(Constant.getUsertouxiangByid+userinfo_getId);
//					String getreply=SocketClient.readinfo;//得到图片信息
//					if(getreply.equals("空"))
//					{//如果为空  就用默认
//						touxiang.setImageDrawable(getResources().getDrawable(moren_picture[0]));//设置图片
//					}
//					else 
//					{
//						Bitmap bm=DataUtil.stringtoBitmap(getreply);
//						touxiang.setImageBitmap(bm);
//					}
					 //!!!!!!!!!!!!!!!!!!!!!!
					 //如果头像更新 则在下次登录时生效  即重启软件生效
					 touxiang.setImageBitmap(PerSon_Home.bm_get);
					 touxiang.setOnClickListener(clickListenerTouXiang);	 

				 }
				 
				
			 
			 
					 
	}
	
	
	private void initView() 
	{
		cheyouquan_1=(ImageView) findViewById(R.id.cheyouquan_1);
		cheyouquan_2=(ImageView) findViewById(R.id.cheyouquan_2);
		cheyouquan_3=(ImageView) findViewById(R.id.cheyouquan_3);
		cheyouquan_4=(ImageView) findViewById(R.id.cheyouquan_4);
		cheyouquan_5=(ImageView) findViewById(R.id.cheyouquan_5);

		
		MyBtnOnClick mytouchlistener = new MyBtnOnClick();
		cheyouquan_1.setOnClickListener(mytouchlistener);
        cheyouquan_2.setOnClickListener(mytouchlistener);
		cheyouquan_3.setOnClickListener(mytouchlistener);
        cheyouquan_4.setOnClickListener(mytouchlistener);
		cheyouquan_5.setOnClickListener(mytouchlistener);

		
	}
	private class MyBtnOnClick implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			int mBtnid = v.getId();
			switch(mBtnid){
			case R.id.cheyouquan_1:
				luntanXX="0001";
				luntanMZ="科一交流";
				startActivity(new Intent().setClass(CheYouQuanActivity.this, keyijiaoliuActivity.class));
				break;
			case R.id.cheyouquan_2:
				luntanXX="0002";
				luntanMZ="科二交流";
				startActivity(new Intent().setClass(CheYouQuanActivity.this, keyijiaoliuActivity.class));
				break;
			case R.id.cheyouquan_3:
				luntanXX="0003";
				luntanMZ="科三交流";
				startActivity(new Intent().setClass(CheYouQuanActivity.this, keyijiaoliuActivity.class));
				break;
			case R.id.cheyouquan_4:
				luntanXX="0004";
				luntanMZ="科四交流";
				startActivity(new Intent().setClass(CheYouQuanActivity.this, keyijiaoliuActivity.class));
				break;
			case R.id.cheyouquan_5:
				luntanXX="0005";
				luntanMZ="新手上路";
				startActivity(new Intent().setClass(CheYouQuanActivity.this, keyijiaoliuActivity.class));
				break;
			
			
			default:
				break;
			}
			
		}
	}
	
	/**
	 * 显示选择对话框
	 */
	@SuppressWarnings("unused")
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
                    Toast.makeText(CheYouQuanActivity.this, "未找到存储卡，无法存储照片！",
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
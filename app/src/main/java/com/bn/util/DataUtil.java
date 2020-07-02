package com.bn.util;

import java.io.File;
import java.io.FileOutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.bn.driversystem_android.kemuyi.fangfa;
import com.bn.fyq.Constant.Constant;
import com.bn.fyq.Constant.ResetConstant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
public class DataUtil {
	// 发送测试，是否连接的上服务器
	public static void testConnect(final Handler handler) 
	{
		final Message msg = new Message();
		final Bundle b = new Bundle();
		msg.what = ResetConstant.TESTCONNECT;
				try {
					DataGetUtilSimple.ConnectSevert(Constant.TESTCONNECT);
					String testmsg = DataGetUtilSimple.readinfo;
					b.putString("msg", testmsg);
					msg.setData(b);
					handler.sendMessage(msg);
				} catch (UnknownHostException e) {
					e.printStackTrace();
					handler.sendEmptyMessage(ResetConstant.TESTCONNECTERROR);
				} catch (SocketException e) {
					e.printStackTrace();
					// 开启错误对话框
					handler.sendEmptyMessage(ResetConstant.TESTCONNECTERROR);
				} catch (Exception e) {
					e.printStackTrace();
					// 开启错误对话框
					handler.sendEmptyMessage(ResetConstant.TESTCONNECTERROR);
				}
	}
	
	//加载试题信息并存入sqlite数据库
	public  static void jiazaiquestion() throws Exception 
			{
				String sdata[][]=null;
				SocketClient.ConnectSevert(Constant.getQuestion);
				String getreply=SocketClient.readinfo;//得到所有的试题
				String[] s=getreply.split("#");//删除最后的#号  将每条试题信息写于 数组中
				 sdata=new String [s.length][7];//5是主贴表的属性个数
				
				 for(int i=0;i<s.length;i++)
				 {
					String[] sreply=s[i].split("η");//通过η将数组中的每个数据分割开来
					
					for(int j=0;j<sreply.length;j++)
					 {
						
						 sdata[i][j]=sreply[j];//sdata存入的是  每个试题
					 }
				 }
				 DBUtil.insertquestion(sdata);
		
			}
	//加载答案信息  并存入数据库
	public  static void jiazaianswer() throws Exception 
	{
		String sdata[][]=null;
		SocketClient.ConnectSevert(Constant.getAnswerAll);
		String getreply=SocketClient.readinfo;//得到所有的试题
		String[] s=getreply.split("#");//删除最后的#号  将每条试题信息写于 数组中
		 sdata=new String [s.length][5];//5是答案表的属性个数
		
		 for(int i=0;i<s.length;i++)
		 {
			String[] sreply=s[i].split("η");//通过η将数组中的每个数据分割开来
			
			for(int j=0;j<sreply.length;j++)
			 {
				
				 sdata[i][j]=sreply[j];//sdata存入的是  每个试题
			 }
		 }
		 DBUtil.insertanswer(sdata);

	}
	//加载试题图片并存入手机端
	public static void jiazaitupian() 
	{
		String paths=null;
		try
		{
			 paths = Environment.getExternalStorageDirectory().toString()+"/Drive/Image";
    		 File destDir = new File(paths);
    		  if (!destDir.exists()) 
    		  {
    		      destDir.mkdirs();
    		  }
		}
		catch(Exception e)
		{
			System.out.println("创建图片位置异常");
		}
		String image[]=DBUtil.getQueImage();//拿到图片
		for(int i=0;i<image.length;i++)
		{
			if(!image[i].equals("空"))
			{
				try
				{
					SocketClient.ConnectSevert(Constant.getimage+image[i]);
					String getinfor=SocketClient.readinfo;//得到当前图片的字符串
					Bitmap bm=stringtoBitmap(getinfor);
					int TPid=i;
					TPid++;
					String imageid=fangfa.zhuanhuanSTid(TPid);//根据i的大小  获取试题编号
					File f = new File(paths,imageid+".png");//传入存储位置以及名字
					if (f.exists()) { 
					f.delete(); 
					} 
					try { 
						FileOutputStream out = new FileOutputStream(f); 
						bm.compress(Bitmap.CompressFormat.PNG, 90, out); 
						out.flush(); 
						out.close(); 
						}
					catch (Exception e) 
						{ 
						e.printStackTrace(); 
						}
				}
				catch(Exception e)
				{
					System.out.println("获取图片异常");
				}
			}
		}
	}
	//加载考题整理表信息并存入sqlite数据库
		public  static void jiazaiqzhengli() throws Exception 
				{
					String sdata[][]=null;
					SocketClient.ConnectSevert(Constant.getQzhengli);
					String getreply=SocketClient.readinfo;//得到所有的整理表信息
					String[] s=getreply.split("#");//删除最后的#号  将每条整理表信息写于 数组中
					 sdata=new String [s.length][2];//2是主贴表的属性个数
					
					 for(int i=0;i<s.length;i++)
					 {
						String[] sreply=s[i].split("η");//通过η将数组中的每个数据分割开来
						
						for(int j=0;j<sreply.length;j++)
						 {
							
							 sdata[i][j]=sreply[j];//sdata存入的是  每个整理表数据
						 }
					 }
					 DBUtil.insertqzhengli(sdata);
			
				}
		//加载错题表表信息并存入sqlite数据库
				public  static void jiazaierror(String yonghu) throws Exception 
						{
					DBUtil.deleteAllCUOTI();//删除所有的错题  开始更新
							String sdata[][]=null;
							SocketClient.ConnectSevert(Constant.gete_que_idByu+yonghu);
							String getreply=SocketClient.readinfo;//得到所有的错题信息
							String[] s=getreply.split("#");//删除最后的#号  将每条错题表信息写于 数组中
							 sdata=new String [s.length][1];//1是主贴表的属性个数
							
							 for(int i=0;i<s.length;i++)
							 {
								String[] sreply=s[i].split("η");//通过η将数组中的每个数据分割开来
								
								for(int j=0;j<sreply.length;j++)
								 {
									
									 sdata[i][j]=sreply[j];//sdata存入的是  每个整理表数据
								 }
							 }
							 DBUtil.insertALLcuoti(sdata);
					
						}
	 public static Bitmap stringtoBitmap(String string){
		    //将字符串转换成Bitmap类型
		    Bitmap bitmap=null;
		    try {
		    byte[]bitmapArray;
		    bitmapArray=Base64.decode(string, Base64.DEFAULT);
		bitmap=BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
		} catch (Exception e) {
		e.printStackTrace();
		}
		    
		    return bitmap;
		    }
}
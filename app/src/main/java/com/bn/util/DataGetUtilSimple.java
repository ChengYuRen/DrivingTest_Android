package com.bn.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.bn.fyq.Constant.Constant;

public class DataGetUtilSimple
{
	private static DataInputStream din;
	private static DataOutputStream dout;
	private static Socket s;
	public static String readinfo;
	public static byte[] data;
	
	//在服务员端，当快速点击多个菜品子类时，会有多个线程同时调用ConnectSevert方法，而din,dout都是静态的，因此
	//会产生EOFException，因此将ConnectSevert ()方法声明为同步方法。
	public synchronized static void ConnectSevert(String info) throws Exception
	{
		try
		{
			s=new Socket();
			s.connect(new InetSocketAddress(Constant.IP,Constant.POINT),5000);
			if(!s.isConnected())
			{
				if(s!=null)
				{
					s.close();
				}
				return;
			}
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			//编码
			info=MyConverter.escape(info);
			dout.writeInt(info.length());
			dout.write(info.getBytes());
			String getinfo=din.readUTF();
			if(getinfo.equals("STR"))
			{
				readinfo=IOUtil.readstr(din);
			}
			else if(getinfo.equals("BYTE")) 
			{
				data=IOUtil.readBytes(din);
			}
		}
		finally
		{
			try{if(din!=null) {din.close();} }catch(Exception e){ e.printStackTrace();}
			try{if(dout!=null) {dout.close();} }catch(Exception e){ e.printStackTrace();}
			try{if(s!=null){s.close();} }catch(Exception e){e.printStackTrace();}
		}
    }
}
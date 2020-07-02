package com.bn.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IOUtil 
{
	//v2版本的新方法，代替din.writeUTF();发送字符串
	public static void writeStr(DataOutputStream dout,String str) throws IOException
	{
		try{
			dout.writeInt(str.length());
			dout.write(str.getBytes());
			dout.flush();//清空数据输出流
		    }catch(Exception e){
			  e.printStackTrace();
		     }finally{
		    	 try{dout.flush();}catch(Exception e){e.printStackTrace();}
		     }
	}//接收字符串
	public static String readstr(DataInputStream din) throws IOException
	{		
		String str=null;
		byte[]  data=null;
		ByteArrayOutputStream out= new ByteArrayOutputStream(1024); //创建一个新的 byte数组输出流，它具有指定大小的缓冲区容量(以字节为单位)
		//循环接受数据
		int len =0,temRev =0,size;
		len =din.readInt();
		byte[] buf=new byte[len-temRev];
		while ((size = din.read(buf)) != -1)
		{ 	
			temRev+=size;
			out.write(buf, 0, size);
			if(temRev>=len)
			{
				break;
			}
			buf=new byte[len-temRev];
		}
		data = out.toByteArray();
		str= new String(data,0,len,"utf-8");
		str=MyConverter.unescape(str);//解码
		return str;
	}
    //接收图片数据
	public static byte[]readBytes(DataInputStream din) throws IOException
	{
		byte[]  data=null;
		ByteArrayOutputStream out= new ByteArrayOutputStream(1024); 
			//循环接受数据
				int len =0,temRev =0,size;
				len =din.readInt();
				byte[] buf=new byte[len-temRev];
				while ((size = din.read(buf)) != -1)
				{ 	
					temRev+=size;
					out.write(buf, 0, size);
					if(temRev>=len)
					{
						break;
					}
					buf = new byte[len-temRev];
				}
				data = out.toByteArray();			
		return data;
	}
}

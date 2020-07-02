package com.bn.driversystem_android.kemuyi;

import android.text.format.Time;

import com.bn.util.DBUtil;
import com.bn.util.TypeExchangeUtil;

public class fangfa {
	//得到指定试题的 A B C D 各个选项的内容
		public static String[][] getdaanxuanxiangneirong(String id)
		{
			String shitixuanxiang[][]=new String[4][];
			try
			{
				String getData=TypeExchangeUtil.listToString(DBUtil.getAnswerqByid(id));
			String[] s=getData.split("#");//删除最后的#号  将每条数据写于 数组中  接收为： s[0]=“10001η范雨晴η男”  s[1]同理
			 
			 String[] sreply=null;//接收为：当分割s[0]时   sreply[0]=10001   当分割s[1]时    sreply[0]=10002
			 
			 String[][] sreturn=new String[s.length][];
			 
			 for(int i=0;i<s.length;i++)
			 {
				 sreply=s[i].split("η");//通过η将数组中的每个数据分割开来
				 sreturn[i]=sreply;
				 
			 }
			shitixuanxiang=sreturn;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return shitixuanxiang;
		}
		//根据指定id得到试题的  题目   科目  所含照片。。。。等各个内容
		public static String[][] getshiti(String stid)
		{
			String shitixinxi[][]=new String[1][5];
			try
			{
				String getData=TypeExchangeUtil.listToString(DBUtil.getQuestionByid(stid));
			String[] s=getData.split("#");//删除最后的#号  将每条数据写于 数组中  接收为： s[0]=“10001η范雨晴η男”  s[1]同理
			 
			 String[] sreply=null;//接收为：当分割s[0]时   sreply[0]=10001   当分割s[1]时    sreply[0]=10002
			 
			 String[][] sreturn=new String[s.length][];
			 
			 for(int i=0;i<s.length;i++)
			 {
				 sreply=s[i].split("η");//通过η将数组中的每个数据分割开来
				 sreturn[i]=sreply;
				 
			 }
			shitixinxi=sreturn;//得到第五个试题的    q_title,q_subject,q_image,q_class,q_zhangjie
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return shitixinxi;
		}
	//根据传进来的试题ID  在前面加上0000
		public static String zhuanhuanSTid(int int_ceshistid)
		{String String_ceshistid;
			String_ceshistid=int_ceshistid+"";
			if(String_ceshistid.length()==1)
			{
				String_ceshistid="0000"+String_ceshistid;
			}
			else if(String_ceshistid.length()==2)
			{
				String_ceshistid="000"+String_ceshistid;
			}
			else if(String_ceshistid.length()==3)
			{
				String_ceshistid="00"+String_ceshistid;
			}
			return String_ceshistid;
		}
		
		//获取系统时间
				public static String huoqushijian()
				{
					Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。

					t.setToNow(); // 取得系统时间。

					int year = t.year;

					String month = t.month+1+"";

					String  day = t.monthDay+"";
					month=Integer.parseInt(month)<10?("0"+month):month;//不足位补零
				       day=Integer.parseInt(day)<10?("0"+day):day;//不足位补零
				       String shijian=year+"-"+month+"-"+day;
					return shijian;
				}

		

}

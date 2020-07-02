package com.bn.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bn.driversystem_android.kemuyi.fangfa;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DBUtil 
{
	static SQLiteDatabase sld;
	public static void createOrOpenDatabase()
    {
    	try
    	{
    		 String paths = Environment.getExternalStorageDirectory().toString()+"/Drive";
    		 File destDir = new File(paths);
    		  if (!destDir.exists()) 
    		  {
    		      destDir.mkdirs();
    		  }
	    	sld=SQLiteDatabase.openDatabase
	    	(
	    			Environment.getExternalStorageDirectory().toString()+"/Drive/mydb", //数据库所在路径
	    			null, 								//CursorFactory
	    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY //读写、若不存在则创建
	    	);
	    	String sqlquestion ="create table if not exists question(q_id varchar(20) primary key,q_title varchar(400),q_subject varchar(20),q_image varchar(100),q_class varchar(20),q_zhangjie varchar(50),q_jiexi varchar(400) );";
	    	String sqlanswer="create table if not exists answer(a_id varchar(20) primary key,a_que_id varchar(20),a_select varchar(20),a_se_neirong varchar(400),a_yorn varchar(20));";
	    	String sqlshoucang="create table if not exists shoucang(sc_id varchar(20) primary key,sc_q_id varchar(20));";
	    	String sqlcuoti="create table if not exists cuoti(ct_id varchar(20) primary key,ct_q_id varchar(20));";
	    	String sqlkaoshijilu="create table if not exists kaoshijilu(ks_id varchar(20),ks_q_id varchar(20),ks_kemu varchar(20), ks_shijian varchar(30), ks_fenshu varchar(20),primary key(ks_id,ks_q_id));";
	    	String sqlshujutongji="create table if not exists shujutongji (sjtj_q_id  varchar(20) primary key,sjtj_jieguo varchar(20));";
	    	String sqlqzhengli="create table if not exists qzhengli(qz_que_id  varchar(20) ,qz_car_id varchar(20),primary key (qz_que_id,qz_car_id));";
	    	String scar="create table if not exists select_car(car_id varchar(20));";//选车表
	    	
	    	sld.execSQL(sqlquestion);
	    	sld.execSQL(sqlanswer);
	    	sld.execSQL(sqlshoucang);
	    	sld.execSQL(sqlcuoti);
	    	sld.execSQL(sqlkaoshijilu);
	    	sld.execSQL(sqlshujutongji);
	    	sld.execSQL(sqlqzhengli);
	    	sld.execSQL(scar);
	    	 		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
	
	 //关闭数据库的方法
    public static void closeDatabase()
    {
    	try
    	{
	    	sld.close();    
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
  //插入记录的方法    插入试题全部
    public static void insertquestion(String [][]sque)
    {
    	try
    	{
    		createOrOpenDatabase();
        	String sql;
        	for(int i=0;i<sque.length;i++)
			{	
        		sql="insert into question values('"+sque[i][0]+"','"+sque[i][1]+"','"+sque[i][2]+"','" +sque[i][3]+"','"+sque[i][4]+"','"+sque[i][5]+"','"+sque[i][6]+"');";
				sld.execSQL(sql);
			}
        	closeDatabase();
        	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
	//将得到的试题de  答案   插入表格
    public static void insertanswer(String [][]sansw)
    {
    	try
    	{
    		createOrOpenDatabase();
        	String sql;
        	for(int i=0;i<sansw.length;i++)
			{	
				sql="insert into answer values('"+sansw[i][0]+"','"+sansw[i][1]+"','"+sansw[i][2]+"','" +sansw[i][3]+"','"+sansw[i][4]+"');";			
				sld.execSQL(sql);
			}
        	closeDatabase();
        	
        	
    	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
		//根据试题id  找到答案的内容
		public static String[] getAnswerneirongByid(String id) 
		 {
			String pid[] = new String[4];
			int i=0;
			try
			{
				createOrOpenDatabase();//打开数据库
	   		String sql="select a_se_neirong from answer where a_yorn='是' and a_que_id='"+id+"';";
	   		Cursor cur=sld.rawQuery(sql, null);
	   		while(cur.moveToNext())
	   		{
	   			pid[i]=cur.getString(0);
	   			i++;
	   		}
	   		cur.close();
	   		closeDatabase();
	   		
			}
			catch(Exception e)
			{
				pid[0]="获取答案选项内容时   抛出异常";
			}
			return pid;
		 }
	//得到某个试题的正确答案选项     //记得更改  为  数组模式
		public static String[] getAnswerzhengqueeByid(String id)
		 {
			    
			    String pid[] = new String[4];
			    int i=0;
			    try
			    {
			    	createOrOpenDatabase();//打开数据库
			   		String sql="select a_select from answer where a_yorn='是' and a_que_id='"+id+"';";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   		}
			   		cur.close();
			   		closeDatabase();
			    }
			    catch(Exception e)
			    {
			    	pid[0]="数据异常  检查吧";
			    }
	   		return pid;
		 }
		//根据试题编号找到所属于的全部答案
		public static List<String[]> getAnswerqByid(String id) throws Exception {		
			 List<String[]> list=new ArrayList<String[]>();
			 createOrOpenDatabase();//打开数据库
			 String sql="select a_select,a_se_neirong from answer where a_que_id='"+id+"';";
			 Cursor cur=sld.rawQuery(sql, null);
			 while(cur.moveToNext())
			 {
				 String[] detail=new String[2];
				 for(int i=0;i<detail.length;i++)
				 {
					 detail[i]=cur.getString(i);
				 }
				 list.add(detail);
			 }
			 cur.close();
			 closeDatabase();
			 return list;
		}
		//根据id得到题目信息
		public static List<String[]>  getQuestionByid(String id) throws Exception {		
			 List<String[]> list=new ArrayList<String[]>();
			createOrOpenDatabase();//打开数据库
			 String sql="select  q_title,q_subject,q_image,q_class,q_zhangjie,q_jiexi from question where q_id='"+id+"';";
			 Cursor cur=sld.rawQuery(sql, null);
			 while(cur.moveToNext())
			 {
				 String[] detail=new String[6];
				 for(int i=0;i<detail.length;i++)
				 {
					 detail[i]=cur.getString(i);
				 }
				 list.add(detail);
			 }
			 cur.close();
			 closeDatabase();
			 return list;
		}
		//得到试题的最大数目
				public static String getQuestionCount() 
				 {
					String pid = null;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select count(*) from question;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					return pid;
				 }
				//插入收藏表   传进去试题ID
			    public static String insertSC(String STid )
			    {
			    	deleteSC(STid);
			    	String a="ok";
			    	String scmaxid;
			    	scmaxid=DBUtil.getSCmaxid();
			    	try
			    	{
			    		createOrOpenDatabase();
			    		String sql;
			    		int int_sc_id=Integer.parseInt(scmaxid);
			    		int_sc_id++;
			    		scmaxid=fangfa.zhuanhuanSTid(int_sc_id);
			        	sql="insert into shoucang values('"+scmaxid+"','"+STid+"');";	
			        	sld.execSQL(sql);
						closeDatabase();
			        	}
					catch(Exception e)
					{
						a="插入出现异常  检查吧";
					}
			    	return a;
			    }
			  //得到收藏表的最大ID
				public static String getSCmaxid() 
				 {
					String pid = null;
					try
					{ 
						createOrOpenDatabase();//打开数据库
			   		String sql="select max(sc_id) from shoucang;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					if(pid==null)
					{
						pid="0";
					}
					return pid;
				 }
				//删除收藏表数据   根据传进来的试题ID
				public static void deleteSC(String STid ) 
				 {
					
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="delete from shoucang where sc_q_id='"+STid+"';";
			   		sld.execSQL(sql);
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				 }
				//根据 试题ID  得到  是否存在于收藏表
				public static String SFshoucang(String STid)
				{
					String pid = null;
					try
					{ 
						createOrOpenDatabase();//打开数据库
			   		String sql="select sc_id from shoucang where sc_q_id='"+STid+"';";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					if(pid==null)
					{
						pid="no";
					}
					return pid;
				}
				//删除收藏表数据   全部删除  即清空数据
				public static void deleteAllSC() 
				 {
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="delete from shoucang;";
			   		sld.execSQL(sql);
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				 }
			//得到  收藏表的总数
				public static String getSCCount() 
				 {
					String pid = null;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select count(*) from shoucang;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					return pid;
				 }
				//得到试题de 各个章节名称
				public static String[] getzhangjieName() 
				 {
					String pid []=new String [4];
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select distinct q_zhangjie from question;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				//得到试题中   "无"的总数目     从网上查   现在用的是  总数为20
				public static String getZJCount(String zhangjie,String kemu,String chexing) 
				 {
					String pid = null;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select count(question.q_zhangjie) from question,qzhengli where question.q_class!='多项选择题' and question.q_zhangjie='"+zhangjie+"' and question.q_subject='"+kemu+"' and qzhengli.qz_car_id='"+chexing+"' and question.q_id=qzhengli.qz_que_id;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					return pid;
				 }
				//得到  各个章节的试题 ID
				public static String[] getQuestionIdByZJ(String zhangjie,String kemu,String chexing) 
				 {
					String ZJlength=getZJCount(zhangjie,kemu,chexing);
					int int_ZJlength=Integer.parseInt(ZJlength);
					String pid []=new String [int_ZJlength];
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select question.q_id from question,qzhengli where question.q_class!='多项选择题' and question.q_zhangjie='"+zhangjie+"' and question.q_subject='"+kemu+"' and qzhengli.qz_car_id='"+chexing+"' and question.q_id=qzhengli.qz_que_id;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				//得到 收藏表  的所有试题ID
				public static String[] getSCallID() 
				 {
					String pid []=new String [20];
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select sc_q_id from shoucang;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
	//根据题目   找到  属于的 试题ID
				public static String getSTidBYtimu(String timu)
				{
					String pid = null;
					try
					{ 
						createOrOpenDatabase();//打开数据库
			   		String sql="select q_id from question where q_title='"+timu+"';";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					if(pid==null)
					{
						pid="no";
					}
					return pid;
				}	
				 //得到错题表的最大ID
				public static String getCUOTImaxid() 
				 {
					String pid = null;
					try
					{ 
						createOrOpenDatabase();//打开数据库
			   		String sql="select max(ct_id) from cuoti;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					if(pid==null)
					{
						pid="0";
					}
					return pid;
				 }
				//插入错题表   传进去试题ID
			    public static String insertCUOTI(String STid )
			    {
			    	DBUtil.deleteCUOTI(STid);
			    	String a="ok";
			    	String scmaxid;
			    	scmaxid=DBUtil.getCUOTImaxid();
			    	try
			    	{
			    		createOrOpenDatabase();
			    		String sql;
			    		int int_sc_id=Integer.parseInt(scmaxid);
			    		int_sc_id++;
			    		scmaxid=fangfa.zhuanhuanSTid(int_sc_id);
			        	sql="insert into cuoti values('"+scmaxid+"','"+STid+"');";	
			        	sld.execSQL(sql);
						closeDatabase();
			        	}
					catch(Exception e)
					{
						a="插入出现异常  检查吧";
					}
			    	return a;
			    }
				//得到  错题表的总数
				public static String getCUOTICount() 
				 {
					String pid = null;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select count(*) from cuoti;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					return pid;
				 }
				//删除cuoti数据   根据传进来的试题ID
				public static void deleteCUOTI(String STid ) 
				 {
					
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="delete from cuoti where ct_q_id='"+STid+"';";
			   		sld.execSQL(sql);
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				 }
				//删除错题数据   全部删除  即清空数据
				public static void deleteAllCUOTI() 
				 {
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="delete from cuoti;";
			   		sld.execSQL(sql);
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				 }
				//得到 错题表  的所有试题ID
				public static String[] getCUOTIallID() 
				 {
					String pid []=new String [20];
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select ct_q_id from cuoti;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				//得到试题de 各个分类名称
				public static String[] getfenleiName() 
				 {
					String pid []=new String [3];//先写成这样
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select distinct q_class from question;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				//得到试题中    根据传入的分类  得到总数
				public static String getFLCount(String zhangjie,String kemu,String chexing) 
				 {
					String pid = null;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select count(question.q_class) from question,qzhengli where question.q_class='"+zhangjie+"' and question.q_subject='"+kemu+"' and qzhengli.qz_car_id='"+chexing+"' and question.q_id=qzhengli.qz_que_id;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					return pid;
				 }
				//得到  各个分类的试题 ID
				public static String[] getQuestionIdByFL(String zhangjie,String kemu,String chexing) 
				 {
					String ZJlength=getFLCount(zhangjie,kemu,chexing);
					int int_ZJlength=Integer.parseInt(ZJlength);
					String pid []=new String [int_ZJlength];
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select question.q_id from question,qzhengli where question.q_class='"+zhangjie+"' and question.q_subject='"+kemu+"' and qzhengli.qz_car_id='"+chexing+"' and question.q_id=qzhengli.qz_que_id;select count(question.q_class) from question,qzhengli where question.q_class='"+zhangjie+"' and question.q_subject='"+kemu+"' and qzhengli.qz_car_id='"+chexing+"' and question.q_id=qzhengli.qz_que_id;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				//得到不属于多项选择题的  试题ID  的数目
				public static String getCountBUYduoxiang(String kemu,String chexing) 
				 {
					String pid = null;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select count(question.q_id) from question,qzhengli where question.q_class!='多项选择题' and question.q_subject='"+kemu+"' and qzhengli.qz_car_id='"+chexing+"' and question.q_id=qzhengli.qz_que_id;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					return pid;
				 }	
		//得到不属于多项选择题的  题目ID   顺序练习专用
				public static String[] getQuestionIdBUYduoxiang(String kemu,String chexing) 
				 {
					String ZJlength=getCountBUYduoxiang(kemu,chexing);
					int int_ZJlength=Integer.parseInt(ZJlength);
					String pid []=new String [int_ZJlength];
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select question.q_id from question,qzhengli where question.q_class!='多项选择题' and question.q_subject='"+kemu+"' and qzhengli.qz_car_id='"+chexing+"' and question.q_id=qzhengli.qz_que_id;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				//得到试题中所含有的所有的图片路径
				public static String[] getQueImage() 
				 {
					String ZJlength=DBUtil.getQuestionCount();
					int int_ZJlength=Integer.parseInt(ZJlength);
					String pid []=new String [int_ZJlength];
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select q_image from question;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				//插入考试记录   插入  考试ID  试题ID  科目  时间  分数
			    public static String insertKSJL(String ksid,String STid,String kemu,String shijian,String fenshu)
			    {
			    	String a="ok";
			    	try
			    	{
			    		createOrOpenDatabase();
			    		String sql;
			        	sql="insert into kaoshijilu values('"+ksid+"','"+STid+"','"+kemu+"','"+shijian+"','"+fenshu+"');";	
			        	sld.execSQL(sql);
						closeDatabase();
			        	}
					catch(Exception e)
					{
						a="插入出现异常  检查吧";
					}
			    	return a;
			    }
			  //得到考试记录  考试ID 最大ID
				public static String getKSJLmaxid() 
				 {
					String pid = null;
					try
					{ 
						createOrOpenDatabase();//打开数据库
			   		String sql="select max(ks_id) from kaoshijilu;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					if(pid==null)
					{
						pid="0";
					}
					return pid;
				 }
				 //得到  一次考试有几个试题
				public static String getKSJLcountbyks_id(String ksid) 
				 {
					String pid = null;
					try
					{ 
						createOrOpenDatabase();//打开数据库
			   		String sql="select count(*) from kaoshijilu where ks_id='"+ksid+"';";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					if(pid==null)
					{
						pid="0";
					}
					return pid;
				 }
				//得到考试记录(根据考试ID)中的 试题ID
				public static String[] getSTidFromKSByks_id(String ksid) 
				 {
					String ZJlength=DBUtil.getKSJLcountbyks_id(ksid);
					int int_ZJlength=Integer.parseInt(ZJlength);
					String pid []=new String [int_ZJlength];
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select ks_q_id from kaoshijilu where ks_id='"+ksid+"';";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				//根据试题ID 从考试记录表得到分数  时间
				public static String[] getKSJUFENandSJ(String ksid,String STid) 
				 {
					String pid []=new String [2];
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select ks_shijian,ks_fenshu from kaoshijilu where ks_q_id='"+STid+"' and ks_id='"+ksid+"';";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		
			   		while(cur.moveToNext())
			   		{
			   			for(int i=0;i<pid.length;i++)
			   			{
			   				pid[i]=cur.getString(i);
			   			}
			   			
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				 //得到  (根据科目)考试ID
				public static String[] getksidBYkemu(String kemu) 
				 {
					String pid []=new String [10];//这个具体数目不会写  可以这么用  无非就是  考试记录就能获得10个
					int i=0;
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="select distinct ks_id from kaoshijilu where ks_kemu='"+kemu+"';";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid[i]=cur.getString(0);
			   			i++;
			   			
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid[0]="获取内容时   抛出异常";
					}
					return pid;
				 }
				//删除考试记录  全部删除  即清空数据
				public static void deleteAllKSJL() 
				 {
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="delete from kaoshijilu;";
			   		sld.execSQL(sql);
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				 }
				//得到数据统计表的总数
				public static String getSJTJcount(String jieguo) 
				 {
					String pid = null;
					try
					{ 
						createOrOpenDatabase();//打开数据库
			   		String sql="select count(sjtj_q_id) from shujutongji where sjtj_jieguo='"+jieguo+"';";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					if(pid==null)
					{
						pid="0";
					}
					return pid;
				 }
				//插入数据统计表   传进去试题ID  and  做题结果  yes   no
			    public static String insertSJTJ(String STid ,String jieguo)
			    {
			    	DBUtil.deleteSJTJ(STid);
			    	String a="ok";
			    	try
			    	{
			    		createOrOpenDatabase();
			    		String sql;
			        	sql="insert into shujutongji values('"+STid+"','"+jieguo+"');";	
			        	sld.execSQL(sql);
						closeDatabase();
			        	}
					catch(Exception e)
					{
						a="插入出现异常  检查吧";
					}
			    	return a;
			    }
			  //删除数据统计数据   根据传进来的试题ID
				public static void deleteSJTJ(String STid ) 
				 {
					
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="delete from shujutongji where sjtj_q_id='"+STid+"';";
			   		sld.execSQL(sql);
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				 }
				//删除数据统计数据  all
				public static void deleteAllSJTJ() 
				 {
					
					try
					{
						
					    createOrOpenDatabase();//打开数据库
			   		String sql="delete from shujutongji;";
			   		sld.execSQL(sql);
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				 }
				//插入记录的方法    插入考题整理表
			    public static void insertqzhengli(String [][]sque)
			    {
			    	try
			    	{
			    		createOrOpenDatabase();
			        	String sql;
			        	for(int i=0;i<sque.length;i++)
						{	
			        		sql="insert into qzhengli values('"+sque[i][0]+"','"+sque[i][1]+"');";
							sld.execSQL(sql);
						}
			        	closeDatabase();
			        	}
					catch(Exception e)
					{
						e.printStackTrace();
					}
			    }
			  //根据科目 车型得到试题的总数
				public static String getquanbucount(String kemu,String chexing) 
				 {
					String pid = null;
					try
					{ 
						createOrOpenDatabase();//打开数据库
			   		String sql="select count(question.q_id) from question,qzhengli where  question.q_subject='"+kemu+"' and qzhengli.qz_car_id='"+chexing+"' and question.q_id=qzhengli.qz_que_id;";
			   		Cursor cur=sld.rawQuery(sql, null);
			   		while(cur.moveToNext())
			   		{
			   			pid=cur.getString(0);
			   		}
			   		cur.close();
			   		closeDatabase();
			   		
					}
					catch(Exception e)
					{
						pid="获取内容时   抛出异常";
					}
					if(pid==null)
					{
						pid="0";
					}
					return pid;
				 }
				
		//选车表所用方法
		//删除表中所有内容
		public static void deleteCar() 
		 {
			
			try
			{
				
			createOrOpenDatabase();//打开数据库
	   		String sql="delete from select_car;";
	   		sld.execSQL(sql);
	   		closeDatabase();
	   		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		 }		
		//插入记录的方法    插入试题全部
	    public static void insertCar(String id)
	    {
	    	try
	    	{
	    		createOrOpenDatabase();
	        	String sql;
        		sql="insert into select_car values('"+id+"');";
				sld.execSQL(sql);
	        	closeDatabase();
	        	}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	    }
	   //得到数据
		public static String getCar() 
		 {
			String pid=new String();
			@SuppressWarnings("unused")
			int i=0;
			try
			{
				createOrOpenDatabase();//打开数据库
	   		String sql="select * from select_car;";
	   		Cursor cur=sld.rawQuery(sql, null);
	   		while(cur.moveToNext())
	   		{
	   			pid=cur.getString(0);
	   		}
	   		cur.close();
	   		closeDatabase();
	   		
			}
			catch(Exception e)
			{
				pid="获取答案选项内容时   抛出异常";
			}
			return pid;
		 }
		//得到试题的最大数目
		public static String getcarrrCount() 
		 {
			String pid = null;
			try
			{
				
			    createOrOpenDatabase();//打开数据库
	   		String sql="select count(*) from select_car;";
	   		Cursor cur=sld.rawQuery(sql, null);
	   		while(cur.moveToNext())
	   		{
	   			pid=cur.getString(0);
	   		}
	   		cur.close();
	   		closeDatabase();
	   		
			}
			catch(Exception e)
			{
				pid="获取内容时   抛出异常";
			}
			return pid;
			
		 }
		//插入记录的方法    插入错题表
	    public static void insertALLcuoti(String [][]sque)
	    {
	    	try
	    	{
	    		createOrOpenDatabase();
	        	String sql;
	        	int cuotiid=0;
	        	for(int i=0;i<sque.length;i++)
				{
	        		cuotiid++;
	        		String cuotideID=fangfa.zhuanhuanSTid(cuotiid);
	        		sql="insert into cuoti values('"+cuotideID+"','"+sque[i][0]+"');";
					sld.execSQL(sql);
				}
	        	closeDatabase();
	        	}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	    }

			    
				
		
}

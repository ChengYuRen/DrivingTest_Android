package com.bn.driversystem_android.cheyouquan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.bn.driversystem_android.CheYouQuanActivity;
import com.bn.driversystem_android.R;
import com.bn.driversystem_android.kemuyi.fangfa;
import com.bn.fyq.Constant.Constant;
import com.bn.util.SocketClient;

public class fabutiezi extends Activity{
	String tiemainadd[]=new String [6];//tiemain  的添加  共有6条属性
	private EditText edittext_biaoti,edittext_neirong;
	private ImageView imageview_fabu;
	StringBuilder sadd=new StringBuilder();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.fabutiezi);
				
				//返回按钮
				 ImageView subject_two_kaogui_title;
				subject_two_kaogui_title=(ImageView)findViewById(R.id.fanhui_lt);
				subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
				{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//相当于手机的返回按钮
						fabutiezi.this.finish();
					}
					
				}
				);
				
				edittext_biaoti=(EditText)findViewById(R.id.edittext_biaoti);
				edittext_neirong=(EditText)findViewById(R.id.edittext_neirong);
				imageview_fabu=(ImageView)findViewById(R.id.imageview_fabu);
				imageview_fabu.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//新开一个帖子
							tiemainadd[1]=CheYouQuanActivity.luntanXX;//得到所要开贴的类别Id
							SocketClient.ConnectSevert(Constant.getTiemainMaxID);
							String String_getid=SocketClient.readinfo;//得到主贴类别最大ID
							int int_getid=Integer.parseInt(String_getid);
							int_getid++;
							tiemainadd[0]=com.bn.driversystem_android.kemuyi.fangfa.zhuanhuanSTid(int_getid);//现在得到可输入ID
							tiemainadd[2]="00001";//这里的用户先写00001   看晴儿是怎么获取的谁登陆的就行
							//Toast.makeText(fabutiezi.this, maxid, Toast.LENGTH_SHORT).show();
							tiemainadd[3]=edittext_biaoti.getText().toString();
							tiemainadd[4]=edittext_neirong.getText().toString();
							if(tiemainadd[3].length()==0||tiemainadd[4].length()==0)
							{
								Toast.makeText(fabutiezi.this, "客官，请输入标题/内容", Toast.LENGTH_SHORT).show();
								return;
							}
							tiemainadd[5]=fangfa.huoqushijian();//得到时间
							for(int i=0;i<tiemainadd.length;i++)
							{
								sadd.append(Constant.addTiemain+tiemainadd[i]);
							}
							SocketClient.ConnectSevert(sadd.toString());
							String isok=SocketClient.readinfo;
							if(isok.equals("ok"))
							{
								fabutiezi.this.finish();
								//应该根据那个跳转的 就返回那个activity
								startActivity(new Intent().setClass(fabutiezi.this, keyijiaoliuActivity.class));	
							}
							else
							{
								Toast.makeText(fabutiezi.this, "哦哦  貌似失败了", Toast.LENGTH_SHORT).show();
							}
					}
				});
	}
}

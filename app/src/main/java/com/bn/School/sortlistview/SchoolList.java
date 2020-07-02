package com.bn.School.sortlistview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bn.School.sortlistview.SideBar.OnTouchingLetterChangedListener;
import com.bn.driversystem_android.MainActivity;
import com.bn.driversystem_android.R;
import com.bn.fyq.Constant.Constant;
import com.bn.util.SocketClient;
import com.example.sortlistview.CityList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class SchoolList extends Activity {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	
	public static String City_GetIt;
	//private TextView city_GetIt;
	public static int Sigin_City=0;//是否选中城市的标志位
	
	TextView noSchool;//未报考学校
	
	//定位
	TextView textView1;
	private LocationClient mLocClient;
	private TextView tLocation;
	
	//private String getCity;
	
	public static String GET_SCHOOLLIST;//得到驾校内容
	public static int SCHOOLLIST_OK=0;//0代表未从列表这里选择 
	
	public static boolean CunCuOkOrNot_School=false;//是否存储标志位
	
	public static String GetSchoolListByCunCu;//得到存储的数据
	
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.select_school);
		System.out.println("=============dfjadslfkjdaslfa");
		initViews();
		
		//返回按钮
		ImageView subject_two_kaogui_title;
		subject_two_kaogui_title=(ImageView)findViewById(R.id.button_subject_two_kaogui_title);
		subject_two_kaogui_title.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//相当于手机的返回按钮
				SchoolList.this.finish();
			}
			
		}
		);
		
		
		//getCity=CityList.City_GetIt;

		//点击定位的监听
		tLocation=(TextView)findViewById(R.id.textview_location);
		tLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(SchoolList.this, CityList.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
				startActivity(intent);
//				//跳转之后把自己finish掉
//				SchoolList.this.finish();
			}
		});
		
		
		
		
		if(CityList.City_GetIt!=null)
		{
			tLocation.setText(CityList.City_GetIt);
		}
		else
		{
			//定位
			mLocClient = ((Location)getApplication()).mLocationClient;
			textView1 = (TextView)findViewById(R.id.textview_location);
			((Location)getApplication()).mTv = textView1;
			setLocationOption();
			mLocClient.start();
			mLocClient.requestLocation();
		}
	}

	/*
	 * 此处为设置完全退出程序后 再次进入 保留自己所选择的车型
	 */
	//数据读取方法
	private void loadData(Context context) {
	    SharedPreferences sp = context.getSharedPreferences("config", MODE_PRIVATE);
	    //Toast.makeText(this, sp.getString("content", "").toString(), 0).show();
	    GetSchoolListByCunCu=sp.getString("content", "").toString();
	  }
	//对数据进行存储
	private void saveData(Context context,String string){
	    SharedPreferences sp = context.getSharedPreferences("config", MODE_PRIVATE);
	    Editor editor = sp.edit();
	    editor.putString("content", string);
	    editor.commit();
	    //CunCuOkOrNot_School=true;//存储完成后标志位设为 真
	  }
	
	// 定位设置相关参数
	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
//				option.setOpenGps(true); // 打开gps
		option.setCoorType("bd009ll"); // 设置坐标类型
		option.setServiceName("com.baidu.location.service_v2.9");
		option.setPoiExtraInfo(true);

		option.setAddrType("all");

		option.setScanSpan(3000);

		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先

		option.setPoiNumber(10);
		option.disableCache(true);
		mLocClient.setLocOption(option);
	}
	
	
	private void initViews() {
		//实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		
		//设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			@Override
			public void onTouchingLetterChanged(String s) {
				//该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}
				
			}
		});
		
		noSchool= (TextView) findViewById(R.id.textview_NOSchool);
		noSchool.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SCHOOLLIST_OK=1;//驾校是否点击的标志位
				GET_SCHOOLLIST="未报考驾校";
				Intent intent=new Intent();
				intent.setClass(SchoolList.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
				startActivity(intent);
			}
		});
		
		
			
		
		
		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//这里要利用adapter.getItem(position)来获取当前position所对应的对象
			
				GET_SCHOOLLIST=((SortModel)adapter.getItem(position)).getName().toString();//得到所选值
				
				saveData(SchoolList.this,GET_SCHOOLLIST);//存储驾校的值
				CunCuOkOrNot_School=true;
				loadData(SchoolList.this);//读取的值
				
				SCHOOLLIST_OK=1;//驾校是否点击的标志位
				Intent intent=new Intent();
				intent.setClass(SchoolList.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行的FLAG设置  实现了退出时不会再显示登录界面的问题
				startActivity(intent);
				
			}
		});
		
		if(CityList.City_GetIt==null)
		{
			String userinfo=null;
			SocketClient.ConnectSevert(Constant.getDrivescCityName+"唐山");
			userinfo=SocketClient.readinfo;
			String[] sgo=userinfo.split("#");//删除最后的#号  将每条数据写于 数组中  接收为： s[0]=“10001η范雨晴η男”  s[1]同理
	 		String[] sreply=null;
			String[] getIt=new String[sgo.length];//用于接收最后的各个账号
			for(int i=0;i<sgo.length;i++)
			{
				 sreply=sgo[i].split("η");
				 //System.out.println("string="+sreply[0]);//srepley[0]是分割后的 u1 u2等
				 getIt[i]=sreply[0];
			}
			
			SourceDateList = filledData(getIt);
		}
		else
		{
			//获得所在城市驾校
			String userinfo=null;
			SocketClient.ConnectSevert(Constant.getDrivescCityName+CityList.City_GetIt);
			userinfo=SocketClient.readinfo;
			String[] sgo=userinfo.split("#");//删除最后的#号  将每条数据写于 数组中  接收为： s[0]=“10001η范雨晴η男”  s[1]同理
	 		String[] sreply=null;
			String[] getIt=new String[sgo.length];//用于接收最后的各个账号
			for(int i=0;i<sgo.length;i++)
			{
				 sreply=sgo[i].split("η");
				 //System.out.println("string="+sreply[0]);//srepley[0]是分割后的 u1 u2等
				 getIt[i]=sreply[0];
			}	
			SourceDateList = filledData(getIt);
		}
		

		
//		if(CityList.City_GetIt==null)
//		{
//			SourceDateList = filledData(getResources().getStringArray(R.array.schoollist_lunan));
//		}
//		//字符串值相等=========不是直接等于#112
//		else if(CityList.City_GetIt.equals("保定"))
//		{
//			SourceDateList = filledData(getResources().getStringArray(R.array.schoollist_lunan));
//		}
//		else if(CityList.City_GetIt.equals("丰南区"))
//		{
//			SourceDateList = filledData(getResources().getStringArray(R.array.schoollist_fengnan));
//		}
//		else if(CityList.City_GetIt.equals("曹妃甸"))
//		{
//			SourceDateList = filledData(getResources().getStringArray(R.array.schoollist_caofeidian));
//		}
		
		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);
		
		
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		
		//根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}


	/**
	 * 为ListView填充数据
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String [] date){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
		for(int i=0; i<date.length; i++){
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			//汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// 正则表达式，判断首字母是否是英文字母
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			
			mSortList.add(sortModel);
		}
		return mSortList;
		
	}
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	@SuppressLint("DefaultLocale")
	private void filterData(String filterStr){
		List<SortModel> filterDateList = new ArrayList<SortModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(SortModel sortModel : SourceDateList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
	
}

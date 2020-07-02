package com.bn.fyq.Constant;

public class Constant {

	public final static String IP = "192.168.137.1";
    public final static int POINT = 9999;
    public static final String SOCKET_ERROR="0";
	public static final String SOCKET_IOERROR="1";
	public static final String TESTCONNECT = "TESTCONNECT";//测试连接
	
	//驾校信息
	
	public static final String JiaXiao_XinXi="JiaXiao_XinXi";
	//试题信息
	public static final String ADD_QUESTION="ADD_QUESTION";
	public static final String getQuestionByid="getQuestionByid"; //根据id得到试题其他信息
	public static final String getAnswerqByid="getAnswerqByid"; //根据id 找到试题的所欲选项内容
	public static final String getAnswerById="getAnswerById"; //根据id 找到试题的所欲选项内容
	public static final String updateQuestion="updateQuestion"; //根据id  更改试题内容
	public static final String delAnswer="delAnswer"; //根据id  删除试题全部达纳
	public static final String updateAnswerall="updateAnswerall"; //根据答案id 及试题id  更改其他信息
	public static final String getAnswerAByid="getAnswerAByid"; //得到试题的A 选线位置
	public static final String getAnswerneirongByid="getAnswerneirongByid";//得到id试题的大难内容
	public static final String getQuestion="getQuestion";//得到所有的试题
	public static final String getAnswerAll="getAnswerAll";//得到所有的试题
	public static final String getimage="getimage";//向服务器得到图片
	public static final String getTiemainbyclass="getTiemainbyclass";//得到  该类别  所有发过的帖子
	public static final String getUserNameById="getUserNameById";//根据id查询用户昵称
	public static final String getUsertouxiangByid="getUsertouxiangByid";//根据id查询用户头像
	
	//手机端账号登录 及密码找回 PHONE_LOGIN
	public static final String PHONE_LOGIN="PHONE_LOGIN"; //判断登录
	
	public static int LOGIN_OK=0;//判断手机端登录与否
	public static final String PERSON_ALL="PERSON_ALL"; //用户信息
	public static final String UPDATE_PERSON_SEX_MAN="UPDATE_PERSON_SEX_MAN";//修改性别为男性
	public static final String UPDATE_PERSON_SEX_WOMAN="UPDATE_PERSON_SEX_WOMAN";//修改性别为女性
	public static final String UPDATE_PERSON_NICHENG="UPDATE_PERSON_NICHENG";//更新昵称
	public static final String UPDATE_PERSON_PHONE="UPDATE_PERSON_PHONE";//更新电话
	public static final String GET_MAXUSER_ID_FORPHOEN="GET_MAXUSER_ID_FORPHOEN";//得到用户最大ID
	public static final String FORPHONE="FORPHONE";//添加手机用户
	public static final String GET_USER_ZHANGHAO_FORPHONE="GET_USER_ZHANGHAO_FORPHONE";//得到手机用户的账号
	public static final String GET_USER_PASSWORD_FORPHONE="GET_USER_PASSWORD_FORPHONE";//找回密码
	public static final String GET_SUBJECT_ONE_FOR_XIAOCHE="GET_SUBJECT_ONE_FOR_XIAOCHE";//获取小车科目一的题目总数
	public static final String GET_SUBJECT_FOUR_FOR_XIAOCHE="GET_SUBJECT_FOUR_FOR_XIAOCHE";//获取小车科目四的题目总数
	
	public static final String GET_REPLYbyid="GET_REPLYbyid";//得到所有的回复   根据id
	public static final String getTiemainIDByName="getTiemainIDByName";//根据题目内容查询题目ID
	
	public static final String updateUserTouxiang="updateUserTouxiang";//更改用户头像图片
	public static final String GETID_BY_ZHANGHAO="GETID_BY_ZHANGHAO";//由用户账号 得到ID
	
	public static final String getReplyMaxID="getReplyMaxID";//得到回复的最大ID
	public static final String addReply="addReply";//添加一个新的回复
	public static final String addTiemain="addTiemain";//添加一个新的帖子
	public static final String getTiemainMaxID="getTiemainMaxID";//得到主贴表的最大ID
	
	public static final String GET_ADDRESS_BY_NAMEANDCARID="GET_ADDRESS_BY_NAMEANDCARID";//通过视频名称和视频对应的小车编号得到 视频的地址
	public static final String getQzhengli="getQzhengli";//得到考题整理表信息
	//5-5
	public static final String getErrorMaxID="getErrorMaxID";//得到错题表最大ID
	public static final String addError="addError";//添加错题表
	public static final String delErrorByu="delErrorByu";//根据用户删除错题表
	public static final String gete_que_idByu="gete_que_idByu";//根据用户得到错误试题的编号
	public static final String getErrorCount="getErrorCount";//根据用户得到错误试题的编号
	public static final String getUserzhanghaoByid="getUserzhanghaoByid";//根据用户ID得到一户账号
	public static final String getDrivescCity="getDrivescCity";//查询驾校中城市  所有城市
	public static final String getDrivescCityName="getDrivescCityName";//查询城市中驾校
}

package com.bn.util;

import android.util.Log;

/**
 * Log缁熶竴绠＄悊绫?
 * 
 * @author way
 * 
 */
public class L
{

	private L()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = true;// 鏄惁闇?鎵撳嵃bug锛屽彲浠ュ湪application鐨刼nCreate鍑芥暟閲岄潰鍒濆鍖?
	private static final String TAG = "way";

	// 涓嬮潰鍥涗釜鏄粯璁ag鐨勫嚱鏁?
	public static void i(String msg)
	{
		if (isDebug)
			Log.i(TAG, msg);
	}

	public static void d(String msg)
	{
		if (isDebug)
			Log.d(TAG, msg);
	}

	public static void e(String msg)
	{
		if (isDebug)
			Log.e(TAG, msg);
	}

	public static void v(String msg)
	{
		if (isDebug)
			Log.v(TAG, msg);
	}

	// 涓嬮潰鏄紶鍏ヨ嚜瀹氫箟tag鐨勫嚱鏁?
	public static void i(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void e(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void v(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}
}
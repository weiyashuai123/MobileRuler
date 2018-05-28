package com.mobileruler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * 本工程所有Activity 的父类，存放着一些基本配置、标志位信息及常量.
 *
 * @author weiyashuai
 * @date 2017-9-01 10:11:48
 */
public class BaseActivity extends Activity {

	public final static String TAG = "my_log";
	public final static String DB_NAME = "AutoForm.db";
	private int mScreenWidth;
	private int mSreenHeight;

	private AlertDialog.Builder mAlertDailog;

	public final static int SUCCESS = 0;
	public final static int FAILED = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initWindows();
		mAlertDailog = new AlertDialog.Builder(this);
	}

	private void initWindows() {
		Point outSize = new Point();
		getWindowManager().getDefaultDisplay().getSize(outSize);
		mScreenWidth = outSize.x;
		mSreenHeight = outSize.y;
	}

	/**
	 * 得到屏幕宽度
	 * 
	 * @return 屏幕宽度（px）
	 */
	public int getScreenWidth() {
		return mScreenWidth;
	}

	/**
	 * 得到屏幕高度
	 * 
	 * @return 屏幕高度（px）
	 */
	public int getScreenHeight() {
		return mSreenHeight;
	}

	/**
	 * 简化的toast方法
	 */
	public void toast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	public void quickStartActivity(Class<?> Activity) {
		Intent intent = new Intent(this, Activity);
		startActivity(intent);
	}

	// 简化的log方法
	public void logD(String message) {
		Log.d(TAG, message);
	}

	public void logE(String message) {
		Log.e(TAG, message);
	}

	public void logI(String message) {
		Log.i(TAG, message);
	}

	public void logV(String message) {
		Log.v(TAG, message);
	}

	public void logW(String message) {
		Log.w(TAG, message);
	}

	public void showErrorDialog(String message) {
		mAlertDailog.setTitle("错误");
		mAlertDailog.setMessage(message);
		mAlertDailog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				finish();
			}
		});
		mAlertDailog.setPositiveButton("重试", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				methodRetry();
			}
		});
	}

	protected void methodRetry() {
	}
}

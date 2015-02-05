/**
 * 
 * Copyright © 2014VanKe. All rights reserved.
 *
 * @Title: SharePreferenceUtil.java
 * @Prject: zhuzher
 * @Package: com.zhuzher.util
 * @Description: 基本数据存储类
 * @author: 
 * @date: 
 * @version: V3.0
 */
package com.symagic.utils;

import com.example.mylitepal.R;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {

	SharedPreferences spInfo;

	/**
	 * 初始化SharedPreferences
	 */
	public SharePreferenceUtil(Context context) {
		spInfo = context.getSharedPreferences("user_setting", 0);
	}

	/**
	 * 设置文字背景颜色
	 * @param colorValue
	 */
	public void setTextBgColor(int colorValue) {
		spInfo.edit().putInt("bg_color", colorValue).commit();
	}

	/**
	 * 获取文字背景颜色
	 * @return
	 */
	public int getTextBgColor() {
		return spInfo.getInt("bg_color", R.color.text_bg_transprent);
	}

}

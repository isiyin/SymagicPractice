package com.symagic.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.annotation.SuppressLint;

public class StringUtil {

	/**
	 * 检验手机号码
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static boolean checkIsPhoneNum(String phoneNum) {
		// String regFormat = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		String regFormat = "^(1[0-9][0-9])\\d{8}$";
		if (phoneNum.matches(regFormat))
			return true;
		else
			return false;
	}

	/**
	 * 判断字符串是否为"空格",""或null StringUtils.isBlank(null) = true
	 * StringUtils.isBlank("") = true StringUtils.isBlank(" ") = true
	 * StringUtils.isBlank("bob") = false StringUtils.isBlank("  bob  ") = false
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(cs.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否不为"空格",""或null
	 * 
	 * @see StringUtil#isBlank(CharSequence)
	 * @param cs
	 * @return
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !StringUtil.isBlank(cs);
	}

	/**
	 * 判断字符串是为""或null StringUtils.isEmpty(null) = true StringUtils.isEmpty("") =
	 * true StringUtils.isEmpty(" ") = false StringUtils.isEmpty("bob") = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * 判断字符串是不为""或null
	 * 
	 * @see StringUtil#isEmpty(CharSequence)
	 * @param cs
	 * @return
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return !StringUtil.isEmpty(cs);
	}

	/**
	 * 判断字符串是否只包含unicode数字 StringUtils.isNumeric(null) = false
	 * StringUtils.isNumeric("") = false StringUtils.isNumeric("  ") = false
	 * StringUtils.isNumeric("123") = true StringUtils.isNumeric("12 3") = false
	 * StringUtils.isNumeric("ab2c") = false StringUtils.isNumeric("12-3") =
	 * false StringUtils.isNumeric("12.3") = false
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isNumeric(CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证邮箱的正则表达式 合法E-mail地址： 1. 必须包含一个并且只有一个符号“@” 2. 第一个字符不得是“@”或者“.” 3.
	 * 不允许出现“@.”或者.@ 4. 结尾不得是字符“@”或者“.” 5. 允许“@”前的字符中出现“＋” 6. 不允许“＋”在最前面，或者“＋@”
	 */
	public static boolean isEmail(String email) {
		String format = "^([a-z0-9A-Z]+[_|-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if (email.matches(format)) {
			return true;// 邮箱名合法，返回true
		} else {
			return false;// 邮箱名不合法，返回false
		}
	}

	/**
	 * 注意,只替换首字符replace("112341234", "1", "c") 结果是:c12341234
	 * 
	 * @param line
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static final String replace(String line, String oldString,
			String newString) {
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			buf.append(line2, i, line2.length - i);
			return buf.toString();
		}
		return line;
	}

	public static int toInt(String str, int def) {

		if (str == null || isBlank(str) || !isNumeric(str)) {
			return def;
		}

		return Integer.valueOf(str);
	}

	public static byte[] getBytesUtf8(String string) {
		if (string == null) {
			return null;
		}

		try {
			return string.getBytes("UTF-8");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/***
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 将String 首字母大写 如zhansan 转为 ZhanSan
	 */
	@SuppressLint("DefaultLocale")
	private static String toUpCase(String str) {
		StringBuffer newstr = new StringBuffer();
		String upperCase = (str.substring(0, 1)
				.toUpperCase(Locale.getDefault()));
		newstr.append(upperCase).append(str.substring(1, str.length()));
		return newstr.toString();
	}

	/**
	 * 获取6位随机数字验证码
	 * 
	 * @return
	 */
	public static String getRandomVeriCode() {
		Random random = new Random();
		String str = "";
		for (int i = 0; i < 6; i++) {
			String rand = String.valueOf(random.nextInt(10));
			str += rand;
		}
		return str;
	}

	/**
	 * 获取4位随机数字
	 * 
	 * @return
	 */
	public static String get4Random() {
		Random random = new Random();
		String str = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			str += rand;
		}
		return str;
	}

	/**
	 * 把字符串转换为浮点数，len为保留的小数点位数
	 * 
	 * @return
	 */
	public static float toFloat(String str, int len) {
		float v = Float.parseFloat(str);
		BigDecimal bd = new BigDecimal(v);
		bd.setScale(len, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
		/*
		 * float fee = 0.00f; float average = Float.valueOf(str); fee = fee +
		 * average; fee = (float)(Math.round(fee*100))/100; return fee;
		 */
	}

	/**
	 * 把浮点数转换为字符串，保留两位小数点
	 * 
	 * @return
	 */
	public static String toFloatString(float f) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(f);
	}

	public static String getNumWithStar(String srcStr, int starAmount,
			int tailAmount) {
		if (srcStr.length() <= starAmount) {
			String tmpStr = "";
			for (int i = 0; i < starAmount; i++)
				tmpStr = tmpStr + "*";
			return tmpStr;
		} else if (srcStr.length() < tailAmount + starAmount) {
			String tmpStr = "";
			for (int i = 0; i < starAmount; i++)
				tmpStr = tmpStr + "*";
			tmpStr = tmpStr
					+ srcStr.substring(srcStr.length() - starAmount + 1,
							srcStr.length());
			return tmpStr;
		} else {
			String desStr = srcStr.substring(0, srcStr.length() - starAmount
					- tailAmount);
			for (int i = 0; i < starAmount; i++) {
				desStr = desStr + "*";
			}
			desStr = desStr
					+ srcStr.substring(srcStr.length() - tailAmount,
							srcStr.length());
			return desStr;
		}
	}

}

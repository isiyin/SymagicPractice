package com.symagic.utils;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.util.EncodingUtils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.symagic.bean.City;

public class Utils {
	public static List<City> getCityListFromJson(Context context) {
		List<City> cityList = new ArrayList<City>();
		String res = "";
		try {
			InputStream in = null;
			in = context.getAssets().open("city.json");
			int length = in.available();
			byte[] buffer = new byte[length];
			in.read(buffer);
			in.close();
			res = EncodingUtils.getString(buffer, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<City>>() {
		}.getType();
		cityList = gson.fromJson(res, type);
		return sortCityList(cityList);
	}

	/**
	 * 城市排序
	 */
	public static List<City> sortCityList(List<City> contactList) {
		List<City> sortList = new ArrayList<City>();
		List<String> sortKeyList = new ArrayList<String>();
		for (int i = 0; i < contactList.size(); i++) {
			sortKeyList.add(contactList.get(i).getSortKey());
		}
		RuleBasedCollator cmp = (RuleBasedCollator) java.text.Collator
				.getInstance(java.util.Locale.CHINA);
		Collections.sort(sortKeyList, cmp);

		for (int i = 0; i < sortKeyList.size(); i++) {
			String sortKey = (String) sortKeyList.get(i);
			for (int j = 0; j < contactList.size(); j++) {
				if (sortKey.equals(contactList.get(j).getSortKey())) {
					sortList.add(contactList.get(j));
					contactList.remove(j);
					break;
				}
			}
		}

		return sortList;
	}

	public static int getDrawableResourceId(Context context, String picName) {
		try {
			return context.getResources().getIdentifier(picName, "drawable",
					context.getPackageName());

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}

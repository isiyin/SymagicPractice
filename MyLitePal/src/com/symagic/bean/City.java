package com.symagic.bean;

import org.litepal.crud.DataSupport;

public class City extends DataSupport{
	private int id;
	private String city; // 城市
	private String sortKey; // 排序key
	private String cityId; // 城市代号

	/**
	 * 设置获取城市名称
	 */
	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	/**
	 * 设置获取排序�?
	 */
	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getSortKey() {
		return sortKey;
	}

	/**
	 * 设置获取城市代号
	 */
	public void setCityId(String id) {
		this.cityId = id;
	}

	public String getCityId() {
		return cityId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public City() {
	}

	public City(String city, String sortKey, String id) {
		this.city = city;
		this.sortKey = sortKey;
		this.cityId = id;
	}

}

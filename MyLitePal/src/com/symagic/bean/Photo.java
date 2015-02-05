package com.symagic.bean;

public class Photo {
	private String picName;
	private String description;

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Photo(String picName, String description) {
		super();
		this.picName = picName;
		this.description = description;
	}

}

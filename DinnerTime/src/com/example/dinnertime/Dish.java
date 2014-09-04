package com.example.dinnertime;

import android.graphics.drawable.Drawable;

public class Dish {
	
	public Dish() {
		super();
	}
	public Dish(String name, String type, Drawable image) {
		super();
		this.name = name;
		this.type = type;
		this.image = image;
	}
	private String name;
	private String type;
	private Drawable image;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Drawable getImage() {
		return image;
	}
	public void setImage(Drawable image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

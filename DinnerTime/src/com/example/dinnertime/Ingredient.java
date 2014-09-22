package com.example.dinnertime;

import java.io.Serializable;

public class Ingredient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean mCheckBox;
	private String mIngredient;
	private String mAmount;
	
	public String getmAmount() {
		return mAmount;
	}
	public void setmAmount(String mAmount) {
		this.mAmount = mAmount;
	}
	public boolean ismCheckBox() {
		return mCheckBox;
	}
	public Ingredient(){
		
	}
	public Ingredient(String mIngredient) {
		super();
		this.mIngredient = mIngredient;
	}
	public void setmCheckBox(boolean mCheckBox) {
		this.mCheckBox = mCheckBox;
	}
	public String getmIngredient() {
		return mIngredient;
	}
	public void setmIngredient(String mIngredient) {
		this.mIngredient = mIngredient;
	}
}

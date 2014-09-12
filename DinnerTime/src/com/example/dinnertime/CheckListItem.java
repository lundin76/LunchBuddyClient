package com.example.dinnertime;

public class CheckListItem {
	private boolean mCheckBox;
	private String mIngredient;
	public boolean ismCheckBox() {
		return mCheckBox;
	}
	public CheckListItem(String mIngredient) {
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

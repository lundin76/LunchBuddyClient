package com.example.dinnertime;

import java.io.Serializable;
import java.util.ArrayList;

import android.graphics.drawable.Drawable;

public class Dish implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mName;
	private String mType;
	private transient Drawable mImage;
	private String mImageName;
	private String mInstructions="Directions Melt the stick of butter in a large pot over medium heat. Add the onions, garlic, bay leaves, thyme, and salt and pepper and cook until the onions are very soft and caramelized, about 25 minutes. Add the wine, bring to a boil, reduce the heat and simmer until the wine has evaporated and the onions are dry, about 5 minutes. Discard the bay leaves and thyme sprigs. Dust the onions with the flour and give them a stir. Turn the heat down to medium low so the flour doesn't burn, and cook for 10 minutes to cook out the raw flour taste. Now add the beef broth, bring the soup back to a simmer, and cook for 10 minutes. Season, to taste, with salt and pepper. When you're ready to eat, preheat the broiler. Arrange the baguette slices on a baking sheet in a single layer. Sprinkle the slices with the Gruyere and broil until bubbly and golden brown, 3 to 5 minutes. Ladle the soup in bowls and float several of the Gruyere croutons on top. Alternative method: Ladle the soup into bowls, top each with 2 slices of bread and top with cheese. Put the bowls into the oven to toast the bread and melt the cheese. Read more at: http://www.foodnetwork.com/recipes/tyler-florence/french-onion-soup-recipe2.html?oc=linkback";
	private ArrayList<Ingredient> mIngredients;
	

	public Dish() {
		super();
		mIngredients = new ArrayList<Ingredient>();
	}
	public Dish(String mName, String mType, Drawable mImage, String mImageName, ArrayList<Ingredient> mIngredients) {
		super();
		this.mName = mName;
		this.mType = mType;
		this.mImage = mImage;
		this.mImageName=mImageName;
			
		mIngredients = new ArrayList<Ingredient>();
	}
	
	
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public Drawable getmImage() {
		return mImage;
	}
	public void setmImage(Drawable mImage) {
		this.mImage = mImage;
	}
	public String getmImageName() {
		return mImageName;
	}
	public void setmImageName(String mImageName) {
		this.mImageName = mImageName;
	}
	public String getmInstructions() {
		return mInstructions;
	}
	public void setmInstructions(String mInstructions) {
		this.mInstructions = mInstructions;
	}
	public ArrayList<Ingredient> getmIngredients() {
		return mIngredients;
	}
	public void setmIngredients(ArrayList<Ingredient> mIngredients) {
		this.mIngredients = mIngredients;
	}
		
	
		
}

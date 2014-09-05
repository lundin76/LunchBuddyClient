package com.example.dinnertime;

import android.graphics.drawable.Drawable;

public class Dish {
	
	public Dish() {
		super();
	}
	public Dish(String name, String type, Drawable image, String imageName) {
		super();
		this.name = name;
		this.type = type;
		this.image = image;
		this.setImageName(imageName);
	}
	
	private String name;
	private String type;
	private Drawable image;
	private String imageName;
	private String instructions="Directions Melt the stick of butter in a large pot over medium heat. Add the onions, garlic, bay leaves, thyme, and salt and pepper and cook until the onions are very soft and caramelized, about 25 minutes. Add the wine, bring to a boil, reduce the heat and simmer until the wine has evaporated and the onions are dry, about 5 minutes. Discard the bay leaves and thyme sprigs. Dust the onions with the flour and give them a stir. Turn the heat down to medium low so the flour doesn't burn, and cook for 10 minutes to cook out the raw flour taste. Now add the beef broth, bring the soup back to a simmer, and cook for 10 minutes. Season, to taste, with salt and pepper. When you're ready to eat, preheat the broiler. Arrange the baguette slices on a baking sheet in a single layer. Sprinkle the slices with the Gruyere and broil until bubbly and golden brown, 3 to 5 minutes. Ladle the soup in bowls and float several of the Gruyere croutons on top. Alternative method: Ladle the soup into bowls, top each with 2 slices of bread and top with cheese. Put the bowls into the oven to toast the bread and melt the cheese. Read more at: http://www.foodnetwork.com/recipes/tyler-florence/french-onion-soup-recipe2.html?oc=linkback";
	
		
	
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
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}

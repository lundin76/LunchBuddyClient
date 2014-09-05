package com.example.dinnertime;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DishActivity extends Activity {
	
	private Dish mDish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.main_dish_layout);
		 
		Bundle b =  getIntent().getExtras();		
		Drawable d = getResources().getDrawable((Integer) b.get(String.valueOf(MainActivity.DISH_KEY_IMAGE_ID)));
		mDish = new Dish((String)b.get(MainActivity.DISH_KEY_NAME), (String)b.get(MainActivity.DISH_KEY_TYPE), d, (String) b.get(MainActivity.DISH_KEY_IMAGE_NAME));
		
		populate();
		
		super.onCreate(savedInstanceState);
	}
	private void populate(){
		TextView tv = (TextView) this.findViewById(R.id.dishdescriptionTxt);
		tv.setText(mDish.getInstructions());
		
		ImageView iv = (ImageView) this.findViewById(R.id.dishImageView);
		iv.setImageDrawable(mDish.getImage());
		 	
		
	}
	
}

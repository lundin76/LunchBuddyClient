package com.example.dinnertime;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DishActivity extends Activity implements ISetImage{
	
	private Dish mDish;
	private Button mIngredientsBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.main_dish_layout);
		
		mDish = (Dish) getIntent().getSerializableExtra(MainActivity.DISH_SERIALIZED_DISH);
				
		new ImageFetch((ISetImage)this).execute(mDish.getmImageName());
		populate();
		
		super.onCreate(savedInstanceState);
	}
	private void populate(){
		TextView tv = (TextView) this.findViewById(R.id.dishdescriptionTxt);
		tv.setText(mDish.getmInstructions());
		
		if(mDish.getmImage()!=null){
			ImageView iv = (ImageView) this.findViewById(R.id.dishImageView);			
			iv.setImageDrawable(mDish.getmImage());
		}
		
		mIngredientsBtn = (Button) this.findViewById(R.id.ingredientsBtn);
		mIngredientsBtn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
			
				LayoutInflater li = (LayoutInflater) DishActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				LinearLayout llmain =  (LinearLayout) li.inflate(R.layout.checklist_layout, null, false);
				ListView lv = (ListView) llmain.findViewById(R.id.checkListListView);
				
				
				Builder bu = new AlertDialog.Builder(DishActivity.this);					 
				bu.setView(llmain);	
					
				AlertDialog ad = bu.create();
				ad.show();
				CheckListAdapter cadapt = new CheckListAdapter(mDish.getmIngredients());
				
				lv.setAdapter(cadapt);				
				
			}});		
	}
	

	private class CheckListAdapter extends ArrayAdapter<Ingredient>{
		
		ArrayList<Ingredient> mCheckListItems;
		
		public CheckListAdapter(ArrayList<Ingredient> mCheckListItems) {
			super(DishActivity.this, R.layout.row_checklist_layout, mCheckListItems);
			System.out.println("Constructor " + mCheckListItems.size());
			
			this.mCheckListItems=mCheckListItems;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
						
			Ingredient item = mCheckListItems.get(position);
			LinearLayout ll = (LinearLayout) convertView;
			LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			if(ll == null){
				ll = (LinearLayout) li.inflate(R.layout.row_checklist_layout, parent, false);								
				TextView tv = (TextView) ll.findViewById(R.id.checklistText);
				tv.setText(item.getmIngredient());					
				CheckBox cb = (CheckBox) ll.findViewById(R.id.checkBox1);
				cb.setChecked(item.ismCheckBox());
				TextView tva = (TextView) ll.findViewById(R.id.checkListAmount);
				tva.setText(item.getmAmount());
			}else{
				TextView tv = (TextView) ll.findViewById(R.id.checklistText);
				tv.setText(item.getmIngredient());
				CheckBox cb = (CheckBox) ll.findViewById(R.id.checkBox1);
				cb.setChecked(item.ismCheckBox());
				TextView tva = (TextView) ll.findViewById(R.id.checkListAmount);
				tva.setText(item.getmAmount());
			}
			return ll;
			
		}
	}

	@Override
	public void setImage(Drawable image) {
		mDish.setmImage(image);
		((ImageView)findViewById(R.id.dishImageView)).setImageDrawable(mDish.getmImage());
		findViewById(R.id.dishImageView).invalidate();
			
	}
	
	 @Override
	 public Object onRetainNonConfigurationInstance() {
		 return mDish;
	 }
}

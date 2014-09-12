package com.example.dinnertime;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
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

public class DishActivity extends Activity {
	
	private Dish mDish;
	private Button mIngredientsBtn;
	private CheckListItem[] list={new CheckListItem("Mjölk"), new CheckListItem("Vetemjöl"), new CheckListItem("Ägg"), new CheckListItem("Sirap")};
	private ArrayList<CheckListItem> mItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.main_dish_layout);
		 
		mItems = new ArrayList<CheckListItem>();
		for(CheckListItem cli : list){
			mItems.add(cli);
		}
		
		Bundle b =  getIntent().getExtras();		
		Drawable d = getResources().getDrawable((Integer) b.get(String.valueOf(MainActivity.DISH_KEY_IMAGE_ID)));
		mDish = new Dish((String)b.get(MainActivity.DISH_KEY_NAME), (String)b.get(MainActivity.DISH_KEY_TYPE), d, (String) b.get(MainActivity.DISH_KEY_IMAGE_NAME));
		
		
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
				CheckListAdapter cadapt = new CheckListAdapter(mItems);
				
				lv.setAdapter(cadapt);				
				
			}});

		populate();
		
		super.onCreate(savedInstanceState);
	}
	private void populate(){
		TextView tv = (TextView) this.findViewById(R.id.dishdescriptionTxt);
		tv.setText(mDish.getInstructions());
		
		ImageView iv = (ImageView) this.findViewById(R.id.dishImageView);
		iv.setImageDrawable(mDish.getImage());
		 			
	}
	

	private class CheckListAdapter extends ArrayAdapter<CheckListItem>{
		
		ArrayList<CheckListItem> mCheckListItems;
		
		public CheckListAdapter(ArrayList<CheckListItem> mCheckListItems) {
			super(DishActivity.this, R.layout.row_checklist_layout, mCheckListItems);
			System.out.println("Constructor " + mCheckListItems.size());
			
			this.mCheckListItems=mCheckListItems;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
						
			CheckListItem item = mCheckListItems.get(position);
			LinearLayout ll = (LinearLayout) convertView;
			LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			if(ll == null){
				ll = (LinearLayout) li.inflate(R.layout.row_checklist_layout, parent, false);								
				TextView tv = (TextView) ll.findViewById(R.id.checklistText);
				tv.setText(item.getmIngredient());	
				
				CheckBox cb = (CheckBox) ll.findViewById(R.id.checkBox1);
				cb.setChecked(item.ismCheckBox());
			}else{
				TextView tv = (TextView) ll.findViewById(R.id.checklistText);
				tv.setText(item.getmIngredient());
			
				CheckBox cb = (CheckBox) ll.findViewById(R.id.checkBox1);
				cb.setChecked(item.ismCheckBox());
			}
			return ll;	
			
		}		
		
	}
}

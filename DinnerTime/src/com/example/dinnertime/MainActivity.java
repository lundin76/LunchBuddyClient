package com.example.dinnertime;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks, ISetData {
	
	public static final String DISH_KEY_NAME = "dish_key_name";
	public static final String DISH_KEY_TYPE = "dish_key_type";
	public static final int DISH_KEY_IMAGE_ID = 1;
	public static final String DISH_KEY_IMAGE_INSTR = "dish_key_image_instr";
	public static final String DISH_KEY_IMAGE_NAME = "dish_key_image_name";
	public static final String DISH_KEY_INGREDIENTS = "dish_key_ingredients";
	public  static final String DISH_SERIALIZED_DISH = "serialized_dish";
	private static final String SERVER_URL = "http://10.0.2.2:8080/lunchbuddy/dishes.xml";
		
	private ArrayList<Dish> mDishes;
	private MyAdapter mAdapter;
	
	ArrayList<Dish> mDishesPasta;
	ArrayList<Dish> mDishesSoup;
	ArrayList<Dish> mDishesVeg;
	
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	private ListView mDishesListView;
	private final static String xml_asset_path = "dishes.xml";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDishes = null;
		
		mDishesListView =(ListView) findViewById(R.id.dishesListView);
		
		mDishesPasta = new ArrayList<Dish>();
		mDishesSoup = new ArrayList<Dish>();
		mDishesVeg= new ArrayList<Dish>();
	
		DataFetch mData = new DataFetch((ISetData) this);
		
		mData.execute(SERVER_URL, null, null);
		
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	private class MyAdapter extends ArrayAdapter<Dish> {
		ArrayList<Dish> dishes;
		
		public MyAdapter(ArrayList<Dish> dishes){
			super(MainActivity.this, R.layout.row_layout , dishes);
			this.dishes=dishes;
		}
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			LinearLayout llin = (LinearLayout) convertView;
			TextView tv;
			ImageView im;
						
			OnClickListener cl = new OnClickListener(){
				@Override
				public void onClick(View v) {
					Dish temp = dishes.get(position);
					
					Intent myIntent = new Intent(MainActivity.this, DishActivity.class);
					
					myIntent.putExtra(MainActivity.DISH_SERIALIZED_DISH, temp);
					
					startActivity(myIntent);												
				}				
			};
			
			if(llin==null){
				LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				llin = (LinearLayout) li.inflate(R.layout.row_layout, parent, false);
				tv = (TextView) llin.findViewById(R.id.dishText);
				im = (ImageView) llin.findViewById(R.id.dishImageView); 
				
				tv.setText(dishes.get(position).getmName());		
				im.setImageDrawable(dishes.get(position).getmImage());
				im.setOnClickListener(cl);
				return llin;
			}else{
				tv = (TextView) llin.findViewById(R.id.dishText);
				im = (ImageView) llin.findViewById(R.id.dishImageView); 
				
				tv.setText(dishes.get(position).getmName());	
				im.setImageDrawable(dishes.get(position).getmImage());
				im.setOnClickListener(cl);
				return llin;
			}
		}
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		
		if(mDishes !=null){
			switch (number) {
			case 1:			
				mTitle = getString(R.string.title_section1);
				mAdapter=new MyAdapter(mDishesPasta);
				mDishesListView.setAdapter(mAdapter);
				break;
			case 2:
				mTitle = getString(R.string.title_section2);
				mAdapter=new MyAdapter(mDishesVeg);
				mDishesListView.setAdapter(mAdapter);
				break;
			case 3:
				mTitle = getString(R.string.title_section3);
				mAdapter=new MyAdapter(mDishesSoup);
				mDishesListView.setAdapter(mAdapter);
				
				break;
			case 4:
				mTitle = getString(R.string.title_section4);
				break;
			case 5:
				mTitle = getString(R.string.title_section5);
				break;
			case 6:
				mTitle = getString(R.string.title_section6);
				break;
			}
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}
	@Override
	public void setData(ArrayList<Dish> mDishes) {		
		this.mDishes=mDishes;
		
		for(Dish d : mDishes){
			if(d.getmType().equals("pasta")){
				mDishesPasta.add(d);				
			}
			if(d.getmType().equals("vegetarian")){	
				mDishesVeg.add(d);
			}
			if(d.getmType().equals("soup")){
				mDishesSoup.add(d);
			}
		}		
	}
	
}

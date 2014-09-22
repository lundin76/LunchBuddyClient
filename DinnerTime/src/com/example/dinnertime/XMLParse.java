package com.example.dinnertime;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLParse {
	
	private static final String DISH_TAG="dish";
	private static final String DISH_NAME="dish_name";
	private static final String DISH_DESCRIPTION="description";
	private static final String DISH_IMG_URL="image_url";
	private static final String DISH_CATEGORY="category";
	private static final String INGREDIENT_TAG = "ingredient";	
	private static final String INGREDIENT_NAME="ingredient_name";
	private static final String INGREDIENT_AMOUNT="amount";
	private int mCurrDishPos;
	
	public List<Dish> parse(String xml) throws XmlPullParserException, IOException {
		mCurrDishPos=-1;
		List<Dish> mDishEntries = new ArrayList<Dish>();
	    
	    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	     factory.setNamespaceAware(true);
	     XmlPullParser xpp = factory.newPullParser();
	
	     xpp.setInput( new StringReader (xml));
	     int eventType = xpp.getEventType();
	     
	     while (eventType != XmlPullParser.END_DOCUMENT) {
	    	 String tagName=xpp.getName();	
	    	 	    	 	    	 
	    	 if(eventType == XmlPullParser.START_DOCUMENT) {
	          System.out.println("Start document");
	    	 } else if(eventType == XmlPullParser.START_TAG) {	          
	    		 System.out.println("Start tag "+tagName);
		         if(tagName.equalsIgnoreCase(DISH_TAG)){
		        	 Dish d = new Dish();
		        	 
		        	 for(int i=0; i<xpp.getAttributeCount(); i++){
		        		 String attName = xpp.getAttributeName(i);
		        		 String attText = xpp.getAttributeValue(i);
		        		 if(attName.equalsIgnoreCase(DISH_CATEGORY)){
		        			 d.setmType(attText);
		        		 }else if(attName.equalsIgnoreCase(DISH_NAME)){
		        			 d.setmName(attText);
		        		 }else if(attName.equalsIgnoreCase(DISH_DESCRIPTION)){
		        			 d.setmInstructions(attText);
		        		 }else if(attName.equalsIgnoreCase(DISH_IMG_URL)){
		        			 d.setmImageName(attText);
		        		 }
		        	 }
		        	 mDishEntries.add(d);		 
		        	 mCurrDishPos++;
	         	}else if(tagName.equalsIgnoreCase(INGREDIENT_TAG)){
	        		 
	         		 Dish d = mDishEntries.get(mCurrDishPos);
	         		 d.getmIngredients().add(new Ingredient());
	         		 int pos = d.getmIngredients().size()-1;
	         		 
	        		 int attCount = xpp.getAttributeCount();
	        		 for(int i=0; i<attCount && attCount==2; i++){
	        			 
	        			 String attName = xpp.getAttributeName(i);
		        		 String attText = xpp.getAttributeValue(i);
		        		 	        			        		 
		        		 if(attName.equalsIgnoreCase(INGREDIENT_NAME)){
		        			 d.getmIngredients().get(pos).setmIngredient(attText);
		        		 }else if(attName.equalsIgnoreCase(INGREDIENT_AMOUNT)){
		        			 d.getmIngredients().get(pos).setmAmount(attText);
		        		 }
	        		 }		        		 
	        	 }
	        }else if(eventType == XmlPullParser.END_TAG) {	
	    	  System.out.println("End tag "+tagName);
	      	}
	    	 eventType = xpp.next();
	     	}
	     System.out.println("End document");				
	     return mDishEntries;
	}
}
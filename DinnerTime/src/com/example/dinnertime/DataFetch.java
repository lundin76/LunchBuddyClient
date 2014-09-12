package com.example.dinnertime;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.AssetManager;

public class DataFetch {
	
	private static final String DISHES_TAG="Dishes";
	private static final String DISH_TAG="Dish";
	private static final String DISH_NAME="Name";
	private static final String DISH_DESCRIPTION="Description";
	private static final String DISH_IMG_URL="Img_Url";
	private static final String DISH_CATEGORY="Category";
	
	private Context mCont;
	
	public DataFetch(Context context){
		this.mCont=context;
	}
	
	public String fetchXML(String path){
		String xml = null;
		AssetManager am = mCont.getAssets();		
		InputStream is;
		
		try {
			is = am.open(path);
			int length = is.available();
			byte[] data = new byte[length];
			is.read(data);
			xml = new String(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		try {
			parse(xml);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;
	}
	
	private List parse(String xml) throws XmlPullParserException, IOException {
	    List<Dish> dish_entries = new ArrayList<Dish>();
	    
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
		        		 if(attName.equalsIgnoreCase(DISH_CATEGORY)){
		        			 d.setType(attName);
		        		 }else if(attName.equalsIgnoreCase(DISH_NAME)){
		        			 d.setImageName(attName);
		        		 }else if(attName.equalsIgnoreCase(DISH_DESCRIPTION)){
		        			 d.setImageName(attName);
		        		 }else if(attName.equalsIgnoreCase(DISH_IMG_URL)){
		        			 d.setImageName(attName);
		        		 }
		        	 }
		        	 dish_entries.add(d);
		        	  		   
	         	}
	        }else if(eventType == XmlPullParser.END_TAG) {	
	    	  System.out.println("End tag "+tagName);
	      	}
	    	 eventType = xpp.next();
	     	}
	     System.out.println("End document");				
	     return dish_entries;
	}
}


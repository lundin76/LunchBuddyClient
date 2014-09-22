package com.example.dinnertime;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

public class DataFetch extends AsyncTask<String, String, ArrayList<Dish>> {

	private static final String TAG = "DataFetch";
	private static final String LOG_DOWNLOAD = "Donwloaded from server: ";
	private static final String LOG_BAD_URL = "Not incorrect URL";
	private static final String LOG_NO_DATA_FETCH = "Unable to retrieve data from server ";
	private static final String LOG_BAD_XML = "Incorrect XML on server";

	private ISetData mDataSetter;
		
	public DataFetch(ISetData mDataSetter){
		this.mDataSetter=mDataSetter;
	}
	
//	public ArrayList<Dish> fetchXML(String path){
//		String xml = null;
//		AssetManager am = mCont.getAssets();		
//		InputStream is;
//		
//		try {
//			is = am.open(path);
//			int length = is.available();
//			byte[] data = new byte[length];
//			is.read(data);
//			xml = new String(data);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
//		
//		try {
//			dishes = (ArrayList<Dish>) new XMLParse().parse(xml);
//		} catch (XmlPullParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return dishes;
//	}
	
	
	
	public String fetchXMLfromUrl(String url_string){
		URL url = null;
		try {
			url = new URL(url_string);
		} catch (MalformedURLException e1) {
			Log.i(TAG, LOG_BAD_URL);
			e1.printStackTrace();
			return null;
		}
		String xml = null;
		
		URLConnection ucon;
		try {
			ucon = url.openConnection();
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			
			ByteArrayBuffer baf = new ByteArrayBuffer(5000);
	        int next = 0;
	        while ((next= bis.read()) != -1) {
	           baf.append((byte) next);
	        }
		
	        xml = new String(baf.toByteArray());
		
		} catch (IOException e) {
			Log.i(TAG, LOG_NO_DATA_FETCH);
			e.printStackTrace();
			return null;
		}
		        
        Log.i(TAG, LOG_DOWNLOAD + xml);
        return xml;		
	}
	
	@Override
	protected ArrayList<Dish> doInBackground(String... params) {		
		if (params[0] != null){
			String xml = fetchXMLfromUrl(params[0]);
			ArrayList<Dish> dishes = null;
			
			try {
				dishes = (ArrayList<Dish>) new XMLParse().parse(xml);
								
			} catch (XmlPullParserException e) {
				Log.i(TAG, LOG_BAD_XML);
				e.printStackTrace();
			} catch (IOException e) {
				Log.i(TAG, LOG_NO_DATA_FETCH);
				e.printStackTrace();
			}
			
			
			for(Dish d : dishes){
				try {
					Drawable image = DataFetch.fetchImage(d.getmImageName());
					d.setmImage(image);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
			return dishes;
		}
		return null;
	}
	protected void onPostExecute(ArrayList<Dish> result) {
	        mDataSetter.setData(result);
    }
	
	public static Drawable fetchImage(String url) throws IOException {
	    Bitmap x;

	    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
	    conn.connect();
	    InputStream input = conn.getInputStream();

	    x = BitmapFactory.decodeStream(input);
	    return new BitmapDrawable(x);
	}
}


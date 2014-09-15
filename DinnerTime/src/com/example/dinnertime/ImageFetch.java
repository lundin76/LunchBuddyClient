package com.example.dinnertime;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

public class ImageFetch extends AsyncTask<String, Object, Drawable>{

	private ISetImage mImageSetter;
	
	public ImageFetch(ISetImage iSetImage){
		this.mImageSetter=(ISetImage) iSetImage;
	}
	
	@Override
	protected Drawable doInBackground(String... params) {
		if(params[0] != null){
			try {
				Drawable d =  fetchImage(params[0]);
				return d;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Drawable image) {
		mImageSetter.setImage(image);
		super.onPostExecute(image);
	}

	public Drawable fetchImage(String url) throws IOException {
	    Bitmap x;

	    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
	    conn.connect();
	    InputStream input = conn.getInputStream();

	    x = BitmapFactory.decodeStream(input);
	    return new BitmapDrawable(x);
	}	
}

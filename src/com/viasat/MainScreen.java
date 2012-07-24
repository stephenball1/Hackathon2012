package com.viasat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;

public class MainScreen extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standard);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		ArrayList<ImageButton> buttonList = new ArrayList<ImageButton>();
		buttonList.add((ImageButton)findViewById(R.id.movie1));
		buttonList.add((ImageButton)findViewById(R.id.movie2));
		buttonList.add((ImageButton)findViewById(R.id.movie3));
		buttonList.add((ImageButton)findViewById(R.id.movie4));
		InputStream is = this.getResources().openRawResource(R.raw.movies);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		JSONObject json = null;
		try {
			line = br.readLine();
			json = new JSONObject(line);
			ImageButton curButton;
			for(int i=0;i<4;i++) {
				JSONObject curJson = json.getJSONObject(""+(i+1));
				curButton = buttonList.get(i);
				String url =curJson.getString("Poster");
				final String title = curJson.getString("Title")+" ("+curJson.getString("Year")+")";
				final int cur=i;
				final String length = curJson.getString("Runtime");
				final String description = curJson.getString("Plot");
				new DownloadImageTask(curButton).execute(url);
				curButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent m = new Intent(MainScreen.this,MovieActivity.class);
						Bundle b = new Bundle();
						b.putInt("id",cur+1);
						b.putString("title", title);
						b.putString("description", description);
						b.putString("length", length);
						m.putExtras(b);
						startActivity(m);
					}
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/* movie1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent m = new Intent(MainScreen.this,MovieActivity.class);
				Bundle b = new Bundle();
				b.putInt("id",1);
				m.putExtras(b);
				startActivity(m);
			}
		});*/


	}
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageButton bmImage;

		public DownloadImageTask(ImageButton bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(Bitmap.createScaledBitmap(result,bmImage.getDrawable().getBounds().width(),bmImage.getDrawable().getBounds().height(),false));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity, menu);
		return true;
	}

	
}

package com.viasat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
				curButton.setEnabled(false);
				String url =curJson.getString("Poster");
				final String title = curJson.getString("Title")+" ("+curJson.getString("Year")+")";
				final int cur=i;
				final String length = curJson.getString("Runtime");
				final String description = curJson.getString("Plot");
				final String director = curJson.getString("Director");
				final String genre = curJson.getString("Genre");
				final String rating = curJson.getString("Rated");
				final double review = curJson.getDouble("imdbRating");
				new DownloadImageTask(curButton,cur,title,description,length, director, genre, rating,review).execute(url);
				
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
		ImageButton button;
		Integer cur;
		final String title;
		final String description;
		final String length;
		final String director;
		final String genre;
		final String rating;
		final double review;
		
		public DownloadImageTask(ImageButton button,int cur, String title, String description,
				String length,String director,String genre,String rating,double review) {
			this.button = button;
			this.cur = cur;
			this.title = title;
			this.description = description;
			this.length = length;
			this.director = director;
			this.rating = rating;
			this.genre = genre;
			this.review = review;
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
			final Bitmap scaledBitmap = Bitmap.createScaledBitmap(result,button.getDrawable().getBounds().width(),button.getDrawable().getBounds().height(),false);
			button.setImageBitmap(scaledBitmap);
			button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent m = new Intent(MainScreen.this,MovieActivity.class);
					Bundle b = new Bundle();
					b.putInt("id",cur+1);
					b.putString("title", title);
					b.putString("description", description);
					b.putString("length", length);
					b.putParcelable("poster", scaledBitmap);
					b.putString("director", director);
					b.putString("genre",genre);
					b.putString("rating", rating);
					b.putDouble("review", review);
					m.putExtras(b);
					startActivity(m);
				}
			});
			button.setEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_items, menu);
		return true;
	}

	
}

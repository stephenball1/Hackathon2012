package com.viasat;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MovieActivity extends Activity {

	private int id;
	Globals g;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie);
		Bundle b = getIntent().getExtras();
		id = b.getInt("id");
		ImageView poster = (ImageView)findViewById(R.id.poster);
		Bitmap image = b.getParcelable("poster");
		TextView title = (TextView)findViewById(R.id.title);
		TextView description = (TextView)findViewById(R.id.description);
		TextView genre = (TextView)findViewById(R.id.genre);
		TextView rating = (TextView)findViewById(R.id.rating);
		TextView director = (TextView)findViewById(R.id.director);
		TextView length = (TextView)findViewById(R.id.length);
		RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
		ratingBar.setRating((float)b.getDouble("review"));
		description.setText(b.getString("description"));
		title.setText(b.getString("title"));
		genre.setText(b.getString("genre"));
		rating.setText(b.getString("rating"));
		director.setText(b.getString("director"));
		length.setText(b.getString("length"));
		poster.setImageBitmap(image);
		final Button b2 = (Button)findViewById(R.id.button1);
		final ProgressBar bar = (ProgressBar)findViewById(R.id.progressBar);
		g = (Globals)getApplication();
		OnClickListener listener = null;
		if(g.hasVoted() && !g.isEnabled()) {
			b2.setEnabled(false);
		}
		else if(g.isEnabled() && (id==g.getFreeMovie() || g.hasPurchased())) {
			listener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					bar.setVisibility(View.VISIBLE);
					b2.setEnabled(false);
					Intent intent = new Intent(MovieActivity.this,WatchMovie.class);
					Bundle b = new Bundle();
					b.putInt("id", id);
					startActivity(intent);
				}
			};
			b2.setEnabled(true);
			b2.setText("Watch");
		}
		else if(g.isEnabled() && id!=g.getFreeMovie()) {
			listener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					bar.setVisibility(View.VISIBLE);
					b2.setEnabled(false);
					Intent intent = new Intent(MovieActivity.this,PurchaseActivity.class);
					startActivity(intent);
				}
			};
			b2.setEnabled(true);
			b2.setText("Purchase");
		}
		else {
			final Context t = this;
			listener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					bar.setVisibility(View.VISIBLE);
					b2.setEnabled(false);
					new CastVoteTask(bar,id,t).execute(id);
				}
			};
			b2.setEnabled(true);
		}

		b2.setOnClickListener(listener);


		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);


	}

	private class CastVoteTask extends AsyncTask<Integer,Void,Boolean> {
		private ProgressBar bar;
		private int id;
		private Context context;
		public CastVoteTask(ProgressBar bar, int id, Context context) {
			this.bar = bar;
			this.id = id;
			this.context = context;
		}

		@Override
		protected Boolean doInBackground(Integer... params) {
			Integer flightNum = 1234;
			String deviceID = Secure.getString(context.getContentResolver(),Secure.ANDROID_ID);//Settings.Secure.ANDROID_ID;
			String url = "http://10.11.246.246/vote.php?movie="+id+"&flight="+flightNum.toString()+"&mac="+deviceID;
			System.out.println(url);
			try {
				URL urlObject = new java.net.URL(url);
				InputStream is = urlObject.openStream();
				int result = is.read() - 48;
				if (result >= 1) { return true;  }
				if (result > 1) { return false;  }
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block 
				System.out.println("===========URL connect failed ==========\n");
				e.printStackTrace();
				return false;
			}
			catch (IOException e) {
				System.out.println("========URL get content failed ========\n");
				e.printStackTrace();
				return false;
			}
			return false;
		}

		protected void onPostExecute(Boolean b) {
			if(b) {
				g.setVoted(true);
				runOnUiThread(new Runnable() {
					public void run() {
						Intent intent = new Intent(MovieActivity.this,Stats.class);
						startActivity(intent);
						bar.setVisibility(View.INVISIBLE);
					}
				});
			}
			else {
				runOnUiThread(new Runnable() {
					public void run() {
						bar.setVisibility(View.INVISIBLE);
						Toast.makeText(context,"An error has occurred. Please try again.",Toast.LENGTH_LONG).show();
					}
				});
			}
		}    	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


}
package com.viasat;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MovieActivity extends Activity {

	private int id;
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
        final Button b2 = (Button)findViewById(R.id.button1);
		final ProgressBar bar = (ProgressBar)findViewById(R.id.progressBar);
        b2.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
        		bar.setVisibility(View.VISIBLE);
        		b2.setEnabled(false);
				//Intent intent = new Intent(MovieActivity.this,Stats.class);
				//startActivity(intent);
        		new CastVoteTask(bar).execute(id);
			}
        });
        
        description.setText(b.getString("description"));
        title.setText(b.getString("title"));
        genre.setText(b.getString("genre"));
        rating.setText(b.getString("rating"));
        director.setText(b.getString("director"));
        length.setText(b.getString("length"));
        poster.setImageBitmap(image);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        Toast.makeText(this, "id: "+id, Toast.LENGTH_LONG);
        
    }
    
    private class CastVoteTask extends AsyncTask<Integer,Void,Boolean> {
    	private ProgressBar bar;
    	
		public CastVoteTask(ProgressBar bar) {
			this.bar = bar;
		}

		@Override
		protected Boolean doInBackground(Integer... params) {
			Integer flightNum = 1234;
	    	String url = "http://10.11.246.246/watchCheck.php?flightNum=" + flightNum.toString();
	        	
	    	try {
				URL urlObject = new java.net.URL(url);
				InputStream is = urlObject.openStream();
				int result = is.read() - 48;
				System.out.println("result is: " + result);
				if (result == 0) { return false;  }
				else if (result > 4) {
					//throw new Exception("Connection Error");
					return false;
				}
				else {return true;  }

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				System.out.println("===========URL connect failed ==========\n");
				e.printStackTrace();
			}
	    	catch (IOException e) {
	    		System.out.println("========URL get content failed ========\n");
	    		e.printStackTrace();
	    	}
			
			return true;
		}
		
		protected void onPostExecute(Boolean b) {
			runOnUiThread(new Runnable() {
				public void run() {
					bar.setVisibility(View.INVISIBLE);
					//Intent intent = new Intent(MovieActivity.this,Stats.class);
					//startActivity(intent);
				}
			});
		}
    	
    }

    
    
}
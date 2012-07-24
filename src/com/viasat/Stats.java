package com.viasat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Stats extends Activity {	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        // Get all the widget objects on the stats activity.
        Globals g = (Globals)getApplication();
        
		// First get the four progress bars. 
        ProgressBar movie1Bar = (ProgressBar)findViewById(R.id.movie1Bar);
        movie1Bar.setIndeterminate(false);
        movie1Bar.setProgress(g.getMovie1());
        
        ProgressBar movie2Bar = (ProgressBar)findViewById(R.id.movie2Bar);
        movie2Bar.setIndeterminate(false);
        movie2Bar.setProgress(g.getMovie2());
        
        ProgressBar movie3Bar = (ProgressBar)findViewById(R.id.movie3Bar);
        movie3Bar.setIndeterminate(false);
        movie3Bar.setProgress(g.getMovie3());
        
        ProgressBar movie4Bar = (ProgressBar)findViewById(R.id.movie4Bar);
        movie4Bar.setIndeterminate(false);
        movie4Bar.setProgress(g.getMovie4());
        
        // Then get the watch button. This should only be active if the flight
        // is above 10000 feet.
        Button watchButton = (Button)findViewById(R.id.watchMovie);
        if(!g.isEnabled()) {
        	watchButton.setEnabled(false);
        }
       
        watchButton.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
				Intent intent = new Intent(Stats.this,WatchMovie.class);
				startActivity(intent);
			}
        });
        
        
        // Then get the info button. This will send the user back to the 
        // movie info screen.
        Button infoButton = (Button)findViewById(R.id.movieInfo);
        infoButton.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
				Intent intent = new Intent(Stats.this,MainScreen.class);
				startActivity(intent);
			}
        });
        
        // Lastly, get the refresh button. This will just refresh the activity
        Button refreshButton = (Button)findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(getIntent()); 
				finish();
			}
		});
        
        ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
        
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

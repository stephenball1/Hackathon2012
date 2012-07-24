package com.viasat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Stats extends Activity {	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        // Get all the widget objects on the stats activity.

        int[] moviePercs;
		try {
			moviePercs = getPercentages();
			if (moviePercs == null) {
	        	System.out.println("Error in reading from URL");
	        	Toast.makeText(this, "Error in reading from URL", Toast.LENGTH_LONG).show();
	        }
			// First get the four progress bars. 
	        ProgressBar movie1Bar = (ProgressBar)findViewById(R.id.movie1Bar);
	        movie1Bar.setIndeterminate(false);
	        movie1Bar.setProgress(moviePercs[0]);
	        
	        ProgressBar movie2Bar = (ProgressBar)findViewById(R.id.movie2Bar);
	        movie2Bar.setIndeterminate(false);
	        movie2Bar.setProgress(moviePercs[1]);
	        
	        ProgressBar movie3Bar = (ProgressBar)findViewById(R.id.movie3Bar);
	        movie3Bar.setIndeterminate(false);
	        movie3Bar.setProgress(moviePercs[2]);
	        
	        ProgressBar movie4Bar = (ProgressBar)findViewById(R.id.movie4Bar);
	        movie4Bar.setIndeterminate(false);
	        movie4Bar.setProgress(moviePercs[3]);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        // Then get the watch button. This should only be active if the flight
        // is above 10000 feet.
        Button watchButton = (Button)findViewById(R.id.watchMovie);
        watchButton.setEnabled(false);
        try {
			if (canConnect()) { watchButton.setEnabled(true);  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    
    private int[] getPercentages() throws Exception {
    	Integer flightNum = 1234;
    	String url = "http://10.11.246.246/getvotes.php?flightNum="+flightNum.toString();
		try {
			
			URL urlObject = new java.net.URL(url);
			InputStream is = urlObject.openStream();
			InputStreamReader isReader = new InputStreamReader(is);
			int c = 0;
			StringBuilder sb = new StringBuilder();
			while ((c = isReader.read()) > -1) {
				sb.append((char)c);
			}
			String[] sArr = sb.toString().split(",");
			if (sArr.length > 4) {
				throw new Exception("Error parsing data from WAMP server");
			}
			int[] moviePercs = new int[4];
			for (int i=0; i<sArr.length; i++) {
				moviePercs[i] = Integer.parseInt(sArr[i]);
			}
			return moviePercs;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("===========URL connect failed ==========\n");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    	catch (IOException e) {
    		System.out.println("========URL get content failed ========\n");
    		e.printStackTrace();
    		throw new Exception(e.getMessage());
    	}
    }
    

    /**
     * Checks if the user is above 10000 feet. If so, return true. else false.
     * 
     * Currently getting data from flight simulator. 
     * 
     * @return boolean if above 10000 feet
     * @throws Exception 
     */
    private boolean canConnect() throws Exception {
    	Integer flightNum = 1234;
    	String url = "http://10.11.246.246/watchCheck.php?flightNum=" + flightNum.toString();
        	
    	try {
			URL urlObject = new java.net.URL(url);
			InputStream is = urlObject.openStream();
			int result = is.read() - 48;
			System.out.println("result is: " + result);
			if (result == 0) { return false;  }
			else if (result > 4) {
				throw new Exception("Connection Error");
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

package com.viasat;

import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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

        
        // First get the four progress bars. 
        ProgressBar movie1Bar = (ProgressBar)findViewById(R.id.movie1Bar);
        movie1Bar.setIndeterminate(false);
        movie1Bar.setProgress(5);
        
        ProgressBar movie2Bar = (ProgressBar)findViewById(R.id.movie2Bar);
        movie2Bar.setIndeterminate(false);
        movie2Bar.setProgress(10);
        
        ProgressBar movie3Bar = (ProgressBar)findViewById(R.id.movie3Bar);
        movie3Bar.setIndeterminate(false);
        movie3Bar.setProgress(70);
        
        ProgressBar movie4Bar = (ProgressBar)findViewById(R.id.movie4Bar);
        movie4Bar.setIndeterminate(false);
        movie4Bar.setProgress(15);
        
        
        
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
    	    	
    	String hostString = "http://10.11.90.10:1337/mobilitySim/flightSim/connect/xmlrpc";
	    String flightId = "9085824";
	    Integer speedMultiplier = 400;
	    Integer startPercent = 0;
	    
	    try
	    {
	      XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	      URL hostURL = new URL( hostString );
	      config.setServerURL( hostURL );
	      XmlRpcClient server = new XmlRpcClient();
	      server.setConfig( config );
	      
	      Vector<Comparable> simParams = new Vector<Comparable>();
	      simParams.addElement( flightId );
	      simParams.addElement( speedMultiplier );
	      simParams.addElement( startPercent );
	      Object result = server.execute( "simulateFlight", simParams );
	      Integer simId = (Integer) result;
	      
	      Vector<Comparable> params = new Vector<Comparable>();
	      params.addElement( simId );

	      
	      result = server.execute( "getAltitude", params );
	      Double alt = (Double) result;
	      
	      // kill the connection
	      server = null;
	      
	      if (alt > 10000) { return true;  }
	      else { return true;  }

	    } 
	    catch (Exception exception)
	    {
	      exception.printStackTrace();
	      throw new Exception(exception.fillInStackTrace());
	    }
    	
    }

}

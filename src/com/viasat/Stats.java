package com.viasat;

import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

public class Stats extends Activity {	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        
        // Get all the widget objects on the stats activity.
        
        // First get the four progress bars. 
        ProgressBar movie1Bar = (ProgressBar)findViewById(R.id.movie1Bar);
        movie1Bar.setIndeterminate(false);
        movie1Bar.setProgress(50);
        
        ProgressBar movie2Bar = (ProgressBar)findViewById(R.id.movie2Bar);
        movie2Bar.setIndeterminate(false);
        movie2Bar.setProgress(50);
        
        ProgressBar movie3Bar = (ProgressBar)findViewById(R.id.movie3Bar);
        movie3Bar.setIndeterminate(false);
        movie3Bar.setProgress(50);
        
        ProgressBar movie4Bar = (ProgressBar)findViewById(R.id.movie4Bar);
        movie4Bar.setIndeterminate(false);
        movie4Bar.setProgress(50);
        
        System.out.println("GOT HERE");
        
        
        // Then get the watch button. This should only be active if the flight
        // is above 10000 feet.
        Button watchButton = (Button)findViewById(R.id.watchMovie);
        watchButton.setEnabled(false);
//        
//        if (canConnect()) { 
//        	
//        	watchButton.setActivated(true);  
//        }
//        else { watchButton.setActivated(false);  }
        
        
        // Lastly, get the info button. This will send the user back to the 
        // movie info screen.
        Button infoButton = (Button)findViewById(R.id.movieInfo);
        
        
    }
    

    /**
     * Checks if the user is above 10000 feet. If so, return true. else false.
     * 
     * Currently getting data from flight simulator. 
     * 
     * @return boolean if above 10000 feet
     */
    private boolean canConnect() {
    	
    	return false;
    	
//    	String hostString = "http://10.11.90.10:1337/mobilitySim/flightSim/connect/xmlrpc";
//	    String flightId = "9085824";
//	    Integer speedMultiplier = 400;
//	    Integer startPercent = 0;
//	    
//	    try
//	    {
//	      XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
//	      URL hostURL = new URL( hostString );
//	      config.setServerURL( hostURL );
//	      XmlRpcClient server = new XmlRpcClient();
//	      server.setConfig( config );
//	      
//	      System.out.println( "Starting simulation..." );
//	      System.out.println("");
//	      
//	      Vector<Comparable> simParams = new Vector<Comparable>();
//	      simParams.addElement( flightId );
//	      simParams.addElement( speedMultiplier );
//	      simParams.addElement( startPercent );
//	      Object result = server.execute( "simulateFlight", simParams );
//	      Integer simId = (Integer) result;
//	      
//	      Vector<Comparable> params = new Vector<Comparable>();
//	      params.addElement( simId );
//
//	      
//	      result = server.execute( "getAltitude", params );
//	      Double alt = (Double) result;
//	      
//	      // kill the connection
//	      server = null;
//	      
//	      if (alt > 10000) { return true;  }
//	      else { return false;  }
//
//	    } 
//	    catch (Exception exception)
//	    {
//	      exception.printStackTrace();
//	      return false;
//	    }
    	
    }

}

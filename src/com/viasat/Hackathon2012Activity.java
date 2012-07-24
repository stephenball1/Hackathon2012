package com.viasat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Hackathon2012Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Globals g = (Globals)getApplication();
        //new intializeTask();
        new PopulateGlobalsTask(g).execute();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        Button submitButton = (Button)findViewById(R.id.button1);
        
        submitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(checkFlightInputs(v)) {
					Intent intent = new Intent(Hackathon2012Activity.this,MainScreen.class);
					
					startActivity(intent);
				}
				
			}
		});
    }
    
    public boolean checkFlightInputs(View view) {
    	String flightNum = ((EditText)findViewById(R.id.flightNum)).getText().toString();
    	String flightDate = ((EditText)findViewById(R.id.flightDate)).getText().toString();
    	
    	if(flightNum.isEmpty() && flightDate.isEmpty()) {
    		Toast.makeText(this, "Please enter a flight number and flight date", Toast.LENGTH_LONG).show();
    		return false;
    	}
    	else if(flightNum.isEmpty()) {
    		Toast.makeText(this,"Please enter a flight number",Toast.LENGTH_LONG).show();
    		return false;
    	}
    	else if(flightDate.isEmpty()) {
    		Toast.makeText(this, "Please enter a flight date", Toast.LENGTH_LONG).show();
    		return false;
    	}
    	try {
        	int fn = Integer.parseInt(((EditText)findViewById(R.id.flightNum)).getText().toString());
        	String date = ((EditText)findViewById(R.id.flightDate)).getText().toString();
			Globals g = (Globals)getApplication();
			g.setFlightNumber(fn);
			g.setDate(date);
			return true;
    	} catch (NumberFormatException e) {
    		Toast.makeText(this, "Please enter a valid flight number (numbers only)", Toast.LENGTH_LONG).show();
    		return false;
    	}	
    }
}
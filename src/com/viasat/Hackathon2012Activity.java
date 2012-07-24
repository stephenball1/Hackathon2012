package com.viasat;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class Hackathon2012Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.main);
        Globals g = (Globals)getApplication();
        //new intializeTask();
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
        new PopulateGlobalsTask(g).execute();
        
        EditText dateEntry = (EditText)findViewById(R.id.flightDate);
        dateEntry.setClickable(true);
        dateEntry.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDatePickerDialog(v);	
			}
		});
        dateEntry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					showDatePickerDialog(v);
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
    
    public void showDatePickerDialog(View v) {
    	EditText date = (EditText)findViewById(R.id.flightDate);
        DialogFragment newFragment = new DatePickerFragment(date);
        newFragment.show(getFragmentManager(),"datePicker");
        
    }
    
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    	private EditText dateField;
		public DatePickerFragment(EditText dateField) {
			this.dateField = dateField;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
			String date = Integer.toString(month) +"/"+ Integer.toString(day) +"/"+ Integer.toString(year);
			System.out.println(date);
			dateField.setText(date);
		}


}
    
}
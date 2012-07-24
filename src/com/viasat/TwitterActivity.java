package com.viasat;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class TwitterActivity extends Activity {
	
	private EditText twitterText;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twitter);
		
		twitterText = (EditText)findViewById(R.id.tweetText);
        twitterText.setClickable(true);
        twitterText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!(twitterText.getText().toString().contains("#ExedeInTheAir #Jetblue"))) {
					twitterText.append(" #ExedeInTheAir #Jetblue");
				}
			}
		});

        twitterText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					if (!(twitterText.getText().toString().contains("#ExedeInTheAir #Jetblue"))) {
						twitterText.append(" #ExedeInTheAir #Jetblue");	
					}
				}
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

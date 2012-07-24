package com.viasat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;

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
	}

}

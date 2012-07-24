package com.viasat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Stats extends Activity {	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats);

		// Get all the widget objects on the stats activity.
		Globals g = (Globals)getApplication();
		new PopulateGlobalsTask(g).execute();

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

		TextView movie1Title = (TextView)findViewById(R.id.movie1Title);
		movie1Title.setText(g.getMovie1Title());

		TextView movie2Title = (TextView)findViewById(R.id.movie2Title);
		movie2Title.setText(g.getMovie2Title());

		TextView movie3Title = (TextView)findViewById(R.id.movie3Title);
		movie3Title.setText(g.getMovie3Title());

		TextView movie4Title = (TextView)findViewById(R.id.movie4Title);
		movie4Title.setText(g.getMovie4Title());

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		if(!item.isCheckable()) {
			Intent intent = new Intent(this, TwitterActivity.class);
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.stats_menu, menu);
		return true;
	}



}

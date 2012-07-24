package com.viasat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        Button b2 = (Button)findViewById(R.id.button1);
        b2.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
				Intent intent = new Intent(MovieActivity.this,Stats.class);
				startActivity(intent);
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

    
    
}
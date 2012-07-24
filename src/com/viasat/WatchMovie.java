package com.viasat;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.MediaController;
import android.widget.VideoView;

public class WatchMovie extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watchmovie);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        VideoView videoView = (VideoView)findViewById(R.id.movieView);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        
        /*Bundle b = getIntent().getExtras();
        
        int movieId = b.getInt("id");*/
        
        String str = "http://10.11.90.17/movie.mp4";
        Uri uri = Uri.parse(str);

        videoView.setVideoURI(uri);

        videoView.requestFocus();
        videoView.start();
    }

}

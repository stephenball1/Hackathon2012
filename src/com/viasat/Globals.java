package com.viasat;

import android.app.Application;

public class Globals extends Application{
	private boolean enabled = false;
	private int movie1 = 0;
	private int movie2 = 0;
	private int movie3 = 0;
	private int movie4 = 0;
	private int freeMovie = 0;
	
	public int getMovie1() {
		return movie1;
	}

	public void setMovie1(int movie1) {
		this.movie1 = movie1;
	}

	public int getMovie2() {
		return movie2;
	}

	public void setMovie2(int movie2) {
		this.movie2 = movie2;
	}

	public int getMovie3() {
		return movie3;
	}

	public void setMovie3(int movie3) {
		this.movie3 = movie3;
	}

	public int getMovie4() {
		return movie4;
	}

	public void setMovie4(int movie4) {
		this.movie4 = movie4;
	}

	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public int getFreeMovie() {
		return this.freeMovie;
	}
	
	public void setFreeMovie(int freeMovie) {
		this.freeMovie = freeMovie;
	}
	
	
}

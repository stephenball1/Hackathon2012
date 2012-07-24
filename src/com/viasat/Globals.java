package com.viasat;

import android.app.Application;

public class Globals extends Application{
	private boolean enabled = false;
	private int movie1 = 0;
	private int movie2 = 0;
	private int movie3 = 0;
	private int movie4 = 0;
	private int freeMovie = 0;
	private boolean voted = false;
	private boolean purchased = false;
	private int flightNumber;
	private String date;
	
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

	public boolean hasVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	public boolean hasPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}
	
	public int getFlightNumber() {
		return this.flightNumber;
	}
	
	public void setFlightNumber(int fn) {
		this.flightNumber = fn;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}

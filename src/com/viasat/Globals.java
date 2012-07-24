package com.viasat;

import android.app.Application;

public class Globals extends Application{
	private boolean enabled = false;	//above 10000 ft
	private int movie1 = 0;
	private int movie2 = 0;
	private int movie3 = 0;
	private int movie4 = 0;
	private int freeMovie = 0;
	private int flightNumber = 1234;
	private String date = "qwerty";
	private boolean voted = false;		// when you voted
	private boolean purchased = false;	// if you purchased a plan
	private String movie1Title = "";
	private String movie2Title = "";
	private String movie3Title = "";
	private String movie4Title = "";
	
	
	public int getMovie1() {
		return movie1;
	}
	
	public void setMovieTitle(int id,String title) {
		if(id==1) movie1Title = title;
		else if(id==2) movie2Title = title;
		else if(id==3) movie3Title = title;
		else if(id==4) movie4Title = title;
	}
	public String getMovie1Title() {
		return movie1Title;
	}

	public String getMovie2Title() {
		return movie2Title;
	}

	public String getMovie3Title() {
		return movie3Title;
	}

	public void setMovie1Title(String movie1Title) {
		this.movie1Title = movie1Title;
	}

	public void setMovie2Title(String movie2Title) {
		this.movie2Title = movie2Title;
	}

	public void setMovie3Title(String movie3Title) {
		this.movie3Title = movie3Title;
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

	public String getMovie4Title() {
		return movie4Title;
	}

	public void setMovie4Title(String movie4Title) {
		this.movie4Title = movie4Title;
	}
}

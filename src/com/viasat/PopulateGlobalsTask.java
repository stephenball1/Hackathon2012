package com.viasat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.provider.Settings.Secure;


public class PopulateGlobalsTask extends AsyncTask<Void,Void,Void> {
	Globals g;
	Context context;
	
	public PopulateGlobalsTask(Globals g, Context c) {
		this.g = g;
		this.context = c;
	}
	
	@Override
	protected Void doInBackground(Void... arg0) {
		
		Integer flightNum = g.getFlightNumber();
		String deviceID = Secure.getString(context.getContentResolver(),Secure.ANDROID_ID);
		String checkVoteURL ="http://10.11.246.246/vote.php?movie=false&flight="+flightNum.toString()+"&mac="+deviceID;
		String getOtherDataURL = "http://10.11.246.246/getData.php?flightNum="+flightNum.toString();
    	try {
    		// See if user has voted yet or not.
    		System.out.println(checkVoteURL);
			URL checkVoteURLObject = new java.net.URL(checkVoteURL);
			InputStream is = checkVoteURLObject.openStream();
			int result = is.read() - 48;
			System.out.println("====== RESULT IS ======: " + result);
			if (result == 0) { g.setVoted(false);  }
			else if (result > 0) { g.setVoted(true);  }
			else {
				g.setVoted(false);
				System.out.println("Error in Check Vote URL. did not return 0 or 2");
				return null;
			}
			
			
			// See if they're above 10000, a free movie has been decided, and percentage of votes
			URL getOtherDataURLObject = new java.net.URL(getOtherDataURL);
			InputStream is2 = getOtherDataURLObject.openStream();
			InputStreamReader isReader = new InputStreamReader(is2);
			int c = 0;
			StringBuilder sb = new StringBuilder();
			while ((c = isReader.read()) > -1) {
				sb.append((char)c);
			}
			String[] sArr = sb.toString().split(",");
			if (sArr.length > 6) {
				g.setEnabled(false);
				System.out.println("Error parsing data from WAMP server");
				return null;
			}
			int[] moviePercs = new int[6];
			for (int i=0; i<sArr.length; i++) {
				moviePercs[i] = Integer.parseInt(sArr[i]);
			}
			if (moviePercs[0]==1) {g.setEnabled(true);  }
			else {g.setEnabled(false);  }
			
			g.setFreeMovie(moviePercs[1]);
			g.setMovie1(moviePercs[2]);
			g.setMovie2(moviePercs[3]);
			g.setMovie3(moviePercs[4]);
			g.setMovie4(moviePercs[5]);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("===========URL connect failed ==========\n");
			e.printStackTrace();
			return null;
		}
    	catch (IOException e) {
    		System.out.println("========URL get content failed ========\n");
    		e.printStackTrace();
    		return null;
    	}
		return null;
	}


}

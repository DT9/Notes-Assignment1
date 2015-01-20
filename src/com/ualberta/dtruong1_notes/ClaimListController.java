package com.ualberta.dtruong1_notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;

import com.ualberta.dtruong1_notes.TravelClaim.Status;

public class ClaimListController implements Serializable {
	/**
	 * Serial ID claims
	 */
	private static final long serialVersionUID = 1701493528441668039L;
	//lazy singleton
	private static ArrayList<TravelClaim> claimlist= null;
	protected static ArrayList<Listener> listeners = new ArrayList<Listener>();
	
	public static ArrayList<TravelClaim> getClaimList() {
		if (claimlist == null) {
			claimlist = new ArrayList<TravelClaim>();
		}
		return claimlist;
	}
	public static void editClaim(TravelClaim claim, Status status, Date date, Date end, String text) {
		claim.setDate(date);
		claim.setEnd(end);
		claim.setText(text);
		claim.setStatus(status);
		notifyListeners();
	}
	

	public static void addClaim(Status status,Date date,Date end, String text) {
		claimlist.add(new TravelClaim(status, date, end, text));
		notifyListeners();
		
	}
	public static void removeClaim(TravelClaim claim) 
	{
		if (claimlist == null) {
			claimlist = new ArrayList<TravelClaim>();
			return;
		}
		claimlist.remove(claim);
		notifyListeners();
	}
	public static void notifyListeners() {
		// TODO Auto-generated method stub 
		Collections.sort(claimlist);
		for (Listener listener: listeners){
			listener.update();
		}
	}
	
	public static void addListener(Listener l) {
		listeners.add(l);
	}
	public static void removeListener(Listener l) {
		listeners.remove(l);
	}
}

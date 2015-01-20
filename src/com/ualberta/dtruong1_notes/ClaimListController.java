package com.ualberta.dtruong1_notes;

import java.util.ArrayList;
import java.util.Date;

import com.ualberta.dtruong1_notes.TravelClaim.Status;

public class ClaimListController {
	//lazy singleton
	private static ArrayList<TravelClaim> claimlist= null;
	protected static ArrayList<Listener> listeners = new ArrayList<Listener>();
	
	public static ArrayList<TravelClaim> getClaimList() {
		if (claimlist == null) {
			claimlist = new ArrayList<TravelClaim>();
		}
		return claimlist;
	}
	
	public static void addClaim(Status status,Date date,Date end, String text) {
		claimlist.add(new TravelClaim(status, date, end, text));
		notfiyListeners();
		
	}
	public static void removeClaim(TravelClaim claim) 
	{
		if (claimlist == null) {
			claimlist = new ArrayList<TravelClaim>();
			return;
		}
		claimlist.remove(claim);
		notfiyListeners();
	}
	private static void notfiyListeners() {
		// TODO Auto-generated method stub 
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

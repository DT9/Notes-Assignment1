package com.ualberta.dtruong1_notes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.ualberta.dtruong1_notes.TravelClaim.Status;
import java.util.Collection;

/*
 * This class controls the list of claims that the user
 * has inputed
 */
public class ClaimListController {

	// lazy singleton list of claims
	protected static ArrayList<TravelClaim> claimlist = null;
	protected static ArrayList<Listener> listeners = new ArrayList<Listener>();

	// gets list of claims
	public static ArrayList<TravelClaim> getClaimList() {
		if (claimlist == null) {
			claimlist = new ArrayList<TravelClaim>();
		}
		return claimlist;
	}

	// Edits the claims
	public static void editClaim(TravelClaim claim, Status status, Date date,
			Date end, String text) {
		claim.setDate(date);
		claim.setEnd(end);
		claim.setText(text);
		claim.setStatus(status);
		notifyListeners();
	}

	// Adds new claim
	public static void addClaim(Status status, Date date, Date end, String text) {
		claimlist.add(new TravelClaim(status, date, end, text));
		// Updates the listeners
		notifyListeners();
	}

	// Check if the list contains the claim
	public boolean contains(TravelClaim claim) {
		return claimlist.contains(claim);
	}

	// Remove selected claim from list
	public static void removeClaim(TravelClaim claim) {
		if (claimlist == null) {
			claimlist = new ArrayList<TravelClaim>(); // Creates new list if
														// null
			return;
		}
		claimlist.remove(claim);
		notifyListeners(); // Updates listeners
	}

	// Sorts and notifies the listeners
	public static void notifyListeners() {
		// TODO Auto-generated method stub
		Collections.sort(claimlist);
		for (Listener listener : listeners) {
			listener.update();
		}
	}

	// Adds listener
	public static void addListener(Listener l) {
		listeners.add(l);
	}

	// Remove listener
	public static void removeListener(Listener l) {
		listeners.remove(l);
	}

	// Gets claim at position
	public static TravelClaim getItem(int pos) {
		// TODO Auto-generated method stub
		return claimlist.get(pos);
	}

	/** 
	 * @uml.property name="travelClaim"
	 * @uml.associationEnd multiplicity="(1 -1)" aggregation="shared" inverse="claimListController:com.ualberta.dtruong1_notes.TravelClaim"
	 */
	private Collection<TravelClaim> travelClaim;

	/** 
	 * Getter of the property <tt>travelClaim</tt>
	 * @return  Returns the travelClaim.
	 * @uml.property  name="travelClaim"
	 */
	public Collection<TravelClaim> getTravelClaim() {
		return travelClaim;
	}

	/** 
	 * Setter of the property <tt>travelClaim</tt>
	 * @param travelClaim  The travelClaim to set.
	 * @uml.property  name="travelClaim"
	 */
	public void setTravelClaim(Collection<TravelClaim> travelClaim) {
		this.travelClaim = travelClaim;
	}
}

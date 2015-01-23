package com.ualberta.dtruong1_notes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ualberta.dtruong1_notes.TravelClaim.Status;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
/*
 * This Activity displays the text fields for the user to input their claim
 * information and save it
 */
public class AddClaimInfo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_claim_info_activity);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.status_array,
				android.R.layout.simple_spinner_item); // Creates a Status
														// Spinner
		Spinner viewStatus = (Spinner) findViewById(R.id.claimStatus);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		viewStatus.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim_info, menu);
		return true;
	}
	//Saves claim info
	public void saveClaimInfo(View v) throws ParseException {
		// Find all views of claims
		EditText viewDate = (EditText) findViewById(R.id.claimDate);
		EditText viewDesc = (EditText) findViewById(R.id.claimDesc);
		Spinner viewStatus = (Spinner) findViewById(R.id.claimStatus);
		EditText viewEndDate = (EditText) findViewById(R.id.claimEnd);
		// gets the text of each view
		String startdate = viewDate.getText().toString();
		String enddate = viewEndDate.getText().toString();
		String desc = viewDesc.getText().toString();
		String text = viewStatus.getSelectedItem().toString();
		// parses the dates
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = format.parse(startdate);
		Date end = format.parse(enddate);
		// adds the claim to the rest of the claims
		ClaimListController.addClaim(Status.valueOf(text), date, end, desc);

		// exit activity
		onBackPressed();
	}
	/**
	 * @uml.property  name="mainActivity"
	 * @uml.associationEnd  multiplicity="(0 -1)" dimension="1" inverse="addClaimInfo:com.ualberta.dtruong1_notes.MainActivity"
	 */
	private MainActivity[] mainActivities;
	/**
	 * Getter of the property <tt>mainActivity</tt>
	 * @return  Returns the mainActivities.
	 * @uml.property  name="mainActivity"
	 */
	public MainActivity[] getMainActivity() {
		return mainActivities;
	}

	/**
	 * Setter of the property <tt>mainActivity</tt>
	 * @param mainActivity  The mainActivities to set.
	 * @uml.property  name="mainActivity"
	 */
	public void setMainActivity(MainActivity[] mainActivity) {
		mainActivities = mainActivity;
	}

}

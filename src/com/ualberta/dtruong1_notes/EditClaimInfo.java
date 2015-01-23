package com.ualberta.dtruong1_notes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ualberta.dtruong1_notes.TravelClaim.Status;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * Edits claim information
 */
public class EditClaimInfo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim_info);
		// Creates a spinner of statuses
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.status_array,
				android.R.layout.simple_spinner_item);
		Spinner viewStatus = (Spinner) findViewById(R.id.claimStatus);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		viewStatus.setAdapter(adapter);
		// Claim to be edited
		TravelClaim edit = MainActivity.editableClaim;

		// Obtains each text field views
		EditText viewDate = (EditText) findViewById(R.id.claimDate);
		EditText viewDesc = (EditText) findViewById(R.id.claimDesc);
		EditText viewEndDate = (EditText) findViewById(R.id.claimEnd);

		// Sets the view to previous edit data
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		viewDate.setText(formatter.format(edit.getDate()));
		viewEndDate.setText(formatter.format(edit.getEnd()));
		viewDesc.setText(edit.getText());
		viewStatus.setSelection(edit.getStatus().ordinal());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim_info, menu);
		return true;
	}

	public EditClaimInfo(View v) throws ParseException {
		// Obtains id of each view
		EditText viewDate = (EditText) findViewById(R.id.claimDate);
		EditText viewDesc = (EditText) findViewById(R.id.claimDesc);
		Spinner viewStatus = (Spinner) findViewById(R.id.claimStatus);
		EditText viewEndDate = (EditText) findViewById(R.id.claimEnd);
		// Gets text of each field
		String startdate = viewDate.getText().toString();
		String enddate = viewEndDate.getText().toString();
		String desc = viewDesc.getText().toString();
		String text = viewStatus.getSelectedItem().toString();
		// Parses date
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = format.parse(startdate);
		Date end = format.parse(enddate);

		// Edits claim
		ClaimListController.editClaim(MainActivity.editableClaim,
				Status.valueOf(text), date, end, desc);
		// Exits activity
		onBackPressed();
	}
}

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
import android.widget.Toast;

public class EditClaimInfo extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim_info);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.status_array, android.R.layout.simple_spinner_item);
		Spinner	viewStatus = (Spinner) findViewById(R.id.claimStatus);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		viewStatus.setAdapter(adapter);
		TravelClaim edit = MainActivity.editableClaim;
		
		EditText viewDate = (EditText) findViewById(R.id.claimDate);
		EditText viewDesc = (EditText) findViewById(R.id.claimDesc);
		EditText viewEndDate = (EditText) findViewById(R.id.claimEnd);

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
	public void EditClaimInfo(View v) throws ParseException {
		
		EditText viewDate = (EditText) findViewById(R.id.claimDate);
		EditText viewDesc = (EditText) findViewById(R.id.claimDesc);
		Spinner	viewStatus = (Spinner) findViewById(R.id.claimStatus);
		EditText viewEndDate = (EditText) findViewById(R.id.claimEnd);
		
		String startdate = viewDate.getText().toString();
		String enddate = viewEndDate.getText().toString();
		String desc = viewDesc.getText().toString();
		String text = viewStatus.getSelectedItem().toString();
		int pos = viewStatus.getSelectedItemPosition();
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = format.parse(startdate);
		Date end = format.parse(enddate);
		
		
		ClaimListController.editClaim(MainActivity.editableClaim, Status.valueOf(text), date, end, desc);
		Toast.makeText(this, "edited", Toast.LENGTH_SHORT).show();
		//viewStatus.setOnItemSelectedListener(this);
		onBackPressed();
	}
}

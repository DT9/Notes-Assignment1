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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class AddClaimInfo extends Activity implements OnItemSelectedListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_claim_info_activity);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.status_array, android.R.layout.simple_spinner_item);
		Spinner	viewStatus = (Spinner) findViewById(R.id.claimStatus);
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
	public void saveClaimInfo(View v) throws ParseException {
		
		EditText viewDate = (EditText) findViewById(R.id.claimDate);
		EditText viewDesc = (EditText) findViewById(R.id.claimDesc);
		Spinner	viewStatus = (Spinner) findViewById(R.id.claimStatus);
		EditText viewEndDate = (EditText) findViewById(R.id.claimEnd);
		
		String startdate = viewDate.getText().toString();
		String enddate = viewEndDate.getText().toString();
		String desc = viewDesc.getText().toString();
		String text = viewStatus.getSelectedItem().toString();
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = format.parse(startdate);
		Date end = format.parse(enddate);
		
		
		ClaimListController.addClaim(Status.valueOf(text), date, end, desc);
		//Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		//viewStatus.setOnItemSelectedListener(this);
		onBackPressed();
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		Object p = arg0.getItemAtPosition(arg2);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}

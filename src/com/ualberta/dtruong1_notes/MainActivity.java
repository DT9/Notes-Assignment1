/*
Expense App: Tracks expenses of travel claims

Copyright {2015} {Dennis Truong}
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.ualberta.dtruong1_notes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ualberta.dtruong1_notes.TravelClaim.Status;

/**
 * @author               dtruong1  main Activity where launcher starts, displays claim list
 * @uml.dependency   supplier="com.ualberta.dtruong1_notes.ClaimListController"
 * @uml.dependency   supplier="com.ualberta.dtruong1_notes.ListExpenseActivity"
 * @uml.dependency   supplier="com.ualberta.dtruong1_notes.AddClaimInfo"
 * @uml.dependency   supplier="com.ualberta.dtruong1_notes.EditClaimInfo"
 */
public class MainActivity extends Activity {
	/**
	 * @uml.property   name="editableClaim"
	 * @uml.associationEnd   
	 */
	static protected TravelClaim editableClaim; // claim to be edited
	static protected ArrayAdapter<TravelClaim> claimAdapter; // Adapter claim
																// list view
	private String FILENAME1 = "infoclaims"; // save file name

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// loads file
		loadFromFile();

		// Initiates claim list view
		ArrayList<TravelClaim> claimlist = ClaimListController.getClaimList();
		final ArrayList<TravelClaim> cll = new ArrayList<TravelClaim>(claimlist);
		ListView claims = (ListView) findViewById(R.id.listView1);
		claimAdapter = new ArrayAdapter<TravelClaim>(this,
				android.R.layout.simple_list_item_1, cll);
		claims.setAdapter(claimAdapter);
		// Adds listeners for each claims
		ClaimListController.addListener(new Listener() {
			@Override
			public void update() {
				// Refreshes list view
				cll.clear();
				cll.addAll(ClaimListController.getClaimList());
				claimAdapter.notifyDataSetChanged();
			}
		});

		// Shows expenses on claim click
		claims.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterview, View view,
					int position, long id) {
				//Launches expense list activity
				final int pos = position;
				Intent intent = new Intent(MainActivity.this,
						ListExpenseActivity.class);
				editableClaim = ClaimListController.getItem(pos);
				startActivity(intent);
			}
		});

		//Long click shows Edit, Email, or Delete
		claims.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterview,
					View view, int position, long id) {
				final int pos = position;
				// Alert dialog built
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setCancelable(true);
				builder.setTitle(R.string.long_click_claim).setItems(
						R.array.claim_long_array,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// The 'which' argument contains the index
								// position
								// of the selected item
								TravelClaim claim = cll.get(pos);
								
								switch (which) {
								case 0: // edit
									if (claim.getStatus() == Status.approved
											|| Status.submitted == claim
													.getStatus()) {
										Toast.makeText(
												MainActivity.this,
												claim.getStatus().toString()
														+ ": No further edits can be made!",
												Toast.LENGTH_SHORT).show();
										break;
									}
									Intent edits = new Intent(
											MainActivity.this,
											EditClaimInfo.class);
									editableClaim = claim;
									startActivity(edits);

									break;
								case 1: // email
									Intent emailIntent = new Intent(
											Intent.ACTION_SENDTO,
											Uri.fromParts("mailto",
													"myemail@gmail.com", null));
									String body = claim.toString()
											+ claim.emailBody();
									emailIntent.putExtra(Intent.EXTRA_SUBJECT,
											"Claims");
									emailIntent.putExtra(Intent.EXTRA_TEXT,
											body);
									try {
										startActivity(Intent.createChooser(
												emailIntent, "Send mail..."));
									} catch (android.content.ActivityNotFoundException ex) {
										Toast.makeText(
												MainActivity.this,
												"There are no email clients installed or enabled",
												Toast.LENGTH_SHORT).show();
									}
									break;
								case 2: // delete
									ClaimListController.removeClaim(claim);
									break;
								default:
									break;
								}
							}
						});

				builder.show();
				return false;
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();

	}

	@Override
	public void onResume() {
		super.onResume();
		saveInFile();
	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// add claim item to list
	public void addClaimItem(View v) {
		Intent intent = new Intent(MainActivity.this, AddClaimInfo.class);
		startActivity(intent);

	}

	// Loads file
	@SuppressWarnings("unchecked")
	private void loadFromFile() {
		Gson gson = new Gson();
		try {
			FileInputStream fis = openFileInput(FILENAME1);
			InputStreamReader in = new InputStreamReader(fis);
			// http://google-gson.googlecode.com/…/c…/google/gson/Gson.html
			// 2015-1-20

			Type typeOfT = new TypeToken<ArrayList<TravelClaim>>() {
			}.getType();
			Object tweets = gson.fromJson(in, typeOfT); // Gson loads
			ClaimListController.claimlist = (ArrayList<TravelClaim>) tweets;
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// Saves claims to internal storage
	public void saveInFile() {
		Gson gson = new Gson();

		try {
			FileOutputStream fos = openFileOutput(FILENAME1, 0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(ClaimListController.getClaimList(), osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

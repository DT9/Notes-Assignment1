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


import java.util.ArrayList;

import com.ualberta.dtruong1_notes.TravelClaim.Status;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	static public TravelClaim editableClaim;
	static ArrayAdapter<TravelClaim> claimAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    
        ArrayList<TravelClaim> claimlist = ClaimListController.getClaimList();
        final ArrayList<TravelClaim> cll = new ArrayList<TravelClaim>(claimlist);
        ListView claims = (ListView) findViewById(R.id.listView1);
        claimAdapter = new ArrayAdapter<TravelClaim>(this, android.R.layout.simple_list_item_1,cll);
        claims.setAdapter(claimAdapter);
        
        
        ClaimListController.addListener(new Listener() {
        	@Override
        	public void update() {
        		cll.clear();
        		ArrayList<TravelClaim> newList = ClaimListController.getClaimList();
        		cll.addAll(newList);
        		claimAdapter.notifyDataSetChanged();
        	}
        });
    	
        claims.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterview, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				final int pos = position;
				Intent intent = new Intent(MainActivity.this, ListExpenseActivity.class);
				editableClaim = cll.get(pos);
				startActivity(intent);
			}
		});
        
        claims.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterview, View view,
					int position, long id) {
				final int pos = position;
			    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			    builder.setCancelable(true);
			    builder.setTitle(R.string.long_click_claim)
		           .setItems(R.array.claim_long_array, new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int which) {
		               // The 'which' argument contains the index position
		               // of the selected item
		            	   TravelClaim claim = cll.get(pos);

		            	   switch(which) {
		            	   case 0:	//edit
		            		   if (claim.getStatus() == Status.approved || Status.submitted == claim.getStatus()) {
			            		    Toast.makeText(MainActivity.this, claim.getStatus().toString() + ": No further edits can be made!", Toast.LENGTH_SHORT).show();
		            			   break;
		            		   }
		            		   Intent edits = new Intent(MainActivity.this, EditClaimInfo.class);
		            		   editableClaim = claim;
		            		   startActivity(edits);
		            		   
		            		   break;
		            	   case 1: //email
		            		   Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
		            		            "mailto","myemail@gmail.com", null));
		            		   String body = claim.toString() + claim.emailBody();
		            		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Claims");
		            		emailIntent.putExtra(Intent.EXTRA_TEXT,body);
		            		try {
		            		    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
		            		} catch (android.content.ActivityNotFoundException ex) {
		            		    Toast.makeText(MainActivity.this, "There are no email clients installed or enabled", Toast.LENGTH_SHORT).show();
		            		}
		            		   break;
		            	   case 2: //delete
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public void addClaimItem(View v) {
    	Toast.makeText(this, "add claim", Toast.LENGTH_LONG).show();
    	Intent intent = new Intent(MainActivity.this, AddClaimInfo.class);
    	startActivity(intent);
    	
    }
}


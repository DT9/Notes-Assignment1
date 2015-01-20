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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    
        ArrayList<TravelClaim> claimlist = ClaimListController.getClaimList();
        final ArrayList<TravelClaim> cll = new ArrayList<TravelClaim>(claimlist);
        ListView claims = (ListView) findViewById(R.id.listView1);
        final ArrayAdapter<TravelClaim> claimAdapter = new ArrayAdapter<TravelClaim>(this, android.R.layout.simple_list_item_1,cll);
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
    	
        claims.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterview, View view,
					int position, long id) {
				
				TravelClaim claim = cll.get(position);
				ClaimListController.removeClaim(claim);
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
    
    public void emailPeople(MenuItem menu) {
    	Toast.makeText(this, "Helloz", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, ListExpenseActivity.class);
    	startActivity(intent);
    }
    
    public void addClaimItem(View v) {
    	Toast.makeText(this, "add claim", Toast.LENGTH_LONG).show();
    	Intent intent = new Intent(MainActivity.this, AddClaimInfo.class);
    	startActivity(intent);
    	
    }
}


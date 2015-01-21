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

public class ListExpenseActivity extends Activity {
<<<<<<< HEAD

	
	
=======
	ExpenseItem editableExpense;
>>>>>>> f9278ecb00e80f68ee243a6799081224f0bc0c5e
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_expenses);
		ArrayList<ExpenseItem> claimlist = MainActivity.editableClaim.getItems();
		final ArrayList<ExpenseItem> cll = new ArrayList<ExpenseItem>(claimlist);
        	ListView claims = (ListView) findViewById(R.id.listView1);
        	final ArrayAdapter<ExpenseItem> claimAdapter = new ArrayAdapter<ExpenseItem>(this, android.R.layout.simple_list_item_1,cll);
        	claims.setAdapter(claimAdapter);
        	
        	
        	
        	
	        claims.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterview, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			final int pos = position;
			Intent intent = new Intent(ListExpenseActivity.this, ListExpenseActivity.class);
			editableExpense = cll.get(pos);
			startActivity(intent);
		}
	});
	
        claims.setOnItemLongClickListener(new OnItemLongClickListener() {

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterview, View view,
			int position, long id) {
		final int pos = position;
	    AlertDialog.Builder builder = new AlertDialog.Builder(ListExpenseActivity.this);
	    builder.setCancelable(true);
	    builder.setTitle(R.string.long_click_claim)
           .setItems(R.array.claim_long_array, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
               // The 'which' argument contains the index position
               // of the selected item
            	   ExpenseItem expense = cll.get(pos);

            	   switch(which) {
            	   case 0:	//edit
            		   if (MainActivity.editableClaim.getStatus() == Status.approved || Status.submitted == MainActivity.editableClaim.getStatus()) {
	            		    Toast.makeText(ListExpenseActivity.this, MainActivity.editableClaim.getStatus().toString() + ": No further edits can be made!", Toast.LENGTH_SHORT).show();
            			   break;
            		   }
            		   Intent edits = new Intent(ListExpenseActivity.this, EditClaimInfo.class);
            		   editableExpense = expense;
            		   startActivity(edits);
            		   
            		   break;
            	   case 1: //delete
            		   MainActivity.editableClaim.removeItem(expense);
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
		getMenuInflater().inflate(R.menu.list_expense, menu);
		return true;
	}
<<<<<<< HEAD
=======
	public void addExpenseItem(View v) {
    	Toast.makeText(this, "add Expense", Toast.LENGTH_LONG).show();
    	Intent intent = new Intent(ListExpenseActivity.this, AddExpenseInfo.class);
    	startActivity(intent);
    	}
>>>>>>> f9278ecb00e80f68ee243a6799081224f0bc0c5e
	
	

}

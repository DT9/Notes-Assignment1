package com.ualberta.dtruong1_notes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ListExpenseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_expenses);
		ArrayList<ExpenseItem> claimlist = ClaimListController.getClaimList();
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
		getMenuInflater().inflate(R.menu.list_expense, menu);
		return true;
	}
	public void addExpenseItem(View v) {
    	Toast.makeText(this, "add Expense", Toast.LENGTH_LONG).show();
    	Intent intent = new Intent(ListExpenseActivity.this, AddExpenseInfo.class);
    	startActivity(intent);
    	}
	
	

}

package com.ualberta.dtruong1_notes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ualberta.dtruong1_notes.TravelClaim.Status;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author dtruong1
 */
public class ListExpenseActivity extends Activity {

	/**
	 * @uml.property name="editableExpense"
	 * @uml.associationEnd
	 */
	protected static ExpenseItem editableExpense = null;
	protected static ArrayAdapter<ExpenseItem> expenseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_expenses);
		ArrayList<ExpenseItem> expenselist = MainActivity.editableClaim
				.getItems();
		final ArrayList<ExpenseItem> cll = new ArrayList<ExpenseItem>(
				expenselist);
		ListView expenses = (ListView) findViewById(R.id.expense_list);
		expenseAdapter = new ArrayAdapter<ExpenseItem>(this,
				android.R.layout.simple_list_item_1, cll);
		expenses.setAdapter(expenseAdapter);

		expenses.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterview,
					View view, int position, long id) {
				final int pos = position;
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ListExpenseActivity.this);
				builder.setCancelable(true);
				builder.setTitle(R.string.long_click_claim).setItems(
						R.array.expense_long_array,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// The 'which' argument contains the index
								// position
								// of the selected item
								ExpenseItem expense = cll.get(pos);

								switch (which) {
								case 0: // edit
									if (MainActivity.editableClaim.getStatus() == Status.approved
											|| Status.submitted == MainActivity.editableClaim
													.getStatus()) {
										Toast.makeText(
												ListExpenseActivity.this,
												MainActivity.editableClaim
														.getStatus().toString()
														+ ": No further edits can be made!",
												Toast.LENGTH_SHORT).show();
										break;
									}
									Intent edits = new Intent(
											ListExpenseActivity.this,
											ExpenseInfo.class);
									editableExpense = expense;
									startActivity(edits);

									break;
								case 1: // delete
									MainActivity.editableClaim
											.removeItem(expense);
									onResume();
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

	public void onResume() {
		super.onResume();
		expenseAdapter.clear();
		expenseAdapter.addAll(MainActivity.editableClaim.getItems());
		expenseAdapter.notifyDataSetChanged();

	}

	public void onStart() {
		super.onStart();
		TextView total = (TextView) findViewById(R.id.claimcurrency);
		total.setText(MainActivity.editableClaim.getTotal());
		saveInFile();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_expense, menu);
		return true;
	}

	public void addExpenseItem(View v) {
		Intent intent = new Intent(ListExpenseActivity.this, ExpenseInfo.class);
		editableExpense = null;
		startActivity(intent);

	}

	private String FILENAME = "claiminfo";

	public void saveInFile() {
		Gson gson = new Gson();

		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
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

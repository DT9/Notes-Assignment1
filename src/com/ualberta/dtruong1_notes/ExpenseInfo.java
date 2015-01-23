package com.ualberta.dtruong1_notes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ualberta.dtruong1_notes.ExpenseItem.Category;
import com.ualberta.dtruong1_notes.ExpenseItem.CurrencyUnit;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/*
 *  Edits or Adds new expenses to a claim in an activity
 */
public class ExpenseInfo extends Activity {
	// Expense view fields
	private Spinner currency;
	private Spinner category;
	private EditText date;
	private EditText description;
	private EditText amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_info);
		// Adapter for Category Spinner
		ArrayAdapter<Category> adapter = new ArrayAdapter<ExpenseItem.Category>(
				this, android.R.layout.simple_spinner_item,
				ExpenseItem.Category.values());
		category = (Spinner) findViewById(R.id.spinnerCategory);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		category.setAdapter(adapter);
		// Initialize currency Spinner
		currency = (Spinner) findViewById(R.id.spinnerCurrency);
		currency.setAdapter(new ArrayAdapter<ExpenseItem.CurrencyUnit>(this,
				android.R.layout.simple_spinner_dropdown_item,
				ExpenseItem.CurrencyUnit.values()));
		// Obtains view from id for expenses
		date = (EditText) findViewById(R.id.editTextDate);
		description = (EditText) findViewById(R.id.editTextDescription);
		amount = (EditText) findViewById(R.id.editTextAmount);
	}

	@Override
	public void onStart() {
		super.onStart();
		// If the expense is an edit, fill in text fields
		if (ListExpenseActivity.editableExpense != null) {
			ExpenseItem edit = ListExpenseActivity.editableExpense;
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			// Gets text field values and set Editviews
			currency.setSelection(edit.getCurrency().ordinal());
			category.setSelection(edit.getCategory().ordinal());
			date.setText(formatter.format(edit.getDate()));
			description.setText(edit.getText());
			amount.setText(Integer.toString(edit.getAmount()));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_expense, menu);
		return true;
	}

	public void onSave(View v) throws ParseException {
		ExpenseItem edit = ListExpenseActivity.editableExpense;
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		// Sets expense to be edited to user input
		if (edit != null) {
			// Parses data values
			edit.setAmount(Integer.parseInt(amount.getText().toString()));
			edit.setCategory(Category.valueOf(category.getSelectedItem()
					.toString()));
			edit.setCurrency(CurrencyUnit.valueOf(currency.getSelectedItem()
					.toString()));
			edit.setText(description.getText().toString());
			edit.setDate(format.parse(date.getText().toString()));
			ListExpenseActivity.editableExpense = null;
			onBackPressed();
			// Refresh list view
			MainActivity.claimAdapter.notifyDataSetChanged();

			return;
		}

		// Otherwise add new expense to the claim
		TravelClaim claim = MainActivity.editableClaim;
		claim.addItem(new ExpenseItem(format.parse(date.getText().toString()),
				Category.valueOf(category.getSelectedItem().toString()),
				description.getText().toString(), Integer.parseInt(amount
						.getText().toString()), CurrencyUnit.valueOf(currency
						.getSelectedItem().toString())));
		MainActivity.claimAdapter.notifyDataSetChanged();

		onBackPressed();
	}

}

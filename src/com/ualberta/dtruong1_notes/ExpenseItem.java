package com.ualberta.dtruong1_notes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dtruong1 Struct of Expense item values
 */
public class ExpenseItem implements Serializable {

	private static final long serialVersionUID = -6896056147275778175L;

	private Date date;

	private Category category;

	private String text;

	private int amount = 0;

	private CurrencyUnit currency;

	public enum CurrencyUnit {

		CAD, USD, EUR, GBP;
	}

	/**
	 * @author dtruong1
	 */
	public enum Category {

		airfare("airfare"), groundtransport("groundtransport"), vehiclerental(
				"vehiclerental"), fuel("fuel"), parking("parking"), registration(
				"registration"), accommodation("accommodation"), meal("meal");

		private String cate;

		Category(String cates) {
			cate = cates;
		}

		@Override
		public String toString() {
			return cate;
		}
	}

	// Constructor adds new expenses to the claim
	public ExpenseItem(Date date, Category category, String text, int amount,
			CurrencyUnit currency) {
		// TODO Auto-generated constructor stub
		this.date = date;
		this.category = category;
		this.text = text;
		this.amount = amount;
		this.currency = currency;
	}

	@Override
	public String toString() {
		// Returns a string of all expense information
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String text = formatter.format(date) + "-" + category.toString() + "\n"
				+ this.text + "\nAmount: " + amount + " " + currency.toString();
		return text;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CurrencyUnit getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyUnit currency) {
		this.currency = currency;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCategory(String pos) {
		// TODO Auto-generated method stub
		this.category = Category.valueOf(pos);
	}

	public void setCurrency(String string) {
		// TODO Auto-generated method stub
		this.currency = CurrencyUnit.valueOf(string);
	}

	/**
	 * @uml.property  name="travelClaim"
	 * @uml.associationEnd  inverse="expenseItem:com.ualberta.dtruong1_notes.TravelClaim"
	 */
	private TravelClaim travelClaim;

	/**
	 * Getter of the property <tt>travelClaim</tt>
	 * @return  Returns the travelClaim.
	 * @uml.property  name="travelClaim"
	 */
	public TravelClaim getTravelClaim() {
		return travelClaim;
	}

	/**
	 * Setter of the property <tt>travelClaim</tt>
	 * @param travelClaim  The travelClaim to set.
	 * @uml.property  name="travelClaim"
	 */
	public void setTravelClaim(TravelClaim travelClaim) {
		this.travelClaim = travelClaim;
	}

}

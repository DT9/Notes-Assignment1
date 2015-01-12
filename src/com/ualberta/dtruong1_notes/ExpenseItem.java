package com.ualberta.dtruong1_notes;

import java.util.Date;

public class ExpenseItem {
	private Date date;
	private Category category;
	private String text;
	private float amount;
	private CurrencyUnit currency;
	
	public enum CurrencyUnit {
		CAD,USD,EUR,GBP;
	}
	
	public enum Category {
		airfare, groundtransport, vehiclerental, fuel, parking, registration, accommodation, meal;
	}

	public ExpenseItem() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the currency
	 */
	public CurrencyUnit getCurrency() {
		return currency;
	}
	
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(CurrencyUnit currency) {
		this.currency = currency;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}

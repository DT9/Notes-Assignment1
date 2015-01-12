package com.ualberta.dtruong1_notes;

import java.util.Date;

/**
 * @author  dtruong1
 */
public class ExpenseItem {
	private Date date;
	/**
	 * @uml.property  name="category"
	 * @uml.associationEnd  
	 */
	private Category category;
	private String text;
	private float amount;
	/**
	 * @uml.property  name="currency"
	 * @uml.associationEnd  
	 */
	private CurrencyUnit currency;
	
	/**
	 * @author  dtruong1
	 */
	public enum CurrencyUnit {
		/**
		 * @uml.property  name="cAD"
		 * @uml.associationEnd  
		 */
		CAD,/**
		 * @uml.property  name="uSD"
		 * @uml.associationEnd  
		 */
		USD,/**
		 * @uml.property  name="eUR"
		 * @uml.associationEnd  
		 */
		EUR,/**
		 * @uml.property  name="gBP"
		 * @uml.associationEnd  
		 */
		GBP;
	}
	
	/**
	 * @author  dtruong1
	 */
	public enum Category {
		/**
		 * @uml.property  name="airfare"
		 * @uml.associationEnd  
		 */
		airfare, /**
		 * @uml.property  name="groundtransport"
		 * @uml.associationEnd  
		 */
		groundtransport, /**
		 * @uml.property  name="vehiclerental"
		 * @uml.associationEnd  
		 */
		vehiclerental, /**
		 * @uml.property  name="fuel"
		 * @uml.associationEnd  
		 */
		fuel, /**
		 * @uml.property  name="parking"
		 * @uml.associationEnd  
		 */
		parking, /**
		 * @uml.property  name="registration"
		 * @uml.associationEnd  
		 */
		registration, /**
		 * @uml.property  name="accommodation"
		 * @uml.associationEnd  
		 */
		accommodation, /**
		 * @uml.property  name="meal"
		 * @uml.associationEnd  
		 */
		meal;
	}

	public ExpenseItem() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @return  the amount
	 * @uml.property  name="amount"
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount  the amount to set
	 * @uml.property  name="amount"
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return  the currency
	 * @uml.property  name="currency"
	 */
	public CurrencyUnit getCurrency() {
		return currency;
	}
	
	/**
	 * @param currency  the currency to set
	 * @uml.property  name="currency"
	 */
	public void setCurrency(CurrencyUnit currency) {
		this.currency = currency;
	}

	/**
	 * @return  the text
	 * @uml.property  name="text"
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text  the text to set
	 * @uml.property  name="text"
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return  the category
	 * @uml.property  name="category"
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category  the category to set
	 * @uml.property  name="category"
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return  the date
	 * @uml.property  name="date"
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date  the date to set
	 * @uml.property  name="date"
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}

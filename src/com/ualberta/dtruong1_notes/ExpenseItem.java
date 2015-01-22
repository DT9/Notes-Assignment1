package com.ualberta.dtruong1_notes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  dtruong1
 */
public class ExpenseItem implements Serializable{
	/**
	 * expense item id
	 */
	private static final long serialVersionUID = -6896056147275778175L;
	private Date date;
	/**
	 * @uml.property  name="category"
	 * @uml.associationEnd  
	 */
	private Category category;
	private String text;
	private int amount = 0;
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
		airfare("airfare"), /**
		 * @uml.property  name="groundtransport"
		 * @uml.associationEnd  
		 */
		groundtransport("groundtransport"), /**
		 * @uml.property  name="vehiclerental"
		 * @uml.associationEnd  
		 */
		vehiclerental("vehiclerental"), /**
		 * @uml.property  name="fuel"
		 * @uml.associationEnd  
		 */
		fuel("fuel"), /**
		 * @uml.property  name="parking"
		 * @uml.associationEnd  
		 */
		parking("parking"), /**
		 * @uml.property  name="registration"
		 * @uml.associationEnd  
		 */
		registration("registration"), /**
		 * @uml.property  name="accommodation"
		 * @uml.associationEnd  
		 */
		accommodation("accommodation"), /**
		 * @uml.property  name="meal"
		 * @uml.associationEnd  
		 */
		meal("meal");
		private String cate;
		Category(String cates) {
			cate = cates;
		}
		@Override public String toString() {
			return cate;
		}
	}

	public ExpenseItem(Date date, Category category, String text, int amount,CurrencyUnit currency) {
		// TODO Auto-generated constructor stub
		this.date = date;
		this.category = category;
		this.text = text;
		this.amount = amount;
		this.currency = currency;
	}

	public String toString() {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String text =  formatter.format(date) +"-"+ category.toString() +"\n"+this.text+"\nAmount: " + amount + " " + currency.toString(); 
		return text;
	}
	/**
	 * @return  the amount
	 * @uml.property  name="amount"
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount  the amount to set
	 * @uml.property  name="amount"
	 */
	public void setAmount(int amount) {
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

	public void setCategory(String pos) {
		// TODO Auto-generated method stub
		this.category = Category.valueOf(pos);
	}

	public void setCurrency(String string) {
		// TODO Auto-generated method stub
		this.currency = CurrencyUnit.valueOf(string);
	}

}

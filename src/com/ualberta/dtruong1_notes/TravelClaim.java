/**
 * 
 */
package com.ualberta.dtruong1_notes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dennis
 *
 */
public class TravelClaim {
	
	public enum status {
		inprogress,submitted,returned,approved;
	}
	
	private Boolean editable = true;
	private Float[] total;
	private Date date;
	private String text;
	private List<ExpenseItem> items = new ArrayList<ExpenseItem>();
	/**
	 * 
	 */
	public TravelClaim() {
		// TODO Auto-generated constructor stub
	}

	public void SetTotal() {
		int length = ExpenseItem.CurrencyUnit.values().length;
		Float[] total = new Float[length];
		for (ExpenseItem item : items) {
			total[item.getCurrency().ordinal()] += item.getAmount();
		}
		
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
	 * @return the total
	 */
	public Float[] getTotal() { //String todo
		return total;
	}

	/**
	 * @return the editable
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}


}

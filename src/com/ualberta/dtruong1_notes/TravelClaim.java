/**
 * 
 */
package com.ualberta.dtruong1_notes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author  Dennis
 */
public class TravelClaim {
	
	/**
	 * @author  dtruong1
	 */
	public enum status {
		/**
		 * @uml.property  name="inprogress"
		 * @uml.associationEnd  
		 */
		inprogress,/**
		 * @uml.property  name="submitted"
		 * @uml.associationEnd  
		 */
		submitted,/**
		 * @uml.property  name="returned"
		 * @uml.associationEnd  
		 */
		returned,/**
		 * @uml.property  name="approved"
		 * @uml.associationEnd  
		 */
		approved;
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
	 * @return  the total
	 * @uml.property  name="total"
	 */
	public Float[] getTotal() { //String todo
		return total;
	}

	/**
	 * @return  the editable
	 * @uml.property  name="editable"
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable  the editable to set
	 * @uml.property  name="editable"
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}


}

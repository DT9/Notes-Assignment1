/**
 * 
 */
package com.ualberta.dtruong1_notes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ualberta.dtruong1_notes.ExpenseItem.CurrencyUnit;

/**
 * @author Dennis
 */
public class TravelClaim implements Comparable<TravelClaim>, Serializable {

	/**
	 * travelclaim id
	 */
	private static final long serialVersionUID = 3695701892336777254L;

	/**
	 * @author dtruong1
	 */
	public enum Status {
		/**
		 * @uml.property name="inprogress"
		 * @uml.associationEnd
		 */
		inprogress, /**
		 * @uml.property name="submitted"
		 * @uml.associationEnd
		 */
		submitted, /**
		 * @uml.property name="returned"
		 * @uml.associationEnd
		 */
		returned, /**
		 * @uml.property name="approved"
		 * @uml.associationEnd
		 */
		approved;
	}

	/**
	 * @uml.property name="status"
	 * @uml.associationEnd
	 */
	private Status status;
	/**
	 * @uml.property name="editable"
	 */
	private Boolean editable = true;
	private int[] total;
	/**
	 * @uml.property name="date"
	 */
	private Date date;
	private Date end;
	/**
	 * @uml.property name="text"
	 */
	private String text;
	private ArrayList<ExpenseItem> items = new ArrayList<ExpenseItem>();

	/**
	 * 
	 */
	@Override
	public int compareTo(TravelClaim o) {
		return getDate().compareTo(o.getDate());
	}

	public TravelClaim(Status status, Date date, Date end, String text) {
		// TODO Auto-generated constructor stub
		this.setStatus(status);
		this.date = date;
		this.text = text;
		this.setEnd(end);
	}


	public String toString() {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String claim = status.toString() + "\n" + formatter.format(date)
				+ " to " + formatter.format(end) + "\n" + getTotal();
		return claim;
	}

	public String emailBody() {
		String body = "";
		for (ExpenseItem item : items) {
			body += item.toString() + "\n";
		}

		return body;
	}

	public void addItem(ExpenseItem item) {
		this.items.add(item);
	}

	public void removeItem(ExpenseItem expense) {
		this.items.remove(expense);
	}

	/**
	 * @return
	 * @uml.property name="items"
	 */
	public ArrayList<ExpenseItem> getItems() {
		return items;
	}

	public void SetTotal() {
		int length = ExpenseItem.CurrencyUnit.values().length;
		this.total = new int[length];
		for (ExpenseItem item : items) {
			total[item.getCurrency().ordinal()] += item.getAmount();
		}
	}

	/*
	 * @return the total
	 * 
	 * @uml.property name="total"
	 */
	/**
	 * @return
	 * @uml.property name="total"
	 */
	public String getTotal() { // String
		this.SetTotal();
		String curr = "Total: ";
		CurrencyUnit[] currencies = ExpenseItem.CurrencyUnit.values();
		for (int i = 0; i < ExpenseItem.CurrencyUnit.values().length; i++) {
			// ExpenseItem item = this.items.get(i);
			int price = total[i];
			if (price == 0) {
				continue;
			}
			curr += currencies[i];
			curr += ": " + price + " ";
		}
		return curr;
	}

	/**
	 * @return the date
	 * @uml.property name="date"
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 * @uml.property name="date"
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the text
	 * @uml.property name="text"
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 * @uml.property name="text"
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the editable
	 * @uml.property name="editable"
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the editable to set
	 * @uml.property name="editable"
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return
	 * @uml.property name="status"
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 * @uml.property name="status"
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return
	 * @uml.property name="end"
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param end
	 * @uml.property name="end"
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

}

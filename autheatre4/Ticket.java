package autheatre4;

import java.io.Serializable;
import java.util.*;
/**
 * Parent class of the ticket
 * @author group 1
 *
 */
public abstract class Ticket implements Serializable, Matchable<String> {
	//serialization
	private static final long serialVersionUID = 1L;
	//fields
	private String serialNumber;
	private String customerID;
	private int quantity;
	private Show show;
	private Date showDate;
	private String type;
	protected double price;
	protected double total;
	/**
	 * A constructor method for a ticket object
	 * @param quantity - the quantity of tickets purchased
	 * @param customerID - the ID of the purchasing customer
	 * @param showDate - the date of the show
	 * @param show - the show
	 */

	public Ticket(int quantity, String customerID, Date showDate, Show show) {
		this.setSerialNumber(null);
		this.setQuantity(quantity);
		this.setCustomerID(customerID);
		this.setShowDate(showDate);
		this.setShow(show);
	}

	// Getters and setters
	public double getPrice() {
		return price;
	}

	public abstract void setPrice(double price);

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		if (serialNumber != null) {
			this.serialNumber = serialNumber;
		} else
			this.serialNumber = UUID.randomUUID().toString();
	}

	/**
	 * method to get type of ticket
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type - the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total - the total to set
	 */
	public abstract void setTotal(int quantity);

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	//toSTring method
	public String toString() {
		return "Ticket{" + "serialNumber=" + serialNumber + ", customerID="
				+ customerID + ", showName=" + show.getShowName()
				+ ", date of ticket=" + showDate + ", ticket type=" + type
				+ ", price per ticket=$" + price + ", quantity=" + quantity
				+ ", total cost=$" + total + '}';
	}
}

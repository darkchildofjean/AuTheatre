package autheatre4;

import java.util.*;

/**
 * 
 * @author Don't even know anymore 
 * Class object to represent a student advanced ticker
 */

public class StudentAdvanceTicket extends Ticket {

	//serialization
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param quantity - quantity of tickets purchased
	 * @param customerID - the ID of purchasing customer
	 * @param showDate - the date of the show
	 * @param show - the show
	 */

	public StudentAdvanceTicket(int quantity, String customerID, Date showDate,
			Show show) {
		super(quantity, customerID, showDate, show);
		this.setPrice(show.getPrice());
		this.setType("student advance ticket");
		this.setTotal(quantity);
	}
	//setters and getters. Why are these overidden?
	@Override
	public void setPrice(double price) {
		this.price = price * 0.35;
	}

	@Override
	public void setTotal(int quantity) {
		// TODO Auto-generated method stub
		this.total = this.getPrice() * this.getQuantity();
	}

	@Override
	public boolean matches(String key) {
		// TODO Auto-generated method stub
		return false;
	}

}

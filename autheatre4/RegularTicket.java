package autheatre4;

import java.util.*;

/**
 * 
 * @author group 1
 * A class for the regular ticket specifications
 *
 */
public class RegularTicket extends Ticket {

	//serialization version control
	private static final long serialVersionUID = 1L;
	/**
	 * constructor method for the regular ticket
	 * @param quantity - quantity of tickets to be sold
	 * @param customerID - the ID of the purchaser
	 * @param showDate -the date of the show purchased for
	 * @param show - the show object
	 */
	public RegularTicket(int quantity, String customerID, Date showDate,
			Show show) {
		super(quantity, customerID, showDate, show);
		this.setPrice(show.getPrice());
		this.setType("regular ticket");
		this.setTotal(quantity);
	}
	/*
	 * (non-Javadoc) BEGIN THE SETTERS AND GETTERS!
	 */
	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public void setTotal(int quantity) {
		// TODO Auto-generated method stub
		this.total = this.getPrice() * this.getQuantity();
	}
	//end setters and getters
	//matches function which I don't think we need again?
	@Override
	public boolean matches(String key) {
		// TODO Auto-generated method stub
		return false;
	}

}

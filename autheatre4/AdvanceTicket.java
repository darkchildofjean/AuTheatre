package autheatre4;

import java.util.*;
/**
 * 
 * @author group 1
 * A class for the advanced ticket specifications
 *
 */

public class AdvanceTicket extends Ticket{
	
    	//for serialization
	private static final long serialVersionUID = 1L;
	/**
	 * A constructor for the Advanced Ticket class
	 * @param quantity - quantity of tickets to be purchased
	 * @param customerID - Id of the purchasing customer
	 * @param showDate - date of show that tickets purchased for
	 * @param show - the show object which tickets are purchased for
	 */
	public AdvanceTicket(int quantity, String customerID, Date showDate, Show show) {
	    	super(quantity, customerID, showDate, show);
	    	this.setPrice(show.getPrice());
	    	this.setType("advance ticket");
	    	this.setTotal(quantity);
	    }
		//Getters and setters.
		@Override
		public void setPrice(double price) {
			 this.price = price * 0.7;
		}
		@Override
		public void setTotal(int quantity) {
			// TODO Auto-generated method stub
			this.total = this.getPrice() * this.getQuantity();
		}
		/**
		 * Matches function. Don't think we need this but
		 * will leave here for now.
		 */
		@Override
		public boolean matches(String key) {
			// TODO Auto-generated method stub
			return false;
		}

}

package autheatre4;

import java.util.Date;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 * 
 *            Redistribution and use with or without modification, are permitted
 *            provided that the following conditions are met:
 *
 *            - the use is for academic purpose only - Redistributions of source
 *            code must retain the above copyright notice, this list of
 *            conditions and the following disclaimer. - Neither the name of
 *            Brahma Dathan or Sarnath Ramnath may be used to endorse or promote
 *            products derived from this software without specific prior written
 *            permission.
 *
 *            The authors do not make any claims regarding the correctness of
 *            the code in this module and are not responsible for any loss or
 *            damage resulting from its use.
 */
/**
 * Creates different types of Ticket objects. When a new Ticket is
 * introduced, the constructor for that class must be invoked from here. This is
 * a singleton.
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
/**
 * 
 * @author I hope professor tandon said it was okay
 * to use this class? - Cole
 *
 */
public class TicketFactory {
	private static TicketFactory factory;

	/**
	 * Private for singleton
	 */
	private TicketFactory() {
	}

	/**
	 * Returns the only instance of the class.
	 * 
	 * @return the instance
	 */
	public static TicketFactory instance() {
		if (factory == null) {
			factory = new TicketFactory();
		}
		return factory;
	}

	/**
	 * Creates a Ticket object and returns it.
	 * 
	 * @param type
	 *            the type of the ticket
	 * @param quantity
	 *            the quantity of tickets being sold
	 * @param customerID
	 *            the ID of the customer purchasing the tickets
	 * @param showDate
	 *            the date of the show on the ticket
	 * @param show
	 *            the show to be viewed
	 * @return the ticket that was created
	 */
	public Ticket createTicket(int type, int quantity, String customerID, Date showDate, Show show) {
		switch (type) {
		case Theatre.REGULAR_TICKET:
			return new RegularTicket(quantity, customerID, showDate, show);
		case Theatre.ADVANCE_TICKET:
			return new AdvanceTicket(quantity, customerID, showDate, show);
		case Theatre.STUDENT_ADVANCE_TICKET:
			return new StudentAdvanceTicket(quantity, customerID, showDate, show);
		default:
			return null;
		}
	}

}

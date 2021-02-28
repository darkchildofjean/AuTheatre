/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autheatre4;

import java.util.*;
import java.text.*;
import java.io.*;

/**
 *
 * @author enricorastelli
 */
public class Theatre implements Serializable {
	//serialization
	private static final long serialVersionUID = 1L;
	//fields for a theater
	private int seatCapacity;
	private String name;
	private ClientList clientList;
	private ShowList showList;
	private CustomerList customerList;
	private CreditCardList creditCardList;
	private TicketList ticketsSold;
	private Double balance;
	private double revenue;
	private static Theatre theatre;
	//options
	public static final int CLIENT_NOT_FOUND = 1;
	public static final int CLIENT_STILL_HAS_SHOWS = 2;
	public static final int OPERATION_COMPLETED = 3;
	public static final int OPERATION_FAILED = 4;
	public static final int CUSTOMER_NOT_FOUND = 5;
	public static final int CREDITCARD_NOT_FOUND = 6;
	public static final int REGULAR_TICKET = 1;
	public static final int ADVANCE_TICKET = 2;
	public static final int STUDENT_ADVANCE_TICKET = 3;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	/**
	 * Private for the singleton pattern Creates the catalog and member
	 * collection objects
	 */
	private Theatre() {
		clientList = ClientList.instance();
		showList = ShowList.instance();
		customerList = CustomerList.instance();
		creditCardList = CreditCardList.instance();
		ticketsSold = TicketList.instance();
	}

	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static Theatre instance() {
		if (theatre == null) {
			return theatre = new Theatre();
		} else {
			return theatre;
		}
	}

	/**
	 * Organizes the operations for adding a client
	 *
	 * @param clientName    member name
	 * @param clientAddress member address
	 * @param clientPhone   member phone
	 * @param contactName   client contact name
	 * @return the Member object created
	 */
	public Client addClient(String clientName, String clientAddress,
			String clientPhone, String contactName, String id) {
		Client client = new Client(clientName, clientAddress, clientPhone,
				contactName, id, 1000.00);
		if (clientList.insertClient(client)) {
			return (client);
		}
		return null;
	}
	/**
	 * method to pay a client
	 * @param id - id of the client to be paid
	 * @param payment - payment amount
	 * @return - not found, failed, or completed
	 */

	public int payClient(String id, Double payment) {
		Client client = clientList.search(id);
		if (client == null) {
			return (CLIENT_NOT_FOUND);
		}
		if (clientList.payClient(id, payment)) {
			return (OPERATION_COMPLETED);
		}
		return (OPERATION_FAILED);
	}
	/**
	 * I'm not sure what this is for?
	 * To check to see if a show has conflicting dates?
	 * @param client
	 * @return
	 */
	public boolean stillHasShows(Client client) {
		Client producer = client;
		if (showList.stillHasShows(producer.getId())) {
			return true;
		} else {
			return false;
		}
	}
	//I think this should belong in a different class..
	public boolean hasDuplicateCard(CreditCard possibleDup) {
		return creditCardList.isDuplicate(possibleDup);

	}

	/**
	 * Removes a specific book from the catalog
	 *
	 * @param clientId id of the book
	 * @return a code representing the outcome
	 */
	public int removeClient(String clientId) {
		Client client = clientList.search(clientId);
		if (client == null) {
			return (CLIENT_NOT_FOUND);
		}
		if (stillHasShows(client)) {
			return (CLIENT_STILL_HAS_SHOWS);
		}
		if (clientList.removeClient(clientId)) {
			return (OPERATION_COMPLETED);
		}
		return (OPERATION_FAILED);
	}

	/**
	 * Returns an iterator to the books issued to a member
	 *
	 * @param memberId member id
	 * @return iterator to the collection
	 */
	public Iterator<Client> getClients() {
		return clientList.getClients();
	}

	/**
	 * Returns an iterator to the books issued to a member
	 *
	 * @param memberId member id
	 * @return iterator to the collection
	 */
	public Iterator<Customer> getCustomers() {
		return customerList.getCustomers();
	}

	/**
	 * Returns an iterator to the books issued to a member
	 *
	 * @param memberId member id
	 * @return iterator to the collection
	 */
	public Iterator<Show> getShows() {
		return showList.getShows();
	}

	public void listTicketsforGivenDay(Date showDate) {
		ticketsSold.searchByDate(showDate);
	}

	/**
	 * Retrieves a deserialized version of the library from disk
	 *
	 * @return a Theatre object
	 */
	public static Theatre retrieve() {
		try {
			FileInputStream file = new FileInputStream(
					"TheatreData");
			input = new ObjectInputStream(file);
			input.readObject();
			// CreditCardList.retrieve(input);
			// ClientList.retrieve(input);
			// CustomerList.retrieve(input);
			// ShowList.retrieve(input);
			return theatre;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Organizes the operations for adding a member
	 *
	 * @param name    member name
	 * @param address member address
	 * @param phone   member phone
	 * @return the Member object created
	 */
	public CreditCard addCreditCard(String customerID, String cardNumber,
			String expDate, String type) {
		Customer cus = customerList.search(customerID);
		if (cus == null) {
			System.out.println("CUSTOMER_NOT_FOUND");
		} else {
			CreditCard card = new CreditCard(customerID, cardNumber, expDate,
					type);
			if (creditCardList.insertCreditCard(card)) {
				return (card);
			}
		}
		return null;
	}

	/**
	 * Organizes the operations for adding a client
	 *
	 * @param clientName    member name
	 * @param clientAddress member address
	 * @param clientPhone   member phone
	 * @param contactName   client contact name
	 * @return the Member object created
	 */
	public Customer addCustomer(String name, String address, String phoneNumber,
			String customerId) {
		Customer customer = new Customer(name, address, phoneNumber,
				customerId);
		if (customerList.insertCustoomer(customer)) {
			return (customer);
		}
		return null;
	}

	/**
	 * Organizes the operations for adding a client
	 *
	 * @param clientId
	 * @param clientName    member name
	 * @param clientAddress member address
	 * @param clientPhone   member phone
	 * @param contactName   client contact name
	 * @return the Member object created
	 */
	public Show addShow(String clientId, String showId, String clientName,
			String showName, Date newShowStartDate, Date newShowEndDate,
			Double price) {
		Client client = clientList.search(clientId);
		if (client == null) {
			System.out.println("NO_CLIENT_FOUND");
			return null;
		} else {
			Show show = new Show(clientId, showId, clientName, showName,
					newShowStartDate, newShowEndDate, price);
			if (showList.insertShow(show)) {
				System.out.println("Show was added...");
				return (show);
			}

		}
		return null;
	}

	/**
	 * Removes a specific book from the catalog
	 *
	 * @param clientId id of the book
	 * @return a code representing the outcome
	 */
	public int removeCustomer(String cusId) {
		Customer customer = customerList.search(cusId);
		if (customer == null) {
			return (CUSTOMER_NOT_FOUND);
		}
		if (customerList.removeCustomer(cusId)) {
			return (OPERATION_COMPLETED);
		}
		return (OPERATION_FAILED);
	}

	/**
	 * Removes a specific book from the catalog
	 *
	 * @param clientId id of the book
	 * @return a code representing the outcome
	 */
	public int removeCreditCard(String cardNo) {
		CreditCard card = creditCardList.search(cardNo);
		if (card == null) {
			return (CREDITCARD_NOT_FOUND);
		}
		if (creditCardList.removeCreditCard(cardNo)) {
			// creditCardList.cardsRemaining(cardNo);
			return (OPERATION_COMPLETED);
		}
		return (OPERATION_FAILED);
	}
	/**
	 * a method to sell tickets
	 * @param type - the type of ticket being sold
	 * @param customerID - the customer who is buying
	 * @param cardNo - the card number
	 * @param showDate - the date of the show
	 * @param quantity - the quantity
	 * @return
	 */

	public Ticket sellTickets(int type, String customerID, String cardNo,
			Date showDate, int quantity) {
		int errorCount = 0;
		Customer customer = customerList.search(customerID);
		CreditCard card = creditCardList.search(cardNo);
		Show show = showList.findShowByDate(showDate);
		if (customer == null) {
			System.out.println("Customer Not Found");
			errorCount++;
		}
		if (card == null) {
			System.out.println("Credit Card Not Found");
			errorCount++;
		}
		if (show == null) {
			System.out.println("There are no shows on that date");
			errorCount++;
		}
		if (errorCount != 0) {
			return null;
		} else {
			Ticket ticket = TicketFactory.instance().createTicket(type,
					quantity, customerID, showDate, show);
			this.increaseRevenue(ticket.getTotal());
			Client client = clientList.search(show.getClientID());
			client.increaseBalance(ticket.getTotal() / 2);
			if (ticketsSold.insertTicket(ticket)) {
				System.out.println("Ticket was inserted to list");
				return ticket;
			} else {
				return null;
			}
		}
	}

	/**
	 * Serializes the Library object
	 *
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream(
					"TheatreData");
			output = new ObjectOutputStream(file);
			output.writeObject(theatre);
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
	/**
	 * supports serialization
	 * @param output
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(theatre);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	/*
	 * Supports serialization
	 * 
	 * @param input the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			input.defaultReadObject();
			if (theatre == null) {
				theatre = (Theatre) input.readObject();
			} else {
				input.readObject();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * method to see the revenue of the theatre?
	 * @return
	 */
	public double getRevenue() {
		return revenue;
	}

	/**
	 * @param revenue the revenue to set
	 */
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	/**
	 * @param revenue the revenue to increase
	 */
	public void increaseRevenue(double revenue) {
		this.revenue += revenue;
	}
}

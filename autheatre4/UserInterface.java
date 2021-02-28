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
public class UserInterface {
	//reader object to suppost console interaction
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in));
	//options
	private static Theatre theatre;
	private static final int EXIT = 0;
	private static final int ADD_CLIENT = 1;
	private static final int REMOVE_CLIENT = 2;
	private static final int LIST_ALL_CLIENTS = 3;
	private static final int ADD_CUSTOMER = 4;
	private static final int REMOVE_CUSTOMER = 5;
	private static final int ADD_CREDIT_CARD = 6;
	private static final int REMOVE_CREDIT_CARD = 7;
	private static final int LIST_ALL_CUSTOMERS = 8;
	private static final int ADD_SHOW = 9;
	private static final int LIST_ALL_SHOWS = 10;
	private static final int SAVE = 11;
	private static final int RETRIEVE = 12;
	private static final int SELL_REGULAR_TICKETS = 13;
	private static final int SELL_ADVANCE_TICKETS = 14;
	private static final int SELL_STUDENT_ADVANCE_TICKETS = 15;
	private static final int PAY_CLIENT = 16;
	private static final int LIST_ALL_TICKETS_FOR_GIVEN_DATE = 17;
	private static final int HELP = 18;
	//private constructor for singleton
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			theatre = Theatre.instance();
		}
	}

	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 *
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 *
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 *
	 * @return a valid command
	 *
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(
						getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 *
	 */
	public void help() {
		System.out
				.println("Enter a number between 0 and 12 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_CLIENT + " to add a client");
		System.out.println(REMOVE_CLIENT + " to remove a client");
		System.out.println(LIST_ALL_CLIENTS + " to show a list of Clients");
		System.out.println(ADD_CUSTOMER + " to add a customer ");
		System.out.println(REMOVE_CUSTOMER + " to remove a customer ");
		System.out.println(ADD_CREDIT_CARD + " to add a credit card");
		System.out.println(REMOVE_CREDIT_CARD + " to remove a credit card");
		System.out.println(LIST_ALL_CUSTOMERS + " to list all customers");
		System.out.println(ADD_SHOW + " to add a show");
		System.out.println(LIST_ALL_SHOWS + " to show a list of shows");
		System.out.println(SAVE + " to  save data");
		System.out.println(RETRIEVE + " to  retrieve");
		System.out.println(SELL_REGULAR_TICKETS + " to  sell regular tickets");
		System.out.println(SELL_ADVANCE_TICKETS + " to  sell advance tickets");
		System.out.println(SELL_STUDENT_ADVANCE_TICKETS
				+ " to  sell student advance tickets");
		System.out.println(PAY_CLIENT + " to pay a client");
		System.out.println(LIST_ALL_TICKETS_FOR_GIVEN_DATE
				+ " to list all tickets for a given date");
		System.out.println(HELP + " for help");
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 *
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 *
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Converts the string to a number
	 *
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 *
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object
	 *
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat
						.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	/**
	 * Method to be called for saving the Theatre object. Uses the appropriate
	 * Theatre method for saving.
	 *
	 */
	private void save() {
		if (Theatre.save()) {
			System.out.println(
					" The Theatre has been successfully saved in the file TheatreData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}
	/**
	 * retrieve method for getting a saved theatre
	 */
	private void retrieve() {
		try {
			Theatre tempLibrary = Theatre.retrieve();
			if (tempLibrary != null) {
				System.out.println(
						" The Theatre has been successfully retrieved from the file TheatreData \n");
				theatre = tempLibrary;
			} else {
				System.out.println("File doesnt exist; creating new library");
				theatre = Theatre.instance();
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * method to add a client
	 */
	public void addClient() {
		String name = getToken("Enter the name of the new Production Co");
		String address = getToken("Enter address");
		String phone = getToken("Enter phone");
		String contact = getToken("Who's the contact person?");
		String id = UUID.randomUUID().toString();
		Client result;
		result = theatre.addClient(name, address, phone, contact, id);
		if (result == null) {
			System.out.println("Could not add this client");
		}
		System.out.println(result);
	}

	/**
	 * Method to be called for removing clients. Prompts the user for the
	 * appropriate values and uses the appropriate Theatre method for removing
	 * books.
	 *
	 */
	public void removeClient() {
		int result;
		do {
			String clientID = getToken("Enter client id to remove");
			result = theatre.removeClient(clientID);
			switch (result) {
			case Theatre.CLIENT_NOT_FOUND:
				System.out.println("No such client in our season");
				break;
			case Theatre.CLIENT_STILL_HAS_SHOWS:
				System.out.println(" This client still has shows");
				break;
			case Theatre.OPERATION_FAILED:
				System.out.println("Client could not be removed");
				break;
			case Theatre.OPERATION_COMPLETED:
				System.out.println(" Client has been removed");
				break;
			default:
				System.out.println("An error has occurred");
			}
			if (!yesOrNo("Remove another client?")) {
				break;
			}
		} while (true);
	}

	/**
	 * Method to be called for adding a customer. Prompts the user for the
	 * appropriate values and uses the appropriate Theatre method for adding the
	 * customer.
	 *
	 */
	public void addCustomer() {
		String name = getToken("Enter customer name");
		String address = getToken("Enter address");
		String phone = getToken("Enter phone");
		String id = UUID.randomUUID().toString();
		String cardNo = getToken("Enter credit card number for this customer");
		String expDate = getToken("Enter the expiration date");
		String type = getToken("What type of card is it?");
		CreditCard starter = new CreditCard(id, cardNo, expDate, type);

		if (theatre.hasDuplicateCard(starter)) {
			System.out.println(
					"Could not add Duplicate Card...Aborting operation");

		} else {
			Customer result;
			result = theatre.addCustomer(name, address, phone, id);

			if (result == null) {
				System.out.println("Could not add Customer");
			} else {
				theatre.addCreditCard(id, cardNo, expDate, type);
			}
			System.out.println(result.toString());
		}

	}
	/**
	 * removeCustomer method
	 * method for removing a customer from the theatre
	 */
	public void removeCustomer() {
		int result;
		do {
			String cusId = getToken("Enter customer id to be removed");
			result = theatre.removeCustomer(cusId);
			switch (result) {
			case Theatre.CUSTOMER_NOT_FOUND:
				System.out.println("No such customer in our system");
				break;
			case Theatre.OPERATION_FAILED:
				System.out.println("Customer could not be removed");
				break;
			case Theatre.OPERATION_COMPLETED:
				System.out.println("Customer has been removed");
				break;
			default:
				System.out.println("An error has occurred");
			}
			if (!yesOrNo("Remove another customer?")) {
				break;
			}
		} while (true);
	}

	/**
	 * Method to be called for adding a customer. Prompts the user for the
	 * appropriate values and uses the appropriate Theatre method for adding the
	 * credit card.
	 *
	 */
	public void addCreditCard() {
		String id = getToken(
				"Enter the customer ID to which we want to add a card");
		String cardNo = getToken("Enter credit card number for this customer");
		String expDate = getToken("Enter the expiration date");
		String type = getToken("What type of card is it?");
		
		CreditCard result;
		result = theatre.addCreditCard(id, cardNo, expDate, type);
		if (result == null) {
			System.out.println("Could not add credit card");
		} else {
			System.out.println(result);
		}
		// }

	}
	/**
	 * method to remove a credit card from a customer
	 * customer must have more than one credit card
	 * validation tested/working (highfive)
	 */
	public void removeCreditCard() {
		int result;
		do {
			String cardNo = getToken("Enter credit card number to be removed");
			result = theatre.removeCreditCard(cardNo);
			switch (result) {
			case Theatre.CREDITCARD_NOT_FOUND:
				System.out.println("No such credit card in our system");
				break;
			case Theatre.OPERATION_FAILED:
				System.out.println("Credit Card could not be removed");
				break;
			case Theatre.OPERATION_COMPLETED:
				System.out.println("Credit card has been removed");
				break;
			default:
				System.out.println("An error has occurred");
			}
			if (!yesOrNo("Remove another credit card?")) {
				break;
			}
		} while (true);
	}

	/*
	 * Method to list all customers
	 */
	public void listAllCustomers() {
		Customer x;
		Iterator ourCustomers = theatre.getCustomers();
		while (ourCustomers.hasNext()) {
			x = (Customer) (ourCustomers.next());
			System.out.println(x.toString());
		}
	}

	/*
	 * Method to list all shows
	 */
	public void listAllShows() {
		Show x;
		Iterator ourShows = theatre.getShows();
		while (ourShows.hasNext()) {
			x = (Show) (ourShows.next());
			System.out.println(x.toString());
		}
	}

	/*
	 * Method to list all clients
	 */
	public void listAllClients() {
		Client x;
		Iterator ourClients = theatre.getClients();
		while (ourClients.hasNext()) {
			x = (Client) (ourClients.next());
			System.out.println(x.toString());
		}
	}
	/**
	 * method to list all tickets for a given day
	 * @throws IOException
	 */
	public void listAllTicketsForAGivenDay() throws IOException {
		Date showDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("For what date would you like a list of tickets?");
		try {
			showDate = dateFormat.parse(input.readLine());
		} catch (ParseException e) {
			System.out.println("Not a viable date");
		}
		theatre.listTicketsforGivenDay(showDate);
	}

	/**
	 * Method to be called for adding shows. Prompts the user for the
	 * appropriate values and uses the appropriate Theatre method for adding
	 * shows.
	 *
	 */
	public void addShow() throws IOException {
		Date newShowStartDate = null;
		Date newShowEndDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String clientId = getToken(
				"Enter the clientID for the Show's producer");
		String clientName = getToken("Enter the name of the Show's producer");
		String showId = UUID.randomUUID().toString();
		String showName = getToken("What's the name of the show?");
		Double price = 00.00;
		System.out.println("How much does a Regular Ticket cost?");
		try {
			price = Double.parseDouble(input.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Not a viable price");
		}
		System.out.println(
				"What is the start date for the show you would like to add?");
		try {
			newShowStartDate = dateFormat.parse(input.readLine());
		} catch (ParseException e) {
			System.out.println("Not a viable date");
		}
		System.out.println("What is the end date for the show?");
		try {
			newShowEndDate = dateFormat.parse(input.readLine());
		} catch (ParseException e) {
			System.out.println("Not a viable date");
		}
		Show result;
		result = theatre.addShow(clientId, showId, clientName, showName,
				newShowStartDate, newShowEndDate, price);
		if (result == null) {
			System.out.println("Could not add show");
		}
		System.out.println(result);
	}
	/**
	 * Sell tickets method
	 * @param type - the type of ticket
	 * @throws IOException
	 */
	public void sellTickets(int type) throws IOException {
		Date showDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println(
				"What is the date of the show you wish to purchase tickets for?");
		try {
			showDate = dateFormat.parse(input.readLine());
		} catch (ParseException e) {
			System.out.println("Not a viable date");
		}

		int quantity = 0;
		System.out.println("How many tickets do you wish to purchase?");
		try {
			quantity = Integer.parseInt(input.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Not a valid quantity");
		}

		String customerID = getToken("Enter customer ID: ");
		String cardNo = getToken("Enter this customer's credit card number: ");

		Ticket result = theatre.sellTickets(type, customerID, cardNo, showDate,
				quantity);
		if (result == null) {
			System.out.println("Could not sell ticket(s)!");
		} else {
			System.out.println(result.toString());
		}

	}
	/**
	 * method to pay a client
	 * @throws IOException
	 */
	public void payClient() throws IOException {
		Double amountToPay = 0.0;
		int result;
		String clientId = getToken(
				"Enter the clientID for the client requesting payment");

		System.out.println("How much would you like to pay this client?");
		try {
			amountToPay = Double.parseDouble(input.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Not a viable amount");
		}
		result = theatre.payClient(clientId, amountToPay);
		if (result == Theatre.OPERATION_FAILED
				|| result == Theatre.CLIENT_NOT_FOUND) {
			System.out.println("Payment could not be made");
		} else {
			System.out.println("Payment successful");
		}
	}
	/**
	 * method to process?
	 * @throws IOException
	 */
	public void process() throws IOException {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_CLIENT:
				addClient();
				break;
			case REMOVE_CLIENT:
				removeClient();
				break;
			case LIST_ALL_CLIENTS:
				listAllClients();
				break;
			case ADD_CUSTOMER:
				addCustomer();
				break;
			case REMOVE_CUSTOMER:
				removeCustomer();
				break;
			case ADD_CREDIT_CARD:
				addCreditCard();
				break;
			case REMOVE_CREDIT_CARD:
				removeCreditCard();
				break;
			case LIST_ALL_CUSTOMERS:
				listAllCustomers();
				break;
			case ADD_SHOW:
				addShow();
				break;
			case LIST_ALL_SHOWS:
				listAllShows();
				break;
			case SAVE:
				save();
				break;
			case RETRIEVE:
				retrieve();
				break;
			case HELP:
				help();
				break;
			case SELL_REGULAR_TICKETS:
				sellTickets(Theatre.REGULAR_TICKET);
				break;
			case SELL_ADVANCE_TICKETS:
				sellTickets(Theatre.ADVANCE_TICKET);
				break;
			case SELL_STUDENT_ADVANCE_TICKETS:
				sellTickets(Theatre.STUDENT_ADVANCE_TICKET);
				break;
			case PAY_CLIENT:
				payClient();
				break;
			case LIST_ALL_TICKETS_FOR_GIVEN_DATE:
				listAllTicketsForAGivenDay();
				break;
			}
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 *
	 * @param args not used
	 */
	public static void main(String[] args)
			throws IOException {
		UserInterface.instance().process();
	}
}

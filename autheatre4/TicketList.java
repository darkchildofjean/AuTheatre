/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autheatre4;

import java.util.*;
import java.io.*;

/**
 *
 * @author enricorastelli
 * @author Jemal Abdullahi
 */
public class TicketList extends GenericList implements Serializable {
	//cereal sounds good
	private static final long serialVersionUID = 1L;
	//variables
	ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	private static TicketList ticketList;
	//private constructor for the singleton
	private TicketList() {

	}

	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static TicketList instance() {
		if (ticketList == null) {
			return (ticketList = new TicketList());
		} else {
			return ticketList;
		}
	}

	/**
	 * Inserts a Ticket into the collection
	 *
	 * @param Ticket ticket to be inserted
	 * @return true iff the ticket could be inserted. Currently always true
	 */
	public boolean insertTicket(Ticket ticket) {
		tickets.add(ticket);
		return true;
	}

	/**
	 * Removes a credit ticket from the list
	 *
	 * @param String ticketNo
	 * @return true iff credit ticket could be removed
	 */
	public boolean removeTicket(String serialNumber) {
		Ticket ticket = search(serialNumber);
		if (ticket == null) {
			return false;
		} else {
			return tickets.remove(ticket);
		}
	}

	/**
	 * Checks whether a credit ticket with a given ticket number exists.
	 *
	 * @param CardNo the number of the credit ticket
	 * @return the ticket iff the credit ticket exists else return null
	 *
	 */
	public Ticket search(String serialNumber) {
		for (Iterator<Ticket> iterator = tickets.iterator(); iterator
				.hasNext();) {
			Ticket ticket = (Ticket) iterator.next();
			if (ticket.getSerialNumber().equals(serialNumber)) {
				return ticket;
			}
		}
		return null;
	}
	/**
	 * search by showDate function
	 * @param showDate - the date of the show searcing for
	 *
	 */
	public void searchByDate(Date showDate) {
		int count = 0;
		int totalTicketsSold = 0;
		for (Ticket ticket : tickets) {
			if (ticket.getShowDate().equals(showDate)) {
				System.out.println(ticket.toString());
				count++;
				totalTicketsSold += ticket.getQuantity();
			}
		}
		if (count == 0)
			System.out.println("No tickets were sold for that date");
		else {
			System.out.println(totalTicketsSold + " tickets sold on "
					+ showDate.toString());
		}
	}
	/**
	 * an iterator
	 * @return - the iteration
	 */
	public Iterator<Ticket> getClients() {
		return tickets.iterator();
	}

	/*
	 * Supports serialization
	 * 
	 * @param output the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output)
			throws IOException {
		try {
			output.defaultWriteObject();
			output.writeObject(ticketList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/*
	 * Supports serialization
	 * 
	 * @param input the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (ticketList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (ticketList == null) {
					ticketList = (TicketList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			System.out.println("in TicketList readObject \n" + ioe);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

}

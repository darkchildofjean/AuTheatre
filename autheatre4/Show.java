/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autheatre4;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * class representing a show object
 * @author enricorastelli
 * refractored by cole I thought
 */

public class Show implements Serializable, Matchable<String> {
	//serialization stuff
	private static final long serialVersionUID = 1L;
	//fields for a show object
	private String showName;
	private String ShowID; // this is a nono you guys - <3
	private String clientID;
	private String clientName;
	private Date startDate;
	private Date endDate;
	private double price;
	/**
	 * A constructor method for the show object
	 * @param clientID - Id for the client who owns the show
	 * @param ShowID - the Id for the show.
	 * @param clientName - the name of the client
	 * @param showName - the name of the show
	 * @param startDate - the start date
	 * @param endDate - the end date
	 * @param price - the price
	 */

	public Show(String clientID, String showID, String clientName,
			String showName, Date startDate, Date endDate, double price) {
		this.setShowName(showName);
		this.setShowID(showID);
		this.setClientID(clientID);
		this.setClientName(clientName);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setPrice(price);
	}
	//setters and getters
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getShowID() {
		return ShowID;
	}

	public void setShowID(String ShowID) {
		this.ShowID = ShowID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	//end setters an getters
	
	//toString method.
	@Override
	public String toString() {
		return "Show{" + "showName=" + showName + ", ShowID=" + ShowID
				+ ", clientID=" + clientID + ", clientName=" + clientName
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", price=$" + price + '}';
	}
	/*
	 * Okay, I definitely did matches for show
	 * even if it wasn't needed.
	 * 
	 */
	@Override
	public boolean matches(String key) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}

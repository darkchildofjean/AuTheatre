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
/**
 * 
 * @author Refactored By Cole Burnham
 *
 */
public class ShowList extends GenericList<Show, String>
		implements Serializable {
	private static final long serialVersionUID = 1L;
	ArrayList<Show> shows = new ArrayList<Show>();
	private static ShowList showList;

	private ShowList() {

	}

	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static ShowList instance() {
		if (showList == null) {
			return (showList = new ShowList());
		} else {
			return showList;
		}
	}

	/**
	 * Checks whether a client with a given client id still has shows remaining.
	 *
	 * @param clientID the id of the client
	 * @return true iff the client still has shows
	 *
	 */

	public boolean stillHasShows(String clientID) {
		for (Iterator<Show> iterator = shows.iterator(); iterator.hasNext();) {
			Show show = (Show) iterator.next();
			Date today = Calendar.getInstance().getTime();
			if (show.getClientID() == clientID
					&& show.getEndDate().after(today)) {
				System.out.println("This client still has " + show.getShowName()
						+ " yet to perform");
			}
			return true;
		}
		return false;
	}

	/**
	 * Checks whether a show with a given show id exists.
	 *
	 * @param searchID the id of the show
	 * @return true iff the show exists
	 *
	 */
	public Show search(String searchID) {
		for (Iterator<Show> iterator = shows.iterator(); iterator.hasNext();) {
			Show show = (Show) iterator.next();
			if (show.getShowID().equals(searchID)) {
				return show;
			}
		}
		return null;
	}

	/**
	 * Inserts a show into the collection
	 *
	 * @param Show show to be inserted
	 * @return true iff the show could be inserted.
	 */
	public boolean insertShow(Show show) {
		boolean check = true;
		for (Show theShow : shows) {
			if (show.getStartDate().after(theShow.getStartDate())
					&& show.getStartDate().before(theShow.getEndDate())) {
				System.out.println(
						"This show conflicts with " + theShow.toString());
				check = false;
				return false;
			} else if (show.getEndDate().after(theShow.getStartDate())
					&& show.getEndDate().before(theShow.getEndDate())) {
				System.out.println(
						"This show conflicts with " + theShow.toString());
				check = false;
				return false;
			} else if (show.getStartDate().before(theShow.getStartDate())
					&& show.getEndDate().after(theShow.getEndDate())) {
				System.out.println(
						"This show conflicts with " + theShow.toString());
				check = false;
				return false;
			}
		}
		if (check == true) {
			shows.add(show);
		}

		return true;
	}

	// Returns the shows iterator
	public Iterator<Show> getShows() {
		return shows.iterator();
	}
	/**
	 * search function to search for a show by date
	 * @param showDate - the date of the show
	 * @return - the show or null
	 */

	public Show findShowByDate(Date showDate) {
		for (Show show : shows) {
			if (!(showDate.before(show.getStartDate())
					|| showDate.after(show.getEndDate()))) {
				return show;
			}
		}

		return null;
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
			output.writeObject(showList);
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
			if (showList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (showList == null) {
					showList = (ShowList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			System.out.println("in ShowList readObject \n" + ioe);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

}

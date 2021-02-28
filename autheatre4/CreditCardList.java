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
 *  Refactored by cole burnham, or maybe it wasn't?
 *  I thought I added that comment to all the classes
 *  
 */

public class CreditCardList extends GenericList<CreditCard, String>
		implements Serializable {
	// supports serialization
	private static final long serialVersionUID = 1L;
	// variables
	ArrayList<CreditCard> cards = new ArrayList<CreditCard>();
	private static CreditCardList creditCardList;

	// private constructor for singleton
	private CreditCardList() {

	}

	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static CreditCardList instance() {
		if (creditCardList == null) {
			return (creditCardList = new CreditCardList());
		} else {
			return creditCardList;
		}
	}

	/**
	 * Inserts a CreditCard into the collection
	 *
	 * @param CreditCard card to be inserted
	 * @return true iff the card could be inserted. Currently always true
	 */
	public boolean insertCreditCard(CreditCard card) {
		if (isDuplicate(card)) {
			System.out.println("Could not add Duplicate Card");
			return false;
		} else {
			cards.add(card);
			return true;
		}
	}

	/**
	 * Removes a credit card from the list
	 *
	 * @param String cardNo
	 * @return true iff credit card could be removed
	 */
	public boolean removeCreditCard(String cardNo) {
		int cardCount = 0;
		CreditCard card = search(cardNo);
		if (card == null) {
			return false;
		} else {
			for (Iterator<CreditCard> iterator = cards.iterator(); iterator
					.hasNext();) {
				CreditCard theCard = (CreditCard) iterator.next();
				ArrayList<CreditCard> customerCards = new ArrayList<CreditCard>();
				if (theCard.getCustId().equals(card.getCustId())) {
					customerCards.add(theCard);
					cardCount++;
				}
			}
			if (cardCount == 1) {
				System.out.println("Cannot remove only customer credit card!");
				return false;
			}
			System.out.println("Cards remaining for this customer are");
			for (Iterator<CreditCard> iterator = cards.iterator(); iterator
					.hasNext();) {
				CreditCard theCard2 = (CreditCard) iterator.next();
				if (theCard2.getCustId().equals(card.getCustId())
						&& !theCard2.equals(card))
					System.out.println(theCard2.toString());
			}
			return cards.remove(card);
		}
	}

	/**
	 * Checks whether a credit card with a given card number exists.
	 *
	 * @param CardNo the number of the credit card
	 * @return the card iff the credit card exists else return null
	 *
	 */
	public CreditCard search(String CardNo) {
		for (Iterator<CreditCard> iterator = cards.iterator(); iterator
				.hasNext();) {
			CreditCard card = (CreditCard) iterator.next();
			if (card.getCardNumber().equals(CardNo)) {
				return card;
			}
		}
		return null;
	}
	//An iterator
	public Iterator<CreditCard> getClients() {
		return cards.iterator();
	}

	/**
	 * Checks whether a credit card is a duplicate of an existing one.
	 *
	 * @param entry the new CreditCard to be checked
	 * @return true iff the card already exists
	 *
	 */
	public boolean isDuplicate(CreditCard entry) {
		boolean check = false;
		for (CreditCard card : cards) {
			if (card.equals(entry)) {
				check = true;
				System.out.println(card.getCardNumber());
				System.out.println("Duplicate");
				break;
			}
		}
		// System.out.println(check);
		return check;
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
			output.writeObject(creditCardList);
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
			if (creditCardList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (creditCardList == null) {
					creditCardList = (CreditCardList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			System.out.println("in CreditCardList readObject \n" + ioe);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

}

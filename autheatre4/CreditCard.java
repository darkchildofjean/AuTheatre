/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autheatre4;
import java.io.*;
/**
 *
 * @author enricorastelli
 * @author Jemal Abdullahi
 * 
 */
/**
 * 
 * @author Refactored By Cole Burnham
 *
 */
public class CreditCard implements Serializable, Matchable<String> {
    	//supports serialization
	private static final long serialVersionUID = 1L;
	//Fields for a credit card object
	private String custId;
	private String cardNumber;
	private String expDate;
	private String type;
	/**
	 * A constructor for creating a Credit Card (CC)
	 * @param custId - the customers ID which the CC is assigned
	 * @param cardNumber - number of the credit card
	 * @param expDate - expirary date of the CC
	 * @param type - card type
	 */
	public CreditCard(String custId, String cardNumber, String expDate, String type) {
		this.setCustId(custId);
		this.setCardNumber(cardNumber);
		this.setExpDate(expDate);
		this.setType(type);
	}
	/**
	 * Begin setters and getters
	 * @return - respective field of sets/gets
	 */
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	/**
	 * End sets/gets!
	 */
/*
	@Override
	public int hashCode() {
		int hash = 7;
		return hash;
	}
*/
	/**
	 * Overidden equals object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CreditCard other = (CreditCard) obj;
		if (this.cardNumber.equals(other.cardNumber)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "CreditCard{" + "custId=" + custId + ", cardNumber=" 
		+ cardNumber + ", expDate=" + expDate + ", type=" + type + '}';
	}
	/**
	 * Overidden matches object. Don't think we need this
	 * Bring it up with Derrick/Jemal.
	 */
	@Override
	public boolean matches(String key) {
		// TODO Auto-generated method stub
		return false;
	}



}

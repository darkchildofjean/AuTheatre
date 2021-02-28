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
 *
 */
/**
 * 
 * @author REFACTORED by Cole Burnham
 *
 */
public class Client implements Serializable, Matchable<String> {
    // for serialization
    private static final long serialVersionUID = 1L;
    // fields for a Client object
    private String clientName;
    private String clientAddress;
    private String clientPhone;
    private String contactName;
    private String id;
    private Double balanceDue;

    /**
     * A constructor method for the client object
     * 
     * @param clientName    - name of the client
     * @param clientAddress - address of the client
     * @param clientPhone   - phone number of the client
     * @param contactName   - contact name of the client (Non atomic!) Maybe we
     *                      should have made this an atomic field? at this point it
     *                      seems like more work than needed.
     * @param id            - unique ID of the client
     * @param balanceDue    - balance due from the client
     */
    public Client(String clientName, String clientAddress, String clientPhone, String contactName, String id,
	    Double balanceDue) {
	this.setClientName(clientName);
	this.setClientAddress(clientAddress);
	this.setClientPhone(clientPhone);
	this.setContactName(contactName);
	this.setId(id);
	this.setBalanceDue(balanceDue);
    }
    /*
     * BEGIN SETTERS AND GETTERs
     */

    public Double getBalanceDue() {
	return balanceDue;
    }

    public void increaseBalance(Double revenue) {
	this.balanceDue += revenue;
    }

    public void setBalanceDue(Double balanceDue) {
	this.balanceDue = balanceDue;
    }

    public String getClientName() {
	return clientName;
    }

    public void setClientName(String clientName) {
	this.clientName = clientName;
    }

    public String getClientAddress() {
	return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
	this.clientAddress = clientAddress;
    }

    public String getClientPhone() {
	return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
	this.clientPhone = clientPhone;
    }

    public String getContactName() {
	return contactName;
    }

    public void setContactName(String contactName) {
	this.contactName = contactName;
    }

    public String getId() {
	return id;

    }

    public void setId(String id) {
	this.id = id;
    }

    // END SETTERS AND GETTERS
    /**
     * Equals overide - generic function used with matches
     */
    public boolean equals(Object object) {
	Client client = (Client) object;
	return this.matches(client.getId());
    }

    // the matches function
    @Override
    public boolean matches(String key) {

	return id.equals(key);
    }

    // To string function, formatted under 80 chars long per standards
    @Override
    public String toString() {
	return "Client{" + "clientName=" + clientName + ", clientAddress=" 
         + clientAddress + ", clientPhone="+ clientPhone + ", contactName=" 
	+ contactName + ", id=" + id + ", balanceDue=$" + balanceDue + '}';
    }

}

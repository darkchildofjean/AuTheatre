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
 */
/**
 * 
 * @author Refactored By Cole Burnham
 *
 */
public class Customer implements Serializable, Matchable<String> {
	//for serialization version
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String phoneNumber;
    private String customerId;

    public Customer(String name, String address, String phoneNumber, String customerId) {
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.setCustomerId(customerId);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerId() {
        return customerId;
    }
    /**
     * overidden equals method for the customer class
     */
	@Override
	public boolean equals(Object object) {
		Customer customer = (Customer) object;
		return this.matches(customer.getCustomerId());
	}

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", customerId=" + customerId + '}';
    }

	@Override
	public boolean matches(String key) {
		
		return customerId.equals(key);
	}
    
    
    
    
}

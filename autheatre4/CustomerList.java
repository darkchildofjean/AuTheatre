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
public class CustomerList extends GenericList<Customer, String> implements Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<Customer> theCustomers = new ArrayList<Customer>();
    private static CustomerList customerList;

    private CustomerList() {

    }

    /**
     * Supports the singleton pattern
     *
     * @return the singleton object
     */
    public static CustomerList instance() {
        if (customerList == null) {
            return (customerList = new CustomerList());
        } else {
            return customerList;
        }
    }

    /**
     * Inserts a customer into the collection
     *
     * @param Customer customer to be inserted
     * @return true iff the customer could be inserted. Currently always true
     */
    public boolean insertCustoomer(Customer customer) {
        theCustomers.add(customer);
        return true;
    }

    /**
     * Removes a customer from the List
     *
     * @param String cusId
     * @return true iff customer could be removed
     */
    public boolean removeCustomer(String cusId) {
        Customer customer = search(cusId);
        if (customer == null) {
            return false;
        } else {
            return theCustomers.remove(customer);
        }
    }

    /**
     * Checks whether a customer with a given cusId exists.
     *
     * @param searchID the id of the customer
     * @return true iff the customer exists
     *
     */
    public Customer search(String searchID) {
        for (Iterator<Customer> iterator = theCustomers.iterator(); iterator.hasNext();) {
            Customer customer = (Customer) iterator.next();
            if (customer.getCustomerId().equals(searchID)) {
                return customer;
            }
        }
        return null;
    }

    public Iterator<Customer> getCustomers() {
        return theCustomers.iterator();
    }


    /*
   * Supports serialization
   * @param output the stream to be written to
     */
    private void writeObject(java.io.ObjectOutputStream output) throws IOException {
        try {
            output.defaultWriteObject();
            output.writeObject(customerList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /*
   * Supports serialization
   * @param input the stream to be read from
     */
     private void readObject(java.io.ObjectInputStream input) {
        try {
            if (customerList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (customerList == null) {
                    customerList = (CustomerList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch (IOException ioe) {
            System.out.println("in CustomerList readObject \n" + ioe);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

}

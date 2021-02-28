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
 * 
 */
/**
 * 
 * @author Refactored By Cole Burnham
 *
 */
public class ClientList extends GenericList<Client, String> implements Serializable {
    //for serialization
    private static final long serialVersionUID = 1L;
    //Variables - I personally would have used linked list - <3 Cole
    ArrayList<Client> theProducers = new ArrayList<Client>();
    private static ClientList clientList;
    
    /**
     * Private constructor for the singleton
     */
    private ClientList() {
        
    }
    
      /**
   * Supports the singleton pattern
   * 
   * @return the singleton object
   */
  public static ClientList instance() {
    if (clientList == null) {
      return (clientList = new ClientList());
    } else {
      return clientList;
    }
  }
   /**
   * Inserts a client into the collection
   * @param Client client to be inserted
   * @return true iff the client could be inserted. Currently always true
   */
    public boolean insertClient(Client client) {
    theProducers.add(client);
    return true;
  }
    
    /**
   * Removes a book from the catalog
   * @param String clientId
   * @return true iff client could be removed
   */
  public boolean removeClient(String clientId) {
    Client client = search(clientId);
    if (client == null) {
      return false;
    } else {
      return theProducers.remove(client);
    }
  }
     /**
   * Checks whether a client with a given client id exists.
   * @param searchID the id of the client
   * @return true iff the book exists
   * 
   */
  public Client search(String searchID) {
    for (Iterator<Client> iterator = theProducers.iterator(); iterator.hasNext(); ) {
      Client client = (Client) iterator.next();
      if (client.getId().equals(searchID)) {
        return client;
      }
    }
    return null;
  }
  /**
   * An iterator
   * @return the iteration of the collection
   */
  public Iterator<Client> getClients() {
    return theProducers.iterator();
  }
  /**
   * method to pay the client
   * @param clientId - id of the client to be paid
   * @param payment - the amount to be paid
   * @return
   */

    public boolean payClient(String clientId, Double payment) {
    Double newBalance;  
    Client client = search(clientId);
    if (client == null) {
      return false;
    } else if(client != null && client.getBalanceDue() < payment){
        System.out.println("We don't owe this client that much!");
        return false;
    } else
        newBalance = client.getBalanceDue() - payment; 
        client.setBalanceDue(newBalance);
        System.out.println(client.getClientName() + " has been paid " 
        + payment.toString() + "\n" + "New balance for  " + client.getClientName()
        //tried to do above line to 80 chars but 83 looked prettier
        + "= "+ newBalance.toString());
      return true;
    
  }
  /*
   * Supports serialization
   * @param output the stream to be written to
   */
  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(clientList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  /*
   * Supports serialization
   * @param input the stream to be read from
   */
      private void readObject(java.io.ObjectInputStream input) {
        try {
            if (clientList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (clientList == null) {
                    clientList = (ClientList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch (IOException ioe) {
            System.out.println("in CLientList readObject \n" + ioe);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
}
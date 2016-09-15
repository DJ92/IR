/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice_dj;

/**
 *
 * @author DJ
 */
import java.io.*;
import java.util.Scanner;

public class AddressBook {
    public static class Contact {

    private String name; // Stores name of Contact
    private int age;  // Stores age of Contact
    private long phonenumber; // Stores phone number of contact
    private int bday;  // Stores birthday in an int

    // Creates Contact object based on parameters.
    public Contact(String n, int a, long p, int month, int day) {
		name = n;
		age = a;
		phonenumber = p;
		bday = 100*month + day;
    }

    // Changes phone number of contact
    public void changeNumber(long newnum) {
		phonenumber = newnum;
    }

    // Implements the passing of Contact's birthday.
    public void Birthday() {
		age++;
    }

    // Returns the name of a Contact
    public String getName() {
		return name;
    }

    // Returns the age of a Contact
    public int getAge() {
		return age;
    }

    // Returns the phone number of a Contact
    public long getNumber() {
		return phonenumber;
    }

    // Prints all information about a contact out.
    public void printContact() {
		System.out.print("Name: " + name + " Age: " + age + " Phone#: " + phonenumber);
		System.out.println(" Birthday: " + getBdayMonth() + "/" + getBdayDay());
    }

    // Returns month of Contact's birthday
    public int getBdayMonth() {
		return bday/100;
    }
    
    // Returns day of the month of Contact's birthday
    public int getBdayDay() {
		return bday%100;
    }

    }
    public Contact[] friends; // An array of Contacts - each stores info for one friend
    public int numfriends; // Number of friends currently in AddressBook

    // Create an empty AddressBook
    public AddressBook() {
		friends = new Contact[10];
		numfriends = 0;
    }

    // Add a contact that's passed in as a parameter.
    public void addContact(Contact c) {
		friends[numfriends] = c;
		numfriends++;
    }

    // Print out info on all contacts using method Contact class.
    public void printContacts() {
		for (int i=0;i<numfriends;i++)
	    	friends[i].printContact();
    }

    // Returns the number of friends currently in AddressBook
    public int numContacts() {
		return numfriends;
    }

    // Returns a non-neg integer if a Contact with name s exists corresponding
    // to which place in the array friends the Contact is stored. Returns -1
    // otherwise.
    private int haveContact(String s) {
	
		for (int i=0;i<numfriends;i++)
	    	if (friends[i].getName().equals(s))
				return i;
		return -1;
    }

    // Deletes a contact with name s, if one is in the AddressBook.
    public void deleteContact(String s) {
	
		int place = haveContact(s);
		if (place >= 0) {
	    	friends[place] = friends[numfriends-1];
	    	numfriends--;
		}
    }

    public static void main(String[] args) throws IOException {

	
		Scanner stdin = new Scanner(System.in);
	
		// Instantiate AddressBook object
		AddressBook blackbook = new AddressBook();

 		// Menu driven loop.
		menu();
		int choice = stdin.nextInt();
	
	
		while (choice!=5) {
	    
	    	// Only adds contact if there is room in AddressBook blackbook.
	    	if (choice == 1) {
			
				if (blackbook.numContacts() < 10) {
		    
		    		//Reads in all appropriate information.");
		    		System.out.println("Enter your friend\'s name:");
		    		String name = stdin.next();
		    		System.out.println("Enter their age.");
		    		int age = stdin.nextInt();
		    		System.out.println("Enter their phone number.");
		    		long number = stdin.nextLong();
		    		System.out.println("Enter the birthday, month on one line, then day on the next.");
		    		int mon = stdin.nextInt();
		    		int day = stdin.nextInt();

		    		// Uses information to create Contact object, which is
		    		// a parameter to the addContact method.
		    		blackbook.addContact(new Contact(name,age,number,mon,day));
				}
				else
		    		System.out.println("Sorry, can not add anyone, your blackbook is full.");
	    	}
	    	
	    	// Implements rest of the choices by calling appropriate AddressBook methods on blackbook.
	    	else if (choice == 2) {
				System.out.println("What is the name of the contact you want to delete?");
				String name = stdin.next();
				blackbook.deleteContact(name);
	    	}
	    	else if (choice == 3) {
				System.out.println("You have " + blackbook.numContacts() + " contacts.");
	    	}
	    	else if (choice == 4) {
				blackbook.printContacts();
	    	}
	    	else if (choice !=5) {
				System.out.println("Sorry, that was an invalid menu choice, try again.");
	    	}
	    
	    	menu();
	    	choice = stdin.nextInt();
		}
	
    }

    public static void menu() {
		System.out.println("1.Add a new contact to your address book.");
		System.out.println("2.Delete a contact from your address book.");
		System.out.println("3.Print out the number of contacts you have.");
		System.out.println("4.Print out information of all of your contacts.");
		System.out.println("5.Quit.");
		System.out.println("Enter your menu choice:");
    }
}

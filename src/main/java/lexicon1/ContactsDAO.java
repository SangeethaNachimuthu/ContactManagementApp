package lexicon1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
This class hold all the core logics for the application.
 */
public class ContactsDAO {

    Scanner scan = new Scanner(System.in);
    ArrayList<String> contactList = new ArrayList<>();

    /*
    This method handles adding contact information and
    notifies the user if a duplicate entry exists.
     */
    public void addContacts() {
        Contacts contact = new Contacts();

        System.out.print("Enter Name: ");
        contact.setName(scan.next());

        System.out.print("Enter Mobile Number: ");
        contact.setMobileNo(scan.next());

        boolean exists = false;
        for (String entry : contactList) {
            String[] splitting = entry.split("\\|");
            String existingMobileNo = splitting[1];

            if (existingMobileNo.equals(contact.getMobileNo())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.print("Contact already exists.\n");
        } else {
            contactList.add(contact.getName() + "|" + contact.getMobileNo());
            System.out.print("Contact added successfully.\n");
        }

       /* for (String str : contactList) {
            System.out.println(str);
        }*/
    }

    /*
    This method used to search the contact either using name or mobile number.
     */
    public void searchContact() {

        if (!contactList.isEmpty()) {
            System.out.println("a. Search by Name");
            System.out.println("b. Search by Mobile Number");
            String searchOption = scan.next();
            if (searchOption.equalsIgnoreCase("a"))
                searchByName();
            else if (searchOption.equalsIgnoreCase("b")) {
                searchByMobileNumber();
            } else {
                System.out.println("Invalid option, try again.");
            }
        } else {
            System.out.println("No contacts!");
        }
    }

    /*
   This method search the contact using name and
   displays all the contacts matches with the name.
    */
    public void searchByName() {

        System.out.print("Enter the Name: ");
        String searchName = scan.next();

        boolean found = false;
        for (String entry : contactList) {
            String[] splitting = entry.split("\\|");
            String names = splitting[0];
            if (names.equalsIgnoreCase(searchName)) {
                System.out.println(entry);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Contact not found");
        }
    }

    /*
    This method search the contact using mobile number and
    displays all the contacts matches with the name.
     */
    public void searchByMobileNumber() {

        System.out.print("Enter the Mobile Number: ");
        String searchMobileNo = scan.next();

        boolean found = false;
        for (String entry : contactList) {
            String[] splitting = entry.split("\\|");
            String mobileNo = splitting[1];
            if (mobileNo.equals(searchMobileNo)) {
                System.out.println(entry);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Contact not found");
        }
    }

    /*
    This method displays all the contacts and
    display message if there is no contacts.
    */
    public void displayAllContacts() {

        int index = 0;
        if (contactList.isEmpty()) {
            System.out.println("No contacts to display.");
        } else {
            for (String entry : contactList) {
                String[] splitting = entry.split("\\|");
                String names = splitting[0];
                String mobileNo = splitting[1];

                System.out.println( (index+1) + ". " + names + " (" + mobileNo+ ")" );
                index++;
            }
        }

    }

    /*
    This method sort the contacts by name and display them and
    display message if there is no contacts.
    */
    public void sortAllContacts() {
        int index = 0;
        if (contactList.isEmpty()) {
            System.out.println("No contact available.");
        } else {
            Collections.sort(contactList);
            for (String entry : contactList) {
                String[] splitting = entry.split("\\|");
                String names = splitting[0];
                String mobileNo = splitting[1];

                System.out.println( (index+1) + ". " + names + " (" + mobileNo+ ")" );
                index++;
            }
        }
    }

    /*
    This method used to delete the contact as per user's wish.
     */
    public void deleteContact() {

        System.out.println("Enter the Name to delete: ");
        String deleteContact = scan.next();

        boolean found = false;
        ArrayList<String> matchedContacts = new ArrayList<>();
        for (String entry : contactList) {
            String[] splitting = entry.split("\\|");
            String names = splitting[0];
            if (names.equalsIgnoreCase(deleteContact)) {
                matchedContacts.add(entry);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Contact not found");
        }

        System.out.println("Matching contacts: ");
        for (int i = 0; i < matchedContacts.size(); i++) {
            System.out.println((i + 1) + ". " + matchedContacts.get(i));
        }

        System.out.print("Enter index to delete the contact: ");
        int choice = scan.nextInt();

        if (choice <= 0 || choice > matchedContacts.size()) {
            System.out.println("Invalid selection.");
        } else {
            String indexToDelete = matchedContacts.get(choice - 1);
            System.out.print("Are you sure to delete the contact? (Yes/No): ");
            String deleteForSure = scan.next();
            if (deleteForSure.equalsIgnoreCase("Yes")) {
                contactList.remove(indexToDelete);
                System.out.println("Contact deleted successfully!");
            } else {
                System.out.println("Deletion cancelled.");
            }
        }
        System.out.println();
    }
}

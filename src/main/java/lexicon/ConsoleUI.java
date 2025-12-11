package lexicon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/*
This class contains the core concepts of the ContactManagementApp.
Displays the options available in the app and performs the
corresponding operation selected by the user and displays
necessary information.
 */
public class ConsoleUI {

    Scanner scan = new Scanner(System.in);
    ArrayList<ContactDAO> contacts = new ArrayList<>();

    /*
    This method runs first, based on the user option,
    it will perform the corresponding action.
     */
    public void start() {

        boolean runApp = true;

        while (runApp) {
            displayMainMenu();
            System.out.print("Choose an option: ");
            String option = scan.next();

            switch (option) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    displayAllContacts();
                    break;
                case "4":
                    deleteContacts();
                    break;
                case "0":
                    System.out.println("Thanks for using the App. Bye.");
                    runApp = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }

        }

    }

    /*
    This method displays the main menu of the App.
     */
    public void displayMainMenu() {
        System.out.println("=== Contact Management ===");
        System.out.println();
        System.out.println("1. Add Contact");
        System.out.println("2. Search by Name");
        System.out.println("3. Display All Contacts");
        System.out.println("4. Delete Contacts");
        System.out.println("0. Exit");
        System.out.println();
    }

    /*
    This method handles adding contact information and
    notifies the user if a duplicate entry exists.
     */
    public void addContact() {

        ContactDAO contactDAO = new ContactDAO();

        System.out.print("Enter the Name: ");
        contactDAO.setName(scan.next());

        System.out.print("Enter Mobile Number: ");
        contactDAO.setMobileNo(scan.next());

        contactDAO.setCombineNameMobileNo(contactDAO.getName() +"|"+ contactDAO.getMobileNo());

        //Checking for duplicate entries
        boolean exists = false;
        for (ContactDAO c : contacts) {
            if (c.getMobileNo().equals(contactDAO.getMobileNo())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Contact already exists.\n");
        } else {
            contacts.add(contactDAO);
            System.out.println("Contact added successfully!\n");
        }

    }

    /*
    This method used to search the contact either using name or mobile number.
     */
    void searchContact() {
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
    }

    /*
    This method search the contact using name and
    displays all the contacts matches with the name.
     */
    public void searchByName() {
        System.out.println("Enter the Name: ");
        String searchName = scan.next();
        boolean found = false;
        if (!contacts.isEmpty()) {
            for (ContactDAO dao : contacts) {
                if (dao.getName().equalsIgnoreCase(searchName)) {
                    System.out.println("Name: " + dao.getName() +
                            ", Mobile: " + dao.getMobileNo());
                    found = true;
                }
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
        System.out.println("Enter Mobile Number:");
        String searchMobNo = scan.next();
        boolean found = false;
        if (!contacts.isEmpty()) {
            for (ContactDAO dao : contacts) {
                if (dao.getMobileNo().equals(searchMobNo)) {
                    System.out.println("Name: " + dao.getName() +
                            ", Mobile: " + dao.getMobileNo());
                    found = true;
                    break;
                }
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
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
        }
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println( (i+1) + ". " + contacts.get(i).getName()
                    + " (" + contacts.get(i).getMobileNo() + ")" );
        }

        //Sorted contacts alphabetically by Name.
        //Collections.sort(contacts, Comparator.comparing(ContactDAO::getName));
        contacts.sort(Comparator.comparing(ContactDAO::getName));
        System.out.println("Sorted by Name: ");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println( (i+1) + ". " + contacts.get(i).getName()
                    + " (" + contacts.get(i).getMobileNo() + ")" );
        }
    }

    /*
    This method used to delete the contact as per user's wish.
     */
    public void deleteContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to delete.");
        }
        System.out.print("Enter the Name to delete: ");
        String deleteName = scan.next();
        ArrayList<ContactDAO> matchedList = new ArrayList<>();

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(deleteName)) {
                matchedList.add(contacts.get(i));
            }
        }

        if (matchedList.isEmpty()) {
            System.out.println("No contacts found.");
        }

        for (int i = 0; i < matchedList.size(); i++) {
            System.out.println((i + 1) + ". " + matchedList.get(i).getName()
                    + " (" + matchedList.get(i).getMobileNo() + ")");
        }

        System.out.print("Enter index to delete the contact: ");
        int choice = scan.nextInt();

        if (choice <= 0 || choice > matchedList.size()) {
            System.out.println("Invalid selection.");
        } else {

            ContactDAO indexToDelete = matchedList.get(choice - 1);

            System.out.print("Are you sure to delete the contact? (Yes/No): ");
            String deleteForSure = scan.next();
            if (deleteForSure.equalsIgnoreCase("Yes")) {
                contacts.remove(indexToDelete);
                System.out.println("Contact deleted successfully!");
            } else {
                System.out.println("Deletion cancelled.");
            }
        }
    }
}

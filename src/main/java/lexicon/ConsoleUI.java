package lexicon;

import java.util.ArrayList;
import java.util.Scanner;

/*
This class contains the core concepts of the ContactManagementApp.
Displays the options available in the app and performs the
corresponding operation selected by the user and displays
necessary information.
 */
public class ConsoleUI {

    Scanner scan = new Scanner(System.in);
    ContactDAO contactDAO = new ContactDAO();
    ArrayList<String> contacts = new ArrayList<>();

    public void start() {

        boolean runApp = true;

        while (runApp) {
            displayMainMenu();
            System.out.println("Choose an option: ");
            String option = scan.next();

            switch (option) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchByName();
                    break;
                case "3":
                    displayAllContacts();
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
        System.out.println("1. Add Contact");
        System.out.println("2. Search by Name");
        System.out.println("3. Display All Contacts");
        System.out.println("0. Exit");
    }

    /*
    This method handles adding contact information and
    notifies the user if a duplicate entry exists.
     */
    public void addContact() {

        System.out.println("Enter the Name: ");
        contactDAO.setName(scan.next());

        System.out.println("Enter Mobile Number: ");
        contactDAO.setMobileNo(scan.next());

        contactDAO.setCombineNameMobileNo(contactDAO.getName() +"|"+
                contactDAO.getMobileNo());

        //Rejecting duplicate entries
        if (contacts.contains(contactDAO.getCombineNameMobileNo())) {
            System.out.println("Contact already exists.");
        } else {
            contacts.add(contactDAO.getCombineNameMobileNo());
            System.out.println("Contact added successfully!");
        }

        //System.out.println(contacts);

    }

    public void searchByName() {

    }

    public void displayAllContacts() {
        System.out.println(contacts);
    }
}

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
    ArrayList<ContactDAO> contacts = new ArrayList<>();

    /*
    This method runs first, based on the user option,
    it will perform the corresponding action.
     */
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
                    searchContact();
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

        ContactDAO contactDAO = new ContactDAO();

        System.out.println("Enter the Name: ");
        contactDAO.setName(scan.next());

        System.out.println("Enter Mobile Number: ");
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
            System.out.println("Contact already exists.");
        } else {
            contacts.add(contactDAO);
            System.out.println("Contact added successfully!");
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
        for (ContactDAO dao : contacts) {
            if(dao.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Name: " + dao.getName() +
                        ", Mobile: " + dao.getMobileNo());
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
        System.out.println("Enter Mobile Number:");
        String searchMobNo = scan.next();
        boolean found = false;
        for (ContactDAO dao : contacts) {
            if (dao.getMobileNo().equals(searchMobNo)) {
                System.out.println("Name: " + dao.getName() +
                        ", Mobile: " + dao.getMobileNo());
                found = true;
                break;
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
    }
}

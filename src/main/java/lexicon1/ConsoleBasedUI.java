package lexicon1;

import java.util.Scanner;

/*
This class displays the options available in the application
and performs the corresponding operation selected by the user
and displays necessary information.
 */
public class ConsoleBasedUI {

    Scanner scan = new Scanner(System.in);
    ContactsDAO contactsDAO = new ContactsDAO();

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
                    contactsDAO.addContacts();
                    break;
                case "2":
                    contactsDAO.searchContact();
                    break;
                case "3":
                    contactsDAO.displayAllContacts();
                    break;
                case "4":
                    contactsDAO.sortAllContacts();
                    break;
                case "0":
                    runApp = false;
                    System.out.print("Thanks for using the App. Bye.");
                    break;
                default:
                    System.out.print("Invalid option. Try again.");
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
        System.out.println("2. Search Contact");
        System.out.println("3. Display All Contacts");
        System.out.println("4. Sort All Contacts");
        System.out.println("5. Delete Contacts");
        System.out.println("0. Exit");
        System.out.println();
    }
}

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
import java.io.Console;
/**
 * this is the MAIN CLASS
 */
public class Main {
    public static void main(String[] args) {
        while(true){
            Scanner objScanner = new Scanner(System.in);
            System.out.println("Press 1 to login as a client");
            System.out.println("Press 2 to login as a admin");
            System.out.println("Press 3 to register");
            System.out.println("Press 4 to display the catalog");
            System.out.println("Press 5 to exit the program");
            String choice = objScanner.nextLine();
            if (choice.trim().equals("1")){
                db daDb = new db();
                System.out.println("Enter the E-mail");
                String mail = objScanner.nextLine();
                Console console = System.console();
                char[] passwordArray = console.readPassword("Enter your password: ");
                String password = new String(passwordArray);
                registerd reg = daDb.clientLogin(mail, password);
                if(reg.getName()!=null)
                    reg.registerdMenu();
            }
            else if(choice.trim().equals("2")){
                db daDb = new db();
                System.out.println("Enter the E-mail");
                String mail = objScanner.nextLine();
                Console console = System.console();
                char[] passwordArray = console.readPassword("Enter your password: ");
                String password = new String(passwordArray);
                Boolean adminCheck = daDb.adminLogin(mail, password);
                if(adminCheck){
                    admincatalog admnCat = new admincatalog();
                    Admin admin = new Admin(admnCat);
                    admin.adminMenu();
                }
            }
            else if(choice.trim().equals("3")){
                while(true){
                    register reg = new register();
                    System.out.println("Enter the E-mail");
                    String mail = objScanner.nextLine();
                    System.out.println("Enter the user name");
                    String userName = objScanner.nextLine();
                    System.out.println("Enter the phone number");
                    String phoneNumber = objScanner.nextLine();
                    Console console = System.console();
                    char[] passwordArray = console.readPassword("Enter your password: ");
                    String password = new String(passwordArray);
                    System.out.println("Enter the address");
                    String address = objScanner.nextLine();
                    if(reg.check(mail, userName, password, address, phoneNumber)){
                        break;
                    }
                }
            }

            else if(choice.trim().equals("4")){
                Catalog catalog = new Catalog();
                catalog.displayItems();
            }
            else if(choice.trim().equals("5")){
                break;
            }
            else
                System.out.println("Invalid choice");
        }
    }
}
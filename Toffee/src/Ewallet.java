import java.io.Console;
import java.util.Scanner;

/**
 * this class for Ewallet
 */
public class Ewallet extends Payment {
    protected String username;
    protected String password;
    protected double balance;

    /**
     * this constrctour to set the method of payment
     */
    public Ewallet(){
        set_method();
    }
    Scanner scan = new Scanner(System.in);

    /**
     * this function to confirm the payment
     */
    void pay()
    {
        int choice;
        System.out.println("Are you sure to confirm pay method? (1)Y or (2)N");
        choice = scan.nextInt();
        if(choice == 1)
            System.out.println("Payment successfully");
        else
            System.out.println("Payment cancelled");
        return;
    }

    /**
     * this function to login to get the balance
     */
    void login()
    {
        System.out.println("Enter your username; ");
        this.username = scan.nextLine();
        Console console = System.console();
        char[] passwordArray = console.readPassword("Enter your password: ");
        String password = new String(passwordArray);
        System.out.println("Login complete");
        System.out.println("Your balance:" + this.balance);
    }

    /**
     * this function to set balance
     * @param b
     */
    void set_balance(double b)
    {
        this.balance = b;
    }

    /**
     * this function to get the balance
     * @return balance
     */
    double get_balance()
    {
        return balance;
    }

    /**
     * this function to set method to Ewallet
     */
    void set_method()
    {
        this.method = "E-wallet";
    }
}

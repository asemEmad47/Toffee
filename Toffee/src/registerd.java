import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class for registerd user
 */
public class registerd{
    private String Email;
    private String UserName;
    private String Password;
    private String address;
    private String Status;
    private ArrayList<Order> order = new ArrayList<>();
    private int LoyalityPoints;
    private String phone;
    private int ID;
    ArrayList <Voucher> vouchers = new ArrayList<>();
    Cart cart = new Cart();
//---------------------------------------------------------

    /**
     * this constructor to register user
     * @param mail
     * @param name
     * @param pass
     * @param add
     * @param phone
     */
    public registerd(String mail,String name,String pass , String add, String phone ){
        this.Email = mail;
        this.Password = pass;
        this.UserName = name;
        this.address = add;
        this.phone = phone;
        unSuspend();
    }
//---------------------------------------------------------

    /**
     * this function to get list of vouchers
     * @return vouchers
     */
    public  ArrayList <Voucher> getVouchers(){
        return vouchers;
    }
//---------------------------------------------------------

    /**
     * this function to set vouchers in database
     */
    public void setVouchers(){
        db d = new db();
        vouchers =  d.getUserVouchers(this);
    }
//---------------------------------------------------------

    /**
     * this function to get orders
     * @return order
     */
    public ArrayList<Order> getOrders(){
        return order;
    }
//---------------------------------------------------------

    /**
     * this function to display orders
     */
    public void displayOrders(){
        for(Order i : order){
            i.review_order();
        }
    }
//---------------------------------------------------------

    /**
     * this function to get the password
     * @return password
     */
    public String getPass(){
        return Password;
    }    
//---------------------------------------------------------

    /**
     * this function to get name
     * @return name
     */
    public String getName(){
        return UserName;
    }   
//---------------------------------------------------------

    /**
     * this function to get status
     * @return status
     */
    public String getStatus(){
        return Status;
    } 
//---------------------------------------------------------

    /**
     * this function to get points
     * @return loyality points
     */
    public int getPoints(){
        return LoyalityPoints;
    } 
//---------------------------------------------------------

    /**
     * this function to get iD
     * @return iD
     */
    public int getID(){
        return ID;
    } 
//---------------------------------------------------------

    /**
     * this function to get mail
     * @return email
     */
    public String getMail(){
        return Email;
    }  
//---------------------------------------------------------

    /**
     * this function to get address
     * @return address
     */
    public String getaddress(){
        return address;
    }   
//---------------------------------------------------------

    /**
     * this function to get phone
     * @return phone
     */
    public String getPhone(){
        return phone;
    }  
//---------------------------------------------------------

    /**
     * this function to get address
     * @return address
     */
    public String getAddress(){
        return address;
    }  
//---------------------------------------------------------

    /**
     * this function to set points
     * @param points
     */
    public void setPoints(int points){
        LoyalityPoints = points;
    }
//---------------------------------------------------------

    /**
     * this function to set iD
     * @param id
     */
    public void setID(int id){
        this.ID = id;
    }
//---------------------------------------------------------

    /**
     * this function to put the statue suspended
     */
    public void suspend(){
        Status = "Suspended";
    }
//---------------------------------------------------------

    /**
     * this function to put the statue unsuspended
     */
    public void unSuspend(){
        Status = "unsuspended";
    }
//---------------------------------------------------------

    /**
     * this function to display catalog
     */
    public void displayCatalog(){
        Catalog catalog = new Catalog();
        catalog.displayItems();
    }
//---------------------------------------------------------

    /**
     * this function to display menu to registerd user
     */
    public void registerdMenu(){
            Scanner objScanner = new Scanner(System.in);
            while (true){
                if(Status.toLowerCase().trim().equals("Suspended".trim().toLowerCase())){
                    System.out.println("U r Suspended u will be logged out");
                    break;
                }
                System.out.println("Here is ur main menu");
                System.out.println("Press 1 to display the catalog");
                System.out.println("Press 2 to display ur cart");
                System.out.println("Press 3 to add item the cart");
                System.out.println("Press 4 to remove item from the cart");
                System.out.println("Press 5 to make an order");
                System.out.println("Press 6 to display orders details");
                System.out.println("press 7 to reorder an order");
                System.out.println("press 8 to buy a voucher");
                System.out.println("Press 9 to log out");
                System.out.println("Press 10 to exit the program");
                String choice = objScanner.nextLine();
                if (choice.trim().equals("1")){
                    displayCatalog();
                    System.out.println("-------------------------------------------");
                }
                else if(choice.trim().equals("2")){
                    Cart crt = new Cart();
                    crt.fillCart(this);
                    cart = crt;
                    cart.displayCart();
                    System.out.println("-------------------------------------------");
                }
                else if(choice.trim().equals("3")){
                    while (true){
                        displayCatalog();
                        System.out.println("Write item's name");
                        String name = objScanner.nextLine();
                        Catalog catalog = new Catalog();
                        if(catalog.returnItem(name) ==null)
                            System.out.println("invalid product name");
                        else{
                            cart.additem(catalog.returnItem(name));
                            break;
                        }
                    }
                }
                else if(choice.trim().equals("4")){
                    while (true){
                        cart.fillCart(this);
                        cart.displayCart();
                        System.out.println("Write item's name");
                        String name = objScanner.nextLine();
                        Catalog catalog = new Catalog();
                        if(catalog.returnItem(name) ==null)
                            System.out.println("invalid product name");
                        else{
                            cart.removeitem(catalog.returnItem(name));
                            break;
                        }
                    }
                }
                else if(choice.trim().equals("5")){
                    Cart crt = new Cart();
                    crt.fillCart(this);
                    cart = crt;
                    System.out.println("Write ur address");
                    String address = objScanner.nextLine();
                    Order order = new Order(cart);
                    order.set_address(address);
                    order.checkout(this);
                }
                else if(choice.trim().equals("6")){
                    db deDb = new db();
                    Cart cart = new Cart();
                    cart.fillCart(this);
                    deDb.gettingAllOrders(this , cart);
                    displayOrders();
                }
                else if (choice.trim().equals("7")){
                    db deDb = new db();
                    Cart cart = new Cart();
                    cart.fillCart(this);
                    deDb.gettingAllOrders(this , cart);
                    displayOrders();
                    System.out.println("Write the ID of the order");
                    int id = objScanner.nextInt();
                    for(Order ord :order){
                        if(ord.getID()==id){
                            ord.checkout(this);
                        }
                    }
                }
                else if (choice.trim().equals("8")){
                    db deDb = new db();
                    vouchers = deDb.getVouchers();
                    deDb.printAllAvilableVouchers();
                    System.out.println("Write the ID of the order");
                    int id = objScanner.nextInt();
                    Cart cart = new Cart();
                    cart.fillCart(this);
                    Ewallet ewallet = new Ewallet();
                    System.out.println("Ewallet login:");
                    ewallet.login();
                    ewallet.pay();
                    for(Voucher vchr :vouchers){
                        if(vchr.getID()==id){
                            deDb.buyAvoucher(this, id);
                        }
                    }
                }
        }
    }
}

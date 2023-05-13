import java.util.ArrayList;
import java.util.Scanner;

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
    public registerd(String mail,String name,String pass , String add, String phone ){
        this.Email = mail;
        this.Password = pass;
        this.UserName = name;
        this.address = add;
        this.phone = phone;
        unSuspend();
    }
//---------------------------------------------------------
    public  ArrayList <Voucher> getVouchers(){
        return vouchers;
    }
//---------------------------------------------------------
    public void setVouchers(){
        db d = new db();
        vouchers =  d.getUserVouchers(this);
    }
//---------------------------------------------------------
    public ArrayList<Order> getOrders(){
        return order;
    }
//---------------------------------------------------------
    public void displayOrders(){
        for(Order i : order){
            i.review_order();
        }
    }
//---------------------------------------------------------
    public String getPass(){
        return Password;
    }    
//---------------------------------------------------------
    public String getName(){
        return UserName;
    }   
//--------------------------------------------------------- 
    public String getStatus(){
        return Status;
    } 
//---------------------------------------------------------
    public int getPoints(){
        return LoyalityPoints;
    } 
//---------------------------------------------------------
    public int getID(){
        return ID;
    } 
//---------------------------------------------------------
    public String getMail(){
        return Email;
    }  
//---------------------------------------------------------  
    public String getaddress(){
        return address;
    }   
//--------------------------------------------------------- 
    public String getPhone(){
        return phone;
    }  
//---------------------------------------------------------  
    public String getAddress(){
        return address;
    }  
//---------------------------------------------------------
    public void setPoints(int points){
        LoyalityPoints = points;
    }
//---------------------------------------------------------
    public void setID(int id){
        this.ID = id;
    }
//---------------------------------------------------------
    public void suspend(){
        Status = "Suspended";
    }
//---------------------------------------------------------
    public void unSuspend(){
        Status = "unsuspended";
    }
//---------------------------------------------------------
    public void displayCatalog(){
        Catalog catalog = new Catalog();
        catalog.displayItems();
    }
//---------------------------------------------------------
    public void registerdMenu(){
            Scanner objScanner = new Scanner(System.in);
            while (true){
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

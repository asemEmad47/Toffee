import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class for order
 */
public class Order {
    private double total_price;
    private String address;
    private Cart thecart;
    private Payment paymentmethod;
    private int orderID;
    private ArrayList<Item> items = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
//---------------------------------------------------------

    /**
     * this function to get order ID
     * @return id
     */
    public int getID(){
    return orderID;
}
//---------------------------------------------------------

    /**
     * this function to get the payment method in order
     * @return payment method
     */
    public Payment getpPayment(){
    return paymentmethod;
}
//---------------------------------------------------------

    /**
     * this function to set payment method for order
     * @param pay
     */
    public void setPayment(Payment pay){
    this.paymentmethod = pay; 
}

    /**
     * this function to get order items
     * @return items
     */
    public ArrayList<Item> getItems(){
    return items;
}
//---------------------------------------------------------

    /**
     * this function to set order id
     * @param id
     */
    public void setID(int id){
    this.orderID = id;
}
//---------------------------------------------------------

    /**
     * this function is to set price of order
     * @param price
     */
    public void setPrice(double price){
    this.total_price = price;
}
//---------------------------------------------------------

    /**
     * this constrctour to order a cart
     * @param cart
     */
    public Order(Cart cart){
    this.thecart = cart;
    items = thecart.returningArr();
    calc_total_price();
}
//---------------------------------------------------------

    /**
     * this function to get the method of payment
     * @return method
     */
    public String payment(){
    return paymentmethod.getMethod();
}
//---------------------------------------------------------

    /**
     * this function to calculate the total price
     */
    public void calc_total_price()
    {
        thecart.calc_items_price();
        double tax = thecart.get_price() * (14/100);
        total_price = thecart.get_price() + tax;
    }
//---------------------------------------------------------

    /**
     * this function to get the total price after calculated
     * @return total price
     */
    public double get_total_price()
    {
        return total_price; 
    }
//---------------------------------------------------------

    /**
     * this function to set address of order
     * @param a
     */
    public void set_address(String a)
    {
        this.address = a;
    }
//---------------------------------------------------------

    /**
     * this function to get address of order
     * @return address
     */
    public String get_address()
    {
        return address;
    }
//---------------------------------------------------------

    /**
     * this function to review the order
     */
    public void review_order()
    {
        for(Item i :items){
            System.out.println("Name:" + " "+i.getName());
            System.out.println("Type:" + " "+i.getType());
            System.out.println("Brand:" + " "+i.getBrand());
            System.out.println("Category:" + " "+i.getCategory());
            System.out.println("Descriiption:" + " "+i.getDescription());
            System.out.println("Price:" + " "+i.getPrice());
            System.out.println("Discount:" + " "+i.getDiscount());
            System.out.println("-------------------------------------------");
        }
        System.out.println("Total Price:" + " " + total_price);
        System.out.println("Address:" + " " + address);
        System.out.println("orderID" + " " + orderID);
        System.out.println("-------------------------------------------");
    }
//---------------------------------------------------------

    /**
     * this function to check out the order
     * @param reg
     */
    public void checkout(registerd reg)
    {
        db deDb = new db();
        int choice;
        int choice2;
        review_order();
        System.out.println("are you sure to checkout (1) YES (2) NO");
        choice = scan.nextInt();
        if(choice == 1)
        {
            // voucher
            ArrayList<Voucher> vouchers = new ArrayList<>();
            vouchers = reg.getVouchers();
            vouchers=deDb.getUserVouchers(reg) ;
            if(!vouchers.isEmpty()){
            System.out.println("U have a vouchers in ur account , do u want to use any? (1) Yes (2) NO");
            int usingVoucher = scan.nextInt();
            if(usingVoucher ==1){
                while(!vouchers.isEmpty()){
                    System.out.println("-------------------------------------");
                    for(Voucher voucher : vouchers){
                        System.out.println("value:"+voucher.getValue());
                        System.out.println("ID:"+voucher.getID());
                        System.out.println("-------------------------------------");
                    }
                    System.out.println("Write voucher id which u want to user , u can press (0) to exit");
                    int choosingVoucher = scan.nextInt();
                    if(choosingVoucher ==0)
                        break;
                    else{
                    for(Voucher voucher : vouchers){
                        if(choosingVoucher == voucher.getID()){
                            total_price-=voucher.getValue();
                            System.out.println("Now price = "+ total_price);
                            deDb.deleteVoucher(voucher.getID());
                            vouchers.remove(voucher);
                            break;
                        }
                    }
                }
                }
            }
        }
            //loyality points
            if(reg.getPoints() !=0){
                System.out.println("U have " + reg.getPoints() + "point , each 50 point decrease price by 1\n do u want to use them? (1) Yes (2) no");
                int choosingVoucher = scan.nextInt();
                if(choosingVoucher ==1){
                    total_price -=(reg.getPoints()/50);
                    System.out.println("Now price = "+ total_price);
                }
            }
            review_order();
            System.out.println("How do you want to pay by ? (1) CASH (2) E-WALLET ");
            choice2 = scan.nextInt();
            if(choice2 == 1)
                paymentmethod = new Cash();
            else
                paymentmethod = new Ewallet();
            db daDb = new db();
            daDb.Order(this,reg);
            System.out.println("Checkout done!");
        }else{
            System.out.println("Checkout cancelled");
            return;
        }

    }
}
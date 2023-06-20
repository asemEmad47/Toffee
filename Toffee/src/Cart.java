import java.util.ArrayList;

/**
 * this class for cart
 */

public class Cart {
    private double items_price = 0;
    private int ID;
    private ArrayList<Item> mycart = new ArrayList<>();
//---------------------------------------------------------

    /**
     * this function for adding items to cart
     * @param it
     */
    public void additem(Item it) {
        mycart.add(it);
        db daDb = new db();
        daDb.addToCart(it, this);
    }
//---------------------------------------------------------

    /**
     * this function for removing items from cart
     * @param it
     */
    public void removeitem(Item it) {
        for (Item i : mycart) {
            if (i.getName().trim().equals(it.getName())) {
                mycart.remove(i);
                db daDb = new db();
                daDb.removeFromCart(it);
                break;
            }
        }
    }
//---------------------------------------------------------

    /**
     * this function for displaying items to user
     */
    public void displayCart(){
        for(Item i :mycart){
            System.out.println("Name:" + " "+i.getName());
            System.out.println("Type:" + " "+i.getType());
            System.out.println("Brand:" + " "+i.getBrand());
            System.out.println("Category:" + " "+i.getCategory());
            System.out.println("Descriiption:" + " "+i.getDescription());
            System.out.println("Price:" + " "+i.getPrice());
            System.out.println("Discount:" + " "+i.getDiscount());
            System.out.println("-------------------------------------------");
        }
    }
//---------------------------------------------------------

    /**
     * this function to calculate price of items in the cart
     */
    public void calc_items_price() {
        for (Item i : mycart) {
            items_price += i.getPrice();
        }
    }
//---------------------------------------------------------

    /**
     * this function is to get items in the cart
     * @return the cart
     */
    public ArrayList<Item> get_items() {
        return mycart;
    }
//---------------------------------------------------------

    /**
     * this function to get the price of items
     * @return items price
     */
    public double get_price() {
        return items_price;
    }
//---------------------------------------------------------

    /**
     * this function get user cart from database
     * @param reg
     */
    public void fillCart(registerd reg){
        db dataDb = new db();
        dataDb.getCart(this, reg);
    }
//---------------------------------------------------------

    /**
     * this function to get the cart of user
     * @return cart
     */
    public ArrayList<Item> returningArr(){
        return mycart;
    }
//---------------------------------------------------------

    /**
     * this function to get the user id
     * @return id
     */
    public int getID(){
        return ID;
    }

    /**
     * this function to set user id
     * @param id
     */
    public void setID(int id){
        this.ID = id;
    }
}
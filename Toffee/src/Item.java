/**
 * this class for items
 */
abstract public class Item{
    protected String name;

    protected String brand;
    protected String Type;
    protected String category;
    protected String description;
    protected double price;
    protected double Quantity;
    protected double discount;
    protected int ID;

    /**
     * this function to set id
     * @param id
     */

    public void setID(int id){
        this.ID = id;
    }

    /**
     * this function to set name
     * @param nm
     */
    public void setName(String nm){
        this.name = nm;
    }

    /**
     * this function to set brand
     * @param br
     */
    public void setBrand(String br){
        this.brand = br;
    }

    /**
     * this function to set type
     */
    public abstract void setType();

    /**
     * this function to set category
     * @param ct
     */
    public void setcategory(String ct){
        this.category = ct;
    }

    /**
     * this function to set describtion
     * @param desc
     */
    public void setDescription(String desc){
        this.description = desc;
    }

    /**
     * this function to set price
     * @param pr
     */
    public void setPrice(double pr){
        this.price = pr;
    }

    /**
     * this function to set discount
     * @param disc
     */
    public void setDiscount(double disc){
        this.discount = disc;
    }

    /**
     * this function to get name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * this function to get brand
     * @return brand
     */
    public String getBrand(){
        return brand;
    }

    /**
     * this function to get id
     * @return id
     */
    public int getID(){
        return ID;
    }

    /**
     * this function to get category
     * @return category
     */
    public String getCategory(){
        return category;
    }

    /**
     * this function to get description
     * @return description
     */
    public String getDescription(){
        return description;
    }

    /**
     * this function to get price
     * @return price
     */
    public double getPrice(){
        return price;
    }

    /**
     * this function to get discount
     * @return discount
     */
    public double getDiscount(){
        return discount;
    }

    /**
     * this function to get quantity
     * @return quantity
     */
    public double getQuantity(){
        return Quantity;
    }

    /**
     * this function to get type
     * @return type
     */
    public String getType(){
        return Type;
    }
}
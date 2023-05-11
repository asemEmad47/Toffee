abstract public class Item{
    protected String name;
    protected String brand;
    protected String Type;
    protected String category;
    protected String description;
    protected double price;
    protected double Quantity;
    protected double discount;
    public void setName(String nm){
        this.name = nm;
    }
    public void setBrand(String br){
        this.brand = br;
    }
    public abstract void setType();
    public void setcategory(String ct){
        this.category = ct;
    }
    public void setDescription(String desc){
        this.description = desc;
    }
    public void setPrice(double pr){
        this.price = pr;
    }
    public void setDiscount(double disc){
        this.discount = disc;
    }
    public String getName(){
        return name;
    }
    public String getBrand(){
        return brand;
    }
    public String getCategory(){
        return category;
    }
    public String getDescription(){
        return description;
    }
    public double getPrice(){
        return price;
    }
    public double getDiscount(){
        return discount;
    }
    public double getQuantity(){
        return Quantity;
    }
    public String getType(){
        return Type;
    }
}
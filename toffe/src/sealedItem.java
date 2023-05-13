public class sealedItem extends Item {
    private int units; 
    public sealedItem(String nm, String br, String ct, String desc, double pr , double disc , int unit , int ID){
        this.name = nm;
        this.brand = br;
        this.category = ct;
        this.description = desc;
        this.price = pr;
        this.discount = disc;
        this.units = unit;
        Quantity = units;
        this.ID = ID;
        setType();
    }
    public void setUnits(int unit){
        this.units = unit;
    }
    public int getUnits(){
        return units;
    }
    public void setType(){
        this.Type="Sealed";
    }
}

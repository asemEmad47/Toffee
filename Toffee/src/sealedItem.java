/**
 * this class for sealed items
 */
public class sealedItem extends Item {
    private int units;

    /**
     * this function is to set item details
     * @param nm name of item
     * @param br brand of item
     * @param ct category of item
     * @param desc description of item
     * @param pr price of item
     * @param disc discount on item
     * @param unit kilos of item
     * @param ID id of item
     */
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

    /**
     * this function to set units of item
     * @param unit
     */
    public void setUnits(int unit){
        this.units = unit;
    }

    /**
     * this function to get units of item
     * @return units
     */
    public int getUnits(){
        return units;
    }

    /**
     * this function to set the type of item
     */
    public void setType(){
        this.Type="Sealed";
    }
}

/**
 * this class for loose item
 */
public class LooseItem extends Item{
    private double Kilos;

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
    public LooseItem(String nm, String br, String ct, String desc, double pr , double disc , Float unit , int ID){
        this.name = nm;
        this.brand = br;
        this.category = ct;
        this.description = desc;
        this.price = pr;
        this.discount = disc;
        this.Kilos = unit;
        Quantity = Kilos;
        this.ID = ID;
        setType();
    }

    /**
     * this function to set the kilos of item
     * @param unit
     */
    public void setKilos(double unit){
        this.Kilos = unit;
    }

    /**
     * this function to get kilos of item
     * @return item
     */
    public double getKilos(){
        return Kilos;
    }

    /**
     * this function to set the type of item if it loosed or sealead
     */
    public void setType(){
        this.Type="Loose";
    }
}

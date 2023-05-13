public class LooseItem extends Item{
    private double Kilos; 
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
    public void setKilos(double unit){
        this.Kilos = unit;
    }
    public double getKilos(){
        return Kilos;
    }
    public void setType(){
        this.Type="Loose";
    }
}

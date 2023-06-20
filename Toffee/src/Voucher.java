/**
 * this class for Voucher
 */
public class Voucher {
    private int ID;
    private Double value;

    /**
     * this function to get the ID
     * @return ID
     */
    public int getID(){
        return ID;
    }

    /**
     * this function to get the value
     * @return value
     */
    public double getValue(){
        return value;
    }

    /**
     * this function to set ID
     * @param id
     */
    public void setID(int id){
        this.ID = id;
    }

    /**
     * this function to set value
     * @param val
     */
    public void setValue(double val){
        this.value = val;
    }
}

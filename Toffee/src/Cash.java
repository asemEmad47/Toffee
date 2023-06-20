/**
 * this class for cash payment
 */
public class Cash extends Payment {

    protected double extraprice;

    /**
     * this constructor use to set method of payment
     */
    public Cash(){
        set_method();
    }

    /**
     * this function to set the method to cash
     */
    void set_method(){
        this.method = "cash";
    }
}
/**
 * this class for payment
 */
abstract public class Payment {
    protected String method;

    /**
     * this function to set method of payment
     */
    abstract void set_method();

    /**
     * this function to get method of payment
     * @return method
     */
    public String getMethod(){
        return method;
    }
}
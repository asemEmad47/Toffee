public class Voucher {
    private int ID;
    private Double value;
    Voucher(int id , Double val){
            try {
                ID = id;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid ID, write it again");
            }
            try {
                value = val;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid value, write it again");
            }
    }
    int getID(){
        return ID;
    }
    double getValue(){
        return value;
    }
}

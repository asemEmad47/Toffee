public class loyalityPoints {
    private int amount;
    loyalityPoints(int amt){
            try {
                amount = amt;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid value, write it again");
            }
    }
    int getAmount(){
        return amount;
    }
}

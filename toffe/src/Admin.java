import java.util.Scanner;
public class Admin {
    private admincatalog catalog;
    private sealedItem sealed;
    private LooseItem loose;
    public void setCatalog(admincatalog cat){
        catalog = cat;
    }

    public void addToCatalog(){
        System.out.println("would u want to add\n1-sealed item\n2-loose item");
        try (Scanner objScanner = new Scanner(System.in)) {
            String choice = objScanner.nextLine();
            while(!choice.trim().equals("1") && !choice.trim().equals("2")){
                System.out.println("Plz press 1 for sealed item or 2 for loose item");
                choice = objScanner.nextLine();
            }
            //name
            System.out.println("Write item's name");
            String itemName = objScanner.nextLine();

            //brand
            System.out.println("Write item's brand");
            String brand = objScanner.nextLine();

            //category
            System.out.println("Write item's category");
            String category = objScanner.nextLine();

            //describtion
            System.out.println("Write item's describtion");
            String describtion = objScanner.nextLine();

            double price , discount;
            //price
            System.out.println("Write item's Price");
            while(true){
            try {
                price = objScanner.nextDouble();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid price, please write  valid ptice");
                objScanner.nextLine();
            }}
            //discount
            System.out.println("Write item's discount");
            while(true){
            try {
                discount = objScanner.nextDouble();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid price, please write  valid ptice");
                objScanner.nextLine();
            }}
            if(choice.trim().equals("1")){
                int availableUnits;
                System.out.println("Write the availavle untis");
                while(true){
                try {
                    availableUnits = objScanner.nextInt();
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid number of units");
                    objScanner.nextLine();
                }}
                sealed =new sealedItem(itemName, brand, category, describtion, price,discount, availableUnits);
                catalog.addToList(sealed);
            }
            else{
                Float availableKilos;
                System.out.println("Write the availavle kilos");
                while(true){
                try {
                    availableKilos = objScanner.nextFloat();
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid number of kilos");
                    objScanner.nextLine();
                }}
                loose =new LooseItem(itemName, brand, category, describtion, price,discount, availableKilos);
                catalog.addToList(loose);
            }
        }
    }

    public void removeFromCatalog(){
        catalog.displayItems();
        System.out.println("Write the name of the item to be deleted");
        try (Scanner objScanner = new Scanner(System.in)) {
            String name = objScanner.nextLine();
            catalog.removefromCatalog(name);
        }
    }

    public void EditItem(){
        catalog.displayItems();
        System.out.println("Write the name of the item to be Updated");
        try (Scanner objScanner = new Scanner(System.in)) {
            String name = objScanner.nextLine();
            catalog.updateProductInfo(name);
        }
    }

}

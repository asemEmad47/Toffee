import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class for admin
 */
public class Admin {
    private admincatalog catalog;
    private sealedItem sealed;
    private LooseItem loose;

    /**
     * this constructor to put admin catalog
     * @param cat
     */
    public Admin(admincatalog cat){
        catalog = cat;
    }
//---------------------------------------------------------

    /**
     * this function to add item to the catalog
     */
    public void addToCatalog(){
        System.out.println("would u want to add\n1-sealed item\n2-loose item");
            Scanner objScanner = new Scanner(System.in);
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
                sealed =new sealedItem(itemName, brand, category, describtion, price,discount, availableUnits,0);
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
                loose =new LooseItem(itemName, brand, category, describtion, price,discount, availableKilos,0);
                catalog.addToList(loose);
        }
    }
//---------------------------------------------------------

    /**
     * this function to remove item from catalog
     */
    public void removeFromCatalog(){
        catalog.displayItems();
        System.out.println("Write the name of the item to be deleted");
        Scanner objScanner = new Scanner(System.in);
            String name = objScanner.nextLine();
            catalog.removefromCatalog(name);
    }
//---------------------------------------------------------

    /**
     * this function to edit item in the catalog
     */
    public void EditItem(){
        catalog.displayItems();
        System.out.println("Write the name of the item to be Updated");
        Scanner objScanner = new Scanner(System.in);
            String name = objScanner.nextLine();
            catalog.updateProductInfo(name);
    }
//---------------------------------------------------------

    /**
     * this is the menu to choose the action
     */
    public void adminMenu(){
        while (true){
            Scanner objScanner = new Scanner(System.in);
            System.out.println("Here is ur main menu");
            System.out.println("Press 1 to display the catalog");
            System.out.println("Press 2 to add item to the the catalog");
            System.out.println("Press 3 to Update item in the catalog");
            System.out.println("Press 4 to remove item from the catalog");
            System.out.println("Press 5 to display all users");
            System.out.println("Press 6 to suspend a user");
            System.out.println("Press 7 to unsuspend a user");
            System.out.println("Press 8 to set voucher");
            System.out.println("Press 9 to give a user loyality points");
            System.out.println("Press 10 to view stats");
            System.out.println("Press 11 to logout");
            String choice = objScanner.nextLine();
            if (choice.trim().equals("1")){
                admincatalog cat = new admincatalog();
                catalog = cat;
                catalog.displayItems();
        }

            else if(choice.trim().equals("2"))
                addToCatalog();
            else if(choice.trim().equals("3"))
                EditItem();
            else if(choice.trim().equals("4"))
                removeFromCatalog(); 
            else if(choice.trim().equals("5")){
                db d = new db();
                d.printAllUsers();
            }
            else if(choice.trim().equals("6")){
                db d = new db();
                d.printAllUsers();
                ArrayList<registerd> reg = new ArrayList<>();
                reg = d.gettingClientsData();
                System.out.println("Write the user's ID");
                int ID = objScanner.nextInt();
                for(registerd use:reg){
                    if(ID == use.getID())
                        d.ChangeStatus("suspended", use);
                }
            }
            else if(choice.trim().equals("7")){
                db d = new db();
                d.printAllUsers();
                ArrayList<registerd> reg = new ArrayList<>();
                reg = d.gettingClientsData();
                System.out.println("Write the user's ID");
                int ID = objScanner.nextInt();
                for(registerd use:reg){
                    if(ID == use.getID())
                        d.ChangeStatus("unsuspended", use);
                }
            }
            else if(choice.trim().equals("8")){
                db d = new db();
                Voucher voucher = new Voucher();
                System.out.println("set voucher value");
                double value = objScanner.nextDouble();
                voucher.setValue(value);
                d.setVoucher(voucher);
                System.out.println("An voucher had been set succefully");
            }
            else if(choice.trim().equals("9")){
                db d = new db();
                d.printAllUsers();
                ArrayList<registerd> reg = new ArrayList<>();
                reg = d.gettingClientsData();
                System.out.println("Write the user's ID");
                int ID = objScanner.nextInt();
                System.out.println("Enter the amount of loyality points");
                int amt = objScanner.nextInt();
                for(registerd use:reg){
                    if(ID == use.getID())
                        d.addPoints(amt , use);
                }
            }
            else if(choice.trim().equals("10")){
                db d = new db();
                d.displayStats();
            }
            else if(choice.trim().equals("11")){
                System.exit(0);
            }
        }

    }
}

import java.util.Scanner;
public class admincatalog extends Catalog{

    private db database = new db();
    public void addToList(Item it){
        item.add(it);
        database.addnewitem(it);
    }

    public void removefromCatalog(String productName){
        boolean found = false;
        int counter = 0;
        for(Item it :item){
            if(it.getName().trim().equals(productName.trim())){
                item.remove(counter);
                found = true;
                break;
            }
            counter++;
        }
        if(found!=false)
            database.removeItem(productName);
        else    
            System.out.println("Not found");
    }
    public void updateProductInfo(String name){
        for(Item it :item){
            String nm="",brand ="",category="",description = "",choice;
            double price=0,discount=0;
            if(it.getName().trim().equals(name)){ 
            try (Scanner objScanner = new Scanner(System.in)) {
                while(true){
                    System.out.println("choose what u want");
                    System.out.println("1-name");
                    System.out.println("2-Brand");
                    System.out.println("3-category");
                    System.out.println("4-description");
                    System.out.println("5-price");
                    System.out.println("6-discount");
                    System.out.println("e- save and exit");

                    try {
                        choice = objScanner.nextLine();
                    if(choice.trim().equals("1".trim())){
                        System.out.println("Enter the new name");
                        nm=objScanner.nextLine();
                    }
                    else if(choice.trim().equals("2".trim())){
                        System.out.println("Enter the new brand");
                        brand=objScanner.nextLine();
                    }
                    else if(choice.trim().equals("3".trim())){
                        System.out.println("Enter the new category");
                        category=objScanner.nextLine();
                    }
                    else if(choice.trim().equals("4".trim())){
                        System.out.println("Enter the new description");
                        description=objScanner.nextLine();
                    }
                    else if(choice.trim().equals("5".trim())){
                        System.out.println("Enter the new Price");
                        while(true){
                            try {
                                price = objScanner.nextDouble();
                                break;
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("Invalid price, please write  valid ptice");
                                objScanner.nextLine();
                            }}
                    }
                    else if(choice.trim().equals("6".trim())){
                        System.out.println("Enter the new discount");
                        while(true){
                            try {
                                discount = objScanner.nextDouble();
                                break;
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("Invalid price, please write  valid ptice");
                                objScanner.nextLine();
                            }}
                    }
                    else if(choice.trim().equals("e".trim())||choice.trim().equals("E".trim()))
                        break;
                    else
                        System.out.println("wrong choice");     
                    }catch (Exception e) {
                        System.out.println("here");
                    }
                }
            }
                if(!nm.trim().equals(""))
                    it.setName(nm);
                if(!brand.trim().equals(""))
                    it.setBrand(brand);
                if(!category.trim().equals(""))
                    it.setcategory(category);
                if(!description.trim().equals(""))
                    it.setDescription(description);
                if(price!=0)
                    it.setPrice(price);
                if(discount!=0)
                    it.setDiscount(discount);
                database.update(name, it);
                return;
        }}
        System.out.println("Not found");   
    }
}
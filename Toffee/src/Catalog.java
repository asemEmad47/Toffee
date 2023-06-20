import java.util.ArrayList;

/**
 * this class for catalog
 */
public class Catalog{
    protected ArrayList<Item> item=new ArrayList<>();

    /**
     * this is constructor to put catalog in database
     */
    Catalog(){
        db d = new db();
        item = d.FillCatalog(this);
    }

    /**
     * this function to get the items in catalog
     * @param name
     * @return items
     */
    public Item returnItem(String name){
        for(Item i :item){
            if(i.getName().trim().equals(name))
                return i;
        }
        return null;
    }

    /**
     * this function to display items in the catalog
     */
    public void displayItems(){
        for(Item i :item){
            System.out.println("Name:" + " "+i.getName());
            System.out.println("Type:" + " "+i.getType());
            System.out.println("Brand:" + " "+i.getBrand());
            System.out.println("Category:" + " "+i.getCategory());
            System.out.println("Description:" + " "+i.getDescription());
            System.out.println("Price:" + " "+i.getPrice());
            System.out.println("Discount:" + " "+i.getDiscount());
            System.out.println("-------------------------------------------");
        }
    }

    /**
     * this function to search item by name
     * @param name
     */
    public void searchByName(String name){
        boolean found = false;
        for(Item i :item){
            if(i.getName().trim().equals(name)){
                System.out.println("Name:" + " "+i.getName());
                System.out.println("Type:" + " "+i.getType());
                System.out.println("Brand:" + " "+i.getBrand());
                System.out.println("Category:" + " "+i.getCategory());
                System.out.println("Descriiption:" + " "+i.getDescription());
                System.out.println("Price:" + " "+i.getPrice());
                System.out.println("Discount:" + " "+i.getDiscount());
                System.out.println("-------------------------------------------");
                found = true;
            }
        }
        if(!found)
            System.out.println("not found");
    }

    /**
     * this function to search item by brand
     * @param brand
     */
    public void searchByBrand(String brand){
        for(Item i :item){
            if(i.getBrand().trim().equals(brand)){
                System.out.println("Name:" + " "+i.getName());
                System.out.println("Type:" + " "+i.getType());
                System.out.println("Brand:" + " "+i.getBrand());
                System.out.println("Category:" + " "+i.getCategory());
                System.out.println("Descriiption:" + " "+i.getDescription());
                System.out.println("Price:" + " "+i.getPrice());
                System.out.println("Discount:" + " "+i.getDiscount());
                System.out.println("-------------------------------------------");
            }
        }
    }
}
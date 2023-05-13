import java.util.ArrayList;
public class Catalog{
    protected ArrayList<Item> item=new ArrayList<>();
    Catalog(){
        db d = new db();
        item = d.FillCatalog(this);
    }
    public Item returnItem(String name){
        for(Item i :item){
            if(i.getName().trim().equals(name))
                return i;
        }
        return null;
    }
    public void displayItems(){
        for(Item i :item){
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
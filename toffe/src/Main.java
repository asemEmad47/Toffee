
public class Main {
    public static void main(String[] args) {
        //Catalog catalog = new Catalog();
        admincatalog cat = new admincatalog();
        //catalog.displayItems();
        // catalog.searchByName("candy");
        Admin ad = new Admin();
        ad.setCatalog(cat);
        ad.EditItem();
        // ad.addToCatalog();
        cat.displayItems();
        // catalog.displayItems();     
    }
}

import java.sql.*;
import java.util.ArrayList;
public class db {
    String URL = "jdbc:sqlserver://Asem:1433;DatabaseName=Toffee";
    private String UserName = "toffee";
    private String password = "123456";
    private sealedItem sealed;
    private LooseItem Loose;
    //filling catalog with which in the databse
    public ArrayList<Item> FillCatalog(Catalog catalog){
    ArrayList<Item> arr = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            Statement stmt = con.createStatement();
            String sel = "select * from Items";
            ResultSet rs= stmt.executeQuery(sel);
                while(rs.next()){
                    if(rs.getString("Type") == "Sealed"){
                        sealed = 
                        new sealedItem(rs.getString("Name"),
                        rs.getString("Brand"), 
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getFloat("price"), 
                        rs.getFloat("discount"), 
                        rs.getInt("Quantity"));
                        arr.add(sealed);
                    }
                    else{
                        Loose = 
                        new LooseItem(rs.getString("Name"),
                        rs.getString("Brand"), 
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getFloat("price"), 
                        rs.getFloat("discount"), 
                        rs.getFloat("Quantity"));   
                        arr.add(0, Loose);    
                    }
                }
                con.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
        return arr;
    }
    // adding item to the database
    public void addnewitem(Item item){
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            String sql = "INSERT INTO Items(Name,Brand,category,description,price,discount,Quantity,Type) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getBrand());
            preparedStatement.setString(3, item.getCategory());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setDouble(5, item.getPrice());
            preparedStatement.setDouble(6, item.getDiscount());
            preparedStatement.setDouble(7, item.getQuantity());
            preparedStatement.setString(8, item.getType());
            preparedStatement.executeUpdate();    
            // Close connection and statement
            preparedStatement.close();
            con.close();
            System.out.println("An item had added successfully");
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
    // remove item to the database
    public void removeItem(String name){
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            String sql = "DELETE FROM Items WHERE name = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();    
            // Close connection and statement
            preparedStatement.close();
            con.close();
            System.out.println("An item had Removed successfully");
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
    //update item
    public void update(String name, Item it){
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            String sqlUpdate = "UPDATE Items SET Name=?, Brand=?, category=?, description=?, price=?, discount=?, Quantity=?  WHERE Name=?";
            PreparedStatement pstmt = con.prepareStatement(sqlUpdate);
            pstmt.setString(1, it.getName());
            pstmt.setString(2, it.getBrand());
            pstmt.setString(3, it.getCategory());
            pstmt.setString(4, it.getDescription());
            pstmt.setDouble(5, it.getPrice());
            pstmt.setDouble(6, it.getDiscount());
            pstmt.setDouble(7, it.getQuantity()); // Updated to set the Quantity parameter
            pstmt.setString(8, name);
            
            // execute the update statement
            pstmt.executeUpdate();
            con.close();
            System.out.println("An item has been updated successfully");
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
}
    // public boolean adminLogin(String name,String pass){
    //     try{
    //         Connection con = DriverManager.getConnection(URL , UserName , password);
    //         Statement stmt = con.createStatement();
    //         String sel = "select * from admin";
    //         Admin ad;
    //         ResultSet rs= stmt.executeQuery(sel);
    //             while(rs.next())
    //             {
    //                 if(rs.getString("Name").trim().equals(name) && rs.getString("Password").trim().equals(pass)){
    //                     return true;
    //                 }
    //             }
    //             return false;
    //     } catch(Exception e){
    //         System.out.println(e.getMessage());
    //     }
    //     return false; 
    // }

}

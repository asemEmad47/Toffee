import java.sql.*;
import java.util.ArrayList;

/**
 * this class for DataBase
 */
public class db {
    String URL = "jdbc:sqlserver://Asem:1433;DatabaseName=Toffee";
    private String UserName = "toffee";
    private String password = "123456";
//---------------------------------------------------------
    //filling catalog with which in the databse

    /**
     * this function to fill the catalog in database
     * @param catalog
     * @return the array of item
     */
    public ArrayList<Item> FillCatalog(Catalog catalog){
        sealedItem sealed;
        LooseItem Loose;
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
                        rs.getInt("Quantity"),
                        rs.getInt("ID"));
                        sealed.setID(rs.getInt("ID"));
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
                        rs.getFloat("Quantity"),
                        rs.getInt("ID"));  
                        Loose.setID(rs.getInt("ID")); 
                        arr.add(Loose);    
                    }
                }
                con.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
        return arr;
    }
//---------------------------------------------------------
    // adding item to the database (admin)

    /**
     * this function to add item in catalog in database
     * @param item
     */
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
//---------------------------------------------------------
    // remove item from the database (admin)

    /**
     * this function to remove item from the catalog in database
     * @param name
     */
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
//---------------------------------------------------------
    //update item's info (admin)

    /**
     * this function is to update item in database
     * @param name
     * @param it
     */
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
//---------------------------------------------------------
    // user registretion

    /**
     * this function for registeration in database
     * @param register
     */
    public void register(registerd register){
        try(Connection con = DriverManager.getConnection(URL , UserName , password)){
            // Insert into client table
            String sql = "INSERT INTO client(Name,Email,Password,Phone,Address,loyalityPoints,status) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, register.getName());
            preparedStatement.setString(2, register.getMail());
            preparedStatement.setString(3, register.getPass());
            preparedStatement.setString(4, register.getPhone());
            preparedStatement.setString(5, register.getAddress());
            preparedStatement.setInt(6,0);
            preparedStatement.setString(7, "unsuspended");
            preparedStatement.executeUpdate();
            preparedStatement.close();
    
            // Get client ID
            String sql2 = "SELECT ID FROM client WHERE Email = ?";
            PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
            preparedStatement2.setString(1, register.getMail());
            ResultSet rs = preparedStatement2.executeQuery();
            int clientId = 0;
            if (rs.next()) {
                clientId = rs.getInt("ID");
            }
            preparedStatement2.close();
    
            // Insert client ID to a cart
            String sql3 = "INSERT INTO cart(clientID) VALUES (?)";
            PreparedStatement preparedStatement3 = con.prepareStatement(sql3);
            preparedStatement3.setInt(1, clientId);
            preparedStatement3.executeUpdate();
            preparedStatement3.close();
    
            System.out.println("A client has been added successfully");
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } 
    }
//---------------------------------------------------------
    //getting user's cart from (database)

    /**
     * this function to get user's cart from database
     * @param cart
     * @param reg
     */
    public void getCart(Cart cart , registerd reg){
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            String sql = "select * from Items where Items.ID in(select itemId from cartItems where cartID in (select ID from cart where cart.clientID = ?))";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,reg.getID());
            ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    if(rs.getString("Type") == "Sealed"){
                        sealedItem sealed = 
                        new sealedItem(rs.getString("Name"),
                        rs.getString("Brand"), 
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getFloat("price"), 
                        rs.getFloat("discount"), 
                        rs.getInt("Quantity"),
                        rs.getInt("ID"));
                        cart.returningArr().add(sealed);
                    }
                    else{
                        LooseItem Loose = 
                        new LooseItem(rs.getString("Name"),
                        rs.getString("Brand"), 
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getFloat("price"), 
                        rs.getFloat("discount"), 
                        rs.getFloat("Quantity"),
                        rs.getInt("ID"));   
                        cart.returningArr().add(Loose);    
                    }
                }
            // Close connection and statement
            preparedStatement.close();
            con.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
//---------------------------------------------------------

    /**
     * this function used in user logging in
     * @param mail
     * @param pass
     * @return registerd
     */
    public registerd clientLogin(String mail, String pass){
        registerd reg;
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            Statement stmt = con.createStatement();
            String sel = "select * from client";
            ResultSet rs= stmt.executeQuery(sel);
                while(rs.next()){
                    if(rs.getString("Email").trim().equals(mail.trim()) && rs.getString("Password").trim().equals(pass)){
                        reg = new registerd(
                        rs.getString("Email"), 
                        rs.getString("Name"),
                        rs.getString("Password"),
                        rs.getString("Address"), 
                        rs.getString("Phone"));
                        int id = rs.getInt("ID");
                        if(rs.getString("status").trim().toLowerCase().equals("unsuspended".trim().toLowerCase()))
                            reg.unSuspend();
                        else
                            reg.suspend();
                        reg.setID(id);
                        reg.setPoints(rs.getInt("loyalityPoints"));
                        Statement stmt2 = con.createStatement();
                        String sql2 = "select ID from cart where clientID = ?";
                        PreparedStatement preparedStatement = con.prepareStatement(sql2);
                        preparedStatement.setInt(1, id);
                        ResultSet rs2 = preparedStatement.executeQuery();
                        if (rs2.next()) {
                            reg.cart.setID(rs2.getInt("ID"));
                        }
                        con.close();
                        System.out.println("Welcome" +" "+ reg.getName());
                        return reg; 
                    }
                }
                con.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
        System.out.println("Invalid email or password please check them and try again");
        reg = new registerd(null,null, null, null, null);
        return reg;
    }
//---------------------------------------------------------

    /**
     * this function used in admin's logging in
     * @param mail
     * @param pass
     * @return
     */
    public boolean adminLogin(String mail, String pass){
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            Statement stmt = con.createStatement();
            String sel = "select * from admin";
            ResultSet rs= stmt.executeQuery(sel);
                while(rs.next()){
                    if(rs.getString("Email").trim().equals(mail.trim()) && rs.getString("Password").trim().equals(pass)){
                        System.out.println("Welcome" +" "+ rs.getString("Name"));
                        con.close();
                        return true; 
                    }
                }
                con.close();    
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
        System.out.println("Invalid email or password please check them and try again");
        return false;
    }
//---------------------------------------------------------

    /**
     * this function to add item in cart in database
     * @param item
     * @param cart
     */
    public void addToCart(Item item , Cart cart){
        try(Connection con = DriverManager.getConnection(URL , UserName , password)){
            // Insert into client table
            String sql = "INSERT INTO cartItems(cartID,itemId) VALUES (?,?)";
            System.out.println(cart.getID());
            System.out.println(item.getID());
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, cart.getID());
            preparedStatement.setInt(2, item.getID());
            preparedStatement.executeUpdate();
            con.close();
            System.out.println("An item had been added to ur cart successfully");
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } 
    }
//---------------------------------------------------------

    /**
     * this function to remove item from cart in database
     * @param item
     */
    public void removeFromCart(Item item){
        try(Connection con = DriverManager.getConnection(URL , UserName , password)){
            // Insert into client table
            String sql = "delete from cartItems where itemId = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, item.getID());
            preparedStatement.executeUpdate();
            System.out.println("An item had been removed from ur cart successfully");
            con.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } 
    }
//---------------------------------------------------------

    /**
     * this function to order
     * @param order
     * @param reg
     */
    public void Order(Order order , registerd reg){
        try(Connection con = DriverManager.getConnection(URL , UserName , password)){
            // Insert into orders table
            String sql = "insert into Orders (addressShipment , paymentMethod , clientID , Price) values (? , ? , ? , ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, order.get_address());
            preparedStatement.setString(2, order.payment());
            preparedStatement.setInt(3, reg.getID());
            preparedStatement.setDouble(4, order.get_total_price());
            updateStats(order.get_total_price());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            //getting order id
            String sql2 = "select ID from Orders where clientID = ?";
            PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
            preparedStatement2.setInt(1, reg.getID());
            ResultSet rs= preparedStatement2.executeQuery();
            int orderID = 0;
            while (rs.next()) {
                orderID = rs.getInt("ID");
            }
            preparedStatement2.close();
            //inserting into order itmes
            for(Item item : order.getItems()){
                String sql3 = "insert into orderItems (orderID , itemID) values (? , ?)";
                PreparedStatement preparedStatement3 = con.prepareStatement(sql3);
                preparedStatement3.setInt(1, orderID);
                preparedStatement3.setInt(2, item.getID());
                preparedStatement3.executeUpdate();
                preparedStatement3.close();
            }
            //empty the cart
            String sql3 = "delete from cartItems where cartID = ?";
            PreparedStatement preparedStatement3 = con.prepareStatement(sql3);
            preparedStatement3.setInt(1, reg.cart.getID());
            preparedStatement3.executeUpdate();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } 
    }
//---------------------------------------------------------

    /**
     * this function to get all orders done
     * @param reg
     * @param cart
     */
    public void gettingAllOrders(registerd reg, Cart cart) {
    try (Connection con = DriverManager.getConnection(URL, UserName, password)) {
        // getting orders info
        String sql2 = "(select ID from Orders where clientID = ?)";
        try (PreparedStatement preparedStatement2 = con.prepareStatement(sql2)) {
            preparedStatement2.setInt(1, reg.getID());
            try (ResultSet rs2 = preparedStatement2.executeQuery()) {
                ArrayList<Integer> ordersID = new ArrayList<>();
                while (rs2.next()) {
                    ordersID.add(rs2.getInt("ID"));
                }
                ArrayList<sealedItem> sealed;
                ArrayList<LooseItem> Loose;
                for (Integer ord : ordersID) {
                    sql2 = "(select * from Items where ID in (select itemID from orderItems where orderID = ?))";
                    try (PreparedStatement preparedStatement3 = con.prepareStatement(sql2)) {
                        preparedStatement3.setInt(1, ord);
                        try (ResultSet rs3 = preparedStatement3.executeQuery()) {
                            sealed = new ArrayList<>();
                            Loose = new ArrayList<>();
                            while (rs3.next()) {
                                if (rs3.getString("Type").trim().equals("Sealed".trim())) {
                                    sealedItem se = new sealedItem(rs3.getString("Name"),
                                            rs3.getString("Brand"),
                                            rs3.getString("category"),
                                            rs3.getString("description"),
                                            rs3.getFloat("price"),
                                            rs3.getFloat("discount"),
                                            rs3.getInt("Quantity"),
                                            rs3.getInt("ID"));
                                    sealed.add(se);
                                } else {
                                    LooseItem Los = new LooseItem(rs3.getString("Name"),
                                            rs3.getString("Brand"),
                                            rs3.getString("category"),
                                            rs3.getString("description"),
                                            rs3.getFloat("price"),
                                            rs3.getFloat("discount"),
                                            rs3.getFloat("Quantity"),
                                            rs3.getInt("ID"));
                                    Loose.add(Los);
                                }
                            }
                        }
                    }
                    String sql3 = "select ID,addressShipment, paymentMethod, Price from orders where ID = (?)";
                    try (PreparedStatement preparedStatement4 = con.prepareStatement(sql3)) {
                        preparedStatement4.setInt(1, ord);
                        try (ResultSet rs4 = preparedStatement4.executeQuery()) {
                            while (rs4.next()) {
                                Cart crt = new Cart();
                                Order order = new Order(crt);
                                if (rs4.getString("paymentMethod").trim().equals("E-wallet".trim())) {
                                    Ewallet ewallet = new Ewallet();
                                    order.setPayment(ewallet);
                                } else {
                                    Cash cash = new Cash();
                                    order.setPayment(cash);
                                }
                                order.setID(rs4.getInt("ID"));
                                order.setPrice(rs4.getDouble("Price"));
                                order.set_address(rs4.getString("addressShipment"));
                                for (Item it : sealed) {
                                    order.getItems().add(it);
                                }
                                for (Item it : Loose) {
                                    order.getItems().add(it);
                                }
                                reg.getOrders().add(order);
                                sealed.clear();
                                Loose.clear();
                            }
                        }
                    }
                }
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } 
}
//---------------------------------------------------------

    /**
     * this function to get all data of clients
     * @return list of clients
     */
    public ArrayList<registerd> gettingClientsData(){
        ArrayList<registerd> reg = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            Statement stmt = con.createStatement();
            String sel = "select * from client";
            ResultSet rs= stmt.executeQuery(sel);
                while(rs.next()){
                    if(rs.getString("status").trim().toLowerCase().equals("Unsuspended".trim().toLowerCase())){
                        registerd registerdUser = new registerd(rs.getString("Email"), rs.getString("Name"), rs.getString("Password"), rs.getString("Address"), rs.getString("Phone"));
                        registerdUser.setID(rs.getInt("ID"));
                        registerdUser.setPoints(rs.getInt("loyalityPoints"));
                        registerdUser.unSuspend();
                        reg.add(registerdUser);
                    }
                    else{
                        registerd registerdUser = new registerd(rs.getString("Email"), rs.getString("Name"), rs.getString("Password"), rs.getString("Address"), rs.getString("Phone"));
                        registerdUser.setID(rs.getInt("ID"));
                        registerdUser.setPoints(rs.getInt("loyalityPoints"));
                        registerdUser.suspend();
                        reg.add(registerdUser);
                    }
                }
                con.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
        return reg;
    }
//---------------------------------------------------------

    /**
     * this function print all user details
     */
    public void printAllUsers(){
        ArrayList<registerd> reg = new ArrayList<>();
        reg = gettingClientsData();
        System.out.println("-------------------------------------------");
        for(registerd r : reg){
            System.out.println("User's name is: "+r.getName());
            System.out.println("User's ID is: "+r.getID());
            System.out.println("User's name Mail: "+r.getMail());
            System.out.println("User's name Address: "+r.getAddress());
            System.out.println("User's name Phone: "+r.getPhone());
            System.out.println("User's Status is: "+r.getStatus());
            System.out.println("User's Points are: "+r.getPoints());
            System.out.println("-------------------------------------------");
        }
    }
//---------------------------------------------------------

    /**
     * this function to change the status of users
     * @param newStatus
     * @param reg
     */
    public void ChangeStatus(String newStatus, registerd reg){
        try{
            if(!newStatus.trim().toLowerCase().equals(reg.getStatus().trim().toLowerCase())){
                Connection con = DriverManager.getConnection(URL , UserName , password);
                String sqlUpdate = "UPDATE client SET status=? WHERE ID=?";
                PreparedStatement pstmt = con.prepareStatement(sqlUpdate);
                pstmt.setString(1, newStatus);
                pstmt.setInt(2, reg.getID());
                pstmt.executeUpdate();
                con.close();
                System.out.println("An user has been "+newStatus+" updated successfully");
            }
            else{
                System.out.println("this user is already "+newStatus);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
//---------------------------------------------------------

    /**
     * this function to add points to client
     * @param amount
     * @param reg
     */
    public void addPoints(int amount , registerd reg){
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            String sqlUpdate = "UPDATE client SET loyalityPoints = ? WHERE ID=?";
            PreparedStatement pstmt = con.prepareStatement(sqlUpdate);
            int oldPoints = reg.getPoints();
            pstmt.setInt(1, oldPoints+amount);
            pstmt.setInt(2, reg.getID());
            pstmt.executeUpdate();
            con.close();
            System.out.println("Loyality points had been added successfully");
        } catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
//---------------------------------------------------------

    /**
     * this function to update the status of client
     * @param price
     */
    public void updateStats(double price) {
    try (Connection con = DriverManager.getConnection(URL, UserName, password)) {
        PreparedStatement selectStmt = con.prepareStatement("SELECT TotalPrice FROM state WHERE monthName=?");
        selectStmt.setString(1, "may");
        ResultSet rs = selectStmt.executeQuery();
        if (rs.next()) {
            double oldPrice = rs.getDouble("TotalPrice");
            double newPrice = oldPrice + price;
            PreparedStatement updateStmt = con.prepareStatement("UPDATE state SET TotalPrice=? WHERE monthName=?");
            updateStmt.setDouble(1, newPrice);
            updateStmt.setString(2, "may");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
//---------------------------------------------------------

    /**
     * this function to display the stats of user
     */
    public void displayStats() {
        try (Connection con = DriverManager.getConnection(URL, UserName, password)) {
            Statement stmt = con.createStatement();
            String sel = "select * from stat";
            ResultSet rs= stmt.executeQuery(sel);
            System.out.println("------------------------------------------");
                while(rs.next()){
                    System.out.println("Total price :" +rs.getDouble("TotalPrice") + " in month: "+ rs.getString("monthName"));
                    System.out.println("------------------------------------------");
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//---------------------------------------------------------

    /**
     * this function to add vouchers
     * @param voucher
     */
    public void setVoucher(Voucher voucher) {
        try (Connection con = DriverManager.getConnection(URL, UserName, password);
            PreparedStatement preparedStatement = con.prepareStatement("insert into Voucher (value ) values (?)")) {
            preparedStatement.setDouble(1, voucher.getValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle the SQL exception here
            e.printStackTrace();
        }
    }
//---------------------------------------------------------

    /**
     * this to get vouchers from database
     * @return list of vouchers
     */
    public ArrayList<Voucher> getVouchers(){
        ArrayList<Voucher> vouchers = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            Statement stmt = con.createStatement();
            String sel = "select * from Voucher";
            ResultSet rs= stmt.executeQuery(sel);
                while(rs.next()){
                    Integer client = rs.getInt("clientID");
                    if(client == 0){
                        Voucher voucher = new Voucher();
                        voucher.setID(rs.getInt("ID"));
                        voucher.setValue(rs.getDouble("Value"));
                        vouchers.add(voucher);
                    }
                }
                con.close();
            return vouchers;
        } catch(Exception e){
        } 
        return vouchers;
    }
//---------------------------------------------------------

    /**
     * this function to print all vouchers
     */
    public void printAllAvilableVouchers(){
        ArrayList<Voucher> vouchers = getVouchers();
        System.out.println("-------------------------------------");
        for(Voucher voucher: vouchers){
            System.out.println("value:"+voucher.getValue());
            System.out.println("ID:"+voucher.getID());
            System.out.println("-------------------------------------");
        }
    }
//---------------------------------------------------------

    /**
     * this function to buy vouchers
     * @param reg
     * @param ID
     */
    public void buyAvoucher(registerd reg,int ID){
        try (Connection con = DriverManager.getConnection(URL, UserName, password);
            PreparedStatement preparedStatement = con.prepareStatement("update Voucher set clientID = ? where ID =?")) {
            preparedStatement.setInt(1, reg.getID());
            preparedStatement.setInt(2, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle the SQL exception here
            e.printStackTrace();
        }
    }
//---------------------------------------------------------

    /**
     * this function to get user vouchers
     * @param reg
     * @return
     */
    public ArrayList<Voucher> getUserVouchers(registerd reg){
        ArrayList<Voucher> vouchers = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(URL , UserName , password);
            Statement stmt = con.createStatement();
            String sel = "select * from Voucher";
            ResultSet rs= stmt.executeQuery(sel);
                while(rs.next()){
                    Integer client = rs.getInt("clientID");
                    if(client == reg.getID()){
                        Voucher voucher = new Voucher();
                        voucher.setID(rs.getInt("ID"));
                        voucher.setValue(rs.getDouble("Value"));
                        vouchers.add(voucher);
                    }
                }
                con.close();
            return vouchers;
        } catch(Exception e){
        } 
        return vouchers;
    }
//--------------------------------------------------------------------------------

    /**
     * this function to delete voucher
     * @param id
     */

    public void deleteVoucher(int id){
        try(Connection con = DriverManager.getConnection(URL , UserName , password)){
            // Insert into client table
            String sql = "delete from Voucher where ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            con.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } 
    }
}


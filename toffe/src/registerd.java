public class registerd extends client{
    private String Email;
    private String UserName;
    private String Password;
    private String address;
    private int phone;

    String []names= {"youssef","assem" ,"ziad"};
    String []pass= {"123","456" ,"678"};


    public boolean login(String name,String password){
        for(int i=0;i<3;i++){
            if(name==names[i]&&password==pass[i]){
                return true;
            }
        }
        return false;
    }
    
}

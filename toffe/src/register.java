
import java.util.Scanner;


public class register{
   
   
    private String Email;
    private String UserName;
    private String Password;
    private String address;
    private String phone;

    private Authentication auth=new Authentication();
    private OTP otp = new OTP();
   
    
//____________________________________________________________

    public void setEmail(String email){
       
        if(auth.isValidmail(email)==true){
            int random_no = (int) (Math.random()*900)+100;
            System.out.println("otp: " + random_no);
    
            
            System.out.print("Enter the otp:");
            Scanner myObj = new Scanner(System.in);  
            int check = myObj.nextInt();
    
            if(check==random_no){
                otp.confirmed(email);
            }
            
            if(otp.otp_is_done(email)){
                Email=email;
            }
            else{
                System.out.println("Wrong otp confirmation" );
            }
            
        }
        else{
            System.out.println("Invalid Email!!");
        }

    }

    public void setUserName(String name){
        if(auth.isValidUserName(name)==true){
            UserName = name;
        }   
        else{
            System.out.println("The username must be from 6 to 30 characters and didn't start by digit");
        }
    }
    public void setPassword(String password){
        if(auth.isStrongPassword(password)==true){
            this.Password=password;
        }   
        else{
            System.out.println("Weak Password");
        }
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setPhone(String phone){
        if(auth.isValidPhone(phone)==true){
            this.phone=phone;
        }
        else{
            System.out.println("Invalid Phone!! Enter digits only");
        }
    }

//_____________________________________________________________

    public String getUserName(){
        return UserName;
    }
    public String getEmail(){
        return Email;
    }
    public String getpassword(){
        return Password;
    }
    public String getaddress(){
        return address;
    }
    public String getphone(){
        return phone;
    }
    
}
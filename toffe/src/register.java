
import java.util.Scanner;

//---------------------------------------------------------
public class register{
    private Authentication auth=new Authentication();
    private OTP otp = new OTP();

//---------------------------------------------------------
public boolean check(String mail,String name,String pass , String add, String phone){
    if(setEmail(mail)&&setPassword(pass)&&setUserName(name)&&setPhone(phone)){
        registerd reg = new registerd(mail,name , pass,add,phone);
        db database = new db();
        database.register(reg);
        return true;
    }
    return false;
}
//---------------------------------------------------------
    public boolean setEmail(String email){
        if(auth.isValidmail(email)){
            int random_no = (int) (Math.random()*900)+100;
            System.out.println("otp: " + random_no);
            System.out.print("Enter the otp:");
            Scanner myObj = new Scanner(System.in);
                int check = myObj.nextInt();

                if(check==random_no){
                    otp.confirmed(email);
                }
            if(otp.otp_is_done(email)){
                return true;
            }
            else{
                System.out.println("Wrong otp confirmation" );
                return false;
            }
            
        }
        else{
            System.out.println("Invalid Email here u are the schema\n example@example.examples");
            return false;
        }
    }
//---------------------------------------------------------
    public boolean setUserName(String name){
        if(auth.isValidUserName(name)){
            return true;
        }   
        else
            System.out.println("The username must be from 6 to 30 characters and not starting by digit");
        return false;
    }
//---------------------------------------------------------
    public boolean setPassword(String password){
        if(auth.isStrongPassword(password)){
            return true;
        }   
        else
            System.out.println("Weak Password password must has\n1-Upper case latter\n2-lower case latter\n3-symbol like $ or &\n4-digit");
        return false;
    }
//---------------------------------------------------------
    public boolean setPhone(String phone2){
        if(auth.isValidPhone(phone2)){
            return true;
        }
        else
            System.out.println("Invalid Phone phone number consits of\n 11 number and startin with 011 or 012 or 015");
        return false;
    }

//---------------------------------------------------------
}
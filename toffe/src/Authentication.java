import java.util.regex.*;

public class Authentication {
//---------------------------------------------------------
    public boolean isValidUserName(String name){
        String regex = "^[A-Za-z]\\w{5,29}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        return m.matches();
    }
//---------------------------------------------------------
    public boolean isValidPhone(String phone){
        String regex = "(011|010|012|015)\\d{8}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
//---------------------------------------------------------
    public boolean isValidmail(String mail){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);
        return m.matches();
    }
//---------------------------------------------------------
    public boolean isStrongPassword(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

}

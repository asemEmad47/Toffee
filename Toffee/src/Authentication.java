import java.util.regex.*;

/**
 *
 * this class for Authentication
 */
public class Authentication {
//---------------------------------------------------------

    /**
     * this is function check if username valid and didn't start with number
     * @param name
     * @return true or false
     */
    public boolean isValidUserName(String name){
        String regex = "^[A-Za-z]\\w{5,29}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        return m.matches();
    }
//---------------------------------------------------------

    /**
     * this function check if phone is valid and start with 01
     * @param phone
     * @return true or false
     */
    public boolean isValidPhone(String phone){
        String regex = "(011|010|012|015)\\d{8}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
//---------------------------------------------------------

    /**
     * this function check validation of email that has "@" symbol and "."
     * @param mail
     * @return true or false
     */
    public boolean isValidmail(String mail){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);
        return m.matches();
    }
//---------------------------------------------------------

    /**
     * this function check if strong password or not
     * @param password
     * @return  true or false
     */
    public boolean isStrongPassword(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

}

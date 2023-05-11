public class OTP {
    
    
    private String [] mails = new String [100]; 
    private int count=0;
    
    public boolean otp_is_done(String mail){
        for(int i=0;i<count+1;i++){
            if(mails[i]==mail){
                return true;
            }
        }
        return false;
    }

    public void confirmed(String mail){
        mails[count]=mail;
        count++;
    }

}

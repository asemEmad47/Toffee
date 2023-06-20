import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;


/**
 * this class for otp sending and checking
 */
   public class OTPsender {

       private static int check;
       private int count=0;
       private String[] mails = new String [100];
        public static void sendOTP(String email) {
            // Generate a random 6-digit OTP
            Random rand = new Random();
            int otp = rand.nextInt(900000) + 100000; // range: 100000 - 999999
            check = otp;

            // Email properties
            String host = "smtp.gmail.com";
            String username = "youssef.alethy123@gmail.com";
            String password = "trjbdrurtlkyibix";
            int port = 587;

            // Sender's and recipient's email addresses
            String from = "youssef.alethy123@gmail.com";
            String to = email;

            // Email message
            String subject = "One-Time Password (OTP)";
            String body = "Your OTP is: " + otp;

            // Setup email session and properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);

            // Authenticate sender's email
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {
                // Create email message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(body);

                // Send email message
                Transport.send(message);

                System.out.println("OTP sent to " + email);

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }

       /**
        *  this function take the input otp to check if true put the email in email array
        * @param number
        * @param email
        */
        public void checked(int number,String email){

            if(check==number){
                mails[count]=email;
                count++;
            }
            else{
                System.out.println("Wrong code");
            }

        }

       /**
        * this is a function to check if otp is done to that email
        * @param email
        * @return true or false
        */
        public boolean otp_is_done(String email){

            for(int i=0;i<count+1;i++){
                if(email==mails[i]){

                    return true;

                }

            }
            return false;
        }




}


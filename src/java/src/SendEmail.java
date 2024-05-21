 package src;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HishamR
 */

public class SendEmail 
{
    //method that will send email
    public boolean sendEmail(UserInfo userInfo)
    {
        boolean testEmail = false;
        final String fromEmail = "hishamprojecttest@gmail.com";  //Password H1234567
        final String password = "H1234567";
        
        String toEmail = userInfo.getEmail(); 
        
        
        try
        {
            Properties pr = new Properties();
            //Here you can use two functions .setProperty or .put both will do the work
            pr.setProperty("mail.smtp.host", "smtp.mail.com"); //to be checked
            pr.setProperty("mail.smtp.port", "587"); //port
            pr.setProperty("mail.smtp.auth", "true"); //authentication
            pr.setProperty("mail.smtp.starttls.enable", "true"); //TLS check
            //Next two lines to use gmail.com, prev was only for mail.com
            pr.put("mail.smtp.socketFactory.port", "587"); //SSL
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
             
             //Getting Mail Session will give us the fromEmail and passsword authentication
              Session session = Session.getInstance(pr, new Authenticator() 
             {
                @Override
                 protected PasswordAuthentication getPasswordAuthentication()
                 {
                     return new PasswordAuthentication(fromEmail, password);//to be checked was fromEmail, and password
                 }
             });
             
             //Set ur message here
             Message msg = new MimeMessage(session); //this method to provide the java mail jar
             
             msg.setRecipient(Message.RecipientType.TO, new InternetAddress(userInfo.getEmail()));//Here accessing the user email that we will send mail to
             msg.setFrom(new InternetAddress(fromEmail));
            
             msg.setSubject("Confirmation Email");
             msg.setText("Yay! You're all set up. your subscription has been confirmed. Thank you!");
            
             //to send email
             Transport.send(msg);
             testEmail = true;
             
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        return testEmail;
    }
    
}

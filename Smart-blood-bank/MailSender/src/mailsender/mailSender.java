/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsender;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author asus
 */
public class mailSender {

    public static void sendMail(String recepient, String name) throws AddressException, MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountGmail = "aliluch.loutfi@gmail.com";
        String password = "aliluchloutfi123";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(myAccountGmail, password);
            }
        });
        
        Message message = prepareMessage(session, myAccountGmail, recepient, name);
        
        Transport.send(message);
        
        System.out.println("Message sent successfully");

    }

    private static Message prepareMessage(Session session, String myAccountGmail, String recepient, String name) throws AddressException, MessagingException {
       Message message = new MimeMessage(session);
       message.setFrom(new InternetAddress(myAccountGmail));
       message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
       message.setSubject("Blood Donation : Save a life !");
       message.setText("Hello savior, the hospital "+name+" is facing a shortage of blood, in need of your blood donation as soon as possible and thank you in advance.");
       return message;
    }

}

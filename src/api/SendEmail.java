package api;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.sun.mail.smtp.SMTPTransport;

import javax.activation.*;

public class SendEmail {
	private static final String SMTP_SERVER = "smtp.office365.com";
    private static final String USERNAME = "danalves24@outlook.com";
    private static final String PASSWORD = "Sani1678";

    private static final String EMAIL_FROM = "danalves24@outlook.com";
    private static final String EMAIL_TO = "danralves1@gmail.com, danalves24@outlook.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    public static void send() {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
        
            // from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

            // to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

            // cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

            // subject
            msg.setSubject(EMAIL_SUBJECT);
            
            // content 
            msg.setText(EMAIL_TEXT);
            
            msg.setSentDate(new Date());

            // Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            
            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
            
            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
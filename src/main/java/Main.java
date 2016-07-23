/**
 * Created by roman on 24.07.2016.
 */

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Main {

    final static String ADRESS = "";
    final static String PASSWORD = "";
    final static String GOAL_ADRESS = "";
    final static String SUBJECT = "Subject";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(ADRESS, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(GOAL_ADRESS));
            message.setSubject(SUBJECT);
            message.setText(setMessage());
            message.setContent(addImage());

            Transport.send(message);
        } catch (MessagingException e) {
            //do nothing
        }
    }

    private static MimeMultipart addImage() throws MessagingException {
        MimeMultipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource dataSource = new FileDataSource(chooseImage());
        messageBodyPart.setDataHandler(new DataHandler(dataSource));
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }

    private static String chooseImage() {
        return "";
    } //D:/images/.......jpg

    private static String setMessage() {
        return "message";
    }
}
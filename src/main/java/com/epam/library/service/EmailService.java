package com.epam.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailService {

    private static final Logger Log = LoggerFactory.getLogger(EmailService.class);

    private static EmailService instance;
    private Properties properties;

    private final static String CUSTOMER_NAME = "TrainEpam2021@gmail.com";
    private final static String CUSTOMER_PASSWORD = "training2021";

    private EmailService(){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        properties = prop;
        instance = this;
    }

    public static EmailService getInstance(){
        if (instance==null){
            instance = new EmailService();
        }
        return instance;
    }

    public void notifyCustomerRegistration(String email, int id) throws MessagingException {
        Log.info("Notifying customer registration");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CUSTOMER_NAME, CUSTOMER_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("from@library.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("You are successfully registered");

        String msg = "This is my first email using JavaMailer. New customer id is:" + id;

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}

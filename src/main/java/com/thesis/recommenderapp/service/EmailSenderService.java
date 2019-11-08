package com.thesis.recommenderapp.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.service.exceptions.EmailCouldNotBeSentException;

@Service
public class EmailSenderService {

    public void sendInvite(String email, User sender, String baseUrl) {
        try {
            Properties prop = setUpProperties();
            Session session = setUpSession(prop);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("recommenderapp2@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Invitation");
            StringBuilder text = createMessageText(email, sender, baseUrl);
            message.setText(text.toString());
            Transport.send(message);
        } catch (MessagingException e) {
            throw new EmailCouldNotBeSentException("Email could not be sent.");
        }
    }

    private StringBuilder createMessageText(String email, User sender, String baseUrl) {
        StringBuilder text = new StringBuilder("Dear ");
        text.append(email.split("@")[0]);
        text.append(",");
        text.append("\n\n Your friend ");
        text.append(sender.getUserName());
        text.append(" has invited you to join them on RecommenderApp! \n Click the link below to join. \n");
        text.append(baseUrl + "/register?friendId=" + sender.getId());
        return text;
    }

    private Session setUpSession(Properties prop) {
        return Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("recommenderapp2", "Almafa123");
            }
        });
    }

    private Properties setUpProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        return prop;
    }

}

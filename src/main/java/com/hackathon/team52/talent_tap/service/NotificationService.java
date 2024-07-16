package com.hackathon.team52.talent_tap.service;

import com.hackathon.team52.talent_tap.entity.TalentInfo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
     private JavaMailSender mailSender;

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendMessageToExternalApp(String firstName, String round, String status,String email){

        String messageText = String.format("Greetings from UPS,\n\nDear %s,\n\nYou have been %s in the %s round discussion. Please feel free to reach out for further details.\n\nContact:1800-22-7171", firstName, status, round);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("pandidurai.ece30@gmail.com");
            message.setTo(email);
            message.setText(messageText);
            message.setSubject("Interview Status");
            mailSender.send(message);
            System.out.println("Mail Send...");
        }


    public void sendWhatsApp(String firstName,String round, String status,String phone){
        String messageText = String.format("Greetings from UPS,\n\nDear %s,\n\nYou have been %s in the %s round discussion. Please feel free to reach out for further details.\n\nContact:1800-22-7171", firstName, status, round);

        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+919500376088"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                messageText).create();

        System.out.println("Whatsapp Message Send...");
    }
}

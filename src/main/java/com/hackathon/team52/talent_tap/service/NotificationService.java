package com.hackathon.team52.talent_tap.service;

import com.hackathon.team52.talent_tap.entity.TalentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
     private JavaMailSender mailSender;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendMessageToExternalApp(String round, String status){

        String message1 = "Dear Candidate, You have been"+status+"in"+round+"discussion";


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("pandidurai.ece30@gmail.com");
            message.setTo("pandi.sundaram28@gmail.om");
            message.setText(message1);
            message.setSubject("interviewResult");
            mailSender.send(message);
            System.out.println("Mail Send...");


        }



}

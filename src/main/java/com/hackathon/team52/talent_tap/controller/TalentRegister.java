package com.hackathon.team52.talent_tap.controller;


import com.hackathon.team52.talent_tap.dto.Customer;
import com.hackathon.team52.talent_tap.dto.EditForm;
import com.hackathon.team52.talent_tap.dto.RegisterForm;
import com.hackathon.team52.talent_tap.entity.TalentInfo;
import com.hackathon.team52.talent_tap.service.NotificationService;
import com.hackathon.team52.talent_tap.service.TalentRegisterService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/talent")
public class TalentRegister {


    @Autowired
    TalentRegisterService talentRegisterService;

    @Autowired
    NotificationService notificationService;


    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC08ea0c73c68afadbfa84e908b04ec4c3";
    public static final String AUTH_TOKEN = "0a1dfbc3b0e8fd506b5bb0ab06b0bba7";

    @PostMapping("/loginuser")
    public Boolean loginUser(@RequestBody Customer customer) {

        return talentRegisterService.validateUserData(customer);

    }

    @GetMapping("/status")
    public String test() {

        return "success";
    }

    @PostMapping("/register")
    public ResponseEntity saveTalentForm(@RequestBody RegisterForm registerForm) {
        try {
            talentRegisterService.saveTalentRegisteration(registerForm);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @GetMapping("/details")
    public List<TalentInfo> getTalentInfo() {

        List<TalentInfo> talentInfoList = new ArrayList<>();

        try {
            talentInfoList = talentRegisterService.getAllTalentDetails();
        } catch (Exception e) {
            e.getMessage();
        }

        return talentInfoList;
    }


    @PutMapping("/details")
    public List<TalentInfo> updateTalentInfo(@RequestBody List<EditForm> editFormList) {
        List<TalentInfo> talentInfo = null;

        try {
            talentInfo = talentRegisterService.updateTalentDetails(editFormList);
        } catch (Exception e) {
            e.getMessage();
        }

        return talentInfo;
    }

    @GetMapping("/sendWhatsApp")
    public String sendWhatsApp(@RequestParam(value = "to") String to, @RequestParam(value = "message") String message1) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:+919500376088"),
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                    "Your appointment is coming up on July 21 at 3PM").create();

            return "Message sent successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

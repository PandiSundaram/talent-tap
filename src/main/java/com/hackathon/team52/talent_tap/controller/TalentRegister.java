package com.hackathon.team52.talent_tap.controller;


import com.hackathon.team52.talent_tap.dto.Customer;
import com.hackathon.team52.talent_tap.dto.EditForm;
import com.hackathon.team52.talent_tap.dto.RegisterForm;
import com.hackathon.team52.talent_tap.entity.TalentInfo;
import com.hackathon.team52.talent_tap.service.NotificationService;
import com.hackathon.team52.talent_tap.service.TalentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/talent")
public class TalentRegister {


    @Autowired
    TalentRegisterService talentRegisterService;

    @Autowired
    NotificationService notificationService;

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

    @GetMapping("/email")
    public String test1() {
        notificationService.sendMessageToExternalApp("level1", "selected");

        return "success";
    }


}

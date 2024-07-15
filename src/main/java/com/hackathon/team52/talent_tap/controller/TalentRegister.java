package com.hackathon.team52.talent_tap.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talent")
public class TalentRegister {


    @GetMapping("/status")
    public String test(){

        return "success";
    }
}

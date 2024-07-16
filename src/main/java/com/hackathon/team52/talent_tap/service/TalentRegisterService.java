package com.hackathon.team52.talent_tap.service;


import com.hackathon.team52.talent_tap.dto.Customer;
import com.hackathon.team52.talent_tap.dto.EditForm;
import com.hackathon.team52.talent_tap.dto.RegisterForm;
import com.hackathon.team52.talent_tap.entity.TalentInfo;
import com.hackathon.team52.talent_tap.repository.TalentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TalentRegisterService {


    private static final Logger log = LoggerFactory.getLogger(TalentRegisterService.class);
    @Autowired
    TalentRepo talentRepo;

    @Autowired
    NotificationService notificationService;


    private final List<Customer> customerList = Arrays.asList(new Customer("pandi","pandi123"),new Customer("boojith","boojith123"),new Customer("nivetha","nivetha123"));


    private Long candidateId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String referrerId;
    private String jobId;
    private String resumeName;
    private String status;
    private String rounds;

    private List<TalentInfo> talentInfos = null;



    public void saveTalentRegisteration(RegisterForm registerForm) {

        TalentInfo talentInfo = new TalentInfo();
        try {

            talentInfo.setFirstName(registerForm.getFirstName());
            talentInfo.setLastName(registerForm.getLastName());
            talentInfo.setEmail(registerForm.getEmail());
            talentInfo.setPhoneNumber(registerForm.getPhoneNumber());
            talentInfo.setReferrerId(registerForm.getReferrerId());
            talentInfo.setJobId(registerForm.getJobId());
            talentInfo.setResumeName(registerForm.getResumeName());
            talentInfo.setStatus("APPLIED");

            talentRepo.save(talentInfo);

        } catch (Exception e) {
            e.getMessage();
        }

    }

    public Boolean validateUserData(Customer customer){

         for(Customer cust: customerList){

             if(cust.getUserName().equalsIgnoreCase(customer.getUserName())&& cust.getPassWord().equalsIgnoreCase(customer.getPassWord())){
                 return true;
             }

         }

         return false;
    }


    public List<TalentInfo> getAllTalentDetails() {

        List<TalentInfo> talentInfoList = new ArrayList<>();
        try {
            talentInfoList = talentRepo.findAll();

        } catch (Exception e) {
            e.getMessage();
        }

        return talentInfoList;
    }


    public List<TalentInfo> updateTalentDetails(List<EditForm> editFormList) {


        List<TalentInfo> updateInfo = new ArrayList<>();
        try {

            log.info("i am here"+ editFormList.get(0).toString() );

            for (int i=0; i<editFormList.size(); i++) {
                log.info("inside loop");
                Optional<TalentInfo> talentInfo = talentRepo.findById(editFormList.get(i).getCandidateId());

                log.info("talentInfo"+ talentInfo.toString());

                talentInfo.get().setRounds(editFormList.get(i).getRounds());
                talentInfo.get().setStatus(editFormList.get(i).getStatus());
                updateInfo.add(talentInfo.get());
                //notificationService.sendMessageToExternalApp(editFormList.get(i).getRounds(),editFormList.get(i).getStatus());

            }


            updateInfo = talentRepo.saveAll(updateInfo);




        } catch (Exception e) {
            e.getMessage();


        }
        return updateInfo;
    }

    public List<TalentInfo> persistDefaultTalentInfo(){
       return Arrays.asList(new TalentInfo(10l,"pandi","sundaram","pandi@ups.com","9787675667","87877","1234","pandi.pdf","APPLIED",null));
    }




}

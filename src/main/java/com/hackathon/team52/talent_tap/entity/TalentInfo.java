package com.hackathon.team52.talent_tap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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




}

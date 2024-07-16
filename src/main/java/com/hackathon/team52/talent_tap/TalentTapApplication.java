package com.hackathon.team52.talent_tap;

import com.hackathon.team52.talent_tap.dto.Customer;
import com.hackathon.team52.talent_tap.entity.TalentInfo;
import com.hackathon.team52.talent_tap.service.TalentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TalentTapApplication {

	@Autowired
	TalentRegisterService talentRegisterService;
	public static void main(String[] args) {
		SpringApplication.run(TalentTapApplication.class, args);
	}

	@Bean
	public List<TalentInfo> persistDefaultTalent(){
		List<TalentInfo> talentInfos = talentRegisterService.persistDefaultTalentInfo();
		System.out.println(talentInfos.get(0).toString());
		return talentInfos;
	}


}

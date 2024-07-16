package com.hackathon.team52.talent_tap.repository;

import com.hackathon.team52.talent_tap.entity.TalentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentRepo extends JpaRepository<TalentInfo,Long> {
}

package com.summonerswars.SW.repository;

import com.summonerswars.SW.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Skill Repository
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

}
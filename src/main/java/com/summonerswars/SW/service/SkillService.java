package com.summonerswars.SW.service;

import com.summonerswars.SW.exception.SkillNotFound;
import com.summonerswars.SW.exception.SummonerNotFound;
import com.summonerswars.SW.model.Skill;
import com.summonerswars.SW.model.Summoner;
import com.summonerswars.SW.repository.SkillRepository;
import com.summonerswars.SW.repository.SummonerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final SummonerRepository summonerRepository;

    public SkillService(SkillRepository skillRepository, SummonerRepository summonerRepository) {
        this.skillRepository = skillRepository;
        this.summonerRepository = summonerRepository;
    }


    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public <S extends Skill> S save(S newSkill) {
        return skillRepository.save(newSkill);
    }


    public Skill findById(Long id) {
        return skillRepository.findById(id).orElseThrow(SkillNotFound::new); //Return Skill with matching id while verifying if id exists
    }

    public Skill create(Skill newSkill, Long id) {
        Summoner summoner = summonerRepository.findById(id).orElseThrow(SummonerNotFound::new);

        if(summoner.getSkill().size()<4) {
            newSkill.setSummoner(summoner);
            skillRepository.save(newSkill);
            return newSkill;
        }
        else throw new SummonerNotFound("Summoner has max skills");
    }


    public Skill update(Long id, String name, String active) {
        Skill skill = this.findById(id); //Get Skill with matching id
        skill.setId(id);
        skill.setName(name);
        skill.setActive(active);

        return skillRepository.save(skill);
    }

    public void deleteById(Long id) {
        this.findById(id); //Check if there is a skill with matching id
        skillRepository.deleteById(id);

    }
}

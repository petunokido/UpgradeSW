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

    //Method to find a skill by id
    public Skill findById(Long id) {
        return skillRepository.findById(id).orElseThrow(SkillNotFound::new); //Return Skill with matching id while verifying if id exists
    }
    //Method to create skill
    public Skill create(Skill newSkill, Long id) {
        //Find if Summoner exists by id if not throw error
        Summoner summoner = summonerRepository.findById(id).orElseThrow(SummonerNotFound::new);
        //if condition to limit nr of skills add to the champion
        if(summoner.getSkill().size()<4) {
            newSkill.setSummoner(summoner);
            skillRepository.save(newSkill);
            return newSkill;
        }
        else throw new SummonerNotFound("Summoner has max skills");
    }

    //Method to update skill
    public Skill update(Long id, String name, String active) {
        Skill skill = this.findById(id); //Get Skill with matching id
        skill.setId(id);
        skill.setName(name);
        skill.setActive(active);

        return skillRepository.save(skill);
    }
    //delete a Skill
    public void deleteById(Long id) {
        this.findById(id); //Check if there is a skill with matching id
        skillRepository.deleteById(id);

    }
}

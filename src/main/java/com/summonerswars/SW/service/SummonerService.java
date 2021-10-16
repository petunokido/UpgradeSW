package com.summonerswars.SW.service;

import com.summonerswars.SW.exception.SummonerNotFound;
import com.summonerswars.SW.model.Skill;
import com.summonerswars.SW.model.Summoner;
import com.summonerswars.SW.model.Tier;
import com.summonerswars.SW.repository.SkillRepository;
import com.summonerswars.SW.repository.SummonerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummonerService {

    private final SummonerRepository summonerRepository;
    private final SkillRepository skillRepository;

    public SummonerService(SummonerRepository summonerRepository, SkillRepository skillRepository) {
        this.summonerRepository = summonerRepository;
        this.skillRepository = skillRepository;
    }


    public List<Summoner> findAll() {
        return summonerRepository.findAll();
    }


    public void deleteById(Long id) {
        Summoner summoner = this.findById(id);  // Get Summoner matching id

        for (Skill skill : summoner.getSkill())      //Delete all Skill in Summoner
        {
            skillRepository.deleteById(skill.getId());
        }
        summonerRepository.deleteById(id);
    }

    public <S extends Summoner> S save(S summoner) {
        return summonerRepository.save(summoner);
    }


    public Summoner findById(Long id) {
        return summonerRepository.findById(id).orElseThrow(SummonerNotFound::new);

    }


    public Summoner update(Long id, String name, Tier tier) {
        Summoner summoner = this.findById(id);
        summoner.setName(name);
        summoner.setTier(tier);

        return summonerRepository.save(summoner);

    }
}

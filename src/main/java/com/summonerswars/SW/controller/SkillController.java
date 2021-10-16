package com.summonerswars.SW.controller;

import com.summonerswars.SW.controller.request.SkillRequestRQ;
import com.summonerswars.SW.controller.response.SkillReturnResponse;
import com.summonerswars.SW.model.Skill;
import com.summonerswars.SW.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SkillController {

    //Relation Autowire whit Skill Service
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    //List of all Skills
    @GetMapping("/getSkill")
    public List<SkillReturnResponse> getSkill() {
        List<Skill> skills = skillService.findAll();
        List<SkillReturnResponse> skillReturnResponses = new ArrayList<>();
        for (Skill skill : skills) {
            skillReturnResponses.add(new SkillReturnResponse(
                    skill.getId(),
                    skill.getName(),
                    skill.getActive(),
                    skill.getSummoner().getId()
            ));
        }
        return skillReturnResponses;
    }

    //Search by ID a specific Skill
    @GetMapping("/getSkillById/{id}")
    public SkillReturnResponse getSkillById(Long id) {
        Skill skill = skillService.findById(id);  //Find

        return new SkillReturnResponse(
                skill.getId(),
                skill.getName(),
                skill.getActive(),
                skill.getSummoner().getId()
        );
    }

    //Create a new Skill
    @PostMapping(value = "/skills/{id}", consumes = "application/json", produces = "application/json")

    public SkillReturnResponse addSkill(@RequestBody SkillRequestRQ skillRequestRQ, @PathVariable(value = "id") Long id) //Class to request the body from Skill RequestRQ with the parameters to fullfill
    {
        Skill newSkill = Skill.builder()        //Method to get the parameters
                .name(skillRequestRQ.getName())
                .active(skillRequestRQ.getActive())
                .build();
        skillService.create(newSkill, id); //Connection to SkillService method
        SkillReturnResponse skillReturnResponse = new SkillReturnResponse();//Response to the user with the Skill data applied previously
        skillReturnResponse.setId(newSkill.getId());
        skillReturnResponse.setName(newSkill.getName());
        skillReturnResponse.setActive(newSkill.getActive());
        return skillReturnResponse;
    }

    //Update Skill
    @PutMapping(value = "/skills/{id}")
    public SkillReturnResponse updateSkill(@PathVariable(value = "id") Long id, @RequestBody SkillRequestRQ skillRequestRQ) {
        Skill skill = skillService.update(id, skillRequestRQ.getName(), skillRequestRQ.getActive());

        return new SkillReturnResponse(
                skill.getId(),
                skill.getName(),
                skill.getActive(),
                skill.getSummoner().getId()
        );
    }

    @DeleteMapping(value = "/skills/{id}")
    public void deleteSkill(@PathVariable(value = "id") Long id) {
        skillService.deleteById(id);
    }

}


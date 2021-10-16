package com.summonerswars.SW.controller;

import com.summonerswars.SW.controller.request.SummonerRequestRQ;
import com.summonerswars.SW.controller.response.SummonersReturnResponse;
import com.summonerswars.SW.model.Summoner;
import com.summonerswars.SW.service.SummonerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SummonerController {
    private final SummonerService summonerService;

    public SummonerController(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    //List of all Summoners
    @GetMapping("/summoners")
    public List<SummonersReturnResponse> getSummoners() {
        List<Summoner> summoners = summonerService.findAll();
        List<SummonersReturnResponse> summonersReturnResponse = new ArrayList<>();
        for (Summoner summoner : summoners) {
            summonersReturnResponse.add(new SummonersReturnResponse(
                    summoner.getId(),
                    summoner.getName(),
                    summoner.getTier()
            ));
        }
        return summonersReturnResponse;

    }

    //Search by ID a specific Summoner
    @GetMapping("getSummonersById/{id}")
    public SummonersReturnResponse getSummonersById(Long id) {
        Summoner summoner = summonerService.findById(id);

        return new SummonersReturnResponse(
                summoner.getId(),
                summoner.getName(),
                summoner.getTier()
        );
    }

    //Create a new Summoner
    @PostMapping(value = "/summoners", consumes = "application/json", produces = "application/json")
    //Class to request the body from Skill RequestRQ with the parameters to fullfill
    public SummonersReturnResponse createSummoners(@RequestBody SummonerRequestRQ summonerRequestRQ) {
        Summoner newSummoner = Summoner.builder()    //Method to get the parameters
                .tier(summonerRequestRQ.getTier())
                .name(summonerRequestRQ.getName())
                .build();
        summonerService.save(newSummoner);    //Connection to SkillService method
        SummonersReturnResponse summonersReturnResponse = new SummonersReturnResponse(); //Response to the user with the Skill data applied previously
        summonersReturnResponse.setId(newSummoner.getId());
        summonersReturnResponse.setName(newSummoner.getName());
        summonersReturnResponse.setTier(newSummoner.getTier());

        return summonersReturnResponse;
    }

    //Update parameters on Summoners
    @PutMapping(value = "/updateSummoners/{id}", consumes = "application/json", produces = "application/json")
    public SummonersReturnResponse updateSummoners(@PathVariable(value = "id") Long id, @RequestBody SummonerRequestRQ summonerRequestRQ) {
        Summoner summoner = summonerService.update(id, summonerRequestRQ.getName(), summonerRequestRQ.getTier());


        return new SummonersReturnResponse(
                summoner.getId(),
                summoner.getName(),
                summoner.getTier()
        );

    }

    //Delete Summoners
    @DeleteMapping(value = "/deleteSummoners/{id}")
    public void deleteSummoners(@PathVariable(value = "id") Long id) {
        summonerService.deleteById(id);
    }
}


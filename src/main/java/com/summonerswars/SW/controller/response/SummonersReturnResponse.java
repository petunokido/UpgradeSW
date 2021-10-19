package com.summonerswars.SW.controller.response;

import com.summonerswars.SW.model.Tier;
import lombok.*;

import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

//Request Class with the Return attributes for Summoner
public class SummonersReturnResponse {
    private Long id;

    private String name;

    @Enumerated
    private Tier tier;


}

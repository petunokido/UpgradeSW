package com.summonerswars.SW.controller.request;

import com.summonerswars.SW.model.Tier;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SummonerRequestRQ {
    private String name;

    @Enumerated(EnumType.STRING)
    private Tier tier;
}


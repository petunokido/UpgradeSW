package com.summonerswars.SW.controller.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SkillRequestRQ {
    private String name;
    private String active;
}

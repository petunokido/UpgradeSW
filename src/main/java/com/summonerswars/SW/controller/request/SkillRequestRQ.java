package com.summonerswars.SW.controller.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Request Class with the Request atributes for skill
public class SkillRequestRQ {
    private String name;
    private String active;
}

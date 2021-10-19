package com.summonerswars.SW.controller.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

//Request Class with the Return attributes for Skill
public class SkillReturnResponse {

    private Long id;

    private String name;

    private String active;

    private Long summonerId;

}


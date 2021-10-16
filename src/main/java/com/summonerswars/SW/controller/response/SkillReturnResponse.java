package com.summonerswars.SW.controller.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillReturnResponse {

    private Long id;

    private String name;

    private String active;

    private Long summonerId;

}


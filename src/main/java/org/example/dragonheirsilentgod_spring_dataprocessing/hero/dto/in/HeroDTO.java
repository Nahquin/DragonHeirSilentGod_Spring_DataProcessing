package org.example.dragonheirsilentgod_spring_dataprocessing.hero.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroDTO {

    private StatsDTO heroStats;
    private GrowthRuleDTO growthRuleDTO;
    private InformationDTO heroOtherInformation;
    private List<InspirationDTO> inspirations;
    private List<SkillDTO> heroSkills;
}

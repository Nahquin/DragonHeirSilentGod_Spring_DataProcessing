package org.example.dragonheirsilentgod_spring_dataprocessing.hero.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {

    private String slot;
    private String changeSlot;
    private Integer skillid;
    private String skilldesc;
    private String skillname;
    private String skillPath;
    private String heroId;
}

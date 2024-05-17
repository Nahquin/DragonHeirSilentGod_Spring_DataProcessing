package org.example.dragonheirsilentgod_spring_dataprocessing.hero.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspirationDTO {

    private String name;
    private String showName;
    private Integer id;
    private String spellTips;
    private double abt;
    private double abtVal;
}

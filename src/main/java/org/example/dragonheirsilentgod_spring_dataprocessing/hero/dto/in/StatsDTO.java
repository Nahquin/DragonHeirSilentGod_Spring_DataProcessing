package org.example.dragonheirsilentgod_spring_dataprocessing.hero.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsDTO {
    private double accuracyBase;
    private double atk_speedBpct;
    private double attackFrequency;
    private double cd_hasteBase;
    private double crit_bonusBpct;
    private double crit_chanceBpct;
    private double defenseBase;
    private double lv_up_base;
    private double masteryBase;
    private double max_healthBase;
    private double phy_dmgBase;
    private double resistanceBase;
}

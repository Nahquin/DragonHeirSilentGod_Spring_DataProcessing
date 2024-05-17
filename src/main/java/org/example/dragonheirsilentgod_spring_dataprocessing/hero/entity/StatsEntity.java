package org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_id")
    private Long statId;

    @Column(name = "accuracy_base")
    private double accuracyBase;

    @Column(name = "atk_speed_bpct")
    private double atk_speedBpct;

    @Column(name = "cd_haste_base")
    private double cd_hasteBase;

    @Column(name = "crit_bonus_bpct")
    private double crit_bonusBpct;

    @Column(name = "crit_chance_bpct")
    private double crit_chanceBpct;

    @Column(name = "defense_base")
    private double defenseBase;

    @Column(name = "mastery_base")
    private double masteryBase;

    @Column(name = "max_health_base")
    private double max_healthBase;

    @Column(name = "phy_dmg_base")
    private double phy_dmgBase;

    @Column(name = "resistance_base")
    private double resistanceBase;

    @Column(name = "lv_up_base")
    private double lv_up_base;

}

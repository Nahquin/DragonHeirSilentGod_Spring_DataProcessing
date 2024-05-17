package org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GrowthRuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long growthRuleId;

    @ManyToOne
    @JoinColumn(name = "lvl_up_base_hero_stat_id")
    StatsEntity lvlUpStats = new StatsEntity();
}

package org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HeroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attack_frequency")
    private double attackFrequency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "information_id")
    private InformationEntity information;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "growthRuleId")
    private GrowthRuleEntity growthRule;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL)
    private List<InspirationEntity> inspirations;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hero_stats_id")
    private StatsEntity heroStats;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL)
    private List<SkillEntity> skills;


}

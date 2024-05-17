package org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class InspirationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inspirationId;

    @Column(name = "name")
    private String name;

    @Column(name = "show_name")
    private String showName;
    @Column(name = "id")
    private Integer id;
    @Column(name = "spell_tips")
    private String spellTips;

    @Column(name = "abt")
    private Double abt;
    @Column(name = "abt_val")
    private Double abtVal;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private HeroEntity hero;
}

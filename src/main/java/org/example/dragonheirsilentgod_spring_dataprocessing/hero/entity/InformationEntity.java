package org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class InformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "information_id")
    private Long informationId;

    @Column(name = "captain_slot_path")
    private String captainSlotPath;

    @Column(name = "id")
    private Integer id;

    @Column(name = "captain_slot")
    private String captainSlot;

    @Column(name = "element")
    private String element;

    @Column(name = "orientation")
    private String orientation;

    @Column(name = "heroname")
    private String heroname;

    @Column(name = "ability_replace")
    private int abilityReplace;

    @Column(name = "atk_type")
    private String atkType;

    @Column(name = "hero_alignment")
    private String heroAlignment;

    @Column(name = "hero_race")
    private String heroRace;

    @Column(name = "show_title")
    private String showTitle;

    @Column(name = "heroPorPath")
    private String heroPorPath;

    @Column(name = "userIconId")
    private Long userIconId;

    @Column(name = "captain_enable_combat_types")
    private int captainEnableCombatTypes;

    @Column(name = "rarity")
    private String rarity;

    @Column(name = "head_icon")
    private Long headIcon;

    @Column(name = "hero_career")
    private String heroCareer;

    @Column(name = "heroId")
    private String heroId;

    @Column(name = "heroIdPath")
    private String heroIdPath;

}

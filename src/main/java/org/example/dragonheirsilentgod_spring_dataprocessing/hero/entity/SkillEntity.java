package org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slot")
    private String slot;

    @Column(name = "skill_id")
    private Integer skillid;

    @Column(name = "skill_desc")
    private String skilldesc;

    @Column(name = "skill_name")
    private String skillname;

    @Column(name = "skill_path")
    private String skillPath;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private HeroEntity hero;
}

package org.example.dragonheirsilentgod_spring_dataprocessing.hero.repository;


import org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<HeroEntity, Long> {
}

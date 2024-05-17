package org.example.dragonheirsilentgod_spring_dataprocessing.hero.repository;


import org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsRepository extends JpaRepository<StatsEntity, Long> {
}

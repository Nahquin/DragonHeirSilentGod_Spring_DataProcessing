package org.example.dragonheirsilentgod_spring_dataprocessing.hero.repository;

import org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<InformationEntity, Long> {
}

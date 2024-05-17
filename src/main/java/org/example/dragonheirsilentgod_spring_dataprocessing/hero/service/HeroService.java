package org.example.dragonheirsilentgod_spring_dataprocessing.hero.service;


import org.example.dragonheirsilentgod_spring_dataprocessing.hero.dto.in.HeroDTO;
import org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity.HeroEntity;
import org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity.InformationEntity;
import org.example.dragonheirsilentgod_spring_dataprocessing.hero.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private InspirationRepository inspirationRepository;

    @Autowired
    private StatsRepository StatsRepository;

    @Autowired
    private SkillRepository skillRepository;

    public void createHero(HeroDTO heroDTO) {
        InformationEntity heroOtherInformationEntity = new InformationEntity();
        // más entitások létrehozása és mentése

        HeroEntity heroEntity = new HeroEntity();
        heroEntity.setInformation(heroOtherInformationEntity);
        // többi entitás beállítása

        heroRepository.save(heroEntity);
    }
}

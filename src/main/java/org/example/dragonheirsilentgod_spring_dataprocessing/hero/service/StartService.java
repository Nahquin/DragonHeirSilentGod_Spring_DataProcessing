package org.example.dragonheirsilentgod_spring_dataprocessing.hero.service;


import jakarta.annotation.PostConstruct;
import org.example.dragonheirsilentgod_spring_dataprocessing.hero.entity.*;
import org.example.dragonheirsilentgod_spring_dataprocessing.hero.repository.HeroRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

@Service
public class StartService {

    private static final String JSONURL = "DragonDATA-S1&S2\\releasedata\\release_257520_en_2023121217\\hero\\hero.json";

    @Autowired
    private HeroRepository heroRepository;

    @PostConstruct
    public void start() {
        try {
            JSONArray heroDatas = new JSONArray(new JSONTokener(new FileReader(JSONURL)));
            for (int i = 0; i < heroDatas.length(); i++) {
                JSONObject heroData = heroDatas.getJSONObject(i);

                // HeroOtherInformation létrehozása
                InformationEntity informationEntity = new InformationEntity();
                informationEntity.setCaptainSlotPath(heroData.optString("captain_slot_path"));
                informationEntity.setId(Integer.valueOf(heroData.optString("id")));
                informationEntity.setCaptainSlot(heroData.optString("captain_slot"));
                informationEntity.setElement(heroData.optString("element"));
                informationEntity.setOrientation(heroData.optString("orientation"));
                informationEntity.setHeroname(heroData.optString("heroname"));
                informationEntity.setAbilityReplace(Integer.parseInt(heroData.optString("ability_replace")));
                informationEntity.setAtkType(heroData.optString("atk_type"));
                informationEntity.setHeroAlignment(heroData.optString("hero_alignment"));
                informationEntity.setHeroRace(heroData.optString("hero_race"));
                informationEntity.setShowTitle(heroData.optString("show_title"));
                informationEntity.setHeroPorPath(heroData.optString("heroPorPath"));
                informationEntity.setUserIconId(Long.valueOf(heroData.optString("userIconId")));
                informationEntity.setCaptainEnableCombatTypes(Integer.parseInt(heroData.optString("captain_enable_combat_types")));
                informationEntity.setRarity(heroData.optString("rarity"));
                informationEntity.setHeadIcon(Long.valueOf(heroData.optString("head_icon")));
                informationEntity.setHeroCareer(heroData.optString("hero_career"));
                informationEntity.setHeroId(heroData.optString("heroId"));
                informationEntity.setHeroIdPath(heroData.optString("heroIdPath"));






                List<InspirationEntity> inspirations;
                if (Objects.equals(informationEntity.getRarity(), "Legendary")){
                    for (int j = 0; j < 5; j++) {
                        InspirationEntity inspirationEntity = new InspirationEntity();

                        heroData.keySet().stream().filter(key -> key.contains("inspiration"))
                                .toList();






                    }


                }
                // Inspirations létrehozása







                // inspirations adatok beállítása

                // HeroStats létrehozása
                StatsEntity statsEntity = new StatsEntity();
                // heroStats adatok beállítása

                // HeroSkills létrehozása
                SkillEntity skillEntity = new SkillEntity();
                // heroSkills adatok beállítása



                // HeroEntity létrehozása és mentése az adatbázisba
                HeroEntity heroEntity = new HeroEntity();
                heroEntity.setInformation(informationEntity);

                heroEntity.setInspirations(inspirationsEntity);
                heroEntity.setHeroStats(heroStatsEntity);
                heroEntity.setHeroSkills(heroSkillsEntity);

                heroRepository.save(heroEntity);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

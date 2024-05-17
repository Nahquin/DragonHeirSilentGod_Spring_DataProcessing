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
import java.util.ArrayList;
import java.util.Iterator;
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

                // InformationEntity létrehozása
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


                // InspirationEntityk létrehozása
                List<InspirationEntity> inspirations = new ArrayList<>();
                if (Objects.equals(informationEntity.getRarity(), "Legendary")) {
                    for (int j = 1; j < 6; j++) {
                        Iterator<String> keys = heroData.keys();
                        InspirationEntity inspirationEntity = new InspirationEntity();
                        String key = "inspiration" + j;
                        inspirationEntity.setName(key);
                        switch (j) {
                            case 1, 2, 5 -> {
                                inspirationEntity.setShowName(heroData.optJSONObject(key).optString("show_name"));
                                inspirationEntity.setId(heroData.optJSONObject(key).getInt("id"));
                                inspirationEntity.setSpellTips(heroData.optJSONObject(key).optString("spell_tips"));
                            }
                            case 3, 4 -> {
                                inspirationEntity.setAbt(heroData.optJSONArray(key + "_abt").optDouble(0));
                                inspirationEntity.setAbtVal(heroData.optJSONArray(key + "_abt_val").optDouble(0));
                            }
                        }
                        inspirations.add(inspirationEntity);
                    }
                }

                // HeroStats létrehozása
                StatsEntity statsEntity = new StatsEntity();
                StatsEntitySetter(heroData, statsEntity);

                // GrowthRule létrehozása
                GrowthRuleEntity growthRuleEntity = new GrowthRuleEntity();
                StatsEntity statsEntityForGrowthRuleEntity = new StatsEntity();
                JSONObject lv_up_base = heroData.optJSONObject("lv_up_base");
                StatsEntitySetter(lv_up_base, statsEntityForGrowthRuleEntity);
                growthRuleEntity.setLvlUpStats(statsEntityForGrowthRuleEntity);

                // HeroSkills létrehozása
                List<SkillEntity> skillsList = new ArrayList<>();
                JSONObject skills = heroData.optJSONObject("skill");
                for (String key : skills.keySet()) {
                    SkillEntity skillEntity = new SkillEntity();
                    JSONObject keyObject = skills.optJSONObject(key);
                    skillEntity.setSkillid(keyObject.optInt("skillid"));
                    skillEntity.setSkilldesc(keyObject.optString("skilldesc"));
                    skillEntity.setSkillname(keyObject.optString("skillname"));
                    skillEntity.setSkillPath(keyObject.optString("skillPath"));
                    skillsList.add(skillEntity);
                }

                // HeroEntity létrehozása és mentése az adatbázisba
                HeroEntity heroEntity = new HeroEntity();
                heroEntity.setInformation(informationEntity);
                heroEntity.setInspirations(inspirations);
                heroEntity.setHeroStats(statsEntity);
                heroEntity.setGrowthRule(growthRuleEntity);
                heroEntity.setSkills(skillsList);


                heroRepository.save(heroEntity);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void StatsEntitySetter(JSONObject heroData, StatsEntity statsEntity) {
        statsEntity.setMax_healthBase(heroData.optDouble("max_health*base"));
        statsEntity.setPhy_dmgBase(heroData.optDouble("phy_dmg*base"));
        statsEntity.setResistanceBase((heroData.optDouble("resistance*base")));
        statsEntity.setAccuracyBase(heroData.optDouble("accuracy*base"));
        statsEntity.setMasteryBase(heroData.optDouble("mastery*base"));
        statsEntity.setCrit_bonusBpct(heroData.optDouble("crit_bonus*bpct"));
        statsEntity.setCrit_chanceBpct(heroData.optDouble("crit_chance*bpct"));
        statsEntity.setAtk_speedBpct(heroData.optDouble("atk_speed*bpct"));
        statsEntity.setDefenseBase(heroData.optDouble("defense*base"));
        statsEntity.setCd_hasteBase(heroData.optDouble("cd_haste*base"));
    }
}

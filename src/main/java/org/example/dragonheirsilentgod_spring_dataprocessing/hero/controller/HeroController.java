package org.example.dragonheirsilentgod_spring_dataprocessing.hero.controller;

import org.example.dragonheirsilentgod_spring_dataprocessing.hero.dto.in.HeroDTO;
import org.example.dragonheirsilentgod_spring_dataprocessing.hero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @PostMapping
    public void createHero(@RequestBody HeroDTO heroDTO) {
        heroService.createHero(heroDTO);
    }
}

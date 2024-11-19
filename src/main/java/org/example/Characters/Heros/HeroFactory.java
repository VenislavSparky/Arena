package org.example.Characters.Heros;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class HeroFactory {

    private static final Map<String, Supplier<Hero>> heroRegistry = new HashMap<>();


    public static Hero createHero(String heroClass) {
        Hero hero = null;

        switch (heroClass){
            case "Warrior":
                hero = new Warrior();
            case "Mage":
                hero = new Mage();
            case "Paladin":
                hero =  new Paladin();
        }
        return hero;
    }
}

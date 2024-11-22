package org.example.Characters.Heros;

import org.example.Characters.ClassType;
import org.example.Helpers.TextColor;

import java.util.Arrays;

public class HeroFactory {

    public static Hero createHero(ClassType heroClass) {
        Hero hero = null;

        switch (heroClass) {
            case WARRIOR -> {
                hero = new Warrior();
            }
            case MAGE -> {
                //hero = new Mage();
            }
            case PALADIN -> {
                //hero = new Paladin();
            }
        }

        return hero;
    }

    public static void listAvailableClasses() {
        Arrays.stream(ClassType.values())
                .filter(classType -> !ClassType.CHARACTER.equals(classType))
                .forEach(c -> System.out.println(TextColor.toClassColor(c, c.name())));
    }
}

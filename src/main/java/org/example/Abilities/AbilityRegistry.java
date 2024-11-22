package org.example.Abilities;

import org.example.Abilities.MageAbilities.Fireball;
import org.example.Abilities.PaladinAbilities.Heal;
import org.example.Abilities.WarriorAbilities.HeroicStrike;

import java.util.HashMap;
import java.util.Map;

public class AbilityRegistry {

    //TODO Make it Open-Close
    private static final Map<String, Ability> abilities = new HashMap<>();

    public static void registerAbility(String name, Ability ability) {
        abilities.put(name, ability);
    }

    public static void initialize() {
        //TODO implementation with reflection -> Open-Close
        new Fireball();
        new Heal();
        new HeroicStrike();
    }

    public static Ability getAbility(String name) {
        Ability ability = abilities.get(name);
        if (ability == null) {
            throw new IllegalArgumentException("No ability found with name: " + name);
        }
        return ability;
    }

    public static Map<String, Ability> getAllAbilities() {
        return Map.copyOf(abilities);
    }

}
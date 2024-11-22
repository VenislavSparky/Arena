package org.example.Characters.Heros;


import org.example.Abilities.AbilityRegistry;
import org.example.Characters.ClassType;

public class Warrior extends Hero{
    protected Warrior() {
        super(150, 3, 10, 3, 2);
        getClassType().add(ClassType.WARRIOR);
        learnAbility(AbilityRegistry.getAbility("HeroicStrike"));
    }
}

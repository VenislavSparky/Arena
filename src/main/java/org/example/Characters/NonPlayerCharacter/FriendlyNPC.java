package org.example.Characters.NonPlayerCharacter;

import org.example.Abilities.Ability;
import org.example.Characters.CharacterClass;
import org.example.Characters.GameCharacter;
import org.example.Characters.Stats;

import java.util.List;

public class FriendlyNPC extends NonPlayerCharacter {

    public FriendlyNPC(String name, CharacterClass characterClass, int level, int maxHealth, int maxEnergy, Stats stats, Ability ability) {
        super(name, characterClass, level, maxHealth, maxEnergy, stats, ability);
    }

    @Override
    List<GameCharacter> getEnemies(List<GameCharacter> heroes, List<GameCharacter> monsters) {
        return monsters;
    }

    @Override
    List<GameCharacter> getAllies(List<GameCharacter> heroes, List<GameCharacter> monsters) {
        return heroes;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Class: %s , Health: %d \n Stats: %s",getName(),getCharacterClass(),getCurrentHealth(), getStats());
    }

}

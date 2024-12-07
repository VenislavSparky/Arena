package org.example.Characters.NonPlayerCharacter;

import org.example.Characters.GameCharacter;

import java.util.List;

public class HostileNPC extends NonPlayerCharacter {

    @Override
    List<GameCharacter> getEnemies(List<GameCharacter> heroes, List<GameCharacter> monsters) {
        return heroes;
    }

    @Override
    List<GameCharacter> getAllies(List<GameCharacter> heroes, List<GameCharacter> monsters) {
        return monsters;
    }
}

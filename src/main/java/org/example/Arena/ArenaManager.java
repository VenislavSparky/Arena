package org.example.Arena;

import org.example.Characters.Character;
import org.example.Characters.Heros.Hero;
import org.example.Characters.Monsters.Monster;
import org.example.Characters.Monsters.MonsterFactory;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    List<Character> arenaParticipants;

    public ArenaManager() {
        arenaParticipants = new ArrayList<>();
    }

    public void startBattle(Hero hero) {

        boolean isBattleOver = false;
        while (isBattleOver) {

        }

    }

    ;

    public void loadBattle(Hero hero) {
        Monster monster = MonsterFactory.createMonster();
        arenaParticipants.add(hero);
        arenaParticipants.add(monster);

    }

}

package org.example.Characters.Monsters;

import org.example.Characters.Character;

import java.util.List;

public class Troll extends Monster{


    @Override
    public boolean selectAbility(Character user, List<Character> allies, List<Character> enemies) {
        return false;
    }
}

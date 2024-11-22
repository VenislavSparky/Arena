package org.example.Characters.Monsters;

import org.example.Characters.Character;


public abstract class Monster extends Character {
    protected Monster(int totalHealth, int totalActionPoints, int strengthStat, int armorStat, int intellectStat) {
        super(totalHealth, totalActionPoints, strengthStat, armorStat, intellectStat);
    }
}

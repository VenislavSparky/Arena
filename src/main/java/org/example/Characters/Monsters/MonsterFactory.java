package org.example.Characters.Monsters;

public class MonsterFactory {
    public static Monster createMonster() {
        //TODO OCP RANDOM FACTORY
        return new Troll();
    }
}

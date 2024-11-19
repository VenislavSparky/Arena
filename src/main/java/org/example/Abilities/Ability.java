package org.example.Abilities;

import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;
import java.util.Set;

public abstract class Ability {

    private int actionPointsCost;
    private Set<ClassType> classType;
    private int countDown;
    private String description;

    public Ability(int actionPointsCost, Set<ClassType> classType) {
        this.actionPointsCost = actionPointsCost;
        this.classType = classType;
    }

    public abstract boolean use(Character user, List<Character> allies, List<Character> enemies);


    protected static class TargetingStrategies {

        public static Character targetUser(Character user) {
            return user;
        }

        public static Character targetSingleEnemy(List<Character> enemies) {
            //todo
            return enemies.get(0);
        }

        public static Character targetSingleAlly(List<Character> allies) {
            //todo
            return allies.get(0);
        }

        public static List<Character> targetMultipleEnemies(List<Character> enemies) {
            //todo
            return enemies;
        }

        public static List<Character> targetMultipleAllies(List<Character> allies) {
            //todo
            return allies;
        }


    }




    public int getActionPointsCost() {
        return actionPointsCost;
    }

    public void setActionPointsCost(int actionPointsCost) {
        this.actionPointsCost = actionPointsCost;
    }

    public Set<ClassType> getClassType() {
        return classType;
    }

    public void setClassType(Set<ClassType> classType) {
        this.classType = classType;
    }


    public int getCountDown() {
        return countDown;
    }

    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

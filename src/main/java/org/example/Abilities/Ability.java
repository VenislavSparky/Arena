package org.example.Abilities;

import org.example.Abilities.TargetingStrategies.TargetSelectionStrategy;
import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;
import java.util.Set;

public abstract class Ability {

    private int actionPointsCost;
    private Set<ClassType> classType;
    private TargetSelectionStrategy targetSelectionStrategy;
    private int countDown;
    private String description;

    public Ability(int actionPointsCost, Set<ClassType> classType, TargetSelectionStrategy targetSelectionStrategy) {
        this.actionPointsCost = actionPointsCost;
        this.classType = classType;
        this.targetSelectionStrategy = targetSelectionStrategy;
    }

    public abstract boolean use(Character user, List<Character> allies, List<Character> enemies) ;

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

    public TargetSelectionStrategy getTargetSelectionStrategy() {
        return targetSelectionStrategy;
    }

    public void setTargetSelectionStrategy(TargetSelectionStrategy targetSelectionStrategy) {
        this.targetSelectionStrategy = targetSelectionStrategy;
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

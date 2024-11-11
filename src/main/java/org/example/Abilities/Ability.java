package org.example.Abilities;

import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.Set;

public abstract class Ability {

    private int actionPointsCost;
    private Set<ClassType> classType;

    public abstract void use(Character user, Character enemy) ;

    public int getActionPointsCost() {
        return actionPointsCost;
    }

    public void setActionPointsCost(int actionPointsCost) {
        this.actionPointsCost = actionPointsCost;
    }


}

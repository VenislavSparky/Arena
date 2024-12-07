package org.example.Abilities.TargetingStrategies;

import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelection;
import org.example.Characters.GameCharacter;
import java.util.List;

public abstract class TargetingStrategy {

    GameCharacter user;
    List<GameCharacter> possibleTargets;
    TargetSelection targetSelection;
    int count;

    public TargetingStrategy(GameCharacter user, List<GameCharacter> possibleTargets, TargetSelection targetSelection, int count) {
        this.user = user;
        this.possibleTargets = possibleTargets;
        this.targetSelection = targetSelection;
        this.count = count;
    }

    public TargetingStrategy() {

    }

    public List<GameCharacter> getTargets() {

        if (count == 0) {
            return List.of(user);
        } else if(possibleTargets.size() <= count){
            return possibleTargets;
        } else{
            return targetSelection.select(possibleTargets, count);
        }

    }

}

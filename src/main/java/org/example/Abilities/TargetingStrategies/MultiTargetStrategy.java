package org.example.Abilities.TargetingStrategies;

import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Characters.GameCharacter;

import java.util.List;

public class MultiTargetStrategy implements TargetingStrategy {

    int targetCount;

    public MultiTargetStrategy(int targetCount) {
        this.targetCount = targetCount;
    }

    @Override
    public List<GameCharacter> getTargets(GameCharacter user, List<GameCharacter> possibleTargets, TargetSelectionMode targetSelectionMode) {
        return possibleTargets.size() <= targetCount ? possibleTargets : targetSelectionMode.select(possibleTargets, targetCount);
    }
}

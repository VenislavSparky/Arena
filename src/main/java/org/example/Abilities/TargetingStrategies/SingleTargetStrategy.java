package org.example.Abilities.TargetingStrategies;


import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Characters.GameCharacter;

import java.util.List;

public class SingleTargetStrategy implements TargetingStrategy {
    int targetCount = 1;

    @Override
    public List<GameCharacter> getTargets(GameCharacter user, List<GameCharacter> possibleTargets, TargetSelectionMode targetSelectionMode) {

        return possibleTargets.size() <= targetCount ? possibleTargets : targetSelectionMode.select(possibleTargets, targetCount);

    }
}

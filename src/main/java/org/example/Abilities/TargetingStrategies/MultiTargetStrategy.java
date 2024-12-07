package org.example.Abilities.TargetingStrategies;


import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelection;
import org.example.Characters.GameCharacter;

import java.util.List;

public class MultiTargetStrategy extends TargetingStrategy {

    public MultiTargetStrategy(GameCharacter user, List<GameCharacter> possibleTargets, TargetSelection targetSelection, int count) {
        super(user, possibleTargets, targetSelection, count);
    }
}

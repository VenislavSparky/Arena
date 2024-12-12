package org.example.Abilities.TargetingStrategies;


import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Characters.GameCharacter;

import java.util.List;

public class SelfTargetStrategy implements TargetingStrategy
{

    @Override
    public List<GameCharacter> getTargets(GameCharacter user, List<GameCharacter> possibleTargets, TargetSelectionMode targetSelectionMode) {
        return List.of(user);
    }
}

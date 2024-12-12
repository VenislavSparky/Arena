package org.example.Abilities.TargetingStrategies.TargetSelection;

import org.example.Characters.GameCharacter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AutoSelectionMode implements TargetSelectionMode {
    @Override
    public List<GameCharacter> select(List<GameCharacter> possibleTargets, int count) {
        List<GameCharacter> targets = new ArrayList<>();

        possibleTargets.stream()
                .sorted(Comparator.comparingInt(GameCharacter::getCurrentHealth))
                .limit(count)
                .forEach(targets::add);

        return targets;
    }
}

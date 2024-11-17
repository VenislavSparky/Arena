package org.example.Abilities.TargetingStrategies;

import org.example.Characters.Character;

import java.util.List;

public class TargetUser implements TargetSelectionStrategy {

    @Override
    public List<Character> getPossibleTargets(Character user, List<Character> allies, List<Character> enemies) {
        return List.of(user);
    }
}

package org.example.Abilities.TargetingStrategies;

import org.example.Characters.Character;

import java.util.List;

public interface TargetSelectionStrategy {

    List<Character> selectFromPossibleTargets(Character user, List<Character> allies, List<Character> enemies);
}

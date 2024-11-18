package org.example.Abilities.TargetingStrategies;

import org.example.Characters.Character;

import java.util.List;

public class TargetMultipleEnemies implements TargetSelectionStrategy {
    @Override
    public List<Character> selectFromPossibleTargets(Character user, List<Character> allies, List<Character> enemies) {

        return enemies;
    }
}




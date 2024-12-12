package org.example.Abilities.TargetingStrategies.TargetSelection;

import org.example.Characters.GameCharacter;

import java.util.List;

public interface TargetSelectionMode {
  List<GameCharacter> select(List<GameCharacter> possibleTargets, int count);
}

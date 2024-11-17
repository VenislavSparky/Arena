package org.example.Abilities.TargetingStrategies;

import org.example.Characters.Character;

import java.util.List;
import java.util.Scanner;

public class TargetSingleEnemy implements TargetSelectionStrategy {

    @Override
    public List<Character> getPossibleTargets(Character user, List<Character> allies, List<Character> enemies) {
        System.out.println("Select enemy");
      enemies.forEach(e -> System.out.printf(e.toString()));
        Scanner scanner = new Scanner(System.in);

        return List.of(enemies.get(0));
    }
}

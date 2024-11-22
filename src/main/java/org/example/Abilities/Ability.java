package org.example.Abilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public abstract class Ability {

    private int actionPointsCost;
    private ClassType classType;
    private String description;

    public abstract boolean use(Character user, List<Character> allies, List<Character> enemies);

    protected static class TargetingStrategies {

        public static Character targetUser(Character user) {
            return user;
        }

        public static Character chooseSingleTarget(List<Character> possibleTargets) {
            //todo selection logic
            return possibleTargets.get(0);
        }

        public static List<Character> chooseMultipleTargets(int targetCount,List<Character> possibleTargets) {
            List<Character> chosenTargets = new ArrayList<>();
            chosenTargets.add(possibleTargets.get(0));
            //todo selection logic
            return chosenTargets;
        }

    }

}

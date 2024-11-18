package org.example.Abilities.PaladinAbilities;

import org.example.Abilities.Ability;
import org.example.Abilities.TargetingStrategies.TargetSelectionStrategy;
import org.example.Abilities.TargetingStrategies.TargetUser;
import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;
import java.util.Set;

public class Heal extends Ability {

    private static final int HEALTH = 30;
    private static final int ACTION_POINTS_COST = 2;
    private static final Set<ClassType> ALLOWED_CLASSES = Set.of(ClassType.PALADIN);
    private static final TargetSelectionStrategy TARGET_STRATEGY = new TargetUser();

    public Heal() {
        super(ACTION_POINTS_COST, ALLOWED_CLASSES, TARGET_STRATEGY);
    }

    @Override
    public boolean use(Character user, List<Character> allies, List<Character> enemies) {
        boolean isExecuted = false;
        if (user.hasEnoughActionPoints(ACTION_POINTS_COST)) {
            List<Character> targets = getTargetSelectionStrategy().selectFromPossibleTargets(user, allies, enemies);
            for (Character target : targets) {
                target.heal(HEALTH + user.getIntellectStat());
                isExecuted = true;
            }
        } else {
            System.out.println("Not Enough Action Points");
        }
        return isExecuted;
    }

}

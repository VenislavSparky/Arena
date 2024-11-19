package org.example.Abilities.MageAbilities;

import org.example.Abilities.Ability;
import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;
import java.util.Set;

public class Fireball extends Ability {

    private static final int DAMAGE = 30;
    private static final int ACTION_POINTS_COST = 3;
    private static final Set<ClassType> ALLOWED_CLASSES = Set.of(ClassType.MAGE);

    private Fireball() {
        super(ACTION_POINTS_COST, ALLOWED_CLASSES);
    }

    @Override
    public boolean use(Character user, List<Character> allies, List<Character> enemies) {
        boolean isExecuted = false;
        if (user.hasEnoughActionPoints(ACTION_POINTS_COST)) {
            List<Character> targets = TargetingStrategies.targetMultipleEnemies(enemies);
            for (Character target : targets) {
                target.takeDamage(DAMAGE);
                isExecuted = true;
            }
        } else {
            System.out.println("Not Enough Action Points");
        }
        return isExecuted;
    }


}

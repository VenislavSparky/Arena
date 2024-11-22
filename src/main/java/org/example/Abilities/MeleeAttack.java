package org.example.Abilities;

import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;

public class MeleeAttack extends Ability {

    private static final int DAMAGE = 30;

    public MeleeAttack() {
        super(1, ClassType.CHARACTER, "Basic Melee Attack: ");
    }

    @Override
    public boolean use(Character user, List<Character> allies, List<Character> enemies) {
        Character target = TargetingStrategies.chooseSingleTarget(enemies);
        target.takeDamage(DAMAGE + user.getStrengthStat());
        return false;
    }
}

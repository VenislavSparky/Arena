package org.example.Abilities.MageAbilities;

import org.example.Abilities.Ability;
import org.example.Abilities.AbilityRegistry;
import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;

public class Fireball extends Ability {

    private static final int DAMAGE = 30;
    private static final int ACTION_POINTS_COST = 3;
    private static final ClassType ALLOWED_CLASSES = ClassType.MAGE;

    public Fireball() {
        super(ACTION_POINTS_COST, ALLOWED_CLASSES, "Fireball");
        AbilityRegistry.registerAbility("Fireball", this);
    }

    @Override
    public boolean use(Character user, List<Character> allies, List<Character> enemies) {
        Character target = TargetingStrategies.chooseSingleTarget(enemies);
        target.takeDamage(DAMAGE);
        return true;
    }


}

package org.example.Abilities.WarriorAbilities;

import org.example.Abilities.Ability;
import org.example.Abilities.AbilityRegistry;
import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;

public class HeroicStrike extends Ability {
    private static final int DAMAGE = 30;
    private static final int ACTION_POINTS_COST = 2;
    private static final ClassType ALLOWED_CLASSES = ClassType.WARRIOR;


    public HeroicStrike() {
        super(ACTION_POINTS_COST, ALLOWED_CLASSES, String.format("Heroic Strike: BASE DAMAGE - %d + Strength Stat, Action points cost: %d",DAMAGE,ACTION_POINTS_COST));
        AbilityRegistry.registerAbility(getClass().getSimpleName(), this);
    }

    @Override
    public boolean use(Character user, List<Character> allies, List<Character> enemies) {
        Character target = TargetingStrategies.chooseSingleTarget(enemies);
        target.takeDamage(DAMAGE + user.getStrengthStat());
        return true;
    }
}

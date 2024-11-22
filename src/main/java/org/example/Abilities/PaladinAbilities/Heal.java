package org.example.Abilities.PaladinAbilities;

import org.example.Abilities.Ability;
import org.example.Abilities.AbilityRegistry;
import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;

public class Heal extends Ability {

    private static final int HEALTH = 30;
    private static final int ACTION_POINTS_COST = 2;
    private static final ClassType ALLOWED_CLASSES = ClassType.WARRIOR;

    public Heal() {
        super(ACTION_POINTS_COST, ALLOWED_CLASSES,"Heal spell");
        AbilityRegistry.registerAbility("Heal", this);
    }

    @Override
    public boolean use(Character user, List<Character> allies, List<Character> enemies) {
        Character target = TargetingStrategies.targetUser(user);
        target.heal(HEALTH + user.getIntellectStat());
        return true;
    }

}

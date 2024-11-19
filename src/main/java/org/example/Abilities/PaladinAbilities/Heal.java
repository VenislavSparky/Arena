package org.example.Abilities.PaladinAbilities;

import org.example.Abilities.Ability;
import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;
import java.util.Set;

public class Heal extends Ability {

    private static final int HEALTH = 30;
    private static final int ACTION_POINTS_COST = 2;
    private static final Set<ClassType> ALLOWED_CLASSES = Set.of(ClassType.PALADIN);

    private Heal() {
        super(ACTION_POINTS_COST, ALLOWED_CLASSES);
    }

    @Override
    public boolean use(Character user, List<Character> allies, List<Character> enemies) {
        Character target = TargetingStrategies.targetUser(user);
        target.heal(HEALTH + user.getIntellectStat());
        return true;
    }

}

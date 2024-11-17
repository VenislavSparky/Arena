package org.example.Abilities.MageAbilities;

import org.example.Abilities.Ability;
import org.example.Abilities.TargetingStrategies.TargetSingleEnemy;
import org.example.Characters.Character;
import org.example.Characters.ClassType;

import java.util.List;
import java.util.Set;

public class Fireball extends Ability {

    int damage = 30;

    public Fireball() {
        super(3, Set.of(ClassType.MAGE) , new TargetSingleEnemy());
    }

    @Override
    public void use(Character user, List<Character> allies, List<Character> enemies) {
        List<Character> targets = getTargetSelectionStrategy().getPossibleTargets(user, allies, enemies);
        for (Character target : targets) {
        target.takeDamage(damage);
        }
    }
}

package org.example.Abilities.WarriorAbilities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.Effects.NoEffect;
import org.example.Abilities.TargetingStrategies.AllTargetStrategy;

import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.CharacterClass;
import org.example.Characters.GameCharacter;
import java.util.List;

@Entity
@DiscriminatorValue("Whirlwind")
public class Whirlwind extends Ability {
    private static final int DAMAGE = 10;
    private static final int ENERGY = 25;
    private static final String DESCRIPTION = String.format("Ability that deals %d base damage to all enemies. Cost %d energy.",DAMAGE,ENERGY );
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.WARRIOR;
    private static final TargetingStrategy TARGETING_STRATEGY = new AllTargetStrategy();
    private static final Effect EFFECT = new NoEffect();

    public Whirlwind() {
        super(ENERGY, DESCRIPTION, ALLOWED_CLASS, EFFECT, TARGETING_STRATEGY);
    }

    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user,enemies,targetSelectionMode);
        for (GameCharacter target : targets) {
            target.receiveDamage(DAMAGE + user.getStats().getStrength());
            getEffect().apply(target);
        }
    }

}

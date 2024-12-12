package org.example.Abilities.PaladinAbilities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.Effects.NoEffect;
import org.example.Abilities.TargetingStrategies.MultiTargetStrategy;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.CharacterClass;
import org.example.Characters.GameCharacter;

import java.util.List;

@Entity
@DiscriminatorValue("DivineStorm")
public class DivineStorm  extends Ability {
    private static final int DAMAGE = 30;
    private static final int ENERGY = 30;
    private static final int TARGETS = 2;
    private static final String DESCRIPTION = String.format("Deal %d base damage to %d targets. Costing %d energy.",DAMAGE,TARGETS,ENERGY);
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.PALADIN;
    private static final TargetingStrategy TARGETING_STRATEGY = new MultiTargetStrategy(TARGETS);
    private static final Effect EFFECT = new NoEffect();

    public DivineStorm() {
        super(ENERGY,DESCRIPTION,ALLOWED_CLASS,EFFECT,TARGETING_STRATEGY);
    }


    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user, allies, targetSelectionMode);
        for (GameCharacter target : targets) {
            target.receiveDamage(DAMAGE);
        }
    }
}

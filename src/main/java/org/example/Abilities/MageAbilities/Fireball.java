package org.example.Abilities.MageAbilities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.NoEffect;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.TargetingStrategies.SingleTargetStrategy;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.GameCharacter;
import org.example.Characters.CharacterClass;

import java.util.List;

@Entity
@DiscriminatorValue("Fireball")
public class Fireball extends Ability {
    private static final int DAMAGE = 60;
    private static final int ENERGY = 75;
    private static final String DESCRIPTION = String.format("Deal %d base damage to a single target. Costing %d energy.", DAMAGE, ENERGY);
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.MAGE;
    private static final TargetingStrategy TARGETING_STRATEGY = new SingleTargetStrategy();
    private static final Effect EFFECT = new NoEffect();

    public Fireball() {
        super(ENERGY, DESCRIPTION, ALLOWED_CLASS, EFFECT, TARGETING_STRATEGY);
    }


    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user, allies, targetSelectionMode);
        for (GameCharacter target : targets) {
            target.receiveDamage(DAMAGE);
        }
    }
}

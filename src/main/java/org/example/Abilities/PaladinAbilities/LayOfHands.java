package org.example.Abilities.PaladinAbilities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.Effects.NoEffect;
import org.example.Abilities.TargetingStrategies.SelfTargetStrategy;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.CharacterClass;
import org.example.Characters.GameCharacter;

import java.util.List;

@Entity
@DiscriminatorValue("LayOfHands")
public class LayOfHands extends Ability {
    private static final int ENERGY = 70;
    private static final String DESCRIPTION = String.format("Restore all your health. Costing %d energy.", ENERGY);
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.PALADIN;
    private static final TargetingStrategy TARGETING_STRATEGY = new SelfTargetStrategy();
    private static final Effect EFFECT = new NoEffect();

    public LayOfHands() {
        super(ENERGY, DESCRIPTION, ALLOWED_CLASS, EFFECT, TARGETING_STRATEGY);
    }

    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user, enemies, targetSelectionMode);
        for (GameCharacter target : targets) {
            target.receiveHeal(target.getMaxHealth());
        }
    }
}

package org.example.Abilities.PaladinAbilities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.NoEffect;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.TargetingStrategies.SelfTargetStrategy;
import org.example.Abilities.TargetingStrategies.SingleTargetStrategy;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.GameCharacter;
import org.example.Characters.CharacterClass;

import java.util.List;

@Entity
@DiscriminatorValue("FlashOfLight")
public class FlashOfLight extends Ability {

    private static final int HEALTH = 30;
    private static final int ENERGY = 30;
    private static final String DESCRIPTION = String.format("Restore %d health to you or ally, cosing %d energy!",HEALTH,ENERGY);
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.PALADIN;
    private static final TargetingStrategy TARGETING_STRATEGY = new SingleTargetStrategy();
    private static final Effect EFFECT = new NoEffect();

    public FlashOfLight() {
        super(ENERGY,DESCRIPTION,ALLOWED_CLASS,EFFECT,TARGETING_STRATEGY);
    }


    @Override
   public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user, allies, targetSelectionMode);
        for (GameCharacter target : targets) {
            target.receiveHeal(HEALTH);
        }
    }
}

package org.example.Abilities.PaladinAbilities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.Effects.NoEffect;
import org.example.Abilities.TargetingStrategies.SingleTargetStrategy;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.CharacterClass;
import org.example.Characters.GameCharacter;

import java.util.List;

@Entity
@DiscriminatorValue("CrusaderStrike")
public class CrusaderStrike  extends Ability {
    private static final int DAMAGE = 25;
    private static final int ENERGY = 35;
    private static final String DESCRIPTION = String.format("Holy strike that deals %d damage costing %d energy.",DAMAGE,ENERGY );
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.PALADIN;
    private static final TargetingStrategy TARGETING_STRATEGY = new SingleTargetStrategy();
    private static final Effect EFFECT = new NoEffect();

    public CrusaderStrike() {
        super(ENERGY, DESCRIPTION, ALLOWED_CLASS, EFFECT, TARGETING_STRATEGY);
    }

    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user,enemies, targetSelectionMode);
        for (GameCharacter target : targets) {
            target.receiveDamage(DAMAGE + user.getStats().getStrength());
        }
    }

}

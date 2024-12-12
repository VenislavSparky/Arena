package org.example.Abilities.WarriorAbilities;

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
@DiscriminatorValue("Bloodrage")
public class Bloodrage extends Ability {
    private static final int DAMAGE = 25;
    private static final int ENERGY = 30;
    private static final String DESCRIPTION = String.format("For %d energy you restore all energy, and all effects are cleared from you, but at a cost of %d of your health.", ENERGY, DAMAGE);
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.WARRIOR;
    private static final TargetingStrategy TARGETING_STRATEGY = new SelfTargetStrategy();
    private static final Effect EFFECT = new NoEffect();

    public Bloodrage() {
        super(ENERGY, DESCRIPTION, ALLOWED_CLASS, EFFECT, TARGETING_STRATEGY);
    }

    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user, enemies, targetSelectionMode);
        for (GameCharacter target : targets) {
            target.receiveDamage(DAMAGE);
            target.receiveEnergy(user.getMaxEnergy());
            target.getEffects().clear();
        }
    }

}

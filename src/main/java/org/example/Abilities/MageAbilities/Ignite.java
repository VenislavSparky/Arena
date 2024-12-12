package org.example.Abilities.MageAbilities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.Burning;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.TargetingStrategies.AllTargetStrategy;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.CharacterClass;
import org.example.Characters.GameCharacter;

import java.util.List;
@Entity
@DiscriminatorValue("Ignite")
public class Ignite extends Ability {

    private static final int DAMAGE = 15;
    private static final int ENERGY = 50;
    private static final String DESCRIPTION = String.format("Deal %d base damage to all targets. Costing %d energy. Special effect Burning.", DAMAGE, ENERGY);
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.MAGE;
    private static final TargetingStrategy TARGETING_STRATEGY = new AllTargetStrategy();
    private static final Effect EFFECT = new Burning();

    public Ignite() {
        super(ENERGY, DESCRIPTION, ALLOWED_CLASS, EFFECT, TARGETING_STRATEGY);
    }


    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user, allies, targetSelectionMode);
        for (GameCharacter target : targets) {
            target.receiveDamage(DAMAGE);
            getEffect().apply(target);
        }
    }


}

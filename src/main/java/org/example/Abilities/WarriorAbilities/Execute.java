package org.example.Abilities.WarriorAbilities;

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
@DiscriminatorValue("Execute")
public class Execute extends Ability {
    private static final int DAMAGE = 10;
    private static final int ENERGY = 20;
    private static final String DESCRIPTION = String.format("Its a heavy-damage ability dealing %d base damage costing %d energy. Targets with less than 50 of their health left take double damage.",DAMAGE,ENERGY );
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.WARRIOR;
    private static final TargetingStrategy TARGETING_STRATEGY = new SingleTargetStrategy();
    private static final Effect EFFECT = new NoEffect();

    public Execute() {
        super(ENERGY, DESCRIPTION, ALLOWED_CLASS, EFFECT, TARGETING_STRATEGY);
    }

    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user,enemies, targetSelectionMode);
        for (GameCharacter target : targets) {
            if (target.getCurrentHealth() <= 50){
                target.receiveDamage((DAMAGE + user.getStats().getStrength()) * 2);
            }else {
                target.receiveDamage(DAMAGE + user.getStats().getStrength());
            }
        }
    }

}

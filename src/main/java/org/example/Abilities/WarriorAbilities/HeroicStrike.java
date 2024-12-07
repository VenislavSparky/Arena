package org.example.Abilities.WarriorAbilities;


import jakarta.persistence.DiscriminatorValue;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.NoEffect;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.TargetingStrategies.SelfTargetStrategy;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.GameCharacter;
import org.example.Characters.CharacterClass;

import java.util.List;

@jakarta.persistence.Entity
@DiscriminatorValue("HeroicStrike")
public class HeroicStrike extends Ability {
    private static final int DAMAGE = 30;
    private static final int ENERGY = 20;
    private static final String DESCRIPTION = String.format("Powerful strike that deals %d costing %d energy.",DAMAGE,ENERGY );
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.WARRIOR;
    private static final TargetingStrategy TARGETING_STRATEGY = new SelfTargetStrategy();
    private static final Effect EFFECT = new NoEffect();

    public HeroicStrike() {
        super(ENERGY, DESCRIPTION, ALLOWED_CLASS, EFFECT, TARGETING_STRATEGY);
    }

    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies) {
        List<GameCharacter> targets = getTargetingStrategy().getTargets();
        for (GameCharacter target : targets) {
            int damageDealt = target.receiveDamage(DAMAGE);
            user.receiveHeal(damageDealt);
            getEffect().apply(user);
        }
    }
}

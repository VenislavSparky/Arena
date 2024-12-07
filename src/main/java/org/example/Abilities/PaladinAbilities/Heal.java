package org.example.Abilities.PaladinAbilities;

import jakarta.persistence.DiscriminatorValue;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.NoEffect;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.GameCharacter;
import org.example.Characters.CharacterClass;

import java.util.List;

@jakarta.persistence.Entity
@DiscriminatorValue("Heal")
public class Heal extends Ability {

    private static final int HEALTH = 30;

    private static final int ENERGY = 40;
    private static final String DESCRIPTION = "Heal for 30!";
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.PALADIN;
    private static final TargetingStrategy TARGETING_STRATEGY = new SelfTargetStrategy();
    private static final Effect STATUS_EFFECT = new NoEffect();

    public Heal() {
        super(ENERGY,DESCRIPTION,ALLOWED_CLASS,STATUS_EFFECT,TARGETING_STRATEGY);
    }


    @Override
   public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies) {
        System.out.println("Heal " + HEALTH);
        List<GameCharacter> targets = getTargetingStrategy().getTargets(user, allies, enemies);
        for (GameCharacter target : targets) {
            target.receiveHeal(HEALTH);
        }
    }
}

package org.example.Abilities.MageAbilities;


import org.example.Abilities.Ability;
import org.example.Abilities.Effects.NoEffect;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.GameCharacter;
import org.example.Characters.CharacterClass;

import java.util.List;

@jakarta.persistence.Entity
public class Fireball extends Ability {

    private static final int ENERGY = 20;
    private static final String DESCRIPTION = "Heal for 30!";
    private static final CharacterClass ALLOWED_CLASS = CharacterClass.PALADIN;
    private static final TargetingStrategy TARGETING_STRATEGY = new SelfTargetStrategy();
    private static final Effect STATUS_EFFECT = new NoEffect();

    public Fireball() {
        super(ENERGY,DESCRIPTION,ALLOWED_CLASS,STATUS_EFFECT,TARGETING_STRATEGY);
    }

    @Override
    public void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies) {

    }
}

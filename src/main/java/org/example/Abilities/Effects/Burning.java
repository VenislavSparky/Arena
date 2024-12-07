package org.example.Abilities.Effects;

import org.example.Characters.GameCharacter;

public class Burning extends Effect{
    @Override
    public void activate(GameCharacter gameCharacter) {
        gameCharacter.receiveDamage(15);
    }
}

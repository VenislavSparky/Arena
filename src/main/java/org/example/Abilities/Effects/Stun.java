package org.example.Abilities.Effects;

import org.example.Characters.GameCharacter;

public class Stun extends Effect {

    @Override
    public void activate(GameCharacter gameCharacter) {
        gameCharacter.endTurn();
    }
}

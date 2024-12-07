package org.example.Abilities.Effects;



import org.example.Characters.GameCharacter;

public class Rejuvenation extends Effect {

    @Override
    public void activate(GameCharacter gameCharacter) {
        gameCharacter.receiveHeal(10);
    }
}

package org.example.Abilities.Effects;



import org.example.Characters.GameCharacter;

public class Rejuvenation extends Effect {

    public Rejuvenation() {
        super("Rejuvenation", "Heal 10 health at the end of the turn. Duration 2 turns.", 2);
    }

    @Override
    public void activate(GameCharacter gameCharacter) {
        gameCharacter.receiveHeal(10);
    }
}

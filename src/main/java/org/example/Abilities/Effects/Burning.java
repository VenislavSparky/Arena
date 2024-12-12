package org.example.Abilities.Effects;

import org.example.Characters.GameCharacter;

public class Burning extends Effect{

    public Burning() {
        super("Burning", "Deal 15 damage at the end of the turn. Duration 2 turns.", 2);
    }

    @Override
    public void activate(GameCharacter gameCharacter) {
        System.out.print("Burning:");
        gameCharacter.receiveDamage(15);
    }
}

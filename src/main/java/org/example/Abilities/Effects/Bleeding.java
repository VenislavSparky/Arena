package org.example.Abilities.Effects;

import org.example.Characters.GameCharacter;

public class Bleeding extends Effect{


    public Bleeding() {
        super("Bleeding", "Deal 10 damage at the end of the turn. Duration 2 turns.", 3);
    }

    @Override
    public void activate(GameCharacter gameCharacter) {
        System.out.print("Bleeding:");
        gameCharacter.receiveDamage(10);
    }

}

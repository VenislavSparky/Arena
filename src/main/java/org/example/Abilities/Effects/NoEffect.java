package org.example.Abilities.Effects;

import org.example.Characters.GameCharacter;


public class NoEffect extends Effect {


    public NoEffect( ) {
        super("Does nothing", "Ability doesn't have special effect!", 0);
    }

    @Override
    public void apply(GameCharacter gameCharacter) {

    }

    @Override
    public void activate(GameCharacter gameCharacter) {

    }
}

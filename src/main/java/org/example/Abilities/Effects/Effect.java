package org.example.Abilities.Effects;


import org.example.Characters.GameCharacter;


public abstract class Effect {

    private String name;
    private String description;
    private int duration;

    public void apply(GameCharacter gameCharacter) {
        gameCharacter.addEffect(this);
    }


    public abstract void activate(GameCharacter gameCharacter);

    public void decrementDuration() {
        this.duration--;
    }


}

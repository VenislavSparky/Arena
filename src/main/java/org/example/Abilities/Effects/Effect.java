package org.example.Abilities.Effects;


import lombok.Getter;
import org.example.Characters.GameCharacter;

import java.io.Serializable;
import java.util.Objects;


@Getter
public abstract class Effect implements Cloneable, Serializable {

    private final String name;
    private final String description;
    private int duration;

    public Effect(String name, String description, int duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public void apply(GameCharacter gameCharacter) {
        gameCharacter.addEffect(this.clone());
    }

    public abstract void activate(GameCharacter gameCharacter);

    public void decrementDuration() {
        this.duration--;
    }

    @Override
    public Effect clone() {
        try {
            return (Effect) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Effect effect = (Effect) o;
        return Objects.equals(name, effect.name) && Objects.equals(description, effect.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}

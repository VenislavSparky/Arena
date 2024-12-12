package org.example.Characters;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Utils.TextUtil;

@NoArgsConstructor
@Embeddable
@Getter
public class Stats {

    private int stamina;
    private int strength;
    private int armor;
    private int intellect;

    public Stats(int stamina, int strength, int armor, int intellect) {
        this.stamina = stamina;
        this.strength = strength;
        this.armor = armor;
        this.intellect = intellect;;
    }

    public void add(Stats other) {
        this.stamina += other.stamina;
        this.strength += other.strength;
        this.armor += other.armor;
        this.intellect += other.intellect;
    }

    public void subtract(Stats other) {
        this.stamina -= other.stamina;
        this.strength -= other.strength;
        this.armor -= other.armor;
        this.intellect -= other.intellect;
    }


    @Override
    public String toString() {
        return String.format(TextUtil.WHITE +
                "Stats [Stamina=%d, Strength=%d, Armor=%d, Intellect=%d]" + TextUtil.RESET,
                getStamina(), getStrength(), getArmor(), getIntellect());
    }

}

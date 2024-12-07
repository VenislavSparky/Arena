package org.example.Characters;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class Stats {

    private double stamina;
    private double strength;
    private double armor;
    private double intellect;
    private double criticalChance;

    public Stats(double stamina, double strength, double armor, double intellect, double criticalChance) {
        this.stamina = stamina;
        this.strength = strength;
        this.armor = armor;
        this.intellect = intellect;
        this.criticalChance = criticalChance;
    }

    public void add(Stats other) {
        this.stamina += other.stamina;
        this.strength += other.strength;
        this.armor += other.armor;
        this.intellect += other.intellect;
        this.criticalChance += other.criticalChance;
    }

    public void subtract(Stats other) {
        this.stamina -= other.stamina;
        this.strength -= other.strength;
        this.armor -= other.armor;
        this.intellect -= other.intellect;
        this.criticalChance -= other.criticalChance;
    }

    public int getFinalStamina() {
        return (int) Math.round(stamina);
    }

    public int getFinalStrength() {
        return (int) Math.round(strength);
    }

    public int getFinalArmor() {
        return (int) Math.round(armor);
    }

    public int getFinalIntellect() {
        return (int) Math.round(intellect);
    }

    public int getFinalCriticalChance() {
        return (int) Math.round(criticalChance * 100);
    }

    @Override
    public String toString() {
        return String.format(
                "Stats [Stamina=%d, Strength=%d, Armor=%d, Intellect=%d, Critical Chance=%d%%]",
                getFinalStamina(), getFinalStrength(), getFinalArmor(), getFinalIntellect(), getFinalCriticalChance()
        );
    }

}

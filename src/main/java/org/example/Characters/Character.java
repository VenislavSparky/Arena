package org.example.Characters;

import org.example.Abilities.Ability;
import org.example.Equipments.Equipment;
import org.example.Equipments.Slot;

import java.util.List;
import java.util.Map;

public abstract class Character {

    private int level;
    private int gold;
    private int totalHealth;
    private int currentHealth;
    private int totalActionPoints;
    private int currentActionPoints = 3;

    private int strengthStat;
    private int armorStat;
    private int intellectStat;

    private Map<Slot, Equipment> equipments;
    private List<Ability> abilities;


    public abstract int useAbility(Character user, List<Character> allies, List<Character> enemies);

    public void takeDamage(int damage) {
        //TODO CHECK DEAD
        this.currentHealth -= damage;
    };

    public void heal(int health) {
        //TODO CHECK DEAD
        this.currentHealth += health;
    };

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getTotalActionPoints() {
        return totalActionPoints;
    }

    public void setTotalActionPoints(int totalActionPoints) {
        this.totalActionPoints = totalActionPoints;
    }

    public int getCurrentActionPoints() {
        return currentActionPoints;
    }

    public void setCurrentActionPoints(int currentActionPoints) {
        this.currentActionPoints = currentActionPoints;
    }

    public int getStrengthStat() {
        return strengthStat;
    }

    public void setStrengthStat(int strengthStat) {
        this.strengthStat = strengthStat;
    }

    public int getArmorStat() {
        return armorStat;
    }

    public void setArmorStat(int armorStat) {
        this.armorStat = armorStat;
    }

    public int getIntellectStat() {
        return intellectStat;
    }

    public void setIntellectStat(int intellectStat) {
        this.intellectStat = intellectStat;
    }

    public Map<Slot, Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Map<Slot, Equipment> equipments) {
        this.equipments = equipments;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
}

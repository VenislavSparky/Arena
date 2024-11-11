package org.example.Characters;

import org.example.Abilities.Ability;
import org.example.Equipments.Equipment;
import org.example.Equipments.Slot;

import java.util.Map;
import java.util.Set;

public abstract class Character {

    private int level;
    private int gold;
    private int totalHealth;
    private int currentHealth;
    private int totalActionPoints;
    private int currentActionPoints;
    private int actionPointsRegenPerTurn;

    private int attackPowerStat;
    private int armorStat;
    private int intellectStat;

    private Map<Slot, Equipment> equipments;
    private Set<Ability> abilities;


    public abstract int useAbility();

    public void takeDamage(int damage) {
        //TODO CHECK DEAD
        this.currentHealth -= damage;
    };

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getActionPointsRegenPerTurn() {
        return actionPointsRegenPerTurn;
    }

    public void setActionPointsRegenPerTurn(int actionPointsRegenPerTurn) {
        this.actionPointsRegenPerTurn = actionPointsRegenPerTurn;
    }

    public int getAttackPowerStat() {
        return attackPowerStat;
    }

    public void setAttackPowerStat(int attackPowerStat) {
        this.attackPowerStat = attackPowerStat;
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

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }
}

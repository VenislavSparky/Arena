package org.example.Characters;

import org.example.Abilities.Ability;
import org.example.Equipments.Equipment;
import org.example.Equipments.Slot;
import org.example.Helpers.TextColor;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class Character {

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



    public boolean selectAbility(Character user, List<Character> allies, List<Character> enemies) {
        if (abilities.isEmpty()) {
            System.out.println("No available abilities");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select ability:");
            for (int i = 0; i < abilities.size(); i++) {
                if (hasEnoughActionPoints(abilities.get(i).getActionPointsCost())){
                    System.out.println(TextColor.toGreen(i + 1 + ":" + abilities.get(i).getDescription()));
                }else {
                    System.out.println(TextColor.toRed(i + 1 + ":" + abilities.get(i).getDescription()));
                }

            }
            int selectedOption = Integer.parseInt(scanner.nextLine());
            if (selectedOption > 0 && selectedOption < abilities.size()) {
                if (hasEnoughActionPoints(abilities.get(selectedOption).getActionPointsCost())){
                    abilities.get(selectedOption).use(user, allies, enemies);
                }else {
                    System.out.println(TextColor.toRed("Not enough Action Points"));
                }

            }
        }
        return true;
    }

    public void takeDamage(int damage) {
        //TODO CHECK DEAD
        this.currentHealth -= damage;
    }


    public void heal(int health) {
        //TODO CHECK DEAD
        this.currentHealth += health;
    }



    public boolean isDead() {
        return currentHealth <= 0;
    }

    public boolean hasEnoughActionPoints(int actionPointsCost) {
        return currentActionPoints - actionPointsCost >= 0;
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

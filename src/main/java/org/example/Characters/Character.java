package org.example.Characters;

import lombok.Getter;
import lombok.Setter;
import org.example.Abilities.Ability;
import org.example.Abilities.MeleeAttack;
import org.example.Inventory.Equipments.Equipment;
import org.example.Inventory.Equipments.Slot;
import org.example.Helpers.TextColor;

import java.util.*;

@Getter
@Setter
public abstract class Character {

    private int gold;
    private int totalHealth;
    private int currentHealth;
    private int totalActionPoints;
    private int currentActionPoints;

    private int strengthStat;
    private int armorStat;
    private int intellectStat;

    private Map<Slot, Equipment> equipments;
    private List<Ability> abilities;
    private Set<ClassType> classType;//TODO

    protected Character(int totalHealth, int totalActionPoints,int strengthStat, int armorStat, int intellectStat ){
        this.totalHealth = totalHealth;
        this.currentHealth = totalHealth;
        this.totalActionPoints = totalActionPoints;
        this.currentActionPoints = totalActionPoints;
        this.strengthStat = strengthStat;
        this.armorStat = armorStat;
        this.intellectStat = intellectStat;
        this.equipments = new HashMap<>();
        this.abilities = new ArrayList<>();
        this.abilities.add(new MeleeAttack());
        this.classType = new HashSet<>();
        this.classType.add(ClassType.CHARACTER);
    }

    public boolean selectAbility(Character user, List<Character> allies, List<Character> enemies) {

        if (abilities.isEmpty()) {
            System.out.println("No available abilities");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select ability:");
            for (int i = 0; i < abilities.size(); i++) {
                if (hasEnoughActionPoints(abilities.get(i).getActionPointsCost())) {
                    System.out.println(TextColor.toGreen(i + 1 + ": " + abilities.get(i).getClass().getSimpleName()));
                } else {
                    System.out.println(TextColor.toRed(i + 1 + ": " + abilities.get(i).getClass().getSimpleName()));
                }
            }

            int selectedOption = Integer.parseInt(scanner.nextLine()) -1;
            if (selectedOption >= 0 && selectedOption < abilities.size()) {
                Ability ability = abilities.get(selectedOption);
                int actionPointsCost = ability.getActionPointsCost();

                if (hasEnoughActionPoints(actionPointsCost)){
                    ability.use(user,allies,enemies);
                    consumeActionPoints(actionPointsCost);
                    System.out.println(user.getClass().getSimpleName() + " used " + ability.getClass().getSimpleName());

                } else {
                    System.out.println(TextColor.toRed("Not enough Action Points"));
                }

            }
        }
        return true;
    }

    public void takeDamage(int damage) {
        this.currentHealth = Math.max(this.currentHealth - damage, 0);
    }


    public void heal(int health) {
        this.currentHealth = Math.min(this.currentHealth + health, totalHealth);
    }

    public boolean isDead() {
        return currentHealth == 0;
    }

    public boolean hasEnoughActionPoints(int actionPointsCost) {
        return currentActionPoints - actionPointsCost >= 0;
    }
    public void consumeActionPoints(int actionPointsCost) {
        currentActionPoints -= actionPointsCost;
    }

    public void resetActionPoints() {
        currentActionPoints = totalActionPoints;
    }


}

package org.example.Characters.PlayerCharacter;


import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Abilities.Ability;
import org.example.Characters.GameCharacter;
import org.example.Characters.CharacterClass;
import org.example.Characters.Stats;
import org.example.Utils.TextColorUtil;
import org.example.Characters.Inventory.Equipments.Equipment;
import org.example.Characters.Inventory.Equipments.Slot;

import java.util.List;
import java.util.Scanner;

@Getter
@jakarta.persistence.Entity
@NoArgsConstructor
@Table(name = "player_characters")
public class PlayerCharacter extends GameCharacter {

    private int wins;
    private int losses;

    public PlayerCharacter(String name, CharacterClass characterClass, int level, int maxHealth, int maxEnergy, Stats stats, Ability ability) {
        super(name, characterClass, level, maxHealth, maxEnergy, stats, ability);

    }

    @Override
    public boolean performActions(GameCharacter gameCharacter, List<GameCharacter> heroes, List<GameCharacter> monsters) {
        boolean isTurnEnded = gameCharacter.getCurrentEnergy() == 0;

        gameCharacter.chooseAbility(gameCharacter, heroes, monsters);
        //TODO CHECK IF end turn
        System.out.println("Energy left: " + gameCharacter.getCurrentEnergy());
        if (gameCharacter.getCurrentEnergy() == 0) {
            isTurnEnded = true;
        }
        return isTurnEnded;
    }

    @Override
    public void chooseAbility(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies) {
        Scanner scanner = new Scanner(System.in);
        if (getAbilities().isEmpty()) {
            System.out.println("No available abilities");
        } else {
            System.out.println("Select ability:");
            for (int i = 0; i < getAbilities().size(); i++) {
                if (hasEnoughEnergy(getAbilities().get(i).getEnergyCost())) {
                    System.out.println(TextColorUtil.toGreen(i + 1 + ": " + getAbilities().get(i).getClass().getSimpleName()));
                } else {
                    System.out.println(TextColorUtil.toRed(i + 1 + ": " + getAbilities().get(i).getClass().getSimpleName()));
                }
            }

            int selectedOption = Integer.parseInt(scanner.nextLine()) - 1;
            if (selectedOption >= 0 && selectedOption < getAbilities().size()) {
                Ability ability = getAbilities().get(selectedOption);

                int actionPointsCost = ability.getEnergyCost();
                if (hasEnoughEnergy(actionPointsCost)) {
                    ability.use(user, allies, enemies);
                    consumeEnergy(actionPointsCost);
                    System.out.println(user.getClass().getSimpleName() + " used " + ability.getClass().getSimpleName());
                } else {
                    System.out.println(TextColorUtil.toRed("Not enough Action Points"));
                }

            }
        }
    }

    public void updateWins() {
        wins++;
    }

    public void updateLosses() {
        losses++;
    }

    public void learnAbility(Ability ability) {
        //TODO VALIDATION
        getAbilities().add(ability);
    }

    public void equip(Equipment equipment) {
        //TODO EQUIP
        if (getEquipped().get(equipment.getSlotType()) != null) {
            unequip(equipment.getSlotType());
            equip(equipment);
        } else {
            getEquipped().put(equipment.getSlotType(), equipment);
            //TODO STATS
            System.out.printf("You equipped %s!\n", equipment);
            getInventory().remove(equipment);
        }
    }

    public void unequip(Slot slot) {
        Equipment unequipped = getEquipped().remove(slot);
        getInventory().add(unequipped);
        //TODO STATS
    }


    @Override
    public String toString() {
        return "PlayerCharacter{" +
                "wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}

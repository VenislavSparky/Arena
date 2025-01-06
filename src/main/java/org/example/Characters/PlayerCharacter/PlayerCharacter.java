package org.example.Characters.PlayerCharacter;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Abilities.Ability;
import org.example.Characters.GameCharacter;
import org.example.Characters.CharacterClass;
import org.example.Characters.Stats;
import org.example.Characters.Inventory.Equipments.Equipment;
import org.example.Characters.Inventory.Equipments.Slot;
import org.example.Networking.ClientHandler;
import org.example.Utils.TextUtil;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "player_characters")
public class PlayerCharacter extends GameCharacter {

    private int wins;
    private int losses;
    @Column(name = "current_exp")
    private int currentExp;

    public PlayerCharacter(String name, CharacterClass characterClass, int level, int maxHealth, int maxEnergy, Stats stats, Ability ability) {
        super(name, characterClass, level, maxHealth, maxEnergy, stats, ability);

    }

    @Override
    public boolean performActions(List<GameCharacter> heroes, List<GameCharacter> monsters) {
        return CombatManager.handleActions(this, heroes, monsters);
    }

    public boolean performActions(PlayerCharacter playerOne, ClientHandler playerOneClientHandler, PlayerCharacter playerTwo,ClientHandler playerTwoClientHandler) {
        return MultiplayerCombatManager.handleActions(playerOne, playerOneClientHandler, playerTwo, playerTwoClientHandler);
    }

    public void updateWins() {
        wins++;
    }

    public void updateLosses() {
        losses++;
    }

    public void receiveGold(int gold) {
        setGold(getGold() + gold);
        System.out.printf("+ %d gold.%n", gold);
    }

    public void learnAbility(Ability ability) {
        //TODO VALIDATION ?
        getAbilities().add(ability);
    }

    public void equip(Equipment equipment) {
        if (getEquipped().get(equipment.getSlotType()) != null) {
            unequip(equipment.getSlotType());
            equip(equipment);
        } else {
            getEquipped().put(equipment.getSlotType(), equipment);
            System.out.printf("You equipped %s!\n", equipment);
            getInventory().remove(equipment);
            getStats().add(equipment.getStats());
        }
    }

    public void unequip(Slot slot) {
        Equipment unequipped = getEquipped().remove(slot);
        getInventory().add(unequipped);
        getStats().subtract(unequipped.getStats());
    }


    public void gainExp(int exp) {
        int totalCurrentExp = currentExp + exp;
        if (totalCurrentExp >= 100) {
            levelUp();
            currentExp = totalCurrentExp - 100;
        } else {
            currentExp += exp;
        }
        System.out.printf("+ %d exp. %d/100\n", exp, currentExp);
    }

    private void levelUp() {
        setLevel(getLevel() + 1);
        System.out.println("Level up! " + getLevel());
    }

    @Override
    public String toString() {
        return String.format(TextUtil.WHITE + "%s (Level %d %s) Health: %d |  Energy: %d , Gold: %d , Wins: %d  |  Losses: %d " + TextUtil.RESET,
                getName(), getLevel(), getCharacterClass(), getMaxHealth(), getMaxEnergy(), getGold(), getWins(), getLosses());
    }
}

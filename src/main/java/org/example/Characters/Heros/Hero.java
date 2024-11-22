package org.example.Characters.Heros;


import lombok.Getter;
import org.example.Abilities.Ability;
import org.example.Characters.Character;
import org.example.Inventory.Equipments.Equipment;
import org.example.Inventory.InventoryItem;

import java.util.List;

@Getter
public abstract class Hero extends Character {

    private String name;
    private List<InventoryItem> inventory;

    protected Hero(int totalHealth, int totalActionPoints, int strengthStat, int armorStat, int intellectStat) {
        super(totalHealth, totalActionPoints, strengthStat, armorStat, intellectStat);
    }

    public void learnAbility(Ability ability) {
        //TODO VALIDATION
        getAbilities().add(ability);
    }

    public void addToInventory(InventoryItem inventoryItem) {
        //TODO VALIDATION
       this.inventory.add(inventoryItem);
    }


    public void equip(Equipment equipment) {
        //TODO EQUIP
        if (getEquipments().get(equipment.getSlotType()) != null) {
            unequip(getEquipments().get(equipment.getSlotType()));
        } else {
            getEquipments().put(equipment.getSlotType(), equipment);
            // equipment.getArmorStat();
            // this.intellectStat += equipment.getIntellectStat();
            // this.strengthStat += equipment.getStrengthStat();
        }
    }

    public void unequip(Equipment equipment) {
        //TODO unequip item
        //this.armorStat -= equipment.getArmorStat();
        // this.intellectStat -= equipment.getIntellectStat();
        // this.strengthStat -= equipment.getStrengthStat();
    }


}

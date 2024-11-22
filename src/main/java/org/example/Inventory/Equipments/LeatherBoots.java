package org.example.Inventory.Equipments;

import org.example.Inventory.InventoryItem;

public class LeatherBoots extends Equipment implements InventoryItem {
    public LeatherBoots() {
        super(Slot.FEET, 5, 1, 2);
    }
}

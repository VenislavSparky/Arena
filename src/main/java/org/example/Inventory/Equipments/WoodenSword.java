package org.example.Inventory.Equipments;

import org.example.Inventory.InventoryItem;

public class WoodenSword extends Equipment implements InventoryItem {

    public WoodenSword() {
        super(Slot.HAND, 10, 1, 2);
    }
}

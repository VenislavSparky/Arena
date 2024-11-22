package org.example.Inventory.Equipments;

import org.example.Inventory.InventoryItem;

public class Crown extends Equipment implements InventoryItem {
    public Crown() {
        super(Slot.HEAD, 1, 1, 10);
    }
}

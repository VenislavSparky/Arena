package org.example.Characters.Inventory.Equipments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Slot {
    HEAD("Helmet"),
    CHEST("Tunic"),
    LEGS("Pants"),
    FEET("Boots"),
    HAND("Gloves");

    private final String displayName;

}

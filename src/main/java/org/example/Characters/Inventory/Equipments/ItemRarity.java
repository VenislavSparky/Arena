package org.example.Characters.Inventory.Equipments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemRarity {
    COMMON("Common", 1),
    UNCOMMON("Uncommon", 1.25),
    RARE("Rare", 1.75),
    EPIC("Epic", 2.25),
    LEGENDARY("Legendary", 3);

    private final String displayName;
    private final double multiplier;

}

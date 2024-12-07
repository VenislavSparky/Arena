package org.example.Characters.Inventory.Equipments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArmorType {
    CLOTH("Cloth",0.1,0.05, 0.2, 0.6),
    LEATHER("Leather",0.1,0.7, 0.2, 0.3),
    PLATE("Plate",0.3,0.4, 0.6, 0.05);

    private final String displayName;
    private final double staminaMultiplier;
    private final double strengthMultiplier;
    private final double armorMultiplier;
    private final double intellectMultiplier;

}

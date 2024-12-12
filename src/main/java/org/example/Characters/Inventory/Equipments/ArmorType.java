package org.example.Characters.Inventory.Equipments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArmorType {
    CLOTH("Cloth",0.1f,0.05f, 0.2f, 0.6f),
    LEATHER("Leather",0.1f,0.7f, 0.2f, 0.3f),
    PLATE("Plate",0.3f,0.4f, 0.6f, 0.05f);

    private final String displayName;
    private final float staminaMultiplier;
    private final float strengthMultiplier;
    private final float armorMultiplier;
    private final float intellectMultiplier;

}

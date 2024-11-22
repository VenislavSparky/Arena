package org.example.Inventory.Equipments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Equipment {

    private Slot slotType;
    private int strengthStat;
    private int armorStat;
    private int intellectStat;

}

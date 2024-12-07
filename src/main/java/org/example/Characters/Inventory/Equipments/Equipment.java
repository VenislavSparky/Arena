package org.example.Characters.Inventory.Equipments;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Characters.Stats;
import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Utils.TextColorUtil;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Slot slotType;

    @Enumerated(EnumType.STRING)
    private ItemRarity itemRarity;

    @Enumerated(EnumType.STRING)
    private ArmorType armorType;

    @Embedded
    private Stats stats;

    public Equipment(String name, Slot slotType, ItemRarity itemRarity, ArmorType armorType, Stats stats) {
        this.name = name;
        this.slotType = slotType;
        this.itemRarity = itemRarity;
        this.armorType = armorType;
        this.stats = stats;
    }

    @Override
    public String toString() {
        String text = String.format("%s Stats: %s", name, stats );
        return TextColorUtil.toRarityColor(itemRarity, text);
    }

}

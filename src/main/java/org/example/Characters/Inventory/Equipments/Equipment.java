package org.example.Characters.Inventory.Equipments;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Characters.Inventory.Inventory;
import org.example.Characters.Stats;
import org.example.Utils.TextUtil;

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

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

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
        return TextUtil.toRarityColor(itemRarity, text);
    }

}

package org.example.Characters.Inventory;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.Characters.Inventory.Consumables.Consumable;
import org.example.Characters.Inventory.Consumables.ConsumableQuantity;
import org.example.Characters.Inventory.Equipments.Equipment;
import org.example.Characters.PlayerCharacter.PlayerCharacter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventories")
@Getter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "inventory", cascade = CascadeType.ALL)
    private PlayerCharacter playerCharacter;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "inventory", orphanRemoval = true)
    private List<ConsumableQuantity> consumables = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Equipment> equipments = new ArrayList<>();

    public void add(Consumable consumable) {
        for (ConsumableQuantity cq : consumables) {
            if (cq.getConsumable().getClass().equals(consumable.getClass())) {
                cq.incrementQuantity();
                System.out.printf("Inventory: 1 x %s added! Total: %d\n", cq.getConsumable().getClass().getSimpleName(), cq.getQuantity());
                return;
            }
        }
        System.out.printf("Inventory: 1 x %s added!\n",consumable.getClass().getSimpleName());
        consumables.add(new ConsumableQuantity(this, consumable, 1));
    }

    public void remove(Consumable consumable) {
        consumables.removeIf(cq -> cq.decrementQuantityIfMatches(consumable));
    }

    public void add(Equipment equipment) {
        equipments.add(equipment);
    }

    public void remove(Equipment equipment) {
        equipments.remove(equipment);
    }

    public void showInventory() {
        System.out.println("Consumables:");
        if (consumables.isEmpty()) {
            System.out.println("No consumables in inventory.");
        } else {
            consumables.forEach(System.out::println);
        }

        System.out.println("\nEquipments:");
        if (equipments.isEmpty()) {
            System.out.println("No equipment in inventory.");
        } else {
            equipments.forEach(System.out::println);
        }
    }
}

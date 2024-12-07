package org.example.Characters.Inventory.Consumables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Characters.Inventory.Inventory;

@Entity
@Table(name = "inventory_consumables")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumableQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne(cascade = CascadeType.ALL)
    private Consumable consumable;

    private int quantity;

    public ConsumableQuantity(Inventory inventory, Consumable consumable, int quantity) {
        this.inventory = inventory;
        this.consumable = consumable;
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public boolean decrementQuantityIfMatches(Consumable consumable) {
        if (this.consumable.getClass().equals(consumable.getClass())) {
            quantity--;
            return quantity <= 0;
        }
        return false;
    }

    @Override
    public String toString() {
        return consumable.getClass().getSimpleName() + " x " + quantity;
    }
}

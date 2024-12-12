package org.example.Characters.Inventory.Consumables;

import jakarta.persistence.Entity;
import org.example.Characters.GameCharacter;

@Entity
public class HealthPotion extends Consumable {

    public HealthPotion() {
        super("Restore 50 health.");
    }


    @Override
    public void use(GameCharacter gameCharacter) {
            gameCharacter.receiveHeal(50);
    }
}

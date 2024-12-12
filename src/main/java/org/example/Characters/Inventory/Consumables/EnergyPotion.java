package org.example.Characters.Inventory.Consumables;

import jakarta.persistence.Entity;
import org.example.Characters.GameCharacter;

@Entity
public class EnergyPotion extends Consumable {

    public EnergyPotion() {
        super("Restores 30 Energy.");
    }

    @Override
    public void use(GameCharacter gameCharacter) {
        gameCharacter.receiveEnergy();
    }
}

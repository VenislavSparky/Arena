package org.example.Characters.Inventory.Consumables;

import jakarta.persistence.Entity;
import org.example.Characters.GameCharacter;

@Entity
public class EnergyPotion extends Consumable {
    @Override
    public String getDescription() {
        return "Restores all Energy. Can be used only once per battle!";
    }

    @Override
    public void use(GameCharacter gameCharacter) {
        gameCharacter.regenEnergy();
    }
}

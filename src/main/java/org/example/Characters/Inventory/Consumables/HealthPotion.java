package org.example.Characters.Inventory.Consumables;

import jakarta.persistence.Entity;
import org.example.Characters.GameCharacter;

@Entity
public class HealthPotion extends Consumable {

    @Override
    public String getDescription() {
        return "Restore 20 health. Can be used only once per battle!";
    }

    @Override
    public void use(GameCharacter gameCharacter) {
            gameCharacter.setCurrentHealth(gameCharacter.getCurrentHealth() + 50);
    }
}

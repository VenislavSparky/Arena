package org.example.Characters.Inventory.Consumables;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.Characters.GameCharacter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "consumable_type")
@Table(name = "consumables")
public abstract class Consumable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract String getDescription();

    public abstract void use(GameCharacter gameCharacter);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

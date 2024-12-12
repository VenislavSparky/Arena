package org.example.Characters.Inventory.Consumables;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.Characters.GameCharacter;

import java.util.Objects;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "consumable_type")
@Table(name = "consumables")
public abstract class Consumable {

    public Consumable(String description) {
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    public abstract void use(GameCharacter gameCharacter);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumable that = (Consumable) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description);
    }
}

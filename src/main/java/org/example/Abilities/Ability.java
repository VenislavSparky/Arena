package org.example.Abilities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Abilities.Effects.Effect;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetingStrategy;
import org.example.Characters.GameCharacter;
import org.example.Characters.CharacterClass;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "abilities")
@DiscriminatorColumn(name = "ability_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int energyCost;
    private String description;
    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;

    @Transient
    private Effect effect;
    @Transient
    private TargetingStrategy targetingStrategy;

    public Ability(int energyCost, String description, CharacterClass characterClass, Effect effect, TargetingStrategy targetingStrategy) {
        this.energyCost = energyCost;
        this.description = description;
        this.characterClass = characterClass;
        this.effect = effect;
        this.targetingStrategy = targetingStrategy;
    }

    public abstract void use(GameCharacter user, List<GameCharacter> allies, List<GameCharacter> enemies, TargetSelectionMode targetSelectionMode);


    public boolean isAvailable(GameCharacter user) {
        return user.hasEnoughEnergy(this.getEnergyCost());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ability ability = (Ability) o;
        return energyCost == ability.energyCost && Objects.equals(id, ability.id) && Objects.equals(description, ability.description) && characterClass == ability.characterClass && Objects.equals(effect, ability.effect) && Objects.equals(targetingStrategy, ability.targetingStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, energyCost, description, characterClass, effect, targetingStrategy);
    }




}

package org.example.Characters;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Abilities.Ability;
import org.example.Abilities.Effects.Effect;
import org.example.Characters.Inventory.Equipments.Equipment;
import org.example.Characters.Inventory.Equipments.Slot;
import org.example.Characters.Inventory.Inventory;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class GameCharacter implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "class")
    private CharacterClass characterClass;
    private int level;
    @Column(name = "max_health")
    private int maxHealth;
    @Column(name = "max_energy")
    private int maxEnergy;

    @Transient
    private int currentHealth;
    @Transient
    private int currentEnergy;

    @Embedded
    private Stats stats;
    private int gold;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Ability> abilities = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Map<Slot, Equipment> equipped = new HashMap<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory inventory = new Inventory();

    @Transient
    private Set<Effect> effects = new LinkedHashSet<>();


    public GameCharacter(String name, CharacterClass characterClass, int level, int maxHealth, int maxEnergy, Stats stats, Ability ability) {
        this.name = name;
        this.characterClass = characterClass;
        this.level = level;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy;
        this.stats = stats;
        this.abilities.add(ability);
    }

    public abstract boolean performActions(List<GameCharacter> heroes, List<GameCharacter> monsters);

    public void endTurn() {
        receiveEnergy();
        activateEffects();
    }

    public int getMaxHealth() {
        return maxHealth + getStats().getStamina();
    }

    public int receiveDamage(int damage) {
        int trueDamage = Math.max(damage - getStats().getArmor(), 0);
        setCurrentHealth(Math.max(getCurrentHealth() - trueDamage, 0));
        //System.out.printf("%s took %d damage. Health left: %d\n", getName(), trueDamage, getCurrentHealth());
        return trueDamage;
    }

    public void receiveHeal(int health) {
        setCurrentHealth(Math.min(getCurrentHealth() + health, getMaxHealth()));
        System.out.printf("%s restored %d health. Health left: %d\n", getName(), health, getCurrentHealth());
    }

    public boolean isDead() {
        return getCurrentHealth() <= 0;
    }

    public boolean hasEnoughEnergy(int energyCost) {
        return getCurrentEnergy() - energyCost >= 0;
    }

    public void consumeEnergy(int energyCost) {
        currentEnergy -= energyCost;
    }

    public void receiveEnergy() {
        setCurrentEnergy(Math.min(getCurrentEnergy() + 30, getMaxEnergy()));
    }

    public void receiveEnergy(int energy) {
        setCurrentEnergy(Math.min(getCurrentEnergy() + energy, getMaxEnergy()));
    }

    public void initCurrentStats() {
        setCurrentHealth(getMaxHealth());
        setCurrentEnergy(getMaxEnergy());
        getEffects().clear();
    }


    public void addEffect(Effect effect) {
        getEffects().add(effect);
    }

    public void removeEffect(Effect effect) {
        getEffects().remove(effect);
    }

    public void activateEffects() {
        for (Effect effect : effects) {
            if (effect.getDuration() == 0) {
                removeEffect(effect);
                return;
            }
            effect.activate(this);
            effect.decrementDuration();
        }


    }

}

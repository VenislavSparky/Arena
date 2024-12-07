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

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class GameCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;
    private int level;
    private int maxHealth;
    private int maxEnergy;

    @Transient
    private int currentHealth;
    @Transient
    private int currentEnergy;

    @Embedded
    private Stats stats;
    private int gold = 100;

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
        //todo learn
        this.abilities.add(ability);
    }

    public abstract boolean performActions(GameCharacter gameCharacter, List<GameCharacter> heroes, List<GameCharacter> monsters);

    public abstract void chooseAbility(GameCharacter user, List<GameCharacter> heroes, List<GameCharacter> monsters);

    public void endTurn() {
        regenEnergy();
    }

    public void startTurn() {
        activateEffects();
    }

    public int getMaxHealth() {
        return maxHealth + getStats().getFinalArmor();
    }

    public int receiveDamage(int damage) {
        int trueDamage = Math.max(damage - getStats().getFinalArmor(), 0);
        setCurrentHealth(getCurrentHealth() - trueDamage);
        System.out.printf("%s took %d damage. Health left - %d\n", getName(), trueDamage, getCurrentHealth());
        return trueDamage;
    }

    public void receiveHeal(int health) {
        setCurrentHealth(Math.min(getCurrentHealth() + health, getMaxHealth()));
    }

    public boolean isDead() {
        return getCurrentHealth() <= 0;
    }

    public boolean hasEnoughEnergy(int energyCost) {
        return getCurrentEnergy() - energyCost >= 0;
    }

    public void consumeEnergy(int energyCost) {
        //TODO CHECK ACTION POINTS
        currentEnergy -= energyCost;
    }

    public void regenEnergy() {
        setCurrentEnergy(Math.min(getCurrentEnergy() + 30, getMaxEnergy()));
    }

    public void initCurrentStats() {
        setCurrentHealth(getMaxHealth());
        setCurrentEnergy(getMaxEnergy());
    }


    public void addEffect(Effect effect) {
        getEffects().add(effect);
    }

    public void removeEffect(Effect effect) {
        getEffects().remove(effect);
    }

    public void activateEffects() {
        for (Effect effect : effects) {
            effect.activate(this);
            effect.decrementDuration();
        }

    }

}
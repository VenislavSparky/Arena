package org.example.Abilities;

import jakarta.persistence.EntityManager;
import org.example.Abilities.MageAbilities.Fireball;
import org.example.Abilities.MageAbilities.Flamestrike;
import org.example.Abilities.MageAbilities.Ignite;
import org.example.Abilities.PaladinAbilities.CrusaderStrike;
import org.example.Abilities.PaladinAbilities.DivineStorm;
import org.example.Abilities.PaladinAbilities.FlashOfLight;
import org.example.Abilities.PaladinAbilities.LayOfHands;
import org.example.Abilities.WarriorAbilities.Bloodrage;
import org.example.Abilities.WarriorAbilities.Execute;
import org.example.Abilities.WarriorAbilities.MortalStrike;
import org.example.Abilities.WarriorAbilities.Whirlwind;
import org.example.Exceptions.AbilityRegistryException;
import org.example.Utils.EntityManagerFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbilityRegistry {

    private static final Map<String, Ability> abilities = new HashMap<>();
    private static final Map<String, Class<? extends Ability>> abilityClassMap = new HashMap<>();


    static {
        registerAbilityClass(Fireball.class);
        registerAbilityClass(Flamestrike.class);
        registerAbilityClass(Ignite.class);
        registerAbilityClass(CrusaderStrike.class);
        registerAbilityClass(DivineStorm.class);
        registerAbilityClass(FlashOfLight.class);
        registerAbilityClass(LayOfHands.class);
        registerAbilityClass(Bloodrage.class);
        registerAbilityClass(Execute.class);
        registerAbilityClass(MortalStrike.class);
        registerAbilityClass(Whirlwind.class);
        loadAbilitiesFromDatabase();
        loadNewAbilitiesToDatabase();
    }

    private static void registerAbilityClass(Class<? extends Ability> abilityClass) {
        try {
            Ability abilityInstance = abilityClass.getDeclaredConstructor().newInstance();
            abilityClassMap.put(abilityInstance.getClass().getSimpleName(), abilityClass);
        } catch (Exception e) {
            throw new AbilityRegistryException("Failed to register ability class: " + abilityClass.getSimpleName());
        }
    }

    private static void loadAbilitiesFromDatabase() {
        try (EntityManager em = EntityManagerFactoryUtil.getEntityManager()) {
            List<Ability> dbAbilities = em.createQuery("SELECT a FROM Ability a", Ability.class).getResultList();

            for (Ability dbAbility : dbAbilities) {
                Class<? extends Ability> abilityClass = abilityClassMap.get(dbAbility.getClass().getSimpleName());
                if (abilityClass != null) {
                    Ability ability = abilityClass.getDeclaredConstructor().newInstance();
                    ability.setId(dbAbility.getId());
                    abilities.put(ability.getClass().getSimpleName(), ability);
                } else {
                    System.err.println("No matching class found for ability: " + dbAbility.getClass().getSimpleName());
                }
            }

        } catch (Exception e) {
            throw new AbilityRegistryException("Failed to load abilities from the database");
        }
    }

    private static void loadNewAbilitiesToDatabase() {
        for (String abilityName : abilityClassMap.keySet()) {
            if (!abilities.containsKey(abilityName)) {
                persistAbility(abilityClassMap.get(abilityName));
            }
        }
    }


    private static void persistAbility(Class<? extends Ability> abilityClass) {
        EntityManager em = null;
        try {
            em = EntityManagerFactoryUtil.getEntityManager();
            em.getTransaction().begin();

            Ability ability = abilityClass.getDeclaredConstructor().newInstance();

            em.persist(ability);
            em.getTransaction().commit();

            abilities.put(ability.getClass().getSimpleName(), ability);
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new AbilityRegistryException("Failed to persist ability: " + abilityClass.getSimpleName());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Ability getAbility(String name) {
        return abilities.get(name);
    }

    public static Map<String, Ability> getAllAbilities() {
        return Map.copyOf(abilities);
    }

}
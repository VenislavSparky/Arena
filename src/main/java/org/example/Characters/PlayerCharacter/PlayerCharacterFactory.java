package org.example.Characters.PlayerCharacter;

import jakarta.persistence.EntityManager;
import org.example.Abilities.AbilityRegistry;
import org.example.Characters.CharacterClass;
import org.example.Characters.Stats;
import org.example.Utils.EntityManagerFactoryUtil;
import org.example.Utils.TextUtil;

import java.util.Arrays;
import java.util.Scanner;

public class PlayerCharacterFactory {

    public static PlayerCharacter createPlayerCharacter() {
        Scanner scanner = new Scanner(System.in);

        PlayerCharacter playerCharacter = null;

        System.out.println("Creating new Character!\nPlease choose class type! (Enter class type name)");
        PlayerCharacterFactory.listAvailableClasses();
        String characteClassInput = scanner.nextLine().trim().toUpperCase();

        while (!isValidClass(characteClassInput)) {
            System.out.println(TextUtil.toRed("Invalid class try again!"));
            characteClassInput = scanner.nextLine().trim().toUpperCase();
        }

        System.out.println("You have selected class: " + characteClassInput + "\nEnter name: ");
        String name = scanner.nextLine();

        EntityManager em = null;
        try {
            em = EntityManagerFactoryUtil.getEntityManager();
            em.getTransaction().begin();
            playerCharacter = getPlayerCharacter(CharacterClass.valueOf(characteClassInput), name);
            em.persist(playerCharacter);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to persist playerCharacter: " + playerCharacter);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return playerCharacter;
    }

    private static PlayerCharacter getPlayerCharacter(CharacterClass characterClass, String name) {
        switch (characterClass) {
            case WARRIOR -> {
                return new PlayerCharacter(name, characterClass, 1,
                        100, 100,
                        new Stats(0, 5, 2, 1), AbilityRegistry.getAbility("MortalStrike"));
            }
            case MAGE -> {
                return new PlayerCharacter(name, characterClass, 1,
                        100, 100,
                        new Stats(0, 1, 1, 6), AbilityRegistry.getAbility("Fireball"));
            }

            case PALADIN -> {
                return new PlayerCharacter(name, characterClass, 1,
                        100, 100,
                        new Stats(0, 4, 4, 1), AbilityRegistry.getAbility("CrusaderStrike"));
            }
        }
        return null;
    }

    private static boolean isValidClass(String inputHeroClass) {
        return Arrays.stream(CharacterClass.values())
                .anyMatch(armorType -> armorType.name().equalsIgnoreCase(inputHeroClass));
    }

    public static void listAvailableClasses() {
        Arrays.stream(CharacterClass.values()).forEach(c -> System.out.println(c.toString() + " - " +  c.getDescription()));
    }
}

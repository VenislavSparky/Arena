package org.example.Characters.PlayerCharacter;

import org.example.Abilities.AbilityRegistry;
import org.example.Characters.CharacterClass;
import org.example.Characters.Stats;
import org.example.Exceptions.DatabaseOperationException;
import org.example.Repositories.PlayerCharacterRepository;
import org.example.Repositories.impl.PlayerCharacterRepositoryImpl;
import org.example.Utils.TextUtil;

import java.util.Arrays;
import java.util.Scanner;

public class PlayerCharacterFactory {

    private static final PlayerCharacterRepository playerRepository = new PlayerCharacterRepositoryImpl();

    public static PlayerCharacter createPlayerCharacter() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating new Character!\nPlease choose class type! (Enter class type name)");
        PlayerCharacterFactory.listAvailableClasses();
        String characteClassInput = scanner.nextLine().trim().toUpperCase();

        while (!isValidClass(characteClassInput)) {
            System.out.println(TextUtil.toRed("Invalid class try again!"));
            characteClassInput = scanner.nextLine().trim().toUpperCase();
        }

        System.out.println("You have selected class: " + characteClassInput + "\nEnter name: ");
        String name = scanner.nextLine();

        PlayerCharacter playerCharacter = getPlayerCharacter(CharacterClass.valueOf(characteClassInput), name);

        try {
            playerRepository.saveOrUpdate(playerCharacter);
        }catch (DatabaseOperationException e){
            System.out.println(e.getMessage());
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
        Arrays.stream(CharacterClass.values()).forEach(c -> System.out.println(c.toString() + " - " + c.getDescription()));
    }
}

package org.example.Interactables.Trainers;

import org.example.Abilities.Ability;
import org.example.Abilities.AbilityRegistry;
import org.example.Interactables.Interactable;
import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Utils.TextColorUtil;

import java.util.List;
import java.util.Scanner;

public class ClassTrainer implements Interactable {

    @Override
    public void interact(PlayerCharacter playerCharacter) {
        Scanner scanner = new Scanner(System.in);

        List<Ability> availableAbilities = AbilityRegistry.getAllAbilities()
                .values().stream()
                .filter(ability ->
                        ability.getCharacterClass().equals(playerCharacter.getCharacterClass()) &&
                                !playerCharacter.getAbilities().contains(ability))
                .toList();

        if (availableAbilities.isEmpty()) {
            System.out.println(TextColorUtil.toRed("No available abilities to learn! :("));
            return;
        }

        printAvailableAbilities(availableAbilities);


        int selectedIndex = -1;
        while (selectedIndex < 0 || selectedIndex >= availableAbilities.size()) {
            System.out.println("Select an ability to learn (number):");
            try {
                selectedIndex = Integer.parseInt(scanner.nextLine()) - 1;
                if (selectedIndex < 0 || selectedIndex >= availableAbilities.size()) {
                    System.out.println(TextColorUtil.toRed("Invalid selection. Please try again."));
                }
            } catch (NumberFormatException e) {
                System.out.println(TextColorUtil.toRed("Invalid input! Please enter a number."));
            }
        }

        Ability selectedAbility = availableAbilities.get(selectedIndex);
        playerCharacter.learnAbility(selectedAbility);

        System.out.println(TextColorUtil.toGreen("Successfully learned: " + selectedAbility.getDescription()));

    }

    private static void printAvailableAbilities(List<Ability> availableAbilities) {
        System.out.println(TextColorUtil.toGreen("Available abilities to learn:"));
        for (int i = 0; i < availableAbilities.size(); i++) {
            Ability ability = availableAbilities.get(i);
            System.out.printf("%d. %s\n", i + 1, ability.getDescription());
        }
    }
}

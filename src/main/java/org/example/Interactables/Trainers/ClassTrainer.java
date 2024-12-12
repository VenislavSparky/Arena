package org.example.Interactables.Trainers;

import org.example.Abilities.Ability;
import org.example.Abilities.AbilityRegistry;
import org.example.Interactables.Interactable;
import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Utils.TextUtil;

import java.util.List;
import java.util.Scanner;

public class ClassTrainer implements Interactable {
    private static final int ABILITY_COST = 40;

    @Override
    public void interact(PlayerCharacter playerCharacter) {
        Scanner scanner = new Scanner(System.in);

        List<Ability> availableAbilities = getAvailableAbilitiesForPlayer(playerCharacter);
        if (availableAbilities.isEmpty()) {
            System.out.println(TextUtil.toRed("No available abilities to learn! :("));
            return;
        }

        printAvailableOptions(availableAbilities);

        int selectedIndex = getAbilitySelection(scanner, availableAbilities.size());
        if (selectedIndex == -1) {
            return;
        }

        Ability selectedAbility = availableAbilities.get(selectedIndex);

        if (playerCharacter.getGold() < ABILITY_COST) {
            System.out.println(TextUtil.toRed("Not enough gold to learn this ability! Requires " + ABILITY_COST + " gold."));
        } else {
            playerCharacter.setGold(playerCharacter.getGold() - ABILITY_COST);
            playerCharacter.learnAbility(selectedAbility);
            System.out.println(TextUtil.toGreen("Successfully learned: " + selectedAbility.getClass().getSimpleName()));
            System.out.println(TextUtil.toGreen("Remaining gold: " + playerCharacter.getGold()));
        }
    }

    private List<Ability> getAvailableAbilitiesForPlayer(PlayerCharacter playerCharacter) {
        return AbilityRegistry.getAllAbilities()
                .values().stream()
                .filter(ability ->
                        ability.getCharacterClass().equals(playerCharacter.getCharacterClass()) &&
                                !playerCharacter.getAbilities().contains(ability))
                .toList();
    }

    private void printAvailableOptions(List<Ability> availableAbilities) {
        System.out.println(TextUtil.toGreen("Available abilities to learn: (Cost: " + ABILITY_COST + " gold each)"));
        int i;
        for (i = 0; i < availableAbilities.size(); i++) {
            Ability ability = availableAbilities.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, ability.getClass().getSimpleName(), ability.getDescription());
        }
        System.out.println((i + 1) + ". Exit");
    }

    private int getAbilitySelection(Scanner scanner, int abilityCount) {
        int selectedIndex = -1;

        while (selectedIndex < 0 || selectedIndex > abilityCount) {
            System.out.println("Select an ability to learn (number) or " + (abilityCount + 1) + " to exit:");
            try {
                selectedIndex = Integer.parseInt(scanner.nextLine()) - 1;

                if (selectedIndex == abilityCount) {
                    return -1;
                }

                if (selectedIndex < -1 || selectedIndex >= abilityCount) {
                    System.out.println(TextUtil.toRed("Invalid selection. Please try again."));
                }
            } catch (NumberFormatException e) {
                System.out.println(TextUtil.toRed("Invalid input! Please enter a number."));
            }
        }
        return selectedIndex;
    }
}



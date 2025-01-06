package org.example.Characters.PlayerCharacter;

import org.example.Abilities.Ability;
import org.example.Abilities.TargetingStrategies.TargetSelection.PlayerSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Characters.GameCharacter;
import org.example.Characters.Inventory.Consumables.ConsumableQuantity;
import org.example.Utils.TextUtil;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CombatManager {

    private static final TargetSelectionMode targetSelectionMode = new PlayerSelectionMode();

    private static final Scanner SCANNER = new Scanner(System.in);

    public static boolean handleActions(PlayerCharacter player, List<GameCharacter> allies, List<GameCharacter> enemies) {
        displayOptions();

        System.out.println("Energy: " + player.getCurrentEnergy());
        int choice = getPlayerChoice();
        switch (choice) {
            case 1 -> selectAndUseAbility(player, allies, enemies);
            case 2 -> useConsumable(player);
            case 3 -> {
                return true;
            }
            default -> System.out.println(TextUtil.toRed("Invalid choice. Try again."));

        }
        return false;
    }

    private static void displayOptions() {
        System.out.println("Select an action:");
        int index = 1;
        System.out.println(index + ": Use ability");
        System.out.println((index + 1) + ": Use consumable");
        System.out.println((index + 2) + ": End turn");
    }

    private static void displayAbilityOptions(PlayerCharacter player) {
        System.out.println("Select an ability:");
        int index = 1;
        for (Ability ability : player.getAbilities()) {
            String abilityName = ability.getClass().getSimpleName();
            String color = ability.isAvailable(player) ? TextUtil.GREEN : TextUtil.RED;
            System.out.println(TextUtil.colorize(index + ": " + abilityName, color));
            index++;
        }
    }

    private static int getPlayerChoice() {
        System.out.print("Your choice: ");
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return -1;
        }
    }

    private static void selectAndUseAbility(PlayerCharacter player, List<GameCharacter> allies, List<GameCharacter> enemies) {
        displayAbilityOptions(player);

        int abilityIndex = getPlayerChoice() - 1;

        Optional<Ability> selectedAbility = getAbilityByIndex(player, abilityIndex);
        if (selectedAbility.isEmpty()) {
            System.out.println(TextUtil.toRed("Invalid ability selection."));
            return;
        }

        Ability ability = selectedAbility.get();
        if (!ability.isAvailable(player)) {
            System.out.println(TextUtil.toRed("You cannot use that ability."));
            return;
        }

        System.out.println(TextUtil.toGreen(player.getName() + " used " + ability.getClass().getSimpleName()));
        player.consumeEnergy(ability.getEnergyCost());
        ability.use(player, allies, enemies, targetSelectionMode);
    }

    private static Optional<Ability> getAbilityByIndex(PlayerCharacter player, int index) {
        if (index >= 0 && index < player.getAbilities().size()) {
            return Optional.of(player.getAbilities().get(index));
        }
        return Optional.empty();
    }


    private static void useConsumable(PlayerCharacter player) {
        List<ConsumableQuantity> consumables = player.getInventory().getConsumables();
        if (consumables.isEmpty()) {
            System.out.println(TextUtil.toRed("No consumables available!"));
            return;
        }

        System.out.println("Select a consumable to use:");
        for (int i = 0; i < consumables.size(); i++) {
            ConsumableQuantity cq = consumables.get(i);
            System.out.printf("%d: %s (x%d)\n", i + 1, cq.getConsumable().getDescription(), cq.getQuantity());
        }

        int choice = getPlayerChoice() - 1;
        if (choice >= 0 && choice < consumables.size()) {
            ConsumableQuantity selected = consumables.get(choice);
            selected.getConsumable().use(player);
            player.getInventory().decrementConsumable(selected);
        } else {
            System.out.println(TextUtil.toRed("Invalid consumable selection."));
        }
    }
}

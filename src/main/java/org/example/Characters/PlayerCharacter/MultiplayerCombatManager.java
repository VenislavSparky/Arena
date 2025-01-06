package org.example.Characters.PlayerCharacter;

import org.example.Abilities.Ability;
import org.example.Abilities.TargetingStrategies.TargetSelection.PlayerSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Characters.GameCharacter;
import org.example.Characters.Inventory.Consumables.ConsumableQuantity;
import org.example.Networking.ClientHandler;
import org.example.Utils.TextUtil;

import java.util.List;
import java.util.Optional;

public class MultiplayerCombatManager {
    private static final TargetSelectionMode targetSelectionMode = new PlayerSelectionMode(); //todo new MultiplayerSelectionMode()

    public static boolean handleActions(PlayerCharacter player, ClientHandler clientHandler, PlayerCharacter enemy,ClientHandler enemyClientHandler) {
        displayOptions(clientHandler);

        clientHandler.clientInputMessage("Energy: " + player.getCurrentEnergy());
        int choice = getPlayerChoice(clientHandler);
        switch (choice) {
            case 1 ->
                    selectAndUseAbility(player, clientHandler, List.of(player), List.of(enemy),enemyClientHandler);
            case 2 -> useConsumable(player,clientHandler);
            case 3 -> {
                return true;
            }
            default -> clientHandler.clientInputMessage(TextUtil.toRed("Invalid choice. Try again."));

        }
        return false;
    }

    private static void displayOptions(ClientHandler clientHandler) {
        clientHandler.clientInputMessage("Select an action:");
        int index = 1;
        clientHandler.clientInputMessage(index + ": Use ability");
        clientHandler.clientInputMessage((index + 1) + ": Use consumable");
        clientHandler.clientInputMessage((index + 2) + ": End turn");
    }

    private static void displayAbilityOptions(PlayerCharacter player, ClientHandler clientHandler) {
        clientHandler.clientInputMessage("Select an ability:");
        int index = 1;
        for (Ability ability : player.getAbilities()) {
            String abilityName = ability.getClass().getSimpleName();
            String color = ability.isAvailable(player) ? TextUtil.GREEN : TextUtil.RED;
            clientHandler.clientInputMessage(TextUtil.colorize(index + ": " + abilityName, color));
            index++;
        }
    }

    private static int getPlayerChoice(ClientHandler clientHandler) {
        clientHandler.clientInputMessage("Your choice: ");
        try {
            return Integer.parseInt(clientHandler.clientOutputMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return -1;
        }
    }

    private static void selectAndUseAbility(PlayerCharacter player, ClientHandler clientHandler, List<GameCharacter> allies, List<GameCharacter> enemies,ClientHandler enemyClientHandler) {
        displayAbilityOptions(player, clientHandler);

        int abilityIndex = getPlayerChoice(clientHandler) - 1;

        Optional<Ability> selectedAbility = getAbilityByIndex(player, abilityIndex);
        if (selectedAbility.isEmpty()) {
            clientHandler.clientInputMessage(TextUtil.toRed("Invalid ability selection."));
            return;
        }

        Ability ability = selectedAbility.get();
        if (!ability.isAvailable(player)) {
            clientHandler.clientInputMessage(TextUtil.toRed("You cannot use that ability."));
            return;
        }

        enemyClientHandler.clientInputMessage(TextUtil.toGreen(player.getName() + " used " + ability.getClass().getSimpleName()));
        player.consumeEnergy(ability.getEnergyCost());
        ability.use(player, allies, enemies, targetSelectionMode);
    }

    private static Optional<Ability> getAbilityByIndex(PlayerCharacter player, int index) {
        if (index >= 0 && index < player.getAbilities().size()) {
            return Optional.of(player.getAbilities().get(index));
        }
        return Optional.empty();
    }


    private static void useConsumable(PlayerCharacter player, ClientHandler clientHandler) {
        List<ConsumableQuantity> consumables = player.getInventory().getConsumables();
        if (consumables.isEmpty()) {
            clientHandler.clientInputMessage(TextUtil.toRed("No consumables available!"));
            return;
        }

        clientHandler.clientInputMessage("Select a consumable to use:");
        for (int i = 0; i < consumables.size(); i++) {
            ConsumableQuantity cq = consumables.get(i);
            clientHandler.clientInputMessage(String.format("%d: %s (x%d)\n", i + 1, cq.getConsumable().getDescription(), cq.getQuantity()));
        }

        int choice = getPlayerChoice(clientHandler) - 1;
        if (choice >= 0 && choice < consumables.size()) {
            ConsumableQuantity selected = consumables.get(choice);
            selected.getConsumable().use(player);
            player.getInventory().decrementConsumable(selected);
        } else {
            clientHandler.clientInputMessage(TextUtil.toRed("Invalid consumable selection."));
        }
    }
}

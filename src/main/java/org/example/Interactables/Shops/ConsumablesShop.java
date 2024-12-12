package org.example.Interactables.Shops;

import org.example.Interactables.Interactable;
import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Utils.TextUtil;
import org.example.Characters.Inventory.Consumables.EnergyPotion;
import org.example.Characters.Inventory.Consumables.Consumable;
import org.example.Characters.Inventory.Consumables.HealthPotion;

import java.util.*;

public class ConsumablesShop implements Interactable {
    private final List<Consumable> availableConsumables = new ArrayList<>();

    public ConsumablesShop() {
        initializeConsumables();
    }

    private void initializeConsumables() {
        availableConsumables.add(new HealthPotion());
        availableConsumables.add(new EnergyPotion());
    }

    public void displayItems() {
        System.out.println("Consumables for sale:");
        for (int i = 0; i < availableConsumables.size(); i++) {
            Consumable consumable = availableConsumables.get(i);
            System.out.printf("%d. %s - Use: %s, Price: %d%n",
                    i + 1,
                    consumable.getClass().getSimpleName(),
                    consumable.getDescription(),
                    calculatePrice(consumable));
        }
    }

    @Override
    public void interact(PlayerCharacter playerCharacter) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Consumables Shop - Select:\n1. Buy\n2. Exit");
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1 -> buy(playerCharacter);
            case 2 -> System.out.println("Leaving the shop!");
            default -> System.out.println("Invalid option, returning to main menu!");
        }
    }

    private void buy(PlayerCharacter playerCharacter) {
        Scanner scanner = new Scanner(System.in);
        displayItems();
        System.out.println("Select the number of the consumable to buy:");
        int selectedOption = Integer.parseInt(scanner.nextLine()) - 1;

        if (selectedOption < 0 || selectedOption >= availableConsumables.size()) {
            System.out.println(TextUtil.toRed("Invalid selection! Returning to shop menu."));
            return;
        }

        Consumable selectedConsumable = availableConsumables.get(selectedOption);
        int price = calculatePrice(selectedConsumable);

        if (playerCharacter.getGold() >= price) {
            playerCharacter.setGold(playerCharacter.getGold() - price);
            playerCharacter.getInventory().add(selectedConsumable);
            System.out.printf("You bought one %s for %d gold.%n",
                    selectedConsumable.getClass().getSimpleName(), price);
        } else {
            System.out.println(TextUtil.toRed("Not enough gold to buy this item!"));
        }
    }



    private int calculatePrice(Consumable consumable) {
        return 10;
    }
}



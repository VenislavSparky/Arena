package org.example.Interactables.Shops;

import org.example.Interactables.Interactable;
import org.example.Characters.GameCharacter;
import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Utils.TextUtil;
import org.example.Characters.Inventory.Equipments.Equipment;
import org.example.Characters.Inventory.Equipments.EquipmentFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Armory implements Interactable {
    private static Armory instance;
    private final List<Equipment> equipments;

    private Armory() {
        this.equipments = new ArrayList<>();
    }

    public static Armory getInstance() {
        if (instance == null) {
            instance = new Armory();
        }
        return instance;
    }

    public void displayItemsForSale() {
        System.out.println("\nEquipments for sale:");
        for (int i = 0; i < equipments.size(); i++) {
            Equipment equipment = equipments.get(i);
            System.out.printf("%d. %s - Price: %d gold%n",
                    i + 1,
                    equipment,
                    calculatePriceForSale(equipment)
            );
        }
    }

    public void displayItemsToSell(List<Equipment> toSell) {
        System.out.println("\nEquipments to sell:");
        for (int i = 0; i < toSell.size(); i++) {
            Equipment equipment = toSell.get(i);
            System.out.printf("%d. %s - Price: %d gold%n",
                    i + 1,
                    equipment,
                    calculatePriceToSell(equipment)
            );
        }
    }


    public void refreshEquipments(GameCharacter gameCharacter) {
        equipments.clear();
        int maxEquipments = Math.min(gameCharacter.getLevel() % 10 + 1, 5);
        for (int i = 0; i < maxEquipments; i++) {
            equipments.add(EquipmentFactory.createRandomEquipment(gameCharacter));
        }
    }

    public void interact(PlayerCharacter playerCharacter) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Armory - Select:\n1. Buy\n2. Sell\n3. Exit\nCurrent Gold: " + playerCharacter.getGold());
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1 -> buy(playerCharacter);
            case 2 -> sell(playerCharacter);
            case 3 -> System.out.println("Exiting Armory.");
            default -> System.out.println(TextUtil.toRed("Invalid option, returning to main menu!"));
        }
    }

    private void buy(GameCharacter gameCharacter) {
        if (equipments.isEmpty()) {
            System.out.println(TextUtil.toRed("Sorry, no equipment for sale at the moment!"));
            return;
        }
        Scanner scanner = new Scanner(System.in);
        displayItemsForSale();
        System.out.print(TextUtil.toGreen("Select the number of the equipment you want to buy:"));

        try {
            int selectedOption = Integer.parseInt(scanner.nextLine()) - 1;

            if (selectedOption < 0 || selectedOption >= equipments.size()) {
                System.out.println(TextUtil.toRed("Invalid selection! Returning to main menu."));
                return;
            }

            Equipment selectedEquipment = equipments.get(selectedOption);
            int price = calculatePriceForSale(selectedEquipment);

            if (gameCharacter.getGold() >= price) {

                gameCharacter.setGold(gameCharacter.getGold() - price);

                System.out.printf(
                        ("You purchased %s for %d gold. Remaining gold: %d.%n"),
                        selectedEquipment,
                        price,
                        gameCharacter.getGold()
                );

                gameCharacter.getInventory().add(selectedEquipment);
                equipments.remove(selectedOption);


            } else {
                System.out.println(TextUtil.toRed("You don't have enough gold to buy this item!"));
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(TextUtil.toRed("Invalid input! Please enter a number."));
        }
    }

    private void sell(GameCharacter gameCharacter) {
        List<Equipment> toSell = gameCharacter.getInventory().getEquipments();
        if (toSell.isEmpty()) {
            System.out.println(TextUtil.toRed("Sorry, you don't have equipments to sell!"));
            return;
        }
        Scanner scanner = new Scanner(System.in);
        displayItemsToSell(toSell);
        System.out.print(TextUtil.toGreen("Select the number of the equipment you want to sell:"));

        try {
            int selectedOption = Integer.parseInt(scanner.nextLine()) - 1;

            if (selectedOption < 0 || selectedOption >= toSell.size()) {
                System.out.println(TextUtil.toRed("Invalid selection! Returning to main menu."));
                return;
            }

            Equipment selectedEquipment = toSell.get(selectedOption);
            int price = calculatePriceToSell(selectedEquipment);

            gameCharacter.setGold(gameCharacter.getGold() + price);

            System.out.printf(
                    ("You sold %s for %d gold. Gold: %d.%n"),
                    selectedEquipment,
                    price,
                    gameCharacter.getGold()
            );

            gameCharacter.getInventory().remove(selectedEquipment);


        } catch (NumberFormatException | IndexOutOfBoundsException e ) {
            System.out.println(TextUtil.toRed("Invalid input! Please enter a number."));
        }
    }

    private int calculatePriceForSale(Equipment equipment) {
        return Math.round(equipment.getItemRarity().getMultiplier() * 15);
    }

    private int calculatePriceToSell(Equipment equipment) {
        return Math.round(equipment.getItemRarity().getMultiplier() * 5);
    }
}


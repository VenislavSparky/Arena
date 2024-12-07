package org.example.Interactables.Shops;

import org.example.Interactables.Interactable;
import org.example.Characters.GameCharacter;
import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Utils.TextColorUtil;
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

    public void displayItems() {
        if (equipments.isEmpty()) {
            System.out.println(TextColorUtil.toRed("Sorry, no equipment for sale at the moment!"));
            return;
        }

        System.out.println("\nEquipments for sale:");
        for (int i = 0; i < equipments.size(); i++) {
            Equipment equipment = equipments.get(i);
            System.out.printf("%d. %s - Price: %d gold%n",
                    i + 1,
                    equipment,
                    calculatePrice(equipment)
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
        if (equipments.isEmpty()) {
            return;
        }

        System.out.println("Select:\n1. Buy\n2. Sell *SOON*\n3. Exit");
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1 -> buy(playerCharacter);
            case 2 -> System.out.println(TextColorUtil.toRed("Sell option coming soon!"));
            case 3 -> System.out.println("Exiting Armory.");
            default -> System.out.println(TextColorUtil.toRed("Invalid option, returning to main menu!"));
        }
    }

    private void buy(GameCharacter gameCharacter) {
        Scanner scanner = new Scanner(System.in);
        displayItems();
        System.out.println(TextColorUtil.toGreen("Select the number of the equipment you want to buy:"));

        try {
            int selectedOption = Integer.parseInt(scanner.nextLine()) - 1;

            if (selectedOption < 0 || selectedOption >= equipments.size()) {
                System.out.println(TextColorUtil.toRed("Invalid selection! Returning to Armory menu."));
                return;
            }

            Equipment selectedEquipment = equipments.get(selectedOption);
            int price = calculatePrice(selectedEquipment);

            if (gameCharacter.getGold() >= price) {

                System.out.printf(
                        ("You purchased %s for %d gold. Remaining gold: %d.%n"),
                        selectedEquipment,
                        price,
                        gameCharacter.getGold()
                );

                gameCharacter.setGold(gameCharacter.getGold() - price);
                gameCharacter.getInventory().add(selectedEquipment);
                equipments.remove(selectedOption);


            } else {
                System.out.println(TextColorUtil.toRed("You don't have enough gold to buy this item!"));
            }
        } catch (NumberFormatException e) {
            System.out.println(TextColorUtil.toRed("Invalid input! Please enter a number."));
        } catch (Exception e) {
            System.out.println(TextColorUtil.toRed("Something went wrong! Returning to Armory menu."));
        }
    }

    private int calculatePrice(Equipment equipment) {
        return (int) (equipment.getItemRarity().getMultiplier() * 15);
    }
}

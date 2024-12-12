package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Characters.Inventory.Equipments.Equipment;
import org.example.Characters.PlayerCharacter.PlayerCharacterFactory;
import org.example.Interactables.Interactable;
import org.example.Interactables.Arenas.ArenaFactory;
import org.example.Interactables.Shops.Armory;
import org.example.Interactables.Shops.ConsumablesShop;
import org.example.Interactables.Trainers.ClassTrainer;
import org.example.Utils.EntityManagerFactoryUtil;
import org.example.Utils.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMenu {

    public static PlayerCharacter initializeCharacter() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Select existing Hero");
            System.out.println("2. Create new Hero");
            System.out.println("3. Exit Game");
            System.out.print("Choose an option: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        return fetchCharacterFromDB(scanner);
                    case 2:
                        return PlayerCharacterFactory.createPlayerCharacter();
                    case 3:
                        System.out.println("Exiting game...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

        }
    }

    public static void getMainMenu(PlayerCharacter playerCharacter) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Interactable> interactables = initializeInteractables(playerCharacter);
            TextUtil.printSeparator();
            System.out.println("Where would you like to go?");
            for (int i = 0; i < interactables.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, interactables.get(i).getClass().getSimpleName());
            }
            System.out.printf("%d. %s\n", interactables.size() + 1, "Edit Character");
            System.out.printf("%d. %s\n", interactables.size() + 2, "Save & Exit");

            try {
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());

                if (option >= 1 && option <= interactables.size()) {
                    TextUtil.printSeparator();
                    interactables.get(option - 1).interact(playerCharacter);
                } else if (option == interactables.size() + 1) {
                    editCharacter(playerCharacter);
                } else if (option == interactables.size() + 2) {
                    saveGame(playerCharacter);
                    System.out.println("Exiting ...");
                    System.exit(0);
                } else {
                    System.out.println("Invalid option! Please try again.");
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Please enter a valid option.");
            }
            saveGame(playerCharacter);
        }
    }


    private static void editCharacter(PlayerCharacter playerCharacter) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TextUtil.printSeparator();
            System.out.println(playerCharacter);
            System.out.println(playerCharacter.getStats());
            System.out.println("Character Editing Menu:");
            System.out.println("1. Equip an item");
            System.out.println("2. Unequip an item");
            System.out.println("3. Return to main menu");

            try {
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        equipItem(playerCharacter, scanner);
                        break;
                    case 2:
                        unequipItem(playerCharacter, scanner);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static List<Interactable> initializeInteractables(PlayerCharacter playerCharacter) {
        List<Interactable> interactables = new ArrayList<>();
        interactables.add(ArenaFactory.getArenaForPlayer(playerCharacter));
        interactables.add(new ConsumablesShop());
        Armory.getInstance().refreshEquipments(playerCharacter);
        interactables.add(Armory.getInstance());
        interactables.add(new ClassTrainer());
        return interactables;
    }


    private static PlayerCharacter fetchCharacterFromDB(Scanner scanner) {
        try (EntityManager em = EntityManagerFactoryUtil.getEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<PlayerCharacter> query = em.createQuery("SELECT c FROM PlayerCharacter c", PlayerCharacter.class);
            List<PlayerCharacter> characters = query.getResultList();

            if (characters.isEmpty()) {
                System.out.println("No existing character found.");
                return PlayerCharacterFactory.createPlayerCharacter();
            }

            System.out.println("Available characters:");
            for (int i = 0; i < characters.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, characters.get(i));
            }

            while (true) {
                System.out.print("Select a character (number): ");

                int option = -1;
                try {
                    option = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println(TextUtil.toRed("Invalid input. Please enter a number."));
                }

                if (option >= 0 && option < characters.size()) {
                    PlayerCharacter playerCharacter = characters.get(option);
                    playerCharacter.initCurrentStats();
                    return playerCharacter;
                } else {
                    System.out.println("Invalid selection. Try again.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch character from database!", e);
        }
    }


    private static void equipItem(PlayerCharacter playerCharacter, Scanner scanner) {
        TextUtil.printSeparator();
        List<Equipment> equipment = playerCharacter.getInventory().getEquipments();

        if (equipment.isEmpty()) {
            System.out.println("No equipment available to equip.");
            return;
        }

        System.out.println("Available equipment:");
        for (int i = 0; i < equipment.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, equipment.get(i));
        }

        System.out.print("Select equipment to equip: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine()) - 1;
            playerCharacter.equip(equipment.get(choice));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid selection.");
        }
    }


    private static void unequipItem(PlayerCharacter playerCharacter, Scanner scanner) {
        TextUtil.printSeparator();
        List<Equipment> equippedItems = new ArrayList<>(playerCharacter.getEquipped().values());

        if (equippedItems.isEmpty()) {
            System.out.println("No items equipped.");
            return;
        }

        System.out.println("Equipped items:");
        for (int i = 0; i < equippedItems.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, equippedItems.get(i));
        }

        System.out.print("Select equipment to unequip: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine()) - 1;
            playerCharacter.unequip(equippedItems.get(choice).getSlotType());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid selection.");
        }
    }


    private static void saveGame(PlayerCharacter playerCharacter) {
        try (EntityManager em = EntityManagerFactoryUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.merge(playerCharacter);
            em.getTransaction().commit();
            System.out.println("Game saved!");
        } catch (Exception e) {
            System.out.println("Failed to save the game: " + e.getMessage());
        }
    }
}
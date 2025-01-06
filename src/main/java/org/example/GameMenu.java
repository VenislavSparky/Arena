package org.example;

import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Characters.Inventory.Equipments.Equipment;
import org.example.Characters.PlayerCharacter.PlayerCharacterFactory;
import org.example.Exceptions.DatabaseOperationException;
import org.example.Interactables.Interactable;
import org.example.Interactables.Arenas.ArenaFactory;
import org.example.Interactables.Shops.Armory;
import org.example.Interactables.Shops.ConsumablesShop;
import org.example.Interactables.Trainers.ClassTrainer;
import org.example.Networking.Client;
import org.example.Repositories.PlayerCharacterRepository;
import org.example.Repositories.impl.PlayerCharacterRepositoryImpl;
import org.example.Utils.TextUtil;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameMenu {

    private static final PlayerCharacterRepository playerRepository = new PlayerCharacterRepositoryImpl();


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
                        return getFromRepository(scanner);
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

            System.out.printf("%d. %s\n", 1, "Arena");
            for (int i = 1; i < interactables.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, interactables.get(i).getClass().getSimpleName());
            }
            System.out.printf("%d. %s\n", interactables.size() + 1, "Edit Character");
            System.out.printf("%d. %s\n", interactables.size() + 2, "Save & Exit");

            try {
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());
                if (option == 1) {
                    chooseArenaMode(playerCharacter);
                } else if (option > 1 && option <= interactables.size()) {
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

    private static void chooseArenaMode(PlayerCharacter playerCharacter) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TextUtil.printSeparator();
            System.out.println("Arena modes:");
            System.out.println("1. Singleplayer ");
            System.out.println("2. Multiplayer");
            System.out.println("3. Return to main menu");

            try {
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        ArenaFactory.getArenaForPlayer(playerCharacter).interact(playerCharacter);
                        break;
                    case 2:
                        Socket socket = new Socket("localhost", 1233);
                        Client client = new Client(socket,playerCharacter);
                        client.connectToServer();

                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IOException e) {
                System.out.println("Error connection to server...");
            }
        }
    }


    private static List<Interactable> initializeInteractables(PlayerCharacter playerCharacter) {
        List<Interactable> interactables = new ArrayList<>();
        interactables.add(new ConsumablesShop());
        Armory.getInstance().refreshEquipments(playerCharacter);
        interactables.add(Armory.getInstance());
        interactables.add(new ClassTrainer());
        return interactables;
    }


    private static PlayerCharacter getFromRepository(Scanner scanner) {
        try {
            List<PlayerCharacter> characters = playerRepository.fetchAllCharacters();

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
                try {
                    int option = Integer.parseInt(scanner.nextLine()) - 1;
                    return playerRepository.fetchCharacterByIndex(option);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                } catch (DatabaseOperationException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (DatabaseOperationException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
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
        try {
            playerRepository.saveOrUpdate(playerCharacter);
            System.out.println("Game saved!");
        } catch (DatabaseOperationException e) {
            System.out.println("Failed to save the game: " + e.getMessage());
        }
    }

}
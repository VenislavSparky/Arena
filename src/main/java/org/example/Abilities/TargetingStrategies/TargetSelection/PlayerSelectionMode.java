package org.example.Abilities.TargetingStrategies.TargetSelection;

import org.example.Characters.GameCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerSelectionMode implements TargetSelectionMode {
    @Override
    public List<GameCharacter> select(List<GameCharacter> possibleTargets, int count) {
        Scanner scanner = new Scanner(System.in);

        List<GameCharacter> targets = new ArrayList<>();
        List<GameCharacter> availableTargets = new ArrayList<>(possibleTargets);

        while (targets.size() < count) {
            System.out.println("Select targets: (Targets left: " + (count - targets.size()) + ")");
            for (int i = 0; i < availableTargets.size(); i++) {
                System.out.println((i + 1) + ". " + availableTargets.get(i));
            }
            System.out.print("Enter the number of your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine()) - 1;
                if (choice >= 0 && choice < availableTargets.size()) {
                    targets.add(availableTargets.remove(choice));
                } else {
                    System.out.println("Invalid choice. Please select a valid target.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return targets;
    }
}

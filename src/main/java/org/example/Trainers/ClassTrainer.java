package org.example.Trainers;

import org.example.Abilities.Ability;
import org.example.Abilities.AbilityRegistry;
import org.example.Characters.Heros.Hero;
import org.example.Helpers.ScannerSingleton;
import org.example.Helpers.TextColor;

import java.util.List;

public class ClassTrainer implements Trainer {

    @Override
    public void listAvailableAbilities(Hero hero) {
        List<Ability> availableAbilities = AbilityRegistry.getAllAbilities()
                .values().stream()
                .filter(ability -> hero.getClassType().contains(ability.getClassType()) && !hero.getAbilities().contains(ability)).toList();

        if (availableAbilities.isEmpty()) {
            System.out.println(TextColor.toRed("No available abilities to learn! :("));
        } else {
            System.out.println(TextColor.toGreen("Available abilities to learn:"));
            availableAbilities.forEach(a -> System.out.println(TextColor.toClassColor(a.getClassType(), a.getDescription())));
            System.out.println("Select ability to learn!");
            String selectedAbility = ScannerSingleton.getInstance().nextLine();
            //TODO VALIDATION
            hero.learnAbility(AbilityRegistry.getAbility(selectedAbility));
        }

    }
}

package org.example.Characters.NonPlayerCharacter;
import org.example.Abilities.Ability;
import org.example.Abilities.TargetingStrategies.TargetSelection.AutoSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Characters.GameCharacter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NpcCombatManager {

    private static final TargetSelectionMode targetSelectionMode = new AutoSelectionMode();

    public static boolean handleActions(NonPlayerCharacter npc, List<GameCharacter> allies, List<GameCharacter> enemies) {
        if (npc.getAbilities().isEmpty()) {
            System.out.println(npc.getName() + " has no abilities to use.");
            return true;
        }

        Ability selectedAbility = selectRandomAbility(npc);
        if (selectedAbility == null) {
            System.out.println(npc.getName() + " cannot perform any action.");
            return true;
        }

        if (!npc.hasEnoughEnergy(selectedAbility.getEnergyCost())) {
            System.out.println(npc.getName() + " does not have enough energy to use " + selectedAbility.getClass().getSimpleName());
            return true;
        }


        System.out.println(npc.getName() + " uses " + selectedAbility.getClass().getSimpleName());
        selectedAbility.use(npc, allies, enemies, targetSelectionMode);
        npc.consumeEnergy(selectedAbility.getEnergyCost());
        return true;
    }

    private static Ability selectRandomAbility(NonPlayerCharacter npc) {
        List<Ability> abilities = npc.getAbilities();
        if (abilities.isEmpty()) return null;

        return abilities.get(ThreadLocalRandom.current().nextInt(abilities.size()));
    }
}
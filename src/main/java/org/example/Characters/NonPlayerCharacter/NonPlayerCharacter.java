package org.example.Characters.NonPlayerCharacter;

import org.example.Abilities.Ability;
import org.example.Abilities.TargetingStrategies.TargetSelection.AutoSelectionMode;
import org.example.Abilities.TargetingStrategies.TargetSelection.TargetSelectionMode;
import org.example.Characters.CharacterClass;
import org.example.Characters.GameCharacter;
import org.example.Characters.Stats;

import java.util.List;



public abstract class NonPlayerCharacter extends GameCharacter {

    TargetSelectionMode targetSelectionMode;

    public NonPlayerCharacter(String name, CharacterClass characterClass, int level, int maxHealth, int maxEnergy, Stats stats, Ability ability) {
        super(name, characterClass, level, maxHealth, maxEnergy, stats, ability);
        this.targetSelectionMode = new AutoSelectionMode();
    }

    @Override
    public boolean performActions(List<GameCharacter> heroes, List<GameCharacter> monsters) {

        return NpcCombatManager.handleActions(this,getAllies(heroes,monsters),getEnemies(heroes,monsters));

    }

    abstract List<GameCharacter> getEnemies(List<GameCharacter> heroes, List<GameCharacter> monsters);

    abstract List<GameCharacter> getAllies(List<GameCharacter> heroes, List<GameCharacter> monsters);


}

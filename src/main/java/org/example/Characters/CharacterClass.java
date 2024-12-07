package org.example.Characters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CharacterClass {
    WARRIOR("Warrior",
            "A fearless melee combatant with high strength and resilience." +
                    "\n Masters of close-range combat, capable of enduring heavy damage and dealing devastating blows."),
    PALADIN("Paladin",
            "A holy knight who balances offense and defense." +
                    "\n Possesses healing abilities, protective skills, and decent melee strength to aid allies and smite enemies."),
    MAGE("Mage",
            "A master of arcane magic with high intellect." +
                    "\n Excels in ranged spell casting to deal massive damage or control the battlefield, but fragile in close combat."),
    ROGUE("Rogue",
            "A swift and cunning assassin who excels in agility and stealth. " +
                    "\nMasters of critical strikes and evasive maneuvers, capable of dealing high burst damage while avoiding direct confrontation.");

    private final String name;
    private final String description;

}

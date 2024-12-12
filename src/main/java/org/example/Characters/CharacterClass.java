package org.example.Characters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Utils.TextUtil;

@AllArgsConstructor
@Getter
public enum CharacterClass {
    WARRIOR("Warrior",
            "A fearless melee combatant with high strength and armor."),
    PALADIN("Paladin",
            "A holy knight who balances offense and defense. Possesses healing abilities decent melee strength."),
    MAGE("Mage",
            "A master of magic with high intellect. Excels in  spell casting to deal massive damage, but quite fragile.");

    private final String name;
    private final String description;

    @Override
    public String toString() {
        return TextUtil.toClassColor(CharacterClass.valueOf(name.toUpperCase()), name);
    }
}

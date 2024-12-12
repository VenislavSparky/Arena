package org.example.Characters.NonPlayerCharacter;

import org.example.Abilities.AbilityRegistry;
import org.example.Characters.CharacterClass;
import org.example.Characters.Stats;

public class NPCFactory {

    public static NonPlayerCharacter createFriendlyNPC() {
        return new FriendlyNPC("Arthas", CharacterClass.PALADIN, 2,100,70,new Stats(1,2,3,4), AbilityRegistry.getAbility("FlashOfLight"));
    }

    public static NonPlayerCharacter createHostileNPC() {
        return new HostileNPC("Troll", CharacterClass.WARRIOR, 2 , 150 , 50 , new Stats(1,2,3,4),AbilityRegistry.getAbility("MortalStrike"));
    }

    public static NonPlayerCharacter createHostileBossNPC() {
        return new HostileNPC("Orc", CharacterClass.WARRIOR, 5 , 500 , 100 , new Stats(5,5,5,4),AbilityRegistry.getAbility("MortalStrike"));
    }
}

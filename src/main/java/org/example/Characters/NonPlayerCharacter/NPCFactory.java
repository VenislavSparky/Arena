package org.example.Characters.NonPlayerCharacter;

public class NPCFactory {

    public static NonPlayerCharacter createFriendlyNPC() {
        return new FriendlyNPC();
    }

    public static NonPlayerCharacter createHostileNPC() {
        return new HostileNPC();
    }
}

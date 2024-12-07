package org.example.Interactables.Arenas;


import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Characters.NonPlayerCharacter.NonPlayerCharacter;
import org.example.Characters.NonPlayerCharacter.NPCFactory;

public class TeamBattleArena extends Arena {

    @Override
    protected void prepareArena(PlayerCharacter playerCharacter) {
        entities.add(playerCharacter);
        heroes.add(playerCharacter);
//TODO EXTRACT METHODS ?
        NonPlayerCharacter HostileNPCOne = NPCFactory.createHostileNPC();
        entities.add(HostileNPCOne);
        monsters.add(HostileNPCOne);

        NonPlayerCharacter FriendlyNPCTwo = NPCFactory.createFriendlyNPC();
        entities.add(FriendlyNPCTwo);
        heroes.add(FriendlyNPCTwo);

        NonPlayerCharacter HostileNPCTwo = NPCFactory.createHostileNPC();
        entities.add(HostileNPCTwo);
        monsters.add(HostileNPCTwo);
    }

    @Override
    protected String getArenaType() {
        return "2v2";
    }

    @Override
    public void endArena(PlayerCharacter playerCharacter) {

    }
}

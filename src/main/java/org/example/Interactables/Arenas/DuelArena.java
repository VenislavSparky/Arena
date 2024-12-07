package org.example.Interactables.Arenas;


import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Characters.NonPlayerCharacter.NonPlayerCharacter;
import org.example.Characters.NonPlayerCharacter.NPCFactory;

public class DuelArena extends Arena {

    @Override
    public void prepareArena(PlayerCharacter playerCharacter) {
        entities.add(playerCharacter);
        heroes.add(playerCharacter);

        NonPlayerCharacter nonPlayerCharacter = NPCFactory.createHostileNPC();
        entities.add(nonPlayerCharacter);
        monsters.add(nonPlayerCharacter);
    }

    @Override
    protected String getArenaType() {
        return "1v1";
    }

    @Override
    public void endArena(PlayerCharacter playerCharacter) {
        if (monsters.isEmpty()) {
            System.out.println("Hero won!");
            playerCharacter.updateWins();
        } else {
            System.out.println("Monster won!");
            playerCharacter.updateLosses();
        }
    }


}

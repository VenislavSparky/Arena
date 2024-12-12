package org.example.Interactables.Arenas;

import org.example.Characters.NonPlayerCharacter.NPCFactory;
import org.example.Characters.NonPlayerCharacter.NonPlayerCharacter;
import org.example.Characters.PlayerCharacter.PlayerCharacter;

public class BossChallengeArena extends Arena{
    @Override
    protected void prepareArena(PlayerCharacter playerCharacter) {
        entities.add(playerCharacter);
        heroes.add(playerCharacter);

        NonPlayerCharacter nonPlayerCharacter = NPCFactory.createHostileBossNPC();
        entities.add(nonPlayerCharacter);
        monsters.add(nonPlayerCharacter);
    }

    @Override
    public void endArena(PlayerCharacter playerCharacter) {
        if (monsters.isEmpty()) {
            System.out.println("Hero won!");
            playerCharacter.updateWins();
            playerCharacter.receiveGold(50 + playerCharacter.getLevel());
            playerCharacter.gainExp(100);
        } else {
            System.out.println("Monster won!");
            playerCharacter.updateLosses();
            playerCharacter.receiveGold(10 + playerCharacter.getLevel());
        }
    }

    @Override
    protected String getArenaType() {
        return "Boss Challenge";
    }


}

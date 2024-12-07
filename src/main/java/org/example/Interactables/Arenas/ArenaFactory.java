package org.example.Interactables.Arenas;

import org.example.Characters.PlayerCharacter.PlayerCharacter;

public class ArenaFactory {

    public static Arena getArenaForPlayer(PlayerCharacter playerCharacter) {
        int playerLevel = playerCharacter.getLevel();

        if (playerLevel % 5 == 0) {
            return new BossChallengeArena();
        }

        if (playerLevel % 3 == 0) {
            return new TeamBattleArena();
        }

        return new DuelArena();
    }
}

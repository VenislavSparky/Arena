package org.example.Interactables.Arenas;

import org.example.Interactables.Interactable;
import org.example.Characters.GameCharacter;
import org.example.Characters.PlayerCharacter.PlayerCharacter;

import java.util.ArrayList;
import java.util.List;

public abstract class Arena implements Interactable {
    protected final List<GameCharacter> entities;
    protected final List<GameCharacter> heroes;
    protected final List<GameCharacter> monsters;
    private int currentEntityTurn;

    public Arena() {
        entities = new ArrayList<>();
        heroes = new ArrayList<>();
        monsters = new ArrayList<>();
        currentEntityTurn = 0;
    }

    public void interact(PlayerCharacter playerCharacter) {
        prepareArena(playerCharacter);
        playerCharacter.initCurrentStats();

        System.out.println(getArenaType() + " Arena Started!");
        while (isBattleOngoing()) {
            GameCharacter gameCharacter = entities.get(currentEntityTurn);
            System.out.printf("(%s) %s's Turn\n", gameCharacter.getCharacterClass(), gameCharacter.getName());

            boolean isTurnEnded = false;

            while (!isTurnEnded && isBattleOngoing()) {
                isTurnEnded = gameCharacter.performActions(heroes, monsters);
                removeDead();
            }
            gameCharacter.endTurn();
            removeDead();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            nextTurn();
        }


        endArena(playerCharacter);
        clearArena();
    }

    final boolean isBattleOngoing() {
        return !monsters.isEmpty() && !heroes.isEmpty();
    }

    final void nextTurn() {
        currentEntityTurn = (currentEntityTurn + 1) % entities.size();
    }

    final void removeDead() {
        entities.removeIf(GameCharacter::isDead);
        monsters.removeIf(GameCharacter::isDead);
        heroes.removeIf(GameCharacter::isDead);
    }

    public void clearArena() {
        entities.clear();
        heroes.clear();
        monsters.clear();
    }

    protected abstract void prepareArena(PlayerCharacter playerCharacter);

    public abstract void endArena(PlayerCharacter playerCharacter);

    protected abstract String getArenaType();

}

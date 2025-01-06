package org.example.Interactables.Arenas.MultiplayerArenas;

import org.example.Characters.GameCharacter;

import org.example.Networking.ClientHandler;
import org.example.Utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

public class PvPArena implements Runnable {
    protected final List<ClientHandler> clientHandlers;
    protected final List<GameCharacter> playerCharacterOne;
    protected final List<GameCharacter> playerCharacterTwo;
    private int currentPlayerTurn = 0;
    private boolean isBattleActive = true;

    public PvPArena(ClientHandler playerCharacterOne, ClientHandler playerCharacterTwo) {
        clientHandlers = new ArrayList<>();
        this.playerCharacterOne = new ArrayList<>();
        clientHandlers.add(playerCharacterOne);
        this.playerCharacterOne.add(playerCharacterOne.getPlayerCharacter());
        this.playerCharacterTwo = new ArrayList<>();
        clientHandlers.add(playerCharacterTwo);
        this.playerCharacterTwo.add(playerCharacterTwo.getPlayerCharacter());
    }

    @Override
    public void run() {
        String message = "PvP Arena Started!\n" + playerCharacterOne.get(0).getName() + " vs " + playerCharacterTwo.get(0).getName();
        broadcastMessage(message);

        while (isBattleOngoing()) {
            ClientHandler currentPlayerClientHandler = clientHandlers.get(currentPlayerTurn);
            ClientHandler enemyPlayerClientHandler = clientHandlers.get((currentPlayerTurn + 1) % clientHandlers.size());

            sendToPlayer(currentPlayerClientHandler, TextUtil.toGreen("Your turn!"));
            sendToPlayer(enemyPlayerClientHandler, TextUtil.toRed("Enemy turn!"));

            boolean isTurnEnded = false;

            while (!isTurnEnded && isBattleOngoing()) {

                if (!currentPlayerClientHandler.isConnected() || !enemyPlayerClientHandler.isConnected()) {
                    handleDisconnection();
                    return;
                }

                isTurnEnded = currentPlayerClientHandler.getPlayerCharacter().performActions(
                        currentPlayerClientHandler.getPlayerCharacter(),
                        currentPlayerClientHandler,
                        enemyPlayerClientHandler.getPlayerCharacter(),
                        enemyPlayerClientHandler
                );
                removeDead();
            }

            currentPlayerClientHandler.getPlayerCharacter().endTurn();
            removeDead();

            nextTurn();
        }

        endBattle();
    }


    private boolean isBattleOngoing() {
        return isBattleActive
                && !playerCharacterOne.get(0).isDead()
                && !playerCharacterTwo.get(0).isDead()
                && clientHandlers.stream().allMatch(ClientHandler::isConnected);
    }

    private void nextTurn() {
        currentPlayerTurn = (currentPlayerTurn + 1) % clientHandlers.size();
    }

    private void removeDead() {
        if (playerCharacterOne.get(0).isDead()) {
            isBattleActive = false;
        } else if (playerCharacterTwo.get(0).isDead()) {
            isBattleActive = false;
        }
    }

    private void handleDisconnection() {
        isBattleActive = false;
        for (ClientHandler clientHandler : clientHandlers) {
            if (!clientHandler.isConnected()) {
                broadcastMessage("Player " + clientHandler.getPlayerCharacter().getName() + " has disconnected!");
            }
        }
        endBattle();
    }

    private void endBattle() {
        broadcastMessage("The battle has ended!");
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.getPlayerCharacter().isDead()) {
                sendToPlayer(clientHandler, TextUtil.toRed("You lost!"));
            }else {
                sendToPlayer(clientHandler, TextUtil.toGreen("You won!"));
            }
            clientHandler.disconnect();
        }
        clientHandlers.clear();
    }

    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.isConnected()) {
                clientHandler.clientInputMessage(messageToSend);
            }
        }
    }

    public void sendToPlayer(ClientHandler clientHandler, String message) {
        if (clientHandler.isConnected()) {
            clientHandler.clientInputMessage(message);
        }
    }
}

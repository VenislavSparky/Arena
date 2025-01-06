package org.example.Networking;

import org.example.Interactables.Arenas.MultiplayerArenas.PvPArena;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Lobby {

    private ConcurrentLinkedQueue<ClientHandler> matchmakingQueue;

    public Lobby() {
        this.matchmakingQueue = new ConcurrentLinkedQueue<>();
    }

    public synchronized void addToQueue(ClientHandler clientHandler) {
        matchmakingQueue.add(clientHandler);

        clientHandler.clientInputMessage("Joined PvP lobby!");
        clientHandler.clientInputMessage("Waiting for a match.....");

        if (matchmakingQueue.size() >= 2) {
            ClientHandler player1 = matchmakingQueue.poll();
            ClientHandler player2 = matchmakingQueue.poll();
            startArena(player1, player2);
        }
    }

    private void startArena(ClientHandler player1, ClientHandler player2) {
        PvPArena arenaSession = new PvPArena(player1, player2);
        Thread matchThread = new Thread(arenaSession);
        matchThread.start();
    }

}

package org.example.Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
    Lobby lobby;

    public Server(ServerSocket serverSocket,Lobby lobby) {
        this.serverSocket = serverSocket;
        this.lobby = lobby;

    }

    public void startServer() {

        try {

            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.clientInputMessage("Connected!");

               lobby.addToQueue(clientHandler);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1233);

        Server server = new Server(serverSocket,new Lobby());

        server.startServer();
    }


}

package org.example.Networking;

import lombok.Getter;
import org.example.Characters.PlayerCharacter.PlayerCharacter;

import java.io.*;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;

    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;
    @Getter
    PlayerCharacter playerCharacter;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            playerCharacter = (PlayerCharacter) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

    public void clientInputMessage(String messageToSend) {
        try {
            bufferedWriter.write(messageToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public String clientOutputMessage() {
        String message = "";
        try {
            message = bufferedReader.readLine();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        return message.trim();
    }

    public boolean isConnected() {
      return socket.isConnected();
    }

    public void disconnect() {
        closeEverything(this.socket, this.bufferedReader,  this.bufferedWriter);
    }


    public void closeEverything(Socket socket, BufferedReader br, BufferedWriter bw) {
        try {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}














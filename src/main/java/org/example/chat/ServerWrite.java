package org.example.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerWrite implements Runnable {
    private final Socket socket;

    public ServerWrite(Socket socket) {

        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            String clientMessage = "";
            while (!clientMessage.equals("Close")) {
                clientMessage = inputStream.readUTF();

                outputStream.writeUTF(clientMessage);
            }
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

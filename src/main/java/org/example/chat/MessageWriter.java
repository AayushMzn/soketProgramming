package org.example.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageWriter implements Runnable {
    private final Socket socket;

    public MessageWriter(Socket socket) {

        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(System.in);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            String message = "";
            while (!message.equals("Close")) {
                message = inputStream.readLine();
                outputStream.writeUTF(message);
            }
            System.exit(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

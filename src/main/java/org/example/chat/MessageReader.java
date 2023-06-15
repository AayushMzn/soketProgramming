package org.example.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageReader implements Runnable {
    private final Socket socket;

    public MessageReader(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String message = "";
            while (!message.equals("Close")) {

                message = inputStream.readUTF();

                System.out.println(message);
            }
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {
    private static DataInputStream inputStream = null;
    private static final ExecutorService readWriteThread = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 3000);
            System.out.println("connected");

            MessageReader read = new MessageReader(socket);
            readWriteThread.execute(read);

            MessageWriter write = new MessageWriter(socket);
            readWriteThread.execute(write);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

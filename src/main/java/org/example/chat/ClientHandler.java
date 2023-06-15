package org.example.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler implements Runnable {
    private Socket socket;
//    private static final ExecutorService readWriteThread = Executors.newFixedThreadPool(2);
    private static ArrayList<Socket> clientSocket = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
        clientSocket.add(socket);
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            String clientMessage = "";
            while (!clientMessage.equals("Close")) {
                clientMessage = inputStream.readUTF();
                for (Socket sc :clientSocket ) {
                    if(sc!=socket) {
                        DataOutputStream outputStream = new DataOutputStream(sc.getOutputStream());
                        outputStream.writeUTF(clientMessage);
                    }
                }
            }
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        MessageReader read = new MessageReader(socket);
//        readWriteThread.execute(read);
//
//        MessageWriter write = new MessageWriter(socket);
//        readWriteThread.execute(write);
    }
}
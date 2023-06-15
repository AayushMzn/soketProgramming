package org.example.chat;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("server started");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("A client has connected to Server");

                ClientHandler clientHandler = new ClientHandler(socket);
                pool.execute(clientHandler);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

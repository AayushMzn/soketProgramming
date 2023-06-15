package org.example.socketProgram;
import java.io.*;
import java.net.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    static String[] names = {"Ram","Shyam","Hari","Laxman"};
    static private int clients = 0;
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    private static ClientHandler clientHandler;
    private static Socket socket;
    private static ServerSocket serverSocket;
    public static boolean loop = true;
    public static void main(String[] args) throws IOException, EOFException {
        serverSocket = new ServerSocket(3080);

        System.out.println("--------------------------------");
        System.out.println("Server is waiting for client....");
        System.out.println("--------------------------------");

        while(true){

            socket = serverSocket.accept();
            System.out.println("A client has connected to Server");
            clientHandler = new ClientHandler(socket);
            clients++;
            pool.execute(clientHandler);

        }
    }

    public static String getRandomName(){
        return names[(int) (Math.random()*names.length)];
    }

    static public void quit() throws IOException {
        clients--;
        if(clients==0){
            loop = false;
            serverSocket.close();
            System.exit(0);

        }
    }
}
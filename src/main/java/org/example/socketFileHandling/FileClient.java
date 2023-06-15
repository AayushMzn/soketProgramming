package org.example.socketFileHandling;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {
    private static Socket socket = null;
    private static DataInputStream inputStream = null;
    private static DataOutputStream outputStream = null;

    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 3000);
            System.out.println("connected");

            Scanner scanner = new Scanner(System.in);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            String fileName = "";

            while(!fileName.equals("exit")){
                fileName = scanner.nextLine();
                outputStream.writeUTF(fileName);
                while(true) {
                    System.out.println(inputStream.readUTF());
                }
            }
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
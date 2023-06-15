package org.example.socketProgram;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        MyClient myClient = new MyClient();


        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 3080);

        System.out.println("--------------------------------");
        System.out.println("Connection Established");
        System.out.println("--------------------------------");

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());


        while(true){
            String request = scanner.next();
            if(request.equalsIgnoreCase("quit")) {
                dataOutputStream.writeUTF("quit");
                break;
            }

            dataOutputStream.writeUTF(request);

            System.out.println(dataInputStream.readUTF());
        }

        dataOutputStream.flush();
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }
}
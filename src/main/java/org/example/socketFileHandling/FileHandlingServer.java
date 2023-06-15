package org.example.socketFileHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandlingServer {
    static ServerSocket serverSocket;
    static Socket socket;
    static File obj;
    static Scanner scanner;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(3000);
        System.out.println("Server Started");
        socket = serverSocket.accept();
        System.out.println("A client has connected to Server");

        scanner = new Scanner(System.in);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        String fileRequest = "";
        String fileData = "";

        while (!fileRequest.equals("exit")) {
            fileRequest = dataInputStream.readUTF();

            obj = new File("C:\\Users\\Dell\\IdeaProjects\\socketProgramming\\src\\main\\java\\org\\example\\socketFileHandling\\files\\" + fileRequest + ".txt");
            if (obj.exists()) {
                Scanner myReader = new Scanner(obj);
                while (myReader.hasNextLine()) {
                    fileData = fileData + "\n" + myReader.nextLine();
                }
//                System.out.println(fileData.length());

                char[] response = new char[65534];

                if (fileData.length() >= 65534) {
                    for (int i = 0; i <= fileData.length(); i++) {
                            response[i] += fileData.charAt(i);
                        if (i <= 65534) {
                            break;
                        }
                    }
                    dataOutputStream.writeUTF(Arrays.toString(response));
                } else {
                    dataOutputStream.writeUTF(fileData);
                }

                myReader.close();

            } else {
                dataOutputStream.writeUTF("file does not exist");
            }

        }
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
    }
}

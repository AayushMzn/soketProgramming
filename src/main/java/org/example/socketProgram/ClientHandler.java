package org.example.socketProgram;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.io.DataInputStream;

public class ClientHandler implements Runnable {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.socket = clientSocket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                if ((dataInputStream.readUTF()).equalsIgnoreCase("quit")) {
                    MyServer.quit();
                    break;
                }
                String response = MyServer.getRandomName();
                dataOutputStream.writeUTF(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
                dataInputStream.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
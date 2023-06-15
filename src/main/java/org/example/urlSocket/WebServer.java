package org.example.urlSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServer {
    static File obj;
    private static final int BUFFER_SIZE = 4096;

    public static void main(String args[]) {
        int port = 3000;
        Socket socket;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Web Server is started");

            while (true) {
                socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String clientRequest = reader.readLine();
                String fileName = clientRequest.split(" ")[1];
                if(fileName.equals("/favicon.ico")){
                    continue;
                }
                System.out.println(fileName);
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Dell\\IdeaProjects\\socketProgramming\\src\\main\\java\\org\\example\\urlSocket\\files"+fileName);

                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
//                outputStream.write("HTTP/1.1 206 Partial Content\r\n".getBytes());
                outputStream.write(("Content-Disposition: attachment; filename=\""+fileName+"\"\r\n").getBytes());
//                outputStream.write("Content-Type: text/plain\r\n".getBytes());
                outputStream.write("Content-Transfer-Encoding: binary\r\n".getBytes());
                outputStream.write(("Content-Length: \r\n"+fileInputStream.getChannel().size()).getBytes());
//                outputStream.write("\r\n".getBytes());
//                outputStream.write("<b></b>".getBytes());
                outputStream.write((fileInputStream).readAllBytes());

                outputStream.write("\r\n\r\n".getBytes());


                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Server has been shutdown!");
        }
    }
}

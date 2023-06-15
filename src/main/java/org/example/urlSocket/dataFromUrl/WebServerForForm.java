package org.example.urlSocket.dataFromUrl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.example.urlSocket.dataFromUrl.Validation;

public class WebServerForForm {
    static Validation val =new Validation();
    public static void insertIntoFile(String user,String pass) throws IOException {
        FileWriter myWriter = new FileWriter("C:\\Users\\Dell\\IdeaProjects\\socketProgramming\\src\\main\\java\\org\\example\\urlSocket\\dataFromUrl\\record.txt", true);
        if(val.valid(user,pass)){
            myWriter.write(user+":"+pass+"\n");
        }
        myWriter.close();
    }


    public static String  readFile() throws IOException {
        File myObj = new File("C:\\Users\\Dell\\IdeaProjects\\socketProgramming\\src\\main\\java\\org\\example\\urlSocket\\dataFromUrl\\record.txt");
        Scanner myReader = new Scanner(myObj);
        String response= "";
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            response+= line +"&";
        }
        response= response.substring(0, response.length() - 1);
        return response;
    }

    public static void serverResponse(Socket socket, String response) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
        outputStream.write("Access-Control-Allow-Origin: *\r\n".getBytes());
        outputStream.write("Content-Type: plain/text\r\n".getBytes());
        outputStream.write(("Content-Length:"+response.length()+"\r\n").getBytes());
        outputStream.write("\r\n".getBytes());
        outputStream.write((response+"\r\n").getBytes());
        outputStream.write("\r\n\r\n".getBytes());

        outputStream.flush();
        outputStream.close();
    }
    public static void main(String[] args) throws IOException {
        int port = 3000;
        Socket socket;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Web Server is started");

        while (true) {
            socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String clientRequest = reader.readLine();
            String data = clientRequest.split(" ")[1];
            System.out.println(data);

            String[] value = data.split("[=,&]");
            for(int i=1;i< value.length;i=i+4){
                insertIntoFile(value[i],value[i+2]);
            }

            String response = readFile();

            serverResponse(socket,response);

        }
    }
}

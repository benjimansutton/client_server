package com.benjiman.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static <I0Exception extends Throwable> void main(String[] args) {

        // try block for the serverSocket

        try(ServerSocket serverSocket = new ServerSocket(6000)) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Create an Infinite loop for reading the data from the client

            while(true) {
                String echoString = input.readLine();
                if(echoString.equals("exit")) {
                    break;  // Breaks out of the infinite loop
                }
                output.println("Echo from server: " + echoString);
            }

            // Catch block to get the exceptions

        } catch(IOException e) {
            System.out.println("Server exception " + e.getMessage());

        }
    }
}

package com.benjiman.main;

import java.net.ServerSocket;

public class Main {



    public static void main(String[] args) {

        // try block for the serverSocket

        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket socket = serverSocket.accept();
            System.out.println("Client");

        } catch(I0Exception e) {
            e.printStackTrace();
        }
    }
}

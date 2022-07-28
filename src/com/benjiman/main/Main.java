package com.benjiman.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static <I0Exception extends Throwable> void main(String[] args) {

        // try block for the serverSocket

        try(ServerSocket serverSocket = new ServerSocket(6000)) {


            // Create an Infinite loop for reading the data from the client

            // While loop to link to the Echoer class

            while(true) {
                new Echoer(serverSocket.accept()).start();
            }
            // Catch block to get the exceptions

        } catch(IOException e) {
            System.out.println("Server exception " + e.getMessage());

        }
    }
}

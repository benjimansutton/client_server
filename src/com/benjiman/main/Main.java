package com.benjiman.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static <I0Exception extends Throwable, Database> void main(String[] args) {

        //Initialising Inventory Database on startup
            DatabaseConnection dbc = new DatabaseConnection();
            dbc.insert(1, 0, 0, 0.0);
            dbc.insert(2, 0, 0, 0.0);
            dbc.insert(3, 0, 0, 0.0);
            dbc.insert(4, 0, 0, 0.0);

            // Initializing Location Coords database on startup
            DatabaseLocationCoords dblc = new DatabaseLocationCoords();
            dblc.insert(1, 0, 0);
            dblc.insert(2, 0, 0);
            dblc.insert(3, 0, 0);
            dblc.insert(4, 0, 0);
        // try block for the serverSocket

        try(ServerSocket serverSocket = new ServerSocket(6000)) {

            // While loop to link to the Echoer class

            while(true) {
                new Echoer(serverSocket.accept()).start();
                System.out.println("New client has connected. " + "Press '1' to ready the troops ");
            }
            // Catch block to get the exceptions

        } catch(IOException e) {
            System.out.println("Server exception " + e.getMessage());

        }
    }
}

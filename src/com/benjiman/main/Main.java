package com.benjiman.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static <I0Exception extends Throwable, Database> void main(String[] args) {

        //Initialising Inventory Database on startup
        DatabaseConnection db = new DatabaseConnection();
        db.insert(1, 0, 0, 0.0,);
        db.insert(2, 0, 0, 0.0,);
        db.insert(3, 0, 0, 0.0,);
        db.insert(4, 0, 0, 0.0,);

        // Initializing Location Coords database on startup
        DatabaseLocationCoords dblc = new DatabaseLocationCoords();
        dbea.insert(1,0,0);
        dbea.insert(2,0,0);
        dbea.insert(3,0,0);
        dbea.insert(4,0,0);

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

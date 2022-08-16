package com.benjiman.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {


        int miles = 1;
        // Try block to capture input from the user
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);


            // While if loop to break out of the loop with the exit input
            int echoString;
            while(true) {
                // This changes the input from string to int left the original below
                echoString = Integer.parseInt(input.readLine());
//                String echoString = input.readLine();
                DataBeDeleted app = new DataBeDeleted();
                System.out.println("Received client input: " + echoString);

//                echoString = Integer.parseInt(input.readLine());
//                System.out.println("Received client input: " + echoString);

//                if(echoString.equals("exit")) {
//                    break;
//                }


                if(echoString == 1) {
                    System.out.println("You've started the sim and have been issued your initial kit");

                    DatabaseConnection dbc = new DatabaseConnection();

                    DatabaseConnection.ammoUpdate();
                    DatabaseConnection.foodUpdate();
                    DatabaseConnection.waterUpdate();

                    DatabaseConnection.showStatus();

                    System.out.println("Your Objective is to travel 10 Mile in the least moves possible.. Good Luck!");


                }
                if(echoString == 2) {
                    miles++;
//                    DatabaseConnection.showNumber();
                    DatabaseConnection.showAmmo();
                    DatabaseConnection.showFood();
                    DatabaseConnection.showWater();
                }
                if(echoString == 3) {
                    miles++;
                    System.out.println("You have moved 1 mile.");
                    DatabaseConnection.foodRemove();
                    DatabaseConnection.waterRemove();
                    DatabaseConnection.ammoRemove();
//                    DatabaseLocationCoords.showLocation();
                    System.out.println("You are at Location Point " + miles);



                }
                if(echoString == 4) {

                    DatabaseConnection.foodUpdate();
                    DatabaseConnection.waterUpdate();
                    DatabaseConnection.ammoUpdate();
                    System.out.println("HQ has Replenished your Troops Supply");

                    DatabaseConnection.showStatus();


                }

                if (echoString == 0) {
                    app.deleteIN(1);
//                    app.delete(2);
//                    app.delete(3);
//                    app.delete(4);
                    app.deleteLC(1);
//                    app.deleteEA(2);
//                    app.deleteEA(3);
//                    app.deleteEA(4);
                    System.exit(0);

                    break;
                }
                try {

                }
                catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
                try {
                    Thread.sleep(15000);

                } catch(InterruptedException e) {
                    System.out.println("Thread Interrupted");
                }
                output.println(echoString);
            }
        } catch(IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch(IOException e) {

            }
        }
    }
}

package com.benjiman.main;

import java.sql.*;

public class DatabaseConnection {

    // Constants for the Database to limit the use of hard coding for future upgrades
    public static final String TABLE_INVENTORY = "inventory";

    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_WATER = "water";
    public static final String COLUMN_AMMO = "ammo";
    public static final String COLUMN_FOOD = "food";

    // Constant for location of the Database
    public static final String PATH_TO_DATABASE = "jdbc:sqlite:/Users/benjimansutton/Documents/Troop Tracker/EchoServer/TroopTrackerMain.db";


    //
    private Connection connect() {
        // SQLite connection string
        // Database location on my Computer
        String url = PATH_TO_DATABASE;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Method to set the table headers & setting int & doubles on the table
    public void insert(int number, int ammo, int food, double water) {
        String sql = "INSERT INTO troop(id, ammo, rations, water, location) VALUES(?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, number);
            pstmt.setInt(2, ammo);
            pstmt.setInt(3, food);
            pstmt.setDouble(4, water);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Method to show the levels of current troops
    public static void showStatus(){
        try {
            // Will print the whole Database to show the columns
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("SELECT * FROM troop");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(results.getInt("number") + " " +
                        results.getInt("ammo") + " " +
                        results.getDouble("food") + " " +
                        results.getDouble("water"));
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    // Will show the Number of the Trooper
    public static void showNumber() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("SELECT number FROM inventory");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(results.getInt("number") + " ");
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    // Will show the Number for the Trooper & the Ammo Level
    public static void showAmmo() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("SELECT number, ammo FROM inventory");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(" AMMO COUNT FOR TROOP NUMBER " +
                        results.getInt("number") + " IS " +
                        results.getInt("ammo") + " ROUNDS ");
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    // Will show the Number of the Trooper & Food Level
    public static void showFood(){
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("SELECT number, food FROM inventory");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(" FOOD FOR TROOP NUMBER " +
                        results.getInt("number") + " WILL LAST " +
                        results.getInt("food") + " DAYS ");
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }



}

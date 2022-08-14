package com.benjiman.main;

import java.sql.*;
import org.sqlite.JDBC;

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
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(PATH_TO_DATABASE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Method to set the table headers & setting int & doubles on the table
    public void insert(int number, int ammo, int food, double water) {
        String sql = "INSERT INTO inventory(number, ammo, food, water) VALUES(?,?,?,?)";
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
            statement.execute("SELECT * FROM inventory");
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


    // Will show the Number of the Trooper & Water Level
    public static void showWater(){
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("SELECT number, water FROM inventory");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(" WATER COUNT FOR TROOP NUMBER " +
                        results.getInt("number") + " WILL LAST " +
                        results.getInt("water") + " DAYS ");
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    // Update the Ammo Level of the Trooper set to base of 360
    public static void ammoUpdate() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_INVENTORY + " SET " + COLUMN_AMMO + "=360 + ammo");
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    // Remove Ammo per 30 rounds - Each trooper must fire a minimum of 30 Rounds per Firing Exercise
    public static void ammoRemove() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE" + TABLE_INVENTORY + " SET " + COLUMN_AMMO + " =ammo - 30");

            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            int SQLErrorCode = e.getErrorCode();
            if (SQLErrorCode == 180) {
                System.out.println("You need ammo");
            }
        }
    }


    // Update the Food Level for the Trooper, set to base of 3 days
    public static void foodUpdate() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_INVENTORY + " SET " + COLUMN_FOOD + "=3 + food");

            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            System.out.println("Something went wrong: " + e.getErrorCode());
        }
    }


    // Remove Food Level by 1 day at a time
    public static void foodRemove() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_INVENTORY + " SET " + COLUMN_FOOD + " =food - 1");

            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            int SQLErrorCode = e.getErrorCode();
            if (SQLErrorCode == 3) {
                System.out.println("You need food");
            }
        }
    }

    // Update the water Level for the Trooper, set to base of 10L
    public static void waterUpdate() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_INVENTORY + " SET " + COLUMN_WATER + "=10 + water");
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            System.out.println("Something went wrong: " + e.getErrorCode());
        }
    }


    // Remove Water Level by 2 Liter at a time
    public static void waterRemove() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_INVENTORY + " SET " + COLUMN_WATER + " =water - 2");

            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            int SQLErrorCode = e.getErrorCode();
            if (SQLErrorCode == 6) {
                System.out.println("You need water");
            }
        }
    }


}

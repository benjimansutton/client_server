package com.benjiman.main;

import java.sql.*;

public class DatabaseLocationCoords {

    // Constants for the table & the columns
    public static final String TABLE_LOCATION_COORDS = "location_coords";

    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_CURRENT_LOCATION = "current_location";

    public static final String COLUMN_PREVIOUS_LOCATION = "current_location";
    public static final String PATH_TO_DATABASE = "jdbc:sqlite:/Users/benjimansutton/Documents/Troop Tracker/EchoServer/TroopTrackerMain.db";

    // Path to the database on my system
    private Connection connect() {
        String url = PATH_TO_DATABASE;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(int number, int current_location, int previous_location) {
        String sql = "INSERT INTO location_coords(number, current_location, previous_location) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, number);
            pstmt.setInt(2, current_location);
            pstmt.setInt(3, previous_location);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Method to update the current location of the trooper
    public static void currentLocationUpdate() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_LOCATION_COORDS + " SET " + COLUMN_CURRENT_LOCATION + "=100 + exercise_location");

            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            int SQLErrorCode = e.getErrorCode();
            if(SQLErrorCode == 19) {
                System.out.println("You've completed the game "  + "in " + Echoer.instanceCounter + " moves " + "Exiting...");
                System.exit(0);
            }
        }
    }

    public static void showCurrentLocation() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_LOCATION_COORDS + " SET " + COLUMN_CURRENT_LOCATION + "=100 + exercise_location");

            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            int SQLErrorCode = e.getErrorCode();
            if(SQLErrorCode == 19) {
                System.out.println("You've completed the game "  + "in " + Echoer.instanceCounter + " moves " + "Exiting...");
                System.exit(0);
            }
        }
    }

    // Method to update the current location of the trooper
    public static void previousLocationUpdate() {
        try {
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_LOCATION_COORDS + " SET " + COLUMN_PREVIOUS_LOCATION + "=100 + exercise_location");

            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            int SQLErrorCode = e.getErrorCode();
            if(SQLErrorCode == 19) {
                System.out.println("You've completed the game "  + "in " + Echoer.instanceCounter + " moves " + "Exiting...");
                System.exit(0);
            }
        }
    }
}

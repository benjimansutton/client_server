package com.benjiman.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBeDeleted {

    public static final String PATH_TO_DATABASE = "jdbc:sqlite:/Users/benjimansutton/Documents/Troop Tracker/EchoServer/TroopTrackerMain.db";

    private Connection connect() {
        // Connection to the database on my system
        String url = PATH_TO_DATABASE;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void deleteIN(int number) {
        String sql = "DELETE FROM inventory";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteLC(int number) {
        String sql = "DELETE FROM  location_coords";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

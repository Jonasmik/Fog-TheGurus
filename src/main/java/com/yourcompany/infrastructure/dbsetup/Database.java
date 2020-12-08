package com.yourcompany.infrastructure.dbsetup;

import java.sql.*;

public class Database {

    /**
     * The setup of your database, you will need to create a database using any platform of your choosing Then you can implement
     * it in the url Create a user with your name then implement that aswell.
     * <p>
     * If you want to use migrate you can read more about that in the README.md
     */

    private final String URL;
    private final String USER;

    // Database version
    private static final int version = -1;

    public Database(String url, String user) {
        this.URL = url == null ? "jdbc:mysql://localhost:3306/fogdb?serverTimezone=CET" : url;
        this.USER = user == null ? "fogdb" : user;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Database() {
        this(null, null);
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, null);
    }

    //MIGRATE
    public static int getVersion() {
        return version;
    }

    public int getCurrentVersion() {
        try (Connection conn = connect()) {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT value FROM properties WHERE name = 'version';");
            if (rs.next()) {
                String column = rs.getString("value");
                return Integer.parseInt(column);
            } else {
                System.err.println("No version in properties.");
                return -1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }
}

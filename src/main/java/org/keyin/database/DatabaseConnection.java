package org.keyin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // You only need to change the name of the database in the URL, unless PG runs
    // on another port on your system. Default port is 5432

    // private static final String URL =
    // "jdbc:postgresql://localhost:5432/s3javafinal";
    // By default the username is postgres and the password is what ever you set it
    // to be. I usually keep mine simple
    // private static final String USER = "postgres";
    // private static final String PASSWORD = "password";

    // Alex R's database connection
    private static final String URL = "jdbc:postgresql://localhost:5432/javadb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Fenthick";
    // private static final String URL = "jdbc:postgresql://localhost:5432/javadb";
    // private static final String USER = "postgres";
    // private static final String PASSWORD = "    ";

    // Abdul 's database connection
    // private static final String URL =
    // "jdbc:postgresql://localhost:5432/s3javafinal";
    // private static final String USER = "postgres";
    // private static final String PASSWORD = "Keyin2021";

    // Noah's database connection //
    // private static final String URL =
    // "jdbc:postgresql://localhost:5432/s3javafinal"; //
    // private static final String USER = "postgres"; //
    // private static final String PASSWORD = "Keyin_db_2025"; //

    public static Connection getConnection() throws SQLException, SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            DatabaseConnection.getConnection();
            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

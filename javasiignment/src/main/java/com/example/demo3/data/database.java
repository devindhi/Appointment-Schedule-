package com.example.demo3.data;

import java.sql.*;


public class database {

    // Method to establish a database connection
    public Connection dbConnection() {
        String url = "jdbc:mysql://localhost:3306/management"; // MySQL connection URL
        String username = "root"; // MySQL username
        String password = ""; // MySQL password
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL driver class
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found.");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
        return connection;
    }

    // Method to create a table
    public void createTable(Connection connection, String table) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + table + " (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Name varchar(200), " +
                    "Lastname varchar(200), " +
                    "Contact INT, " +
                    "Email varchar(200), " +
                    "Date DATE, " + // Removed (20) from DATE data type
                    "Time varchar(20), " + // Corrected the typo: Removed the extra '+' at the end
                    "Type varchar(20)" +
                    ")";

            statement.executeUpdate(query);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to insert a row into the table
    public void insertRow(Connection connection, String name, String lastname, String email, int contact, Date date, String time,String type) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "INSERT INTO meetings (Name, Lastname, Email, Contact, Date, Time, Type) " +
                    "VALUES ('" + name + "', '" + lastname + "', '" + email + "', " + contact + ", '" + date + "', '" + time + "', '" + type + "')";

            statement.executeUpdate(query);
            System.out.println("Data inserted into the table successfully.");
        } catch (SQLException e) {
            System.out.println("Data insertion error: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        // created a table
        database db = new database();
        Connection connection = db.dbConnection();
        db.createTable(connection, "meetings");
        Date sqlDate = Date.valueOf("2023-11-07");
        db.insertRow(connection, "John", "Doe", "john@example.com", 1234567890, sqlDate, "14:00","Meeting with dean");
    }
}

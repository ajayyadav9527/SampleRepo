package com.jdbc.mytask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/jdbcdb";
        String username = "root";
        String password = "Deadman@9527";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            String query = "SELECT * FROM authors";
            ResultSet resultSet = statement.executeQuery(query);

            // Get metadata to verify columns
            java.sql.ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.println("Columns in the result set:");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(rsmd.getColumnName(i));
            }

            // Process the result set
            while (resultSet.next()) {
                // Use actual column names from your database
                String authorId = resultSet.getString("author_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String birthdate = resultSet.getString("birthdate");
                System.out.println("Author ID: " + authorId);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Birthdate: " + birthdate);
            }

        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

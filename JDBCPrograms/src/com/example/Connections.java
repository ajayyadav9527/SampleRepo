package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {

    static String jdbcUrl = "jdbc:mysql://localhost:3306/jdbcdb";
    static String username = "root";
    static String password = "Deadman@9527";

 
    public  Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    
    

}

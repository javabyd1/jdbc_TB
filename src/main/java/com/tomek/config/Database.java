package com.tomek.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection = null;
    private final static String ADRESS   = "jdbc:mysql://localhost";
    private final static String DATABASE = "j1b?useSSL=false&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String USER     = "root";
    private final static String PASSWORD = "12345";
    private final static String PORT     = "3306";
    private final static String DRIVER   = "com.mysql.jdbc.Driver";

    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        }
        catch (Exception e) {
            errorHandler("Failed to load the driver " + DRIVER, e);
        }
    }

    private static void loadConnection() {
        try {
            connection = DriverManager.getConnection(getFormatedUrl(), USER, PASSWORD);
        }
        catch (SQLException e) {
            errorHandler("Failed to connect to the database " + getFormatedUrl(), e);
        }
    }

    private static void errorHandler(String message, Exception e) {
        System.out.println(message);
        if (e != null){
            System.out.println(e.getMessage());
        }
    }

    public static String getFormatedUrl() {
        return ADRESS + ":" + PORT + "/" + DATABASE;
    }

    public static Connection getConnection() {
        if (connection == null) {
            System.out.println("Tworze polaczenie");
            loadDriver();
            loadConnection();
        }
        System.out.println(connection);
        return connection;
    }

    public static void closeConnection() {
        if (connection == null) {
            errorHandler("No connection found", null);
        }
        else {
            try {
                connection.close();
                connection = null;
            }
            catch (SQLException e) {
                errorHandler("Failed to close the connection", e);
            }
        }
    }
}